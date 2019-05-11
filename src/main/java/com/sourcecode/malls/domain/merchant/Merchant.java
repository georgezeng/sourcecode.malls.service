package com.sourcecode.malls.domain.merchant;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.BaseUser;
import com.sourcecode.malls.dto.merchant.MerchantDTO;

@Table(name = "merchant_user")
@Entity
public class Merchant extends BaseUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Merchant parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Merchant> subList;

	public List<Merchant> getSubList() {
		return subList;
	}

	public void setSubList(List<Merchant> subList) {
		this.subList = subList;
	}

	public Merchant getParent() {
		return parent;
	}

	public void setParent(Merchant parent) {
		this.parent = parent;
	}

	public MerchantDTO asDTO() {
		MerchantDTO dto = new MerchantDTO();
		BeanUtils.copyProperties(this, dto, "password", "parent");
		return dto;
	}

}
