package com.sourcecode.malls.domain.client;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.dto.client.ClientPointsDTO;

@Table(name = "client_points")
@Entity
public class ClientPoints extends LongKeyEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	private BigDecimal accumulatedAmount = BigDecimal.ZERO;

	private BigDecimal currentAmount = BigDecimal.ZERO;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BigDecimal getAccumulatedAmount() {
		return accumulatedAmount;
	}

	public void setAccumulatedAmount(BigDecimal accumulatedAmount) {
		this.accumulatedAmount = accumulatedAmount;
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public ClientPointsDTO asDTO() {
		ClientPointsDTO dto = new ClientPointsDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
