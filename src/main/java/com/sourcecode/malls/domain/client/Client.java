package com.sourcecode.malls.domain.client;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sourcecode.malls.domain.base.BaseUser;
import com.sourcecode.malls.domain.merchant.Merchant;

@Table(name = "client")
@Entity
public class Client extends BaseUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Client SystemUser = new Client("System");

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;
	@Size(max = 255, message = "昵称长度不能大于255")
	private String nickname;
	@Column(name="unionid")
	private String unionId;

	public Client() {

	}

	private Client(String username) {
		super(username);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

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
		return Arrays.asList(new SimpleGrantedAuthority("AUTH_CLIENT"));
	}

}
