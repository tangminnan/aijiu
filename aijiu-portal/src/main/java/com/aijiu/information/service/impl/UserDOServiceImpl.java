package com.aijiu.information.service.impl;

import com.aijiu.information.dao.UserDao;
import com.aijiu.information.domain.UserDO;
import com.aijiu.information.service.UserDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserDOServiceImpl implements UserDOService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDO get(Long id){
		return userDao.get(id);
	}
	
	@Override
	public List<UserDO> list(Map<String, Object> map){
		return userDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserDO user){
		return userDao.save(user);
	}
	
	@Override
	public int update(UserDO user){
		return userDao.update(user);
	}
	
	@Override
	public int remove(Long id){
		return userDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userDao.batchRemove(ids);
	}

	@Override
	public List<UserDO> listByShouCangLeaveId(Integer leaveId) {
		return userDao.listByShouCangLeaveId(leaveId);
	}

	@Override
	public UserDO getUserDO(String openId) {
		return userDao.getUserDO(openId);
	}

	@Override
	public UserDO getUserDOByOpenId(String openId) {
		return userDao.getUserDOByOpenId(openId);
	}

}
