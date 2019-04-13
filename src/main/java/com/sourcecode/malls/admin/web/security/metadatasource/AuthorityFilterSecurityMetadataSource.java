package com.sourcecode.malls.admin.web.security.metadatasource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.sourcecode.malls.admin.domain.system.setting.Authority;
import com.sourcecode.malls.admin.service.impl.AuthorityService;
import com.sourcecode.malls.admin.web.security.config.attribute.AuthorityConfigAttribute;

@Component
public class AuthorityFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private AuthorityService authorityService;

	private FilterInvocationSecurityMetadataSource originSource;

	public void setOriginSource(FilterInvocationSecurityMetadataSource originSource) {
		this.originSource = originSource;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		List<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>(originSource.getAttributes(object));
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		String link = request.getRequestURI().split("\\/params\\/")[0];
		Optional<Authority> authOp = authorityService.findByLink(link);
		attrs.add(new AuthorityConfigAttribute(authOp.orElse(null)));
		return attrs;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return originSource.getAllConfigAttributes();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
