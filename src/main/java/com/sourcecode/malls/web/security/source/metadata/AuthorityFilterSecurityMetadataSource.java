package com.sourcecode.malls.web.security.source.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sourcecode.malls.domain.system.Authority;
import com.sourcecode.malls.service.impl.AuthorityService;
import com.sourcecode.malls.web.security.config.attribute.AuthorityConfigAttribute;

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
		List<Authority> auths = authorityService.findAllByLink(link);
		if (!CollectionUtils.isEmpty(auths)) {
			for (Authority auth : auths) {
				attrs.add(new AuthorityConfigAttribute(auth));
			}
		}
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
