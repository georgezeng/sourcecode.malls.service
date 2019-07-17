package com.sourcecode.malls.dto.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

public class PageInfo {
	private int num;
	private int size;
	private String property;
	private String order;

	public Pageable pageable() {
		if (StringUtils.isEmpty(property)) {
			return PageRequest.of(num - 1, size);
		}
		if (!"normal".equals(order.toLowerCase())) {
			return pageable(Direction.valueOf(order), property);
		}
		return PageRequest.of(num - 1, size);
	}

	public Pageable pageable(String order, String... properties) {
		if (!"normal".equals(order.toLowerCase())) {
			return PageRequest.of(num - 1, size, Direction.valueOf(order), properties);
		}
		return pageable();
	}

	public Pageable pageable(Direction direction, String... properties) {
		return PageRequest.of(num - 1, size, direction, properties);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}