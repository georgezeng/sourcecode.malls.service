package com.sourcecode.malls.admin.repository.jpa.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.admin.domain.goods.GoodsBrand;

public interface GoodsBrandRepository extends JpaRepository<GoodsBrand, Long>, JpaSpecificationExecutor<GoodsBrand> {

}
