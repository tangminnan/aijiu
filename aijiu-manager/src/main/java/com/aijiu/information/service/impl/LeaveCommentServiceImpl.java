package com.aijiu.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.aijiu.information.dao.LeaveCommentDao;
import com.aijiu.information.domain.LeaveCommentDO;
import com.aijiu.information.service.LeaveCommentService;



@Service
public class LeaveCommentServiceImpl implements LeaveCommentService {
	@Autowired
	private LeaveCommentDao leaveCommentDao;
	
	@Override
	public LeaveCommentDO get(Long id){
		return leaveCommentDao.get(id);
	}
	
	@Override
	public List<LeaveCommentDO> list(Map<String, Object> map){
		return leaveCommentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return leaveCommentDao.count(map);
	}
	
	@Override
	public int save(LeaveCommentDO leaveComment){
		return leaveCommentDao.save(leaveComment);
	}
	
	@Override
	public int update(LeaveCommentDO leaveComment){
		return leaveCommentDao.update(leaveComment);
	}
	
	@Override
	public int remove(Long id){
		return leaveCommentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return leaveCommentDao.batchRemove(ids);
	}
	
}
