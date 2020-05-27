package com.sourcecode.malls.domain.goods;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.goods.GoodsItemPhotoGroupDTO;

@Entity
@Table(name = "goods_item_photo_group")
public class GoodsItemPhotoGroup extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@NotNull(message = "商品不能为空")
	private GoodsItem item;

	@NotBlank(message = "相册组名称不能为空")
	@Size(max = 50, message = "相册组名称长度不能大于50")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@OrderBy("order ASC")
	private List<GoodsItemPhoto> photos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GoodsItemPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<GoodsItemPhoto> photos) {
		this.photos = photos;
	}

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public void addPhoto(GoodsItemPhoto photo) {
		if (photos == null) {
			photos = new ArrayList<>();
		}
		photos.add(photo);
	}

	public GoodsItemPhotoGroupDTO asDTO() {
		GoodsItemPhotoGroupDTO dto = new GoodsItemPhotoGroupDTO();
		BeanUtils.copyProperties(this, dto);
		if (photos != null) {
			List<String> photoPaths = new ArrayList<>();
			for (GoodsItemPhoto photo : photos) {
				photoPaths.add(photo.getPath());
			}
			dto.setPhotos(photoPaths);
		}
		return dto;
	}
}
