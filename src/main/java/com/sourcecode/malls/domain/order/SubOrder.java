package com.sourcecode.malls.domain.order;

import java.math.BigDecimal;

import javax.persistence.Entity;
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
import com.sourcecode.malls.domain.goods.GoodsItem;
import com.sourcecode.malls.domain.goods.GoodsItemProperty;
import com.sourcecode.malls.dto.order.SubOrderDTO;

@Table(name = "sub_order")
@Entity
public class SubOrder extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotNull(message = "父订单不能为空")
	private Order parent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@NotNull(message = "商品不能为空")
	private GoodsItem item;

	@Size(max = 255, message = "商品编号长度不能超过255")
	@NotNull(message = "商品编号不能为空")
	private String itemNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "商品规格不能为空")
	@JoinColumn(name = "property_id")
	private GoodsItemProperty property;

	@Size(max = 50, message = "商品货号长度不能超过50")
	private String itemCode;

	@NotBlank(message = "商品名称不能为空")
	@Size(max = 50, message = "商品名称长度不能超过50")
	private String itemName;

	@Size(max = 255, message = "缩略图长度不能大于255")
	private String thumbnail;

	@NotBlank(message = "商品详情不能为空")
	private String itemContent;

	@Size(max = 100, message = "商品卖点长度不能大于100")
	private String sellingPoints;

	@Size(max = 50, message = "商品分类长度不能大于50")
	private String category;

	@Size(max = 50, message = "品牌名称长度不能大于50")
	private String brand;

	@NotBlank(message = "规格不能为空")
	@Size(max = 255, message = "规格组长度不能大于255")
	private String specificationValues;

	private BigDecimal marketPrice;

	@NotNull(message = "单价不能为空")
	private BigDecimal unitPrice;

	@NotNull(message = "交易金额不能为空")
	private BigDecimal dealPrice;

	private int nums;

	private boolean comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public GoodsItemProperty getProperty() {
		return property;
	}

	public void setProperty(GoodsItemProperty property) {
		this.property = property;
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public Order getParent() {
		return parent;
	}

	public void setParent(Order parent) {
		this.parent = parent;
	}

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public String getSellingPoints() {
		return sellingPoints;
	}

	public void setSellingPoints(String sellingPoints) {
		this.sellingPoints = sellingPoints;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpecificationValues() {
		return specificationValues;
	}

	public void setSpecificationValues(String specificationValues) {
		this.specificationValues = specificationValues;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(BigDecimal dealPrice) {
		this.dealPrice = dealPrice;
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

	public SubOrderDTO asDTO() {
		SubOrderDTO dto = new SubOrderDTO();
		BeanUtils.copyProperties(this, dto);
		try {
			dto.setInventory(property.getInventory());
		} catch (Exception e) {
			
		}
		if (item != null) {
			dto.setItemId(item.getId());
		}
		dto.setParentId(parent.getId());
		return dto;
	}

}
