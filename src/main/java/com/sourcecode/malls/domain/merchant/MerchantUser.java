package com.sourcecode.malls.domain.merchant;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sourcecode.malls.domain.base.BaseUser;

@Table(name = "merchant_user")
@Entity
public class MerchantUser extends BaseUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
