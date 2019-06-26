package com.sourcecode.malls.repository.jpa.impl.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.order.SubOrder;

public interface SubOrderRepository
		extends JpaRepository<SubOrder, Long>, JpaSpecificationExecutor<SubOrder> {
}
