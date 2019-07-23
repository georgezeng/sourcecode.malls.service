package com.sourcecode.malls.domain.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.sourcecode.malls.domain.base.LongKeyEntity;
import com.sourcecode.malls.domain.client.Client;
import com.sourcecode.malls.domain.merchant.Merchant;
import com.sourcecode.malls.domain.order.Order;
import com.sourcecode.malls.domain.order.SubOrder;
import com.sourcecode.malls.dto.goods.GoodsItemEvaluationDTO;
import com.sourcecode.malls.enums.GoodsItemEvaluationValue;

@Entity
@Table(name = "goods_item_evaluation")
public class GoodsItemEvaluation extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@NotNull(message = "商品不能为空")
	private GoodsItem item;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_order_id")
	@NotNull(message = "子订单不能为空")
	private SubOrder subOrder;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@NotNull(message = "订单不能为空")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@NotNull(message = "用户不能为空")
	private Client client;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "评价不能为空")
	private GoodsItemEvaluationValue value;

	@NotBlank(message = "评论不能为空")
	@Size(max = 255, message = "评论长度不能超过255")
	private String remark;
	private boolean anonymous;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "merchant_id")
	@NotNull(message = "商家不能为空")
	private Merchant merchant;

	private boolean passed;

	private boolean replied;

//	private int starsForItem;

	@Size(max = 255, message = "回复长度不能超过255")
	private String reply;

	private Date replyTime;

	private boolean additional;

	private boolean hasAudit;

	@OneToOne(mappedBy = "evaluation", fetch = FetchType.LAZY)
	private GoodsItemEvaluation additionalEvaluation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evaluation_id")
	private GoodsItemEvaluation evaluation;

	@OneToMany(mappedBy = "evaluation", fetch = FetchType.LAZY)
	private List<GoodsItemEvaluationPhoto> photos;

	private boolean open;

	public boolean isHasAudit() {
		return hasAudit;
	}

	public void setHasAudit(boolean hasAudit) {
		this.hasAudit = hasAudit;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isAdditional() {
		return additional;
	}

	public void setAdditional(boolean additional) {
		this.additional = additional;
	}

	public GoodsItemEvaluation getEvaluation() {
		return evaluation;
	}

	public GoodsItemEvaluation getAdditionalEvaluation() {
		return additionalEvaluation;
	}

	public void setAdditionalEvaluation(GoodsItemEvaluation additionalEvaluation) {
		this.additionalEvaluation = additionalEvaluation;
	}

	public void setEvaluation(GoodsItemEvaluation evaluation) {
		this.evaluation = evaluation;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public List<GoodsItemEvaluationPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<GoodsItemEvaluationPhoto> photos) {
		this.photos = photos;
	}

	public boolean isReplied() {
		return replied;
	}

	public void setReplied(boolean replied) {
		this.replied = replied;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public GoodsItem getItem() {
		return item;
	}

	public void setItem(GoodsItem item) {
		this.item = item;
	}

	public GoodsItemEvaluationValue getValue() {
		return value;
	}

	public void setValue(GoodsItemEvaluationValue value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public GoodsItemEvaluationDTO asDTO(boolean noNeedPassed) {
		GoodsItemEvaluationDTO dto = asDTO();
		if (additionalEvaluation != null) {
			if (!additionalEvaluation.isPassed() && noNeedPassed || additionalEvaluation.isPassed()) {
				dto.setAdditionalEvaluation(additionalEvaluation.asDTO());
			}
		}
		return dto;
	}

	public GoodsItemEvaluationDTO asDTO() {
		GoodsItemEvaluationDTO dto = new GoodsItemEvaluationDTO();
		BeanUtils.copyProperties(this, dto, "photos", "additionalEvaluation");
		dto.setClientAvatar(client.getAvatar());
		if (anonymous) {
			dto.setClientNickname("匿名");
		} else {
			dto.setClientNickname(client.getNickname());
		}
		dto.setBuyer(client.getUsername());
		if (photos != null) {
			List<String> list = new ArrayList<>();
			for (GoodsItemEvaluationPhoto photo : photos) {
				list.add(photo.getPath());
			}
			dto.setPhotos(list);
		}
		return dto;
	}
}
