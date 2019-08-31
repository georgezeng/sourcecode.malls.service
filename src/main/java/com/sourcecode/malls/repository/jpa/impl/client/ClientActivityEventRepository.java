package com.sourcecode.malls.repository.jpa.impl.client;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.ClientActivityEvent;
import com.sourcecode.malls.domain.merchant.Merchant;

public interface ClientActivityEventRepository
		extends JpaRepository<ClientActivityEvent, Long>, JpaSpecificationExecutor<ClientActivityEvent> {
	long countByMerchantAndPausedAndDeletedAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(Merchant merchant,
			boolean paused, boolean deleted, Date startTime, Date endTime);
}
