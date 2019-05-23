package com.sourcecode.malls.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcecode.malls.exception.BusinessException;
import com.sourcecode.malls.properties.AliyunProperties;
import com.sourcecode.malls.properties.SmsProperties;

@Service
public class SmsService {
	@Autowired
	private AliyunProperties aliyunConfig;
	@Autowired
	private SmsProperties smsConfig;

	private IAcsClient client;

	@PostConstruct
	public void init() {
		DefaultProfile profile = DefaultProfile.getProfile(aliyunConfig.getRegionId(), aliyunConfig.getAccesskey(), aliyunConfig.getSecret());
		client = new DefaultAcsClient(profile);
	}

	@SuppressWarnings({ "unchecked" })
	public void send(String template, String mobile, Object payload) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CommonRequest request = new CommonRequest();
			// request.setProtocol(ProtocolType.HTTPS);
			request.setMethod(MethodType.POST);
			request.setDomain("dysmsapi.aliyuncs.com");
			request.setVersion("2017-05-25");
			request.setAction("SendSms");
			request.putQueryParameter("SignName", smsConfig.getSignName());
			request.putQueryParameter("PhoneNumbers", mobile);
			request.putQueryParameter("TemplateCode", template);
			request.putQueryParameter("TemplateParam", mapper.writeValueAsString(payload));
			CommonResponse response = client.getCommonResponse(request);
			String json = response.getData();
			Map<String, String> result = mapper.readValue(json, Map.class);
			if (!result.get("Code").equals("OK")) {
				String msg = result.get("message");
				if (!StringUtils.isEmpty(msg)) {
					throw new BusinessException(msg);
				} else {
					throw new BusinessException("发送失败, code: " + result.get("Code"));
				}
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
