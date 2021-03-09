package com.aijiu.information.service.impl;

import com.aijiu.information.dao.LeaveMessageDao;
import com.aijiu.information.domain.LeaveMessageDO;
import com.aijiu.information.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {
	@Autowired
	private LeaveMessageDao leaveMessageDao;
	
	@Override
	public LeaveMessageDO get(Long id){
		return leaveMessageDao.get(id);
	}
	
	@Override
	public List<LeaveMessageDO> list(Map<String, Object> map){
		return leaveMessageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leaveMessageDao.count(map);
	}
	
	@Override
	public int save(LeaveMessageDO leaveMessage){
		return leaveMessageDao.save(leaveMessage);
	}
	
	@Override
	public int update(LeaveMessageDO leaveMessage){
		return leaveMessageDao.update(leaveMessage);
	}
	
	@Override
	public int remove(Long id){
		return leaveMessageDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return leaveMessageDao.batchRemove(ids);
	}



}
