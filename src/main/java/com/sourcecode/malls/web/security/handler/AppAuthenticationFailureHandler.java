package com.sourcecode.malls.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourcecode.malls.dto.base.ResultBean;
import com.sourcecode.malls.util.LogUtil;

@Component
public class AppAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired
	private ObjectMapper mapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		logger.error(exception.getMessage(), exception);
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.getWriter().write(mapper.writeValueAsString(new ResultBean<>(LogUtil.getTraceId(), exception.getMessage())));
		response.getWriter().flush();
		response.getWriter().close();
	}
}
