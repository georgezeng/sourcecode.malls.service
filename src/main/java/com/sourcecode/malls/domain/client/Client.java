package com.sourcecode.malls.domain.client;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.sourcecode.malls.domain.base.BaseUser;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.client.ClientDTO;
import com.sourcecode.malls.enums.Sex;

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
	@Size(max = 50, message = "昵称长度不能大于50")
	private String nickname;
	@Column(name = "unionid")
	private String unionId;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	private Date birthday;

	@Transient
	private String auth;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Client parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<Client> subList;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "client")
	private ClientPoints points;

	private boolean loggedIn;

	@NotNull(message = "累积消费不能为空")
	private BigDecimal consumeTotalAmount = BigDecimal.ZERO;
	
	@NotNull(message = "累积邀请人数不能为空")
	private int inviteMembers;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "level_id")
	@NotNull(message = "会员等级不能为空")
	private ClientLevelSetting level;

	public int getInviteMembers() {
		return inviteMembers;
	}

	public void setInviteMembers(int inviteMembers) {
		this.inviteMembers = inviteMembers;
	}

	public ClientLevelSetting getLevel() {
		return level;
	}

	public void setLevel(ClientLevelSetting level) {
		this.level = level;
	}

	public BigDecimal getConsumeTotalAmount() {
		return consumeTotalAmount;
	}

	public void setConsumeTotalAmount(BigDecimal consumeTotalAmount) {
		this.consumeTotalAmount = consumeTotalAmount;
	}

	public ClientPoints getPoints() {
		return points;
	}

	public void setPoints(ClientPoints points) {
		this.points = points;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Client() {

	}

	private Client(String username) {
		super(username);
	}

	public List<Client> getSubList() {
		return subList;
	}

	public void setSubList(List<Client> subList) {
		this.subList = subList;
	}

	public Client getParent() {
		return parent;
	}

	public void setParent(Client parent) {
		this.parent = parent;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
		if (StringUtils.isEmpty(auth)) {
			auth = "AUTH_CLIENT";
		}
		return Arrays.asList(new SimpleGrantedAuthority(auth));
	}

	public ClientDTO asDTO(boolean withParent) {
		ClientDTO dto = new ClientDTO();
		BeanUtils.copyProperties(this, dto);
		if (withParent && parent != null) {
			dto.setInvitor(parent.getUsername());
		}
		if (level != null) {
			try {
				dto.setLevelName(level.getName());
			} catch (Exception e) {

			}
		}
		return dto;
	}

}
