package com.sourcecode.malls.admin.service;

import java.io.InputStream;

public interface FileOnlineSystemService {

	void upload(boolean publicBucket, String path, InputStream in);

	byte[] load(boolean publicBucket, String path);

	void delete(boolean publicBucket, String path);

}