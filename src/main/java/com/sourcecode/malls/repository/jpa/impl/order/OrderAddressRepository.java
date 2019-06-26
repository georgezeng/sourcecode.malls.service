package com.sourcecode.malls.repository.jpa.impl.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.order.OrderAddress;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long>, JpaSpecificationExecutor<OrderAddress> {
}
