package com.sourcecode.malls.domain.goods;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@Entity
@Table(name = "goods_item_rank")
public class GoodsItemRank extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@NotNull(message = "商品不能为空")
	private GoodsItem item;

	private long goodPoints;
	private long neutralityPoints;
	private long badPoints;
	private long orderNums;

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public long getGoodPoints() {
		return goodPoints;
	}

	public void setGoodPoints(long goodPoints) {
		this.goodPoints = goodPoints;
	}

	public long getNeutralityPoints() {
		return neutralityPoints;
	}

	public void setNeutralityPoints(long neutralityPoints) {
		this.neutralityPoints = neutralityPoints;
	}

	public long getBadPoints() {
		return badPoints;
	}

	public void setBadPoints(long badPoints) {
		this.badPoints = badPoints;
	}

	public long getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(long orderNums) {
		this.orderNums = orderNums;
	}

}
