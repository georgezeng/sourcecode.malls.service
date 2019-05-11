package com.sourcecode.malls.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
	private String endpoint;
	private String publicBucket;
	private String privateBucket;

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getPublicBucket() {
		return publicBucket;
	}

	public void setPublicBucket(String publicBucket) {
		this.publicBucket = publicBucket;
	}

	public String getPrivateBucket() {
		return privateBucket;
	}

	public void setPrivateBucket(String privateBucket) {
		this.privateBucket = privateBucket;
	}

}
