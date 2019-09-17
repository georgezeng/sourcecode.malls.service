package com.sourcecode.malls.domain.client;

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
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.client.ClientLevelSettingDTO;

@Table(name = "client_level_setting")
@Entity
public class ClientLevelSetting extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	private int level;

	@Size(max = 50, message = "等级名称长度不能超过50")
	private String name;

	private BigDecimal upToAmount;

	private BigDecimal discount;

	private BigDecimal discountInActivity;
	
	@NotBlank(message="图标路径不能为空")
	@Size(max=255, message="图标路径长度不能超过255")
	private String imgPath;
	
	private int upToMembers;

	public int getUpToMembers() {
		return upToMembers;
	}

	public void setUpToMembers(int upToMembers) {
		this.upToMembers = upToMembers;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUpToAmount() {
		return upToAmount;
	}

	public void setUpToAmount(BigDecimal upToAmount) {
		this.upToAmount = upToAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getDiscountInActivity() {
		return discountInActivity;
	}

	public void setDiscountInActivity(BigDecimal discountInActivity) {
		this.discountInActivity = discountInActivity;
	}

	public ClientLevelSettingDTO asDTO() {
		ClientLevelSettingDTO dto = new ClientLevelSettingDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
