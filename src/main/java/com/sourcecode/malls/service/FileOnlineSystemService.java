package com.sourcecode.malls.service;

import java.io.InputStream;
import java.util.List;

public interface FileOnlineSystemService {

	void upload(boolean publicBucket, String path, InputStream in);

	byte[] load(boolean publicBucket, String path);
	
	List<String> list(boolean publicBucket, String path);

	void delete(boolean publicBucket, String path);

}