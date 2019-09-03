package com.sourcecode.malls.dto.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResult<T extends Serializable> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> list = new ArrayList<>();
	private long total;

	public PageResult() {
	}

	public PageResult(List<T> list, long total) {
		this.list = list;
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
