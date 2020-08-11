package com.aijiu.common.controller;

import org.springframework.stereotype.Controller;

import com.aijiu.common.utils.ShiroUtils;
import com.aijiu.system.domain.UserDO;
import com.aijiu.system.domain.UserToken;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}