package com.sourcecode.malls.service.impl;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sourcecode.malls.domain.system.Role;
import com.sourcecode.malls.domain.system.User;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.dto.query.QueryInfo;
import com.sourcecode.malls.dto.system.RoleDTO;
import com.sourcecode.malls.properties.SuperAdminProperties;
import com.sourcecode.malls.repository.jpa.impl.system.RoleRepository;
import com.sourcecode.malls.repository.jpa.impl.system.UserRepository;
import com.sourcecode.malls.service.base.JpaService;
import com.sourcecode.malls.util.AssertUtil;

@Service
@Transactional
public class UserService implements UserDetailsService, JpaService<User, Long> {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private SuperAdminProperties superAdminProperties;

	@Autowired
	private RoleService roleService;

	public void prepareSuperAdmin() {
		Optional<User> superAdminOp = userRepository.findByUsername(superAdminProperties.getUsername());
		User user = null;
		if (!superAdminOp.isPresent()) {
			user = new User();
			user.setUsername(superAdminProperties.getUsername());
		} else {
			user = superAdminOp.get();
		}
		user.setPassword(superAdminProperties.getPassword());
		user.setEmail(superAdminProperties.getEmail());
		user.setEnabled(true);
		userRepository.save(user);
		roleService.prepareSuperAdmin(user);
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOp = userRepository.findByUsername(username);
		if (!userOp.isPresent()) {
			throw new UsernameNotFoundException("用户名或密码有误");
		}
		userOp.get().getAuthorities();
		return userOp.get();
	}

	@Transactional(readOnly = true)
	public Optional<User> findByIdWithAuthorities(Long userId) {
		Optional<User> user = findById(userId);
		if (user.isPresent()) {
			user.get().getAuthorities();
		}
		return user;
	}

	@Transactional(readOnly = true)
	public Optional<User> findByUsernameWithAuthorities(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			user.get().getAuthorities();
		}
		return user;
	}

	@Transactional(readOnly = true)
	public Page<User> findAll(QueryInfo<SimpleQueryDTO> queryInfo) {
		SimpleQueryDTO data = queryInfo.getData();
		Page<User> pageReulst = null;
		Specification<User> spec = new Specification<User>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicate = new ArrayList<>();
				if (data != null) {
					if (!StringUtils.isEmpty(data.getSearchText())) {
						String like = "%" + data.getSearchText() + "%";
						predicate.add(criteriaBuilder.or(criteriaBuilder.like(root.get("username").as(String.class), like),
								criteriaBuilder.like(root.get("email").as(String.class), like)));
					}
					if (!"all".equals(data.getStatusText())) {
						predicate.add(criteriaBuilder.equal(root.get("enabled").as(boolean.class), Boolean.valueOf(data.getStatusText())));
					}
				}
				return query.where(predicate.toArray(new Predicate[] {})).getRestriction();
			}
		};
		pageReulst = userRepository.findAll(spec, queryInfo.getPage().pageable());
		return pageReulst;
	}

	@Transactional(readOnly = true)
	public boolean isSuperAdmin(User user) {
		return user.getUsername().equals(superAdminProperties.getUsername());
	}

	public void relateToRoles(User user, List<RoleDTO> roles) {
		save(user);
		if (!CollectionUtils.isEmpty(roles)) {
			for (RoleDTO roleDTO : roles) {
				Optional<Role> roleOp = roleRepository.findById(roleDTO.getId());
				if (roleOp.isPresent()) {
					Role role = roleOp.get();
					boolean found = false;
					if (role.getUsers() != null) {
						for (User data : role.getUsers()) {
							if (data.getId().equals(user.getId())) {
								found = true;
								break;
							}
						}
					}
					if (!found) {
						role.addUser(user);
					}
				}
			}
			if (isSuperAdmin(user)) {
				AssertUtil.assertTrue(user.getRoles().size() == 1, "超级管理员不需要添加其他角色");
			}
			AssertUtil.assertTrue(!CollectionUtils.isEmpty(user.getRoles()), "请关联至少一条角色记录");
			roleRepository.saveAll(user.getRoles());
		}
	}

	@Override
	public JpaRepository<User, Long> getRepository() {
		return userRepository;
	}
}
