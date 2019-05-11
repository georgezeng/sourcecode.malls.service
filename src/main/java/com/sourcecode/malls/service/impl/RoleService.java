package com.sourcecode.malls.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.sourcecode.malls.domain.system.setting.Authority;
import com.sourcecode.malls.domain.system.setting.Role;
import com.sourcecode.malls.domain.system.setting.User;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.dto.query.QueryInfo;
import com.sourcecode.malls.dto.system.AuthorityDTO;
import com.sourcecode.malls.dto.system.RoleDTO;
import com.sourcecode.malls.dto.system.UserDTO;
import com.sourcecode.malls.properties.SuperAdminProperties;
import com.sourcecode.malls.repository.jpa.impl.system.RoleRepository;
import com.sourcecode.malls.repository.jpa.impl.system.UserRepository;
import com.sourcecode.malls.service.base.JpaService;
import com.sourcecode.malls.util.AssertUtil;

@Service
@Transactional
public class RoleService implements JpaService<Role, Long> {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private SuperAdminProperties superAdminProperties;

	public void prepareSuperAdmin(User superAdmin) {
		Optional<Role> roleOp = roleRepository.findByCode(superAdminProperties.getCode());
		Role role = null;
		if (!roleOp.isPresent()) {
			role = new Role();
			role.setCode(superAdminProperties.getCode());
			role.setName("超级管理员");
			role.addUser(superAdmin);
			roleRepository.save(role);
		} else {
			role = roleOp.get();
		}
		authorityService.prepareSuperAdmin(role);
	}

	@Transactional(readOnly = true)
	public Page<Role> findAll(QueryInfo<SimpleQueryDTO> queryInfo) {
		SimpleQueryDTO data = queryInfo.getData();
		Page<Role> pageReulst = null;
		Specification<Role> spec = new Specification<Role>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				predicate.add(criteriaBuilder.equal(root.get("hidden").as(boolean.class), false));
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
		pageReulst = roleRepository.findAll(spec, queryInfo.getPage().pageable());
		return pageReulst;
	}

	public void relateToUsersAndAuthorities(Role role, List<UserDTO> users, List<AuthorityDTO> authorities) {
		List<User> oldUsers = role.getUsers();
		if (!CollectionUtils.isEmpty(users)) {
			for (UserDTO user : users) {
				Optional<User> userOp = userRepository.findById(user.getId());
				if (userOp.isPresent()) {
					boolean found = false;
					if (oldUsers != null) {
						for (User oldUserInRole : oldUsers) {
							if (oldUserInRole.getId().equals(userOp.get().getId())) {
								found = true;
								break;
							}
						}
					}
					if (!found) {
						role.addUser(userOp.get());
						userOp.get().addRole(role);
					}
				}
			}
			for (Iterator<User> it = oldUsers.iterator(); it.hasNext();) {
				User oldUserInRole = it.next();
				boolean remove = true;
				for (UserDTO user : users) {
					if (user.getId().equals(oldUserInRole.getId())) {
						remove = false;
						break;
					}
				}
				if (remove) {
					it.remove();
				}
			}
		}
		List<Authority> oldAuthorities = role.getAuthorities();
		if (!CollectionUtils.isEmpty(authorities)) {
			for (AuthorityDTO authority : authorities) {
				Optional<Authority> authOp = authorityService.findById(authority.getId());
				if (authOp.isPresent()) {
					boolean found = false;
					if (oldAuthorities != null) {
						for (Authority oldAuthInRole : oldAuthorities) {
							if (oldAuthInRole.getId().equals(authOp.get().getId())) {
								found = true;
								break;
							}
						}
					}
					if (!found) {
						role.addAuthority(authOp.get());
						authOp.get().addRole(role);
					}
				}
			}
			for (Iterator<Authority> it = oldAuthorities.iterator(); it.hasNext();) {
				Authority oldAuthInRole = it.next();
				boolean remove = true;
				for (AuthorityDTO auth : authorities) {
					if (auth.getId().equals(oldAuthInRole.getId())) {
						remove = false;
						break;
					}
				}
				if (remove) {
					it.remove();
				}
			}
		}
		if (isSuperAdmin(role)) {
			AssertUtil.assertTrue(role.getUsers().size() == 1, "不能修改超级管理员");
			boolean isSpecificUser = role.getUsers().stream().anyMatch(user -> superAdminProperties.getUsername().equals(user.getUsername()));
			AssertUtil.assertTrue(isSpecificUser, "不能修改超级管理员的所属用户");
		}
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(role.getAuthorities()), "请关联至少一条权限记录");
		roleRepository.save(role);
	}

	public boolean isSuperAdmin(RoleDTO role) {
		return isSuperAdmin(findById(role.getId()).get());
	}

	public boolean isSuperAdmin(Role role) {
		return role.getAuthorities().stream().anyMatch(auth -> superAdminProperties.getAuthority().equals(auth.getCode()));
	}

	@Override
	public void delete(Role entity) {
		AssertUtil.assertTrue(entity.getUsers() == null || entity.getUsers().isEmpty(), "该角色下存在用户的关联信息，不能删除");
		getRepository().delete(entity);
	}

	@Override
	public JpaRepository<Role, Long> getRepository() {
		return roleRepository;
	}
}
