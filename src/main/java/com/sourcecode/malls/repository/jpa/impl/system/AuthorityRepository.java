package com.sourcecode.malls.repository.jpa.impl.system;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sourcecode.malls.domain.system.setting.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {
	Optional<Authority> findByCode(String code);

	Page<Authority> findAllByCodeLikeOrNameLike(String code, String name, Pageable pageable);

	List<Authority> findAllByLink(String link);

	@Query(value = "from Authority a where a.code<>'AUTH_SUPER_ADMIN'")
	List<Authority> findAllWithoutSuperAdmin();
}
