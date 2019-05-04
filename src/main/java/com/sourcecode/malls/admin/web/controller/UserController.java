package com.sourcecode.malls.admin.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.sourcecode.malls.admin.constants.ExceptionMessageConstant;
import com.sourcecode.malls.admin.domain.system.setting.User;
import com.sourcecode.malls.admin.dto.base.KeyDTO;
import com.sourcecode.malls.admin.dto.base.ResultBean;
import com.sourcecode.malls.admin.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.admin.dto.query.PageResult;
import com.sourcecode.malls.admin.dto.query.QueryInfo;
import com.sourcecode.malls.admin.dto.system.UserDTO;
import com.sourcecode.malls.admin.service.impl.RoleService;
import com.sourcecode.malls.admin.service.impl.UserService;
import com.sourcecode.malls.admin.util.AssertUtil;
import com.sourcecode.malls.admin.util.RegexpUtil;
import com.sourcecode.malls.admin.web.controller.base.BaseController;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PasswordEncoder pwdEncoder;

	@Value("${user.type.name}")
	private String userDir;

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
			AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
			data = dataOp.get();
			BeanUtils.copyProperties(dto, data, "id", "username", "password", "roles");
		} else {
			data = dto.asEntity();
		}
		if (!StringUtils.isEmpty(dto.getPassword())) {
			AssertUtil.assertTrue(RegexpUtil.matchPassword(dto.getPassword()), ExceptionMessageConstant.PASSWORD_SHOULD_BE_THE_RULE);
			AssertUtil.assertTrue(dto.getPassword().equals(dto.getConfirmPassword()), ExceptionMessageConstant.TWO_TIMES_PASSWORD_NOT_EQUALS);
			data.setPassword(pwdEncoder.encode(dto.getPassword()));
		}
		if (userService.isSuperAdmin(data)) {
			AssertUtil.assertTrue(data.isEnabled(), ExceptionMessageConstant.CAN_NOT_DISABLE_ADMIN);
		} else if (dto.getRoles() != null) {
			AssertUtil.assertTrue(!dto.getRoles().stream().anyMatch(role -> roleService.isSuperAdmin(role)),
					ExceptionMessageConstant.CAN_NOT_MODIFY_ADMIN);
		}
		List<String> tmpPaths = new ArrayList<>();
		List<String> newPaths = new ArrayList<>();
		if (dto.getAvatar() != null && dto.getAvatar().startsWith("temp")) {
			String newPath = userDir + "/" + data.getId() + "/avatar.png";
			newPaths.add(newPath);
			tmpPaths.add(dto.getAvatar());
			data.setAvatar(newPath);
		}
		userService.relateToRoles(data, dto.getRoles());
		transfer(false, tmpPaths, newPaths);
		return new ResultBean<>();
	}

	@RequestMapping(value = "/updateStatus/params/{status}")
	public ResultBean<Void> updateStatus(@RequestBody KeyDTO<Long> keys, @PathVariable Boolean status) {
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(keys.getIds()), ExceptionMessageConstant.SELECT_AT_LEAST_ONE_TO_UPDATE);
		for (Long id : keys.getIds()) {
			Optional<User> userOp = userService.findById(id);
			if (userOp.isPresent()) {
				User user = userOp.get();
				user.setEnabled(status);
				userService.save(user);
			}
		}
		return new ResultBean<>();
	}

	@RequestMapping(value = "/load/params/{id}")
	public ResultBean<UserDTO> load(@PathVariable Long id) {
		Optional<User> dataOp = userService.findById(id);
		AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
		return new ResultBean<>(dataOp.get().asDTO(true));
	}

	@RequestMapping(value = "/delete")
	public ResultBean<Void> delete(@RequestBody KeyDTO<Long> keys) {
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(keys.getIds()), ExceptionMessageConstant.SELECT_AT_LEAST_ONE_TO_DELETE);
		for (Long id : keys.getIds()) {
			Optional<User> userOp = userService.findById(id);
			if (userOp.isPresent()) {
				User user = userOp.get();
				AssertUtil.assertTrue(!userService.isSuperAdmin(user), ExceptionMessageConstant.CAN_NOT_DELETE_ADMIN);
				userService.delete(user);
			}
		}
		return new ResultBean<>();
	}

	@RequestMapping(value = "/file/upload/params/{id}")
	public ResultBean<String> upload(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws IOException {
		return upload(file, userDir, null, id, false);
	}

	@RequestMapping(value = "/file/load/params/{id}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public Resource load(@RequestParam String filePath, @PathVariable Long id) {
		return load(id, filePath, userDir, false);
	}

}
