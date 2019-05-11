package com.sourcecode.malls.domain.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.merchant.MerchantShopApplicationDTO;
import com.sourcecode.malls.enums.VerificationStatus;

@Entity
@Table(name = "merchant_shop_application")
public class MerchantShopApplication extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "店铺名称不能为空")
	@Size(max = 50, message = "店铺名称长度不能大于50")
	private String name;
	@NotBlank(message = "店铺域名不能为空")
	@Size(max = 50, message = "店铺域名长度不能大于50")
	private String domain;
	@NotBlank(message = "logo不能为空")
	private String logo;
	private boolean androidType;
	private boolean iosType;
	private String androidSmallIcon;
	private String androidBigIcon;
	private String iosSmallIcon;
	private String iosBigIcon;
	@NotBlank(message = "登录背景图不能为空")
	private String loginBgImg;
	@NotBlank(message = "店铺说明不能为空")
	@Size(max = 255, message = "店铺说明长度不能大于255")
	private String description;
	@Enumerated(EnumType.STRING)
	@NotNull
	private VerificationStatus status;
	private String reason;
	private String androidUrl;
	private String iosUrl;
	private boolean deployed;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull
	private Merchant merchant;

	@OneToMany(mappedBy = "shopApplication", fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval=true)
	@OrderBy("order ASC")
	private List<MerchantShopApplicationInstruction> instructions;

	public boolean isDeployed() {
		return deployed;
	}

	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}

	public String getAndroidUrl() {
		return androidUrl;
	}

	public void setAndroidUrl(String androidUrl) {
		this.androidUrl = androidUrl;
	}

	public String getIosUrl() {
		return iosUrl;
	}

	public void setIosUrl(String iosUrl) {
		this.iosUrl = iosUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void addInstruction(MerchantShopApplicationInstruction instruction) {
		if (instructions == null) {
			instructions = new ArrayList<>();
		}
		instructions.add(instruction);
	}

	public List<MerchantShopApplicationInstruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<MerchantShopApplicationInstruction> instructions) {
		this.instructions = instructions;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public VerificationStatus getStatus() {
		return status;
	}

	public void setStatus(VerificationStatus status) {
		this.status = status;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isAndroidType() {
		return androidType;
	}

	public void setAndroidType(boolean androidType) {
		this.androidType = androidType;
	}

	public boolean isIosType() {
		return iosType;
	}

	public void setIosType(boolean iosType) {
		this.iosType = iosType;
	}

	public String getAndroidSmallIcon() {
		return androidSmallIcon;
	}

	public void setAndroidSmallIcon(String androidSmallIcon) {
		this.androidSmallIcon = androidSmallIcon;
	}

	public String getAndroidBigIcon() {
		return androidBigIcon;
	}

	public void setAndroidBigIcon(String androidBigIcon) {
		this.androidBigIcon = androidBigIcon;
	}

	public String getIosSmallIcon() {
		return iosSmallIcon;
	}

	public void setIosSmallIcon(String iosSmallIcon) {
		this.iosSmallIcon = iosSmallIcon;
	}

	public String getIosBigIcon() {
		return iosBigIcon;
	}

	public void setIosBigIcon(String iosBigIcon) {
		this.iosBigIcon = iosBigIcon;
	}

	public String getLoginBgImg() {
		return loginBgImg;
	}

	public void setLoginBgImg(String loginBgImg) {
		this.loginBgImg = loginBgImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MerchantShopApplicationDTO asDTO() {
		MerchantShopApplicationDTO dto = new MerchantShopApplicationDTO();
		BeanUtils.copyProperties(this, dto, "merchant", "instructions");
		if (merchant != null) {
			dto.setUsername(merchant.getUsername());
		}
		if (instructions != null) {
			for (MerchantShopApplicationInstruction instruction : instructions) {
				dto.addInstrcution(instruction.getPath());
			}
		}
		return dto;
	}

}
