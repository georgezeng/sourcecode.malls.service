package com.sourcecode.malls.dto.goods;

public class GoodsItemEvaluationDTO {
	private Long id;
	private String clientNickname;
	private String remark;
	private String clientAvatar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientNickname() {
		return clientNickname;
	}

	public void setClientNickname(String clientNickname) {
		this.clientNickname = clientNickname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClientAvatar() {
		return clientAvatar;
	}

	public void setClientAvatar(String clientAvatar) {
		this.clientAvatar = clientAvatar;
	}
}
