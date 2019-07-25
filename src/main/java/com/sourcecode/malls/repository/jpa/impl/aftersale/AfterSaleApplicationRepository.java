package com.sourcecode.malls.repository.jpa.impl.aftersale;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.aftersale.AfterSaleApplication;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.order.Order;
import com.sourcecode.malls.domain.order.SubOrder;
import com.sourcecode.malls.enums.AfterSaleStatus;

public interface AfterSaleApplicationRepository
		extends JpaRepository<AfterSaleApplication, Long>, JpaSpecificationExecutor<AfterSaleApplication> {
	List<AfterSaleApplication> findAllByOrderAndStatus(Order order, AfterSaleStatus status, Pageable pageable);

	Optional<AfterSaleApplication> findBySubOrder(SubOrder order);

	List<AfterSaleApplication> findAllByClientAndStatus(Client client, AfterSaleStatus status, Pageable pageable);
}
