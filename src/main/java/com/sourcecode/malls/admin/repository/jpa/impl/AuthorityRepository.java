package com.sourcecode.malls.admin.repository.jpa.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sourcecode.malls.admin.domain.system.setting.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {
	Optional<Authority> findByCode(String code);

	Page<Authority> findAllByCodeLikeOrNameLike(String code, String name, Pageable pageable);

	List<Authority> findAllByLink(String link);

	@Query(value = "from Authority a where a.code like 'AUTH_SUB_%'")
	Page<Authority> findAllForSubAccount(Pageable pageable);
	
	@Query(value = "from Authority a where a.code like 'AUTH_SUB_%'")
	List<Authority> findAllForSubAccount();
}
