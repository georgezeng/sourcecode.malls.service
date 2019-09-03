package com.sourcecode.malls.dto.client;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClientPointsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String clientName;
	private BigDecimal currentAmount;
	private BigDecimal accOutAmount = BigDecimal.ZERO;
	private BigDecimal accInAmount = BigDecimal.ZERO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public BigDecimal getAccOutAmount() {
		return accOutAmount;
	}

	public void setAccOutAmount(BigDecimal accOutAmount) {
		this.accOutAmount = accOutAmount;
	}

	public BigDecimal getAccInAmount() {
		return accInAmount;
	}

	public void setAccInAmount(BigDecimal accInAmount) {
		this.accInAmount = accInAmount;
	}

	public BigDecimal getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

}
