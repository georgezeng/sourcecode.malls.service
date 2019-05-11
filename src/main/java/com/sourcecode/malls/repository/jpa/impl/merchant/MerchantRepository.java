package com.sourcecode.malls.repository.jpa.impl.merchant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.merchant.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long>, JpaSpecificationExecutor<Merchant> {
	Optional<Merchant> findByUsername(String username);

	Page<Merchant> findAllByParentAndEnabledAndUsernameLike(Merchant parent, boolean enabled, String username, Pageable pageable);

	Page<Merchant> findAllByParentAndEnabled(Merchant parent, boolean enabled, Pageable pageable);
	
	List<Merchant> findAllByParentIsNotNull();
}
