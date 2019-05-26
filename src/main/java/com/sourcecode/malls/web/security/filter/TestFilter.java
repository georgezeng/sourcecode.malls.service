package com.sourcecode.malls.web.security.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class TestFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getHeaderNames() != null) {
			for (Enumeration<String> e = req.getHeaderNames(); e.hasMoreElements();) {
				String header = e.nextElement();
				logger.info(header + ":" + req.getHeader(header));
			}
		}
		chain.doFilter(request, response);
	}

}