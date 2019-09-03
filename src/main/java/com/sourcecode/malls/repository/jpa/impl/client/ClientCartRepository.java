package com.sourcecode.malls.repository.jpa.impl.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.client.ClientCartItem;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.goods.GoodsItemProperty;

public interface ClientCartRepository extends JpaRepository<ClientCartItem, Long>, JpaSpecificationExecutor<ClientCartItem> {
	List<ClientCartItem> findByClient(Client client);
	
	long countByClient(Client client);

	List<ClientCartItem> findByClientAndItem(Client client, GoodsItem item);
	
	Optional<ClientCartItem> findByClientAndProperty(Client client, GoodsItemProperty property);
}
