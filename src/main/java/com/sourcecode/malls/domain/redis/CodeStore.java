package com.sourcecode.malls.domain.redis;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "CodeStore", timeToLive = 5 * 60)
public class CodeStore {

	@Id
	private String id;
	@Indexed
	private String category;
	@Indexed
	private String key;
	private String value;
	private Date createTime;

	public CodeStore() {

	}

	public CodeStore(String category, String key, String value, Date createTime) {
		this.category = category;
		this.key = key;
		this.value = value;
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}