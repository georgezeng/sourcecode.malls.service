package com.sourcecode.malls.domain.goods;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.order.Order;
import com.sourcecode.malls.domain.order.SubOrder;
import com.sourcecode.malls.dto.goods.GoodsItemEvaluationDTO;
import com.sourcecode.malls.enums.GoodsItemEvaluationValue;

@Entity
@Table(name = "goods_item_evaluation")
public class GoodsItemEvaluation extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@NotNull(message = "商品不能为空")
	private GoodsItem item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_order_id")
	@NotNull(message = "子订单不能为空")
	private SubOrder subOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@NotNull(message = "订单不能为空")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "评价不能为空")
	private GoodsItemEvaluationValue value;
	private int starsForItem;
	private int starsForExpress;
	@NotBlank(message = "评论不能为空")
	@Size(max = 255, message = "评论长度不能超过255")
	private String remark;
	private boolean anonymous;

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getStarsForItem() {
		return starsForItem;
	}

	public void setStarsForItem(int starsForItem) {
		this.starsForItem = starsForItem;
	}

	public int getStarsForExpress() {
		return starsForExpress;
	}

	public void setStarsForExpress(int starsForExpress) {
		this.starsForExpress = starsForExpress;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public GoodsItemEvaluationValue getValue() {
		return value;
	}

	public void setValue(GoodsItemEvaluationValue value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public GoodsItemEvaluationDTO asDTO() {
		GoodsItemEvaluationDTO dto = new GoodsItemEvaluationDTO();
		BeanUtils.copyProperties(this, dto, "client", "item");
		dto.setClientAvatar(client.getAvatar());
		if (anonymous) {
			dto.setClientNickname("匿名");
		} else {
			dto.setClientNickname(client.getNickname());
		}
		return dto;
	}
}
