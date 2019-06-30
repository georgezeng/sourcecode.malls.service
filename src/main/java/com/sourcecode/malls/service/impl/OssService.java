package com.sourcecode.malls.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.sourcecode.malls.properties.AliyunProperties;
import com.sourcecode.malls.properties.OssProperties;
import com.sourcecode.malls.service.FileOnlineSystemService;

@Service
public class OssService implements FileOnlineSystemService {

	@Autowired
	private OssProperties ossConfig;

	@Autowired
	private AliyunProperties aliyunConfig;

	private OSS client = null;

	@PostConstruct
	public void init() {
		CredentialsProvider provider = new DefaultCredentialProvider(aliyunConfig.getAccesskey(),
				aliyunConfig.getSecret());
		ClientConfiguration conf = new ClientConfiguration();
		client = new OSSClient(ossConfig.getEndpoint(), provider, conf);
	}

	@Override
	public void upload(boolean publicBucket, String path, InputStream in) {
		client.putObject(publicBucket ? ossConfig.getPublicBucket() : ossConfig.getPrivateBucket(), path, in);
	}

	@Override
	public byte[] load(boolean publicBucket, String path) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			OSSObject object = client
					.getObject(publicBucket ? ossConfig.getPublicBucket() : ossConfig.getPrivateBucket(), path);
			InputStream in = object.getObjectContent();
			for (int n = 0; n != -1;) {
				n = in.read(buf);
				if (n > -1) {
					out.write(buf, 0, n);
				}
			}
			in.close();
			return out.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(boolean publicBucket, String path) {
		client.deleteObject(publicBucket ? ossConfig.getPublicBucket() : ossConfig.getPrivateBucket(), path);
	}

	@Override
	public List<String> list(boolean publicBucket, String path) {
		ObjectListing listing = client
				.listObjects(publicBucket ? ossConfig.getPublicBucket() : ossConfig.getPrivateBucket(), path);
		List<String> list = new ArrayList<String>();
		if (listing != null) {
			List<OSSObjectSummary> summaries = listing.getObjectSummaries();
			if (summaries != null) {
				for (OSSObjectSummary summary : summaries) {
					list.add(summary.getKey());
				}
			}
		}
		return list;
	}

}