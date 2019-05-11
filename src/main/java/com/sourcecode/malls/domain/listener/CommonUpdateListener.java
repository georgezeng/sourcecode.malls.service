package com.sourcecode.malls.domain.listener;


import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.sourcecode.malls.context.UserContext;
import com.sourcecode.malls.domain.base.BaseEntity;
import com.sourcecode.malls.domain.system.setting.User;
import com.sourcecode.malls.util.DateUtil;

public class CommonUpdateListener {
	@PrePersist
	public void PrePersist(Object object) {
		BaseEntity entity = (BaseEntity) object;
		User currentUser = UserContext.get();

		entity.setCreateBy(currentUser.getUsername());
		entity.setUpdateBy(currentUser.getUsername());

		Date now = DateUtil.now();
		entity.setCreateTime(now);
		entity.setUpdateTime(now);
	}

	@PreUpdate
	public void preUpdate(Object object) {
		BaseEntity entity = (BaseEntity) object;
		User currentUser = UserContext.get();
		entity.setUpdateBy(currentUser.getUsername());
		entity.setUpdateTime(DateUtil.now());
	}
}
