package com.sourcecode.malls.repository.jpa.impl.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
	Optional<Client> findByMerchantAndUsername(Merchant merchant, String username);

	Optional<Client> findByMerchantAndUnionId(Merchant merchant, String unionId);

	List<Client> findAllByParent(Client parent, Pageable page);
}
