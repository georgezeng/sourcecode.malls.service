package com.sourcecode.malls.repository.jpa.impl.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.client.ClientIdentity;

public interface ClientIdentityRepository extends JpaRepository<ClientIdentity, Long>, JpaSpecificationExecutor<ClientIdentity> {
	Optional<ClientIdentity> findByClient(Client client);
}
