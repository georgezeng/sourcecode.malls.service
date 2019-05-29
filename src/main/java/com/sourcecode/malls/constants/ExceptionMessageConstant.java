package com.sourcecode.malls.constants;

public interface ExceptionMessageConstant {
	String NO_SUCH_RECORD = "记录不存在";
	String CAN_NOT_DELETE_ADMIN = "不能删除超级管理员";
	String CAN_NOT_DISABLE_ADMIN = "不能禁用超级管理员";
	String CAN_NOT_MODIFY_ADMIN = "不能修改超级管理员";
	String SELECT_AT_LEAST_ONE_TO_DELETE = "必须选择至少一条记录进行删除";
	String SELECT_AT_LEAST_ONE_TO_UPDATE = "必须选择至少一条记录进行更新";
	String SELECT_AT_LEAST_ONE_TO_DISABLE = "必须选择至少一条记录进行禁用";
	String TWO_TIMES_PASSWORD_NOT_EQUALS = "确认密码与密码不一致";
	String PASSWORD_SHOULD_BE_THE_RULE = "密码必须数字+字母（区分大小写）并且不少于8位";
	String MOBILE_ACCOUNT_SHOULD_BE_THE_RULE = "账号长度必须是11位且全部是数字";
	String HAS_VERIFIED = "已经审核过";
	String FAILED_REASON_CAN_NOT_BE_EMPTY = "失败原因不能为空";
	String VERIFY_CODE_INVALID = "验证码无效";
	String OLD_PASSWORD_IS_INVALID = "旧密码有误";
}
