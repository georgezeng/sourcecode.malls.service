package com.sourcecode.malls.domain.goods;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sourcecode.malls.domain.base.LongKeyEntity;

@Entity
@Table(name = "goods_item_evaluation_photo")
public class GoodsItemEvaluationPhoto extends LongKeyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evaluation_id")
	@NotNull(message = "评价不能为空")
	private GoodsItemEvaluation evaluation;

	@Size(max = 255, message = "图片路径长度不能超过255")
	@NotBlank(message = "图片路径不能为空")
	private String path;

	public GoodsItemEvaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(GoodsItemEvaluation evaluation) {
		this.evaluation = evaluation;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
