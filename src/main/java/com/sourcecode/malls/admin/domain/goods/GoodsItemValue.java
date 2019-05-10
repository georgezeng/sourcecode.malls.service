package com.sourcecode.malls.admin.domain.goods;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sourcecode.malls.admin.domain.base.LongKeyEntity;

@Entity
@Table(name = "goods_item_value")
public class GoodsItemValue extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "uid不能为空")
	private String uid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "value_id")
	@NotNull(message = "规格值不能为空")
	private GoodsSpecificationValue value;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public GoodsSpecificationValue getValue() {
		return value;
	}

	public void setValue(GoodsSpecificationValue value) {
		this.value = value;
	}

}
