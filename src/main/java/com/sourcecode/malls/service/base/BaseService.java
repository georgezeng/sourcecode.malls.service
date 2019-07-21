package com.sourcecode.malls.service.base;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public interface BaseService {

	default String generateId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date()) + new DecimalFormat("0000").format(new Random().nextInt(9999));
	}

}
