package com.sourcecode.malls.dto.client;

import java.math.BigDecimal;

public class ClientPointsDTO {
	private BigDecimal currentAmount;
	private BigDecimal accumulatedAmount;

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
