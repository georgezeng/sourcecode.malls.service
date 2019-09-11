package com.sourcecode.malls.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.sourcecode.malls.config.AlipayConfig;
import com.sourcecode.malls.constants.EnvConstant;
import com.sourcecode.malls.dto.setting.DeveloperSettingDTO;
import com.sourcecode.malls.util.AssertUtil;

@Component
public class AlipayService {
	@Autowired
	private MerchantSettingService service;

	@Autowired
	private AlipayConfig config;

	@Autowired
	private Environment env;

	public void refund(Long merchantId, String transactionId, String refundNum, BigDecimal totalAmount, BigDecimal refundAmount, int subOrderNums)
			throws Exception {
		Optional<DeveloperSettingDTO> setting = service.loadAlipay(merchantId);
		AssertUtil.assertTrue(setting.isPresent(), "未找到商户的支付宝信息");
		AlipayClient alipayClient = new DefaultAlipayClient(config.getGateway(), setting.get().getAccount(), setting.get().getSecret(),
				config.getDataType(), config.getCharset(), setting.get().getMch(), config.getEncryptType());
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setTradeNo(transactionId);
		String totalFee = totalAmount + "";
		String refundFee = refundAmount + "";
		if (!env.acceptsProfiles(Profiles.of(EnvConstant.PROD))) {
			if (totalFee.equals(refundFee)) {
				totalFee = new BigDecimal(subOrderNums).multiply(new BigDecimal("0.01")).toString();
				refundFee = totalFee;
			} else {
				totalFee = "0.01";
				refundFee = "0.01";
			}
		}
		model.setRefundAmount(refundFee);
		model.setRefundReason("用户请求退款");
		model.setOutRequestNo(refundNum);
		request.setBizModel(model);

		AlipayTradeRefundResponse response = alipayClient.execute(request);
		AssertUtil.assertTrue(response.isSuccess(), "退款失败: " + response.getMsg());

	}
}
