package com.sourcecode.malls.dto.goods;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sourcecode.malls.domain.goods.GoodsItemEvaluation;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.enums.GoodsItemEvaluationValue;

public class GoodsItemEvaluationDTO extends SimpleQueryDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long itemId;
	private String itemThumbnail;
	private String itemName;
	private int itemNums;
	private String itemSpecificationValues;
	private String buyer;
	private String clientNickname;
	private String remark;
	private String clientAvatar;
	private List<String> photos;
	private boolean passed;
	private boolean anonymous;
	private GoodsItemEvaluationValue value;
	private String reply;
//	private int starsForItem;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date replyTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	private boolean additional;
	private boolean open;
	private boolean hasAudit;
	private boolean replied;
	private GoodsItemEvaluationDTO additionalEvaluation;

	public boolean isReplied() {
		return replied;
	}

	public void setReplied(boolean replied) {
		this.replied = replied;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public boolean isHasAudit() {
		return hasAudit;
	}

	public void setHasAudit(boolean hasAudit) {
		this.hasAudit = hasAudit;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemNums() {
		return itemNums;
	}

	public void setItemNums(int itemNums) {
		this.itemNums = itemNums;
	}

	public String getItemSpecificationValues() {
		return itemSpecificationValues;
	}

	public void setItemSpecificationValues(String itemSpecificationValues) {
		this.itemSpecificationValues = itemSpecificationValues;
	}

	public String getItemThumbnail() {
		return itemThumbnail;
	}

	public void setItemThumbnail(String itemThumbnail) {
		this.itemThumbnail = itemThumbnail;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public GoodsItemEvaluationValue getValue() {
		return value;
	}

	public void setValue(GoodsItemEvaluationValue value) {
		this.value = value;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public boolean isAdditional() {
		return additional;
	}

	public void setAdditional(boolean additional) {
		this.additional = additional;
	}

	public GoodsItemEvaluationDTO getAdditionalEvaluation() {
		return additionalEvaluation;
	}

	public void setAdditionalEvaluation(GoodsItemEvaluationDTO additionalEvaluation) {
		this.additionalEvaluation = additionalEvaluation;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

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

	public GoodsItemEvaluation asEntity() {
		GoodsItemEvaluation data = new GoodsItemEvaluation();
		BeanUtils.copyProperties(this, data, "id", "photos", "createTime", "replyTime");
		return data;
	}
}
