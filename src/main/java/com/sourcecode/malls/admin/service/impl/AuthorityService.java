package com.sourcecode.malls.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.sourcecode.malls.admin.domain.system.setting.Authority;
import com.sourcecode.malls.admin.domain.system.setting.Role;
import com.sourcecode.malls.admin.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.admin.dto.query.QueryInfo;
import com.sourcecode.malls.admin.properties.SuperAdminProperties;
import com.sourcecode.malls.admin.repository.jpa.impl.AuthorityRepository;
import com.sourcecode.malls.admin.service.base.JpaService;

@Service
@Transactional
public class AuthorityService implements JpaService<Authority, Long> {
	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private SuperAdminProperties superAdminProperties;

	public void prepareSuperAdmin(Role role) {
		Optional<Authority> authorityOp = authorityRepository.findByCode(superAdminProperties.getAuthority());
		if (!authorityOp.isPresent()) {
			Authority auth = new Authority();
			auth.setCode(superAdminProperties.getAuthority());
			auth.setName("超级管理员权限");
			auth.setLink("/**");
			auth.addRole(role);
			authorityRepository.save(auth);
		}
	}

	@Transactional(readOnly = true)
	public Page<Authority> findAll(QueryInfo<SimpleQueryDTO> queryInfo) {
		SimpleQueryDTO data = queryInfo.getData();
		Page<Authority> pageReulst = null;
		Specification<Authority> spec = new Specification<Authority>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Authority> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				if (data != null) {
					if (!StringUtils.isEmpty(data.getSearchText())) {
						String like = "%" + data.getSearchText() + "%";
						predicate.add(criteriaBuilder.or(criteriaBuilder.like(root.get("code").as(String.class), like),
								criteriaBuilder.like(root.get("name").as(String.class), like)));
					}
				}
				return query.where(predicate.toArray(new Predicate[] {})).getRestriction();
			}
		};
		pageReulst = authorityRepository.findAll(spec, queryInfo.getPage().pageable());
		return pageReulst;
	}

	@Transactional(readOnly = true)
	public boolean isSuperAdmin(Authority authority) {
		return authority.getCode().equals(superAdminProperties.getAuthority());
	}

	@Transactional(readOnly = true)
	public List<Authority> findAllByLink(String link) {
		return authorityRepository.findAllByLink(link);
	}

	@Override
	public JpaRepository<Authority, Long> getRepository() {
		return authorityRepository;
	}
}
