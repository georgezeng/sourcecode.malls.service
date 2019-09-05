package com.sourcecode.malls.dto.client;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClientPointsBonus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal rookie = BigDecimal.ZERO;
	private BigDecimal invite = BigDecimal.ZERO;

	public BigDecimal getRookie() {
		return rookie;
	}

	public void setRookie(BigDecimal rookie) {
		this.rookie = rookie;
	}

	public BigDecimal getInvite() {
		return invite;
	}

	public void setInvite(BigDecimal invite) {
		this.invite = invite;
	}
}
