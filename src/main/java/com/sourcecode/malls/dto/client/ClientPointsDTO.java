package com.sourcecode.malls.dto.client;

import java.math.BigDecimal;

public class ClientPointsDTO {
	private BigDecimal currentAmount;
	private BigDecimal accumulatedAmount;
	private BigDecimal accOutAmount = BigDecimal.ZERO;
	private BigDecimal accInAmount = BigDecimal.ZERO;

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

	public BigDecimal getAccumulatedAmount() {
		return accumulatedAmount;
	}

	public void setAccumulatedAmount(BigDecimal accumulatedAmount) {
		this.accumulatedAmount = accumulatedAmount;
	}
}
