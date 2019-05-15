package com.sourcecode.malls.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.sourcecode.malls.context.UserContext;
import com.sourcecode.malls.domain.system.User;
import com.sourcecode.malls.dto.base.ResultBean;
import com.sourcecode.malls.dto.system.UserDTO;
import com.sourcecode.malls.service.impl.UserService;
import com.sourcecode.malls.util.AssertUtil;
import com.sourcecode.malls.util.RegexpUtil;
import com.sourcecode.malls.web.controller.base.BaseController;

@RestController
@RequestMapping(value = "/user/current")
public class UserCurrentController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder pwdEncoder;

	@Value("${default.avatar}")
	private String avatar;

	@Value("${user.type.name}")
	private String userDir;

	@RequestMapping(value = "/info")
	public ResultBean<UserDTO> currentUser() {
		return new ResultBean<>(UserContext.get().asDTO());
	}

	@RequestMapping(value = "/modifyPassword")
	public ResultBean<Void> modifyPassword(@RequestBody UserDTO dto) {
		AssertUtil.assertTrue(!StringUtils.isEmpty(dto.getOldPassword()), "旧密码不能为空");
		AssertUtil.assertTrue(!StringUtils.isEmpty(dto.getPassword()), "新密码不能为空");
		AssertUtil.assertTrue(!StringUtils.isEmpty(dto.getConfirmPassword()), "确认密码不能为空");
		AssertUtil.assertTrue(RegexpUtil.matchPassword(dto.getPassword()), "密码必须数字+字母（区分大小写）并且不少于8位");
		AssertUtil.assertTrue(dto.getPassword().equals(dto.getConfirmPassword()), "确认密码与密码不一致");
		User user = UserContext.get();
		AssertUtil.assertTrue(pwdEncoder.matches(dto.getOldPassword(), user.getPassword()), "旧密码有误");
		user.setPassword(pwdEncoder.encode(dto.getPassword()));
		userService.save(user);
		return new ResultBean<>();
	}

	@RequestMapping(value = "/avatar", produces = { MediaType.IMAGE_PNG_VALUE })
	public Resource loadAvatar() {
		String header = UserContext.get().getAvatar();
		if (!StringUtils.isEmpty(header) && !header.equals(avatar)) {
			return new ByteArrayResource(fileService.load(false, header));
		} else {
			return new ByteArrayResource(fileService.load(true, avatar));
		}
	}

	@RequestMapping(value = "/save")
	public ResultBean<Void> save(@RequestBody UserDTO dto) {
		User data = UserContext.get();
		if (!StringUtils.isEmpty(dto.getPassword())) {
			AssertUtil.assertTrue(RegexpUtil.matchPassword(dto.getPassword()), "密码必须数字+字母（区分大小写）并且不少于8位");
			AssertUtil.assertTrue(dto.getPassword().equals(dto.getConfirmPassword()), "确认密码与密码不一致");
			data.setPassword(pwdEncoder.encode(dto.getPassword()));
		}
		data.setEmail(dto.getEmail());
		List<String> tmpPaths = new ArrayList<>();
		List<String> newPaths = new ArrayList<>();
		if (dto.getAvatar() != null && dto.getAvatar().startsWith("temp")) {
			String newPath = userDir + "/" + data.getId() + "/avatar.png";
			newPaths.add(newPath);
			tmpPaths.add(dto.getAvatar());
			data.setAvatar(newPath);
		}
		userService.save(data);
		transfer(false, tmpPaths, newPaths);
		return new ResultBean<>();
	}

	@RequestMapping(value = "/file/upload")
	public ResultBean<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
		return upload(file, userDir, null, getRelatedCurrentUser().getId(), false);
	}

	@RequestMapping(value = "/file/load")
	public Resource load(@RequestParam String filePath) {
		return load(UserContext.get().getId(), filePath, userDir, false);
	}

}
