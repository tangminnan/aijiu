package com.aijiu.common.controller;

import org.springframework.stereotype.Controller;

import com.aijiu.common.utils.ShiroUtils;
import com.aijiu.owneruser.domain.OwnerUserDO;
import com.aijiu.system.domain.UserToken;

@Controller
public class BaseController {
	public OwnerUserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}