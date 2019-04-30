package com.sourcecode.malls.admin.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.sourcecode.malls.admin.context.UserContext;
import com.sourcecode.malls.admin.domain.system.setting.User;
import com.sourcecode.malls.admin.dto.base.KeyDTO;
import com.sourcecode.malls.admin.dto.base.ResultBean;
import com.sourcecode.malls.admin.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.admin.dto.query.PageResult;
import com.sourcecode.malls.admin.dto.query.QueryInfo;
import com.sourcecode.malls.admin.dto.system.setting.UserDTO;
import com.sourcecode.malls.admin.service.FileOnlineSystemService;
import com.sourcecode.malls.admin.service.impl.RoleService;
import com.sourcecode.malls.admin.service.impl.UserService;
import com.sourcecode.malls.admin.util.AssertUtil;
import com.sourcecode.malls.admin.util.RegexpUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private FileOnlineSystemService fileService;

	@Value("${user.avatar}")
	private String avatar;

	@Value("${user.type.name}")
	private String userDir;

	@RequestMapping(value = "/current")
	public ResultBean<UserDTO> currentUser() {
		return new ResultBean<>(UserContext.get().asDTO());
	}

	@RequestMapping(value = "/list")
	public ResultBean<PageResult<UserDTO>> list(@RequestBody QueryInfo<SimpleQueryDTO> queryInfo) {
		Page<User> pageResult = userService.findAll(queryInfo);
		PageResult<UserDTO> dtoResult = new PageResult<>(pageResult.getContent().stream().map(data -> data.asDTO()).collect(Collectors.toList()),
				pageResult.getTotalElements());
		return new ResultBean<>(dtoResult);
	}

	@RequestMapping(value = "/save")
	public ResultBean<Void> save(@RequestBody UserDTO dto) {
		User data = null;
		if (dto.getId() != null) {
			Optional<User> dataOp = userService.findById(dto.getId());
			AssertUtil.assertTrue(dataOp.isPresent(), "记录不存在");
			data = dataOp.get();
			BeanUtils.copyProperties(dto, data, "id", "username", "password", "roles");
		} else {
			data = dto.asEntity();
		}
		if (!StringUtils.isEmpty(dto.getPassword())) {
			AssertUtil.assertTrue(RegexpUtil.matchPassword(dto.getPassword()), "密码必须数字+字母（区分大小写）并且不少于8位");
			AssertUtil.assertTrue(dto.getPassword().equals(dto.getConfirmPassword()), "确认密码与密码不一致");
			data.setPassword(pwdEncoder.encode(dto.getPassword()));
		}
		if (userService.isSuperAdmin(data)) {
			AssertUtil.assertTrue(data.isEnabled(), "不能禁用超级管理员");
		} else if (dto.getRoles() != null) {
			AssertUtil.assertTrue(!dto.getRoles().stream().anyMatch(role -> roleService.isSuperAdmin(role)), "不能设置超级管理员");
		}
		String newPath = null;
		String tempPath = data.getHeader();
		if (tempPath != null && tempPath.startsWith("temp")) {
			newPath = userDir + "/" + data.getId() + "/header.png";
			data.setHeader(newPath);
		}
		userService.relateToRoles(data, dto.getRoles());
		if (newPath != null) {
			byte[] buf = fileService.load(false, tempPath);
			fileService.upload(false, newPath, new ByteArrayInputStream(buf));
		}
		return new ResultBean<>();
	}

	@RequestMapping(value = "/updateStatus/params/{enabled}")
	public ResultBean<Void> updateStatus(@RequestBody KeyDTO<Long> keys, @PathVariable Boolean enabled) {
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(keys.getIds()), "必须选择至少一条记录进行状态更新");
		for (Long id : keys.getIds()) {
			Optional<User> userOp = userService.findById(id);
			if (userOp.isPresent()) {
				User user = userOp.get();
				user.setEnabled(enabled);
				userService.save(user);
			}
		}
		return new ResultBean<>();
	}

	@RequestMapping(value = "/one/params/{id}")
	public ResultBean<UserDTO> load(@PathVariable Long id) {
		Optional<User> dataOp = userService.findById(id);
		AssertUtil.assertTrue(dataOp.isPresent(), "查找不到相应的记录");
		return new ResultBean<>(dataOp.get().asDTO(true));
	}

	@RequestMapping(value = "/delete")
	public ResultBean<Void> delete(@RequestBody KeyDTO<Long> keys) {
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(keys.getIds()), "必须选择至少一条记录进行删除");
		for (Long id : keys.getIds()) {
			Optional<User> userOp = userService.findById(id);
			if (userOp.isPresent()) {
				User user = userOp.get();
				AssertUtil.assertTrue(!userService.isSuperAdmin(user), "不能删除超级管理员");
				userService.delete(user);
			}
		}
		return new ResultBean<>();
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

	@RequestMapping(value = "/current/authorities")
	public ResultBean<UserDTO> getCurrentUserAuthorities() {
		Optional<User> dataOp = userService.findById(UserContext.get().getId());
		AssertUtil.assertTrue(dataOp.isPresent(), "查找不到相应的记录");
		return new ResultBean<>(dataOp.get().asDTO(true));
	}

	@RequestMapping(value = "/header/upload")
	public ResultBean<String> uploadHeader(@RequestParam("file") MultipartFile file) throws IOException {
		String filePath = "temp/header/" + userDir + "/" + UserContext.get().getId() + "/" + System.nanoTime() + ".png";
		fileService.upload(false, filePath, file.getInputStream());
		return new ResultBean<>(filePath);
	}

	@RequestMapping(value = "/header/load/current")
	public Resource loadHeader() {
		return loadHeader(UserContext.get().getId());
	}

	@RequestMapping(value = "/header/load/params/{id}")
	public Resource loadHeader(@PathVariable Long id) {
		Optional<User> userOp = userService.findById(id);
		AssertUtil.assertTrue(userOp.isPresent(), "找不到用户");
		String header = userOp.get().getHeader();
		if (!StringUtils.isEmpty(header) && !header.equals(avatar)) {
			return new ByteArrayResource(fileService.load(false, header));
		} else {
			return new ByteArrayResource(fileService.load(true, avatar));
		}
	}

	@RequestMapping(value = "/header/preview/params/{id}")
	public Resource previewHeader(@RequestParam String filePath, @PathVariable Long id) {
		AssertUtil.assertTrue(filePath.startsWith("temp/header/" + userDir + "/" + id + "/") || filePath.equals(userDir + "/" + id + "/header.png"),
				"图片路径不合法");
		return new ByteArrayResource(fileService.load(false, filePath));
	}
}
