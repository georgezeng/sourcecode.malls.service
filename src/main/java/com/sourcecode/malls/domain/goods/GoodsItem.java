package com.sourcecode.malls.domain.goods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.goods.GoodsItemDTO;
import com.sourcecode.malls.dto.goods.GoodsItemPhotoGroupDTO;

@Entity
@Table(name = "goods_item")
public class GoodsItem extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "商品名称不能为空")
	@Size(max = 50, message = "商品名称长度不能大于50")
	private String name;
	@Size(max = 255, message = "商品编号长度不能大于255")
	@NotBlank(message = "商品编号不能为空")
	private String number;
	@Size(max = 50, message = "商品货号长度不能大于50")
	private String code;
	private BigDecimal marketPrice;
	@NotNull(message = "最低价格不能为空")
	private BigDecimal minPrice;
	@NotNull(message = "最高价格不能为空")
	private BigDecimal maxPrice;
	@Size(max = 100, message = "商品卖点长度不能大于100")
	private String sellingPoints;
	@NotBlank(message = "商品描述不能为空")
	private String content;
	@NotBlank(message = "缩略图不能为空")
	@Size(max = 255, message = "缩略图长度不能大于255")
	private String thumbnail;
	private boolean enabled;

	private Date putTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	@NotNull(message = "商品分类不能为空")
	private GoodsCategory category;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "item")
	private GoodsItemRank rank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private GoodsBrand brand;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<GoodsItemPhoto> photos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<GoodsItemPhotoGroup> groups;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = { CascadeType.REMOVE })
	private List<GoodsItemProperty> properties;

	@Size(max = 255, message = "视频地址长度不能大于255")
	private String vedioPath;
	
	private int indexRecommend;
	
	private int indexOrder;
	
	private boolean specialDiscount;
	
	private BigDecimal discount = BigDecimal.ONE;

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public boolean isSpecialDiscount() {
		return specialDiscount;
	}

	public void setSpecialDiscount(boolean specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	public int getIndexRecommend() {
		return indexRecommend;
	}

	public void setIndexRecommend(int indexRecommend) {
		this.indexRecommend = indexRecommend;
	}

	public int getIndexOrder() {
		return indexOrder;
	}

	public void setIndexOrder(int indexOrder) {
		this.indexOrder = indexOrder;
	}

	public String getVedioPath() {
		return vedioPath;
	}

	public void setVedioPath(String vedioPath) {
		this.vedioPath = vedioPath;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public GoodsItemRank getRank() {
		return rank;
	}

	public void setRank(GoodsItemRank rank) {
		this.rank = rank;
	}

	public Date getPutTime() {
		return putTime;
	}

	public void setPutTime(Date putTime) {
		this.putTime = putTime;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public List<GoodsItemProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<GoodsItemProperty> properties) {
		this.properties = properties;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addGroup(GoodsItemPhotoGroup group) {
		if (groups == null) {
			groups = new ArrayList<>();
		}
		groups.add(group);
	}

	public void addPhoto(GoodsItemPhoto photo) {
		if (photos == null) {
			photos = new ArrayList<>();
		}
		photos.add(photo);
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<GoodsItemPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<GoodsItemPhoto> photos) {
		this.photos = photos;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getSellingPoints() {
		return sellingPoints;
	}

	public void setSellingPoints(String sellingPoints) {
		this.sellingPoints = sellingPoints;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public GoodsCategory getCategory() {
		return category;
	}

	public void setCategory(GoodsCategory category) {
		this.category = category;
	}

	public GoodsBrand getBrand() {
		return brand;
	}

	public void setBrand(GoodsBrand brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public List<GoodsItemPhotoGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<GoodsItemPhotoGroup> photoGroups) {
		this.groups = photoGroups;
	}

	public GoodsItemDTO asDTO(boolean withPhoto, boolean withProperties, boolean withContent) {
		GoodsItemDTO dto = new GoodsItemDTO();
		BeanUtils.copyProperties(this, dto, "groups", "merchant", "category", "brand", "photos", "properties");
		if (!withContent) {
			dto.setContent(null);
		}
		if (category != null) {
			dto.setCategoryId(category.getId());
		}
		if (brand != null) {
			dto.setBrandId(brand.getId());
			dto.setBrand(brand.getName());
		}
		if (withPhoto) {
			List<GoodsItemPhotoGroupDTO> groupList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(photos)) {
				GoodsItemPhotoGroupDTO group = new GoodsItemPhotoGroupDTO();
				List<String> list = new ArrayList<>();
				for (GoodsItemPhoto photo : photos) {
					list.add(photo.getPath());
				}
				group.setName("默认");
				group.setPhotos(list);
				groupList.add(group);
			}
			if (!CollectionUtils.isEmpty(groups)) {
				for (GoodsItemPhotoGroup group : groups) {
					groupList.add(group.asDTO());
				}
			}
			dto.setGroups(groupList);
		}
		if (withProperties && properties != null) {
			dto.setProperties(properties.stream().map(it -> it.asDTO()).collect(Collectors.toList()));
		}
		if (rank != null) {
			BigDecimal totalEvaluations = new BigDecimal(rank.getGoodEvaluations() + rank.getBadEvaluations() + rank.getNeutralityEvaluations());
			if (totalEvaluations.compareTo(BigDecimal.ZERO) > 0) {
				dto.setGoodEvaluationRate(
						new BigDecimal(rank.getGoodEvaluations()).divide(totalEvaluations, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
			} else {
				dto.setGoodEvaluationRate(BigDecimal.ZERO);
			}
			dto.setTotalEvaluations(totalEvaluations.longValue());
			dto.setOrderNums(rank.getOrderNums());
		}
		return dto;
	}
}
