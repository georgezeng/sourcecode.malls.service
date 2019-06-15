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

	private long goodEvaluations;
	private long neutralityEvaluations;
	private long badEvaluations;
	private long orderNums;

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public long getGoodEvaluations() {
		return goodEvaluations;
	}

	public void setGoodEvaluations(long goodEvaluations) {
		this.goodEvaluations = goodEvaluations;
	}

	public long getNeutralityEvaluations() {
		return neutralityEvaluations;
	}

	public void setNeutralityEvaluations(long neutralityEvaluations) {
		this.neutralityEvaluations = neutralityEvaluations;
	}

	public long getBadEvaluations() {
		return badEvaluations;
	}

	public void setBadEvaluations(long badEvaluations) {
		this.badEvaluations = badEvaluations;
	}

	public long getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(long orderNums) {
		this.orderNums = orderNums;
	}

}
