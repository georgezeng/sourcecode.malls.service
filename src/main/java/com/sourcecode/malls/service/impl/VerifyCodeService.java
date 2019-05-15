package com.sourcecode.malls.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourcecode.malls.domain.redis.CodeStore;
import com.sourcecode.malls.repository.redis.impl.CodeStoreRepository;
import com.sourcecode.malls.util.AssertUtil;
import com.sourcecode.malls.util.CodeUtil;

@Service
public class VerifyCodeService {

	@Autowired
	private SmsService smsService;

	@Autowired
	private CodeStoreRepository codeStoreRepository;

	public void sendLoginCode(String mobile, HttpSession session, String attr, String category, String keySuffix) {
		sendCode(mobile, session, attr, category, "SMS_162450481", keySuffix);
	}

	public void sendRegisterCode(String mobile, HttpSession session, String attr, String category, String keySuffix) {
		sendCode(mobile, session, attr, category, "SMS_162450479", keySuffix);
	}

	public void sendForgetPasswordCode(String mobile, HttpSession session, String attr, String category, String keySuffix) {
		sendCode(mobile, session, attr, category, "SMS_162450478", keySuffix);
	}

	private void sendCode(String mobile, HttpSession session, String attr, String category, String codeId, String keySuffix) {
		Date sendTime = (Date) session.getAttribute(attr);
		if (sendTime != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(sendTime);
			c.add(Calendar.SECOND, 30);
			AssertUtil.assertTrue(new Date().after(c.getTime()), "操作太频繁，请稍后重试");
		}
		String key = mobile;
		if (keySuffix != null) {
			key += "_" + keySuffix;
		}
		Optional<CodeStore> codeStoreOp = codeStoreRepository.findByCategoryAndKey(category, key);
		CodeStore codeStore = codeStoreOp.orElse(new CodeStore(category, key, CodeUtil.generateRandomNumbers(6)));
		if (codeStore.getId() != null) {
			codeStore.setValue(CodeUtil.generateRandomNumbers(6));
		}
		codeStoreRepository.save(codeStore);
		Map<String, Object> payload = new HashMap<>();
		payload.put("code", codeStore.getValue());
		smsService.send(codeId, mobile, payload);
		session.setAttribute(attr, new Date());
	}
}
