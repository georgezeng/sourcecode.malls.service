package com.sourcecode.malls.repository.jpa.impl.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.ClientPoints;

public interface ClientPointsRepository
		extends JpaRepository<ClientPoints, Long>, JpaSpecificationExecutor<ClientPoints> {

}
