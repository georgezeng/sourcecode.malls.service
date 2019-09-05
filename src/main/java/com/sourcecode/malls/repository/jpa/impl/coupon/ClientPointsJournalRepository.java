package com.sourcecode.malls.repository.jpa.impl.coupon;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sourcecode.malls.constants.CacheNameConstant;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.client.ClientPointsJournal;
import com.sourcecode.malls.enums.ClientPointsType;

public interface ClientPointsJournalRepository
		extends JpaRepository<ClientPointsJournal, Long>, JpaSpecificationExecutor<ClientPointsJournal> {

	List<ClientPointsJournal> findAllByClient(Client client, Pageable pageable);

	long countByClient(Client client);

	long countByTypeAndClient(ClientPointsType type, Client client);

	@Cacheable(cacheNames = CacheNameConstant.CLIENT_TOTAL_INVITE_POINTS, key = "#clientId")
	@Query(value = "select sum(bonus_amount) from client_points_journal where type='Invite' and client_id=?1", nativeQuery = true)
	BigDecimal sumInvitePointsForClient(Long clientId);

	Optional<ClientPointsJournal> findByTypeAndClientAndOrderId(ClientPointsType type, Client client, String orderId);
}
