package com.sourcecode.malls.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WePayConfig;
import com.sourcecode.malls.constants.CacheNameConstant;
import com.sourcecode.malls.constants.EnvConstant;
import com.sourcecode.malls.dto.setting.DeveloperSettingDTO;
import com.sourcecode.malls.util.AssertUtil;

@Component
public class WechatService {
	@Autowired
	private MerchantSettingService service;

	@Autowired
	private Environment env;
	
	@Cacheable(cacheNames = CacheNameConstant.WEPAY_CONFIG, key = "#merchantId")
	public WePayConfig createWePayConfig(Long merchantId) throws Exception {
		Optional<DeveloperSettingDTO> info = service.loadWechatGzh(merchantId);
		AssertUtil.assertTrue(info.isPresent(), "未找到商户的微信信息");
		return new WePayConfig(info.get(), service.loadWepayCert(merchantId));
	}

	public void refund(WePayConfig config, String transactionId, String refundNum, BigDecimal totalAmount,
			BigDecimal refundAmount, int subOrderNums) throws Exception {
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("transaction_id", transactionId);
		data.put("out_refund_no", refundNum);
		String totalFee = totalAmount.multiply(new BigDecimal("100")).intValue() + "";
		String refundFee = refundAmount.multiply(new BigDecimal("100")).intValue() + "";
		if (!env.acceptsProfiles(Profiles.of(EnvConstant.PROD))) {
			if (totalFee.equals(refundFee)) {
				refundFee = totalFee;
			} else {
				refundFee = subOrderNums + "";
			}
		}
		data.put("total_fee", totalFee);
		data.put("refund_fee", refundFee);
		Map<String, String> resp = wxpay.refund(data);
		AssertUtil.assertTrue("SUCCESS".equals(resp.get("return_code")), "退款失败: " + resp.get("return_msg"));
		AssertUtil.assertTrue("SUCCESS".equals(resp.get("result_code")), "退款失败: " + resp.get("err_code_des"));
	}
}
