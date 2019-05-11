package com.sourcecode.malls.web.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourcecode.malls.constants.ExceptionMessageConstant;
import com.sourcecode.malls.domain.system.setting.Role;
import com.sourcecode.malls.dto.base.KeyDTO;
import com.sourcecode.malls.dto.base.ResultBean;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.dto.query.PageResult;
import com.sourcecode.malls.dto.query.QueryInfo;
import com.sourcecode.malls.dto.system.RoleDTO;
import com.sourcecode.malls.service.impl.RoleService;
import com.sourcecode.malls.util.AssertUtil;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/list")
	public ResultBean<PageResult<RoleDTO>> list(@RequestBody QueryInfo<SimpleQueryDTO> queryInfo) {
		Page<Role> pageResult = roleService.findAll(queryInfo);
		PageResult<RoleDTO> dtoResult = new PageResult<>(pageResult.getContent().stream().map(data -> data.asDTO()).collect(Collectors.toList()),
				pageResult.getTotalElements());
		return new ResultBean<>(dtoResult);
	}

	@RequestMapping(value = "/save")
	public ResultBean<Void> save(@RequestBody RoleDTO dto) {
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(dto.getAuthorities()), "请关联至少一条权限记录");
		Role data = null;
		if (dto.getId() != null) {
			Optional<Role> dataOp = roleService.findById(dto.getId());
			AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
			data = dataOp.get();
			BeanUtils.copyProperties(dto, data, "id");
		} else {
			data = dto.asEntity();
		}
		roleService.relateToUsersAndAuthorities(data, dto.getUsers(), dto.getAuthorities());
		return new ResultBean<>();
	}

	@RequestMapping(value = "/load/params/{id}")
	public ResultBean<RoleDTO> load(@PathVariable("id") Long id) {
		Optional<Role> dataOp = roleService.findById(id);
		AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
		return new ResultBean<>(dataOp.get().asDTO(true));
	}

	@RequestMapping(value = "/delete")
	public ResultBean<Void> delete(@RequestBody KeyDTO<Long> keys) {
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(keys.getIds()), ExceptionMessageConstant.SELECT_AT_LEAST_ONE_TO_DELETE);
		for (Long id : keys.getIds()) {
			Optional<Role> roleOp = roleService.findById(id);
			if (roleOp.isPresent()) {
				Role role = roleOp.get();
				AssertUtil.assertTrue(CollectionUtils.isEmpty(role.getUsers()), "该角色存在用户数据，不能删除该角色");
				roleService.delete(role);
			}
		}
		return new ResultBean<>();
	}
}
