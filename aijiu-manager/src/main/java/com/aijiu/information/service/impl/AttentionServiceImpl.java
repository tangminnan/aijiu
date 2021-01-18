package com.aijiu.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.aijiu.information.dao.AttentionDao;
import com.aijiu.information.domain.AttentionDO;
import com.aijiu.information.service.AttentionService;



@Service
public class AttentionServiceImpl implements AttentionService {
	@Autowired
	private AttentionDao attentionDao;
	
	@Override
	public AttentionDO get(Long id){
		return attentionDao.get(id);
	}
	
	@Override
	public List<AttentionDO> list(Map<String, Object> map){
		return attentionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return attentionDao.count(map);
	}
	
	@Override
	public int save(AttentionDO attention){
		return attentionDao.save(attention);
	}
	
	@Override
	public int update(AttentionDO attention){
		return attentionDao.update(attention);
	}
	
	@Override
	public int remove(Long id){
		return attentionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return attentionDao.batchRemove(ids);
	}
	
}
