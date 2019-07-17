package com.sourcecode.malls.repository.jpa.impl.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.order.Express;

public interface ExpressRepository extends JpaRepository<Express, Long>, JpaSpecificationExecutor<Express> {
}
