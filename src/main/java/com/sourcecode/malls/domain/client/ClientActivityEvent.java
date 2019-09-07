package com.sourcecode.malls.domain.client;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.dto.client.ClientActivityEventDTO;
import com.sourcecode.malls.enums.ClientActivityEventStatus;

@Table(name = "client_activity_event")
@Entity
public class ClientActivityEvent extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	@NotBlank(message = "活动名称不能为空")
	private String name;

	@NotNull(message = "开始时间不能为空")
	private Date startTime;

	@NotNull(message = "结束时间不能为空")
	private Date endTime;

	private boolean paused;

	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ClientActivityEventDTO asDTO() {
		ClientActivityEventDTO dto = new ClientActivityEventDTO();
		BeanUtils.copyProperties(this, dto);
		if (!deleted) {
			Date now = new Date();
			if (paused) {
				dto.setStatus(ClientActivityEventStatus.Paused);
			} else if (now.before(startTime)) {
				dto.setStatus(ClientActivityEventStatus.UnStarted);
			} else if (now.after(endTime)) {
				dto.setStatus(ClientActivityEventStatus.Stopped);
			} else {
				dto.setStatus(ClientActivityEventStatus.In);
			}
		}
		return dto;
	}
}
