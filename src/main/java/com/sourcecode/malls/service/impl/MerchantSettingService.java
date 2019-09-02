package com.sourcecode.malls.service.impl;

import java.util.Optional;

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
		String value = mapper.writeValueAsString(info);
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		Optional<MerchantSetting> dataOp = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.SITE_INFO);
		MerchantSetting data = null;
		if (dataOp.isPresent()) {
			data = dataOp.get();
		} else {
			data = new MerchantSetting();
			data.setMerchant(merchant.get());
			data.setCode(MerchantSettingConstant.SITE_INFO);
		}
		data.setValue(value);
		settingRepository.save(data);
		cacheEvictService.clearSiteInfo(merchantId);
	}

	public SiteInfo loadSiteInfo(Long merchantId) throws Exception {
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
		Optional<MerchantSetting> dataOp = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.SITE_INFO);
		SiteInfo info = null;
		if (dataOp.isPresent()) {
			info = mapper.readValue(dataOp.get().getValue(), SiteInfo.class);
			if (StringUtils.isEmpty(info.getTitle())) {
				Optional<MerchantShopApplication> shop = applicationRepository.findByMerchantId(merchantId);
				AssertUtil.assertTrue(shop.isPresent(), "尚未创建商铺");
				info.setTitle(shop.get().getName());
				dataOp.get().setValue(mapper.writeValueAsString(info));
				settingRepository.save(dataOp.get());
			}
		} else {
			info = new SiteInfo();
			Optional<MerchantShopApplication> shop = applicationRepository.findByMerchantId(merchantId);
			AssertUtil.assertTrue(shop.isPresent(), "尚未创建商铺");
			info.setTitle(shop.get().getName());
		}
		return info;
	}
}
