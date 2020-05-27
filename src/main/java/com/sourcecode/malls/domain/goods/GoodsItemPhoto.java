package com.sourcecode.malls.domain.goods;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@Entity
@Table(name = "goods_item_photo")
public class GoodsItemPhoto extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "商品图片不能为空")
	@Size(max = 255, message = "商品图片长度不能大于255")
	private String path;

	@Column(name = "order_num")
	private int order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private GoodsItem item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private GoodsItemPhotoGroup group;

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public GoodsItemPhotoGroup getGroup() {
		return group;
	}

	public void setGroup(GoodsItemPhotoGroup group) {
		this.group = group;
	}

}
