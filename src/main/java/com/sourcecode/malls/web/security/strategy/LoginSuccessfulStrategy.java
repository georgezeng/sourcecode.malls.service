package com.sourcecode.malls.web.security.strategy;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcecode.malls.dto.LoginSuccessDTO;
import com.sourcecode.malls.dto.base.ResultBean;
import com.sourcecode.malls.properties.SessionAttributesProperties;

@Component
public class LoginSuccessfulStrategy implements RedirectStrategy {
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private SessionAttributesProperties sessionProperties;

	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		LoginSuccessDTO dto = new LoginSuccessDTO();
		dto.setUserId((Long) request.getSession().getAttribute(sessionProperties.getUserId()));
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.getWriter().write(mapper.writeValueAsString(new ResultBean<>(dto)));
		response.getWriter().flush();
		response.getWriter().close();
	}

}
