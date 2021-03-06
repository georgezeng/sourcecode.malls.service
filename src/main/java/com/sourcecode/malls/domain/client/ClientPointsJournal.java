package com.sourcecode.malls.domain.client;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.client.ClientPointsJournalDTO;
import com.sourcecode.malls.enums.BalanceType;
import com.sourcecode.malls.enums.ClientPointsType;

@Table(name = "client_points_journal")
@Entity
public class ClientPointsJournal extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@NotNull(message = "积分不能为空")
	private BigDecimal bonusAmount;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "类型不能为空")
	private ClientPointsType type;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "收支类型不能为空")
	private BalanceType balanceType;

	@Size(max = 255, message = "订单编号长度不能超过255")
	private String orderId;

	private BigDecimal amount;

	@Size(max = 255, message = "备注长度不能超过255")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public ClientPointsType getType() {
		return type;
	}

	public void setType(ClientPointsType type) {
		this.type = type;
	}

	public BalanceType getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
	}

	public ClientPointsJournalDTO asDTO() {
		ClientPointsJournalDTO dto = new ClientPointsJournalDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
