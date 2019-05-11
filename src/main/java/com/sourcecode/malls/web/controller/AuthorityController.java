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
import com.sourcecode.malls.domain.system.setting.Authority;
import com.sourcecode.malls.dto.base.KeyDTO;
import com.sourcecode.malls.dto.base.ResultBean;
import com.sourcecode.malls.dto.base.SimpleQueryDTO;
import com.sourcecode.malls.dto.query.PageResult;
import com.sourcecode.malls.dto.query.QueryInfo;
import com.sourcecode.malls.dto.system.AuthorityDTO;
import com.sourcecode.malls.repository.jpa.impl.system.AuthorityRepository;
import com.sourcecode.malls.service.impl.AuthorityService;
import com.sourcecode.malls.util.AssertUtil;

@RestController
@RequestMapping(value = "/authority")
public class AuthorityController {

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private AuthorityRepository authorityRepository;

	@RequestMapping(value = "/list")
	public ResultBean<PageResult<AuthorityDTO>> list(@RequestBody QueryInfo<SimpleQueryDTO> queryInfo) {
		Page<Authority> pageResult = authorityService.findAll(queryInfo);
		PageResult<AuthorityDTO> dtoResult = new PageResult<>(pageResult.getContent().stream().map(data -> data.asDTO()).collect(Collectors.toList()),
				pageResult.getTotalElements());
		return new ResultBean<>(dtoResult);
	}

	@RequestMapping(value = "/save")
	public ResultBean<Void> save(@RequestBody AuthorityDTO dto) {
		Authority data = null;
		if (dto.getId() != null) {
			Optional<Authority> dataOp = authorityService.findById(dto.getId());
			AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
			data = dataOp.get();
			AssertUtil.assertTrue(!authorityService.isSuperAdmin(data), ExceptionMessageConstant.CAN_NOT_MODIFY_ADMIN);
			BeanUtils.copyProperties(dto, data, "id");
		} else {
			Optional<Authority> oldDataOp = authorityRepository.findByCode(dto.getCode());
			AssertUtil.assertTrue(!oldDataOp.isPresent(), "编号已存在");
			data = dto.asEntity();
		}
		authorityService.save(data);
		return new ResultBean<>();
	}

	@RequestMapping(value = "/load/params/{id}")
	public ResultBean<AuthorityDTO> load(@PathVariable Long id) {
		Optional<Authority> dataOp = authorityService.findById(id);
		AssertUtil.assertTrue(dataOp.isPresent(), ExceptionMessageConstant.NO_SUCH_RECORD);
		return new ResultBean<>(dataOp.get().asDTO());
	}

	@RequestMapping(value = "/delete")
	public ResultBean<Void> delete(@RequestBody KeyDTO<Long> keys) {
		AssertUtil.assertTrue(!CollectionUtils.isEmpty(keys.getIds()), ExceptionMessageConstant.SELECT_AT_LEAST_ONE_TO_DELETE);
		authorityService.deleteByKeys(keys.getIds());
		return new ResultBean<>();
	}
}
