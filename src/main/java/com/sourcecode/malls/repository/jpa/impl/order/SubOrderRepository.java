package com.sourcecode.malls.repository.jpa.impl.order;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.order.SubOrder;

public interface SubOrderRepository extends JpaRepository<SubOrder, Long>, JpaSpecificationExecutor<SubOrder> {
	List<SubOrder> findAllByClient(Client client, Pageable pageable);
	long countByItem(GoodsItem item);
}
