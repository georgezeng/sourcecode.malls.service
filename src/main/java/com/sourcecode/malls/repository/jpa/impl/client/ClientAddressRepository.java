package com.sourcecode.malls.repository.jpa.impl.client;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.client.ClientAddress;

public interface ClientAddressRepository
		extends JpaRepository<ClientAddress, Long>, JpaSpecificationExecutor<ClientAddress> {
	List<ClientAddress> findByClient(Client client, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "update client_address set is_default=false where client_id=?1", nativeQuery = true)
	void clearDefaultStatus(Long clientId);
}
