package com.sourcecode.malls.service.impl;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcecode.malls.constants.MerchantSettingConstant;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.domain.merchant.MerchantSetting;
import com.sourcecode.malls.domain.merchant.MerchantShopApplication;
import com.sourcecode.malls.dto.client.ClientPointsBonus;
import com.sourcecode.malls.dto.merchant.SiteInfo;
import com.sourcecode.malls.dto.setting.DeveloperSettingDTO;
import com.sourcecode.malls.repository.jpa.impl.merchant.MerchantRepository;
import com.sourcecode.malls.repository.jpa.impl.merchant.MerchantSettingRepository;
import com.sourcecode.malls.repository.jpa.impl.merchant.MerchantShopApplicationRepository;
import com.sourcecode.malls.service.FileOnlineSystemService;
import com.sourcecode.malls.util.AssertUtil;
import com.sourcecode.malls.util.Base64Util;

@Service
@Transactional
public class MerchantSettingService {
	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private MerchantSettingRepository settingRepository;

	@Autowired
	private FileOnlineSystemService fileService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private CacheEvictService cacheEvictService;

	@Autowired
	private MerchantShopApplicationRepository applicationRepository;

	private String certDir = "merchant/setting/wechat/pay/cert/%s/cert.p12";

	public void saveWechatGzh(DeveloperSettingDTO setting, Long merchantId) {
		AssertUtil.assertNotEmpty(setting.getAccount(), "账号不能为空");
		AssertUtil.assertNotEmpty(setting.getSecret(), "密钥不能为空");
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		AssertUtil.assertTrue(merchant.isPresent(), "找不到商户信息");
		MerchantSetting account = settingRepository
				.findByMerchantAndCode(merchant.get(), MerchantSettingConstant.WECHAT_GZH_ACCOUNT)
				.orElseGet(MerchantSetting::new);
		account.setCode(MerchantSettingConstant.WECHAT_GZH_ACCOUNT);
		account.setMerchant(merchant.get());
		account.setValue(Base64Util.encode(setting.getAccount()));
		settingRepository.save(account);
		MerchantSetting secret = settingRepository
				.findByMerchantAndCode(merchant.get(), MerchantSettingConstant.WECHAT_GZH_SECRET)
				.orElseGet(MerchantSetting::new);
		secret.setCode(MerchantSettingConstant.WECHAT_GZH_SECRET);
		secret.setMerchant(merchant.get());
		secret.setValue(Base64Util.encode(setting.getSecret()));
		settingRepository.save(secret);
		MerchantSetting mch = settingRepository
				.findByMerchantAndCode(merchant.get(), MerchantSettingConstant.WECHAT_PAY_MCH)
				.orElseGet(MerchantSetting::new);
		mch.setCode(MerchantSettingConstant.WECHAT_PAY_MCH);
		mch.setMerchant(merchant.get());
		mch.setValue(Base64Util.encode(setting.getMch()));
		settingRepository.save(mch);
	}

	public Optional<DeveloperSettingDTO> loadWechatGzh(Long merchantId) {
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		AssertUtil.assertTrue(merchant.isPresent(), "找不到商户信息");
		Optional<MerchantSetting> account = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.WECHAT_GZH_ACCOUNT);
		Optional<MerchantSetting> secret = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.WECHAT_GZH_SECRET);
		Optional<MerchantSetting> mch = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.WECHAT_PAY_MCH);
		DeveloperSettingDTO setting = new DeveloperSettingDTO();
		if (account.isPresent()) {
			setting.setAccount(Base64Util.decode(account.get().getValue()));
		}
		if (secret.isPresent()) {
			setting.setSecret(Base64Util.decode(secret.get().getValue()));
		}
		if (mch.isPresent()) {
			setting.setMch(Base64Util.decode(mch.get().getValue()));
		}
		return Optional.of(setting);
	}

	public void uploadWepayCert(MultipartFile file, Long merchantId) throws Exception {
		fileService.upload(false, String.format(certDir, merchantId.toString()), file.getInputStream());
	}

	public byte[] loadWepayCert(Long merchantId) throws Exception {
		return fileService.load(false, String.format(certDir, merchantId.toString()));
	}

	public void saveAlipay(DeveloperSettingDTO setting, Long merchantId) {
		AssertUtil.assertNotEmpty(setting.getAccount(), "账号不能为空");
		AssertUtil.assertNotEmpty(setting.getSecret(), "密钥不能为空");
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		AssertUtil.assertTrue(merchant.isPresent(), "找不到商户信息");
		MerchantSetting account = settingRepository
				.findByMerchantAndCode(merchant.get(), MerchantSettingConstant.ALIPAY_ACCOUNT)
				.orElseGet(MerchantSetting::new);
		account.setCode(MerchantSettingConstant.ALIPAY_ACCOUNT);
		account.setMerchant(merchant.get());
		account.setValue(Base64Util.encode(setting.getAccount()));
		settingRepository.save(account);
		MerchantSetting secret = settingRepository
				.findByMerchantAndCode(merchant.get(), MerchantSettingConstant.ALIPAY_SECRET)
				.orElseGet(MerchantSetting::new);
		secret.setCode(MerchantSettingConstant.ALIPAY_SECRET);
		secret.setMerchant(merchant.get());
		secret.setValue(Base64Util.encode(setting.getSecret()));
		settingRepository.save(secret);
		MerchantSetting pubKey = settingRepository
				.findByMerchantAndCode(merchant.get(), MerchantSettingConstant.ALIPAY_PUBLIC_KEY)
				.orElseGet(MerchantSetting::new);
		pubKey.setCode(MerchantSettingConstant.ALIPAY_PUBLIC_KEY);
		pubKey.setMerchant(merchant.get());
		pubKey.setValue(Base64Util.encode(setting.getMch()));
		settingRepository.save(pubKey);
	}

	public Optional<DeveloperSettingDTO> loadAlipay(Long merchantId) {
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		Optional<MerchantSetting> account = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.ALIPAY_ACCOUNT);
		Optional<MerchantSetting> secret = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.ALIPAY_SECRET);
		Optional<MerchantSetting> mch = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.ALIPAY_PUBLIC_KEY);
		DeveloperSettingDTO setting = new DeveloperSettingDTO();
		if (account.isPresent()) {
			setting.setAccount(Base64Util.decode(account.get().getValue()));
		}
		if (secret.isPresent()) {
			setting.setSecret(Base64Util.decode(secret.get().getValue()));
		}
		if (mch.isPresent()) {
			setting.setMch(Base64Util.decode(mch.get().getValue()));
		}
		return Optional.of(setting);
	}

	public void saveSiteInfo(Long merchantId, SiteInfo info) throws Exception {
		AssertUtil.assertNotEmpty(info.getTitle(), "页面标题不能为空");
		AssertUtil.assertNotEmpty(info.getHeaderLogo(), "首页顶部logo不能为空");
		AssertUtil.assertNotEmpty(info.getLoginLogo(), "登录页logo不能为空");
		save(merchantId, info, MerchantSettingConstant.SITE_INFO);
		cacheEvictService.clearSiteInfo(merchantId);
	}

	private <T> T loadSetting(Long merchantId, String code, Class<T> clazz, Function<T, Boolean> f) throws Exception {
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		Optional<MerchantSetting> dataOp = settingRepository.findByMerchantAndCode(merchant.get(), code);
		T obj = null;
		boolean changed = false;
		if (dataOp.isPresent()) {
			obj = mapper.readValue(dataOp.get().getValue(), clazz);
		} else {
			obj = clazz.newInstance();
		}
		changed = f.apply(obj);
		if (changed) {
			dataOp.get().setValue(mapper.writeValueAsString(obj));
			settingRepository.save(dataOp.get());
		}
		return obj;
	}

	public SiteInfo loadSiteInfo(Long merchantId) throws Exception {
		return loadSetting(merchantId, MerchantSettingConstant.SITE_INFO, SiteInfo.class, info -> {
			if (StringUtils.isEmpty(info.getTitle())) {
				Optional<MerchantShopApplication> shop = applicationRepository.findByMerchantId(merchantId);
				AssertUtil.assertTrue(shop.isPresent(), "尚未创建商铺");
				info.setTitle(shop.get().getName());
				return true;
			}
			return false;
		});
	}

	public void saveClientPointsBonus(Long merchantId, ClientPointsBonus info) throws Exception {
		AssertUtil.assertNotNull(info.getRookie(), "新人奖励不能为空");
		AssertUtil.assertNotNull(info.getInvite(), "邀请奖励不能为空");
		save(merchantId, info, MerchantSettingConstant.CLIENT_POINTS_BONUS_INFO);
		cacheEvictService.clearClientPointsBonus(merchantId);
	}

	private void save(Long merchantId, Object data, String code) throws Exception {
		String value = mapper.writeValueAsString(data);
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		AssertUtil.assertTrue(merchant.isPresent(), "商家不存在");
		Optional<MerchantSetting> dataOp = settingRepository.findByMerchantAndCode(merchant.get(), code);
		MerchantSetting setting = null;
		if (dataOp.isPresent()) {
			setting = dataOp.get();
		} else {
			setting = new MerchantSetting();
			setting.setMerchant(merchant.get());
			setting.setCode(code);
		}
		setting.setValue(value);
		settingRepository.save(setting);
	}

	public ClientPointsBonus loadClientPointsBonus(Long merchantId) throws Exception {
		return loadSetting(merchantId, MerchantSettingConstant.CLIENT_POINTS_BONUS_INFO, ClientPointsBonus.class,
				info -> {
					return false;
				});
	}
}
