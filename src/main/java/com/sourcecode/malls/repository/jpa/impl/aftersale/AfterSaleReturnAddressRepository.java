package com.sourcecode.malls.repository.jpa.impl.aftersale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.aftersale.AfterSaleReturnAddress;

public interface AfterSaleReturnAddressRepository
		extends JpaRepository<AfterSaleReturnAddress, Long>, JpaSpecificationExecutor<AfterSaleReturnAddress> {
}
