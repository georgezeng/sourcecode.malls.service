package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.client.ClientPointsJournal;

public interface ClientPointsJournalRepository
		extends JpaRepository<ClientPointsJournal, Long>, JpaSpecificationExecutor<ClientPointsJournal> {

	List<ClientPointsJournal> findAllByClient(Client client, Pageable pageable);

	long countByClient(Client client);
}
