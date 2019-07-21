package com.sourcecode.malls.repository.jpa.impl.aftersale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.aftersale.AfterSalePhoto;

public interface AfterSalePhotoRepository
		extends JpaRepository<AfterSalePhoto, Long>, JpaSpecificationExecutor<AfterSalePhoto> {

}
