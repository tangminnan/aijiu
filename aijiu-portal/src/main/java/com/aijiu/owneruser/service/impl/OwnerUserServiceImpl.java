package com.aijiu.owneruser.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aijiu.common.config.BootdoConfig;
import com.aijiu.common.service.FileService;
import com.aijiu.owneruser.dao.OwnerUserDao;
import com.aijiu.owneruser.domain.OwnerUserDO;
import com.aijiu.owneruser.service.OwnerUserService;



@Transactional
@Service
public class OwnerUserServiceImpl implements OwnerUserService {
	@Autowired
	OwnerUserDao ownerUserMapper;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
/*	private static final Logger logger = LoggerFactory.getLogger(UserService.class);*/

	@Override
	public OwnerUserDO get(Long id) {
		OwnerUserDO user = ownerUserMapper.get(id);
		return user;
	}
	
	@Override
	public OwnerUserDO getbyname(String username){
		
		OwnerUserDO user = ownerUserMapper.getbyname(username);
		return user;
	}

	@Override
	public List<OwnerUserDO> list(Map<String, Object> map) {
		return ownerUserMapper.list(map);
	}

	@Override
	public int save(OwnerUserDO user){
		return ownerUserMapper.save(user);
	}
	


	@Override
	public int update(OwnerUserDO user) {
		int r = ownerUserMapper.update(user);
		
		return r;
	}
	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = ownerUserMapper.list(params).size() > 0;
		return exit;
	}



}
