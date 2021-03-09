package com.aijiu.information.service.impl;

import com.aijiu.information.dao.GoumaiReasonDao;
import com.aijiu.information.domain.GoumaiReasonDO;
import com.aijiu.information.service.GoumaiReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GoumaiReasonServiceImpl implements GoumaiReasonService {
	@Autowired
	private GoumaiReasonDao goumaiReasonDao;

	@Override
	public int save(GoumaiReasonDO goumaiReason){
		return goumaiReasonDao.save(goumaiReason);
	}

	
}
