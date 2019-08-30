package com.sourcecode.malls.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.client.ClientPoints;
import com.sourcecode.malls.domain.client.ClientPointsJournal;
import com.sourcecode.malls.domain.coupon.ClientCoupon;
import com.sourcecode.malls.domain.coupon.CouponSetting;
import com.sourcecode.malls.domain.goods.GoodsCategory;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.order.Order;
import com.sourcecode.malls.domain.order.SubOrder;
import com.sourcecode.malls.enums.BalanceType;
import com.sourcecode.malls.enums.ClientCouponStatus;
import com.sourcecode.malls.enums.ClientPointsType;
import com.sourcecode.malls.enums.CouponEventType;
import com.sourcecode.malls.enums.CouponSettingStatus;
import com.sourcecode.malls.repository.jpa.impl.client.ClientRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientCouponRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientPointsJournalRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.ClientPointsRepository;
import com.sourcecode.malls.repository.jpa.impl.coupon.CouponSettingRepository;
import com.sourcecode.malls.service.base.BaseService;
import com.sourcecode.malls.util.AssertUtil;

@Service
@Transactional
public class ClientBonusService implements BaseService {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CouponSettingRepository couponSettingRepository;

	@Autowired
	private ClientCouponRepository clientCouponRepository;

	@Value("${client.points.ratio}")
	private String pointsRatio;

	@Autowired
	private EntityManager em;

	@Autowired
	private CacheEvictService cacheEvictService;

	@Autowired
	private ClientPointsRepository pointsRepository;

	@Autowired
	private ClientPointsJournalRepository pointsJournalRepository;

	public void addConsumeBonus(Order order) {
		if (CollectionUtils.isEmpty(order.getSubList())) {
			return;
		}
		List<CouponSetting> list = couponSettingRepository.findAllByMerchantAndEventTypeAndStatusAndEnabled(
				order.getMerchant(), CouponEventType.Consume, CouponSettingStatus.PutAway, true);
		if (!CollectionUtils.isEmpty(list)) {
			for (CouponSetting setting : list) {
				if (setting.getConsumeSetting() != null) {
					BigDecimal upToAmount = BigDecimal.ZERO;
					for (SubOrder sub : order.getSubList()) {
						boolean match = false;
						switch (setting.getConsumeSetting().getType()) {
						case All:
							match = true;
							break;
						case Category: {
							if (!CollectionUtils.isEmpty(setting.getConsumeSetting().getRealCategories())) {
								for (GoodsCategory category : setting.getConsumeSetting().getRealCategories()) {
									if (sub.getItem().getCategory().getId().equals(category.getId())) {
										match = true;
										break;
									}
								}
							}
						}
							break;
						case Item: {
							if (!CollectionUtils.isEmpty(setting.getConsumeSetting().getItems())) {
								for (GoodsItem item : setting.getConsumeSetting().getItems()) {
									if (sub.getItem().getId().equals(item.getId())) {
										match = true;
										break;
									}
								}
							}
						}
							break;
						}
						if (match) {
							upToAmount = upToAmount.add(sub.getDealPrice());
						}
					}
					if (upToAmount.compareTo(setting.getConsumeSetting().getUpToAmount()) >= 0) {
						createCoupon(order, null, order.getClient(), setting, true);
					}
				}
			}
		}
		BigDecimal pointsAmount = order.getRealPrice().multiply(new BigDecimal(pointsRatio));
		ClientPoints points = order.getClient().getPoints();
		em.lock(points, LockModeType.PESSIMISTIC_WRITE);
		points.setCurrentAmount(points.getCurrentAmount().add(pointsAmount));
		points.setAccInAmount(points.getAccInAmount().add(pointsAmount));
		pointsRepository.save(points);
		ClientPointsJournal journal = new ClientPointsJournal();
		journal.setClient(order.getClient());
		journal.setAmount(order.getRealPrice());
		journal.setBonusAmount(pointsAmount);
		journal.setOrderId(order.getOrderId());
		journal.setBalanceType(BalanceType.In);
		journal.setType(ClientPointsType.ConsumeAdded);
		pointsJournalRepository.save(journal);
		cacheEvictService.clearClientCurrentPoints(order.getClient().getId());
	}

	private void createCoupon(Order order, Client invitee, Client client, CouponSetting setting, boolean require) {
		if (!require) {
			List<ClientCoupon> list = clientCouponRepository.findAllByClientAndSetting(client, setting);
			require = CollectionUtils.isEmpty(list);
		}
		if (require) {
			em.lock(setting, LockModeType.PESSIMISTIC_WRITE);
			if (setting.getTotalNums() == 0 || setting.getSentNums() < setting.getTotalNums()) {
				ClientCoupon coupon = new ClientCoupon();
				coupon.setClient(client);
				coupon.setMerchant(client.getMerchant());
				coupon.setSetting(setting);
				coupon.setCouponId(generateId());
				coupon.setReceivedTime(new Date());
				coupon.setStatus(ClientCouponStatus.UnUse);
				coupon.setFromOrder(order);
				coupon.setInvitee(invitee);
				clientCouponRepository.save(coupon);
				setting.setSentNums(setting.getSentNums() + 1);
				if (setting.getSentNums() == setting.getTotalNums()) {
					setting.setStatus(CouponSettingStatus.SentOut);
				}
				couponSettingRepository.save(setting);
				cacheEvictService.clearClientCoupons(client.getId());
			}
		}
	}

	public void addInviteBonus(Client invitee, Client parent) {
		List<CouponSetting> list = couponSettingRepository.findAllByMerchantAndEventTypeAndStatusAndEnabled(
				parent.getMerchant(), CouponEventType.Invite, CouponSettingStatus.PutAway, true);
		if (!CollectionUtils.isEmpty(list)) {
			for (CouponSetting setting : list) {
				if (setting.getInviteSetting() != null && !CollectionUtils.isEmpty(parent.getSubList())) {
					int times = parent.getSubList().size() / setting.getInviteSetting().getMemberNums();
					int nums = clientCouponRepository.findAllByClientAndSetting(parent, setting).size();
					while (nums < times) {
						createCoupon(null, invitee, parent, setting, true);
						nums++;
					}
				}
			}
		}
	}

	public void addRegistrationBonus(Long userId) {
		Optional<Client> user = clientRepository.findById(userId);
		AssertUtil.assertTrue(user.isPresent(), "用户不存在");
		List<CouponSetting> list = couponSettingRepository.findAllByMerchantAndEventTypeAndStatusAndEnabled(
				user.get().getMerchant(), CouponEventType.Registration, CouponSettingStatus.PutAway, true);
		if (!CollectionUtils.isEmpty(list)) {
			for (CouponSetting setting : list) {
				createCoupon(null, null, user.get(), setting, false);
			}
		}
	}

	public void removeConsumeBonus(Order order) {
		List<ClientCoupon> coupons = order.getGeneratedCoupons();
		if (!CollectionUtils.isEmpty(coupons)) {
			Date outTime = new Date();
			for (ClientCoupon coupon : coupons) {
				coupon.setStatus(ClientCouponStatus.Out);
				coupon.setOutTime(outTime);
			}
			clientCouponRepository.saveAll(coupons);
			cacheEvictService.clearClientCoupons(order.getClient().getId());
		}
		ClientPoints points = order.getClient().getPoints();
		em.lock(points, LockModeType.PESSIMISTIC_WRITE);
		BigDecimal pointsAmount = order.getRealPrice().multiply(new BigDecimal(pointsRatio));
		points.setCurrentAmount(points.getCurrentAmount().subtract(pointsAmount));
		points.setAccOutAmount(points.getAccOutAmount().add(pointsAmount));
		pointsRepository.save(points);
		ClientPointsJournal journal = new ClientPointsJournal();
		journal.setClient(order.getClient());
		journal.setBonusAmount(pointsAmount);
		journal.setAmount(order.getRealPrice());
		journal.setOrderId(order.getOrderId());
		journal.setBalanceType(BalanceType.Out);
		journal.setType(ClientPointsType.RefundDeduction);
		pointsJournalRepository.save(journal);
		cacheEvictService.clearClientCurrentPoints(order.getClient().getId());
	}

}