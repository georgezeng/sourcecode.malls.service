package com.sourcecode.malls.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sourcecode.malls.constants.MerchantSettingConstant;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.domain.merchant.MerchantSetting;
import com.sourcecode.malls.dto.setting.DeveloperSettingDTO;
import com.sourcecode.malls.repository.jpa.impl.merchant.MerchantRepository;
import com.sourcecode.malls.repository.jpa.impl.merchant.MerchantSettingRepository;
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

	private String certDir = "merchant/setting/wechat/pay/cert/%s/cert.p12";

	public void saveWechatGzh(DeveloperSettingDTO setting, Long merchantId) {
		AssertUtil.assertNotEmpty(setting.getAccount(), "账号不能为空");
		AssertUtil.assertNotEmpty(setting.getSecret(), "密钥不能为空");
		Optional<Merchant> merchant = merchantRepository.findById(merchantId);
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
		Optional<MerchantSetting> account = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.WECHAT_GZH_ACCOUNT);
		Optional<MerchantSetting> secret = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.WECHAT_GZH_SECRET);
		Optional<MerchantSetting> mch = settingRepository.findByMerchantAndCode(merchant.get(),
				MerchantSettingConstant.WECHAT_PAY_MCH);
		if (account.isPresent() && secret.isPresent() && mch.isPresent()) {
			DeveloperSettingDTO setting = new DeveloperSettingDTO();
			setting.setAccount(Base64Util.decode(account.get().getValue()));
			setting.setSecret(Base64Util.decode(secret.get().getValue()));
			setting.setMch(Base64Util.decode(mch.get().getValue()));
			return Optional.of(setting);
		}
		return Optional.empty();
	}

	public void uploadWepayCert(MultipartFile file, Long merchantId) throws Exception {
		fileService.upload(false, String.format(certDir, merchantId.toString()), file.getInputStream());
	}

	public byte[] loadWepayCert(Long merchantId) throws Exception {
		return fileService.load(false, String.format(certDir, merchantId.toString()));
	}
}
