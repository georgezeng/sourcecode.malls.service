package com.sourcecode.malls.domain.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.sourcecode.malls.domain.base.BaseRole;

@Table(name = "merchant_role")
@Entity
public class MerchantRole extends BaseRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "merchant_user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@OrderBy("username ASC")
	private List<MerchantUser> users;

	public void addUser(MerchantUser user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}

	public List<MerchantUser> getUsers() {
		return users;
	}

	public void setUsers(List<MerchantUser> users) {
		this.users = users;
	}

}
