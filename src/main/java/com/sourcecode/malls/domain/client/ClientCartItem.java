package com.sourcecode.malls.domain.client;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.goods.GoodsItemProperty;
import com.sourcecode.malls.dto.client.ClientCartItemDTO;

@Table(name = "client_cart_item")
@Entity
public class ClientCartItem extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "商品不能为空")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private GoodsItem item;

	@NotNull(message = "规格不能为空")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_id")
	private GoodsItemProperty property;

	private int nums;

	@NotNull(message = "用户不能为空")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public GoodsItemProperty getProperty() {
		return property;
	}

	public void setProperty(GoodsItemProperty property) {
		this.property = property;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ClientCartItemDTO asDTO() {
		ClientCartItemDTO dto = new ClientCartItemDTO();
		BeanUtils.copyProperties(this, dto);
		if (item != null) {
			dto.setItem(item.asDTO(false, false, false));
		}
		if (property != null) {
			dto.setProperty(property.asDTO());
		}
		return dto;
	}

}
