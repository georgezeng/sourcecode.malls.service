package com.sourcecode.malls.repository.jpa.impl.aftersale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.aftersale.AfterSaleAddress;

public interface AfterSaleAddressRepository
		extends JpaRepository<AfterSaleAddress, Long>, JpaSpecificationExecutor<AfterSaleAddress> {
}
