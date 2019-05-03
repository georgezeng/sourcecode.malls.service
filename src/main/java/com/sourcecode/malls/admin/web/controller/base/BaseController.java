package com.sourcecode.malls.admin.web.controller.base;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.sourcecode.malls.admin.context.UserContext;
import com.sourcecode.malls.admin.domain.merchant.MerchantShopApplication;
import com.sourcecode.malls.admin.domain.system.setting.User;
import com.sourcecode.malls.admin.dto.base.ResultBean;
import com.sourcecode.malls.admin.enums.VerificationStatus;
import com.sourcecode.malls.admin.repository.jpa.impl.merchant.MerchantShopApplicationRepository;
import com.sourcecode.malls.admin.service.FileOnlineSystemService;
import com.sourcecode.malls.admin.util.AssertUtil;

public abstract class BaseController {

	@Autowired
	protected FileOnlineSystemService fileService;

	@Autowired
	protected MerchantShopApplicationRepository applicationRepository;

	protected void transfer(boolean toPublic, List<String> tmpPaths, List<String> newPaths) {
		for (int i = 0; i < newPaths.size(); i++) {
			String newPath = newPaths.get(i);
			String tmpPath = tmpPaths.get(i);
			byte[] buf = fileService.load(false, tmpPath);
			fileService.upload(toPublic, newPath, new ByteArrayInputStream(buf));
		}
	}

	protected ResultBean<String> upload(MultipartFile file, String dir, Long id, Long userId, boolean toPublic) throws IOException {
		String extend = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String idDir = id != null ? id + "/" : "";
		String filePath = "temp/" + dir + "/" + userId + "/" + idDir + System.nanoTime() + extend;
		fileService.upload(toPublic, filePath, file.getInputStream());
		return new ResultBean<>(filePath);
	}

	protected Resource load(Long userId, String filePath, String dir, boolean isPublic) {
		String dest = dir + "/" + userId + "/";
		AssertUtil.assertTrue(filePath.startsWith("temp/" + dest) || filePath.startsWith(dest), "文件路径不合法: " + filePath);
		if (filePath.startsWith("temp")) {
			return new ByteArrayResource(fileService.load(false, filePath));
		} else {
			return new ByteArrayResource(fileService.load(isPublic, filePath));
		}
	}

	protected void checkIfApplicationPassed(String type) {
		Optional<MerchantShopApplication> applicationOp = applicationRepository.findByMerchantId(getRelatedCurrentUser().getId());
		AssertUtil.assertTrue(applicationOp.isPresent() && VerificationStatus.Passed.equals(applicationOp.get().getStatus()),
				"必须先通过店铺申请才能编辑商品" + type);
	}

	protected User getRelatedCurrentUser() {
		User user = UserContext.get();
		if (user.getParent() != null) {
			user = user.getParent();
		}
		return user;
	}

}
