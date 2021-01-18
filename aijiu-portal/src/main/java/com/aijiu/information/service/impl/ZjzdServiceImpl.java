package com.aijiu.information.service.impl;

import com.aijiu.information.dao.ZjzdDao;
import com.aijiu.information.domain.ZjzdDO;
import com.aijiu.information.service.ZjzdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ZjzdServiceImpl implements ZjzdService {
	@Autowired
	private ZjzdDao zjzdDao;
	
	@Override
	public ZjzdDO get(Long id){
		return zjzdDao.get(id);
	}
	
	@Override
	public List<ZjzdDO> list(Map<String, Object> map){
		return zjzdDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return zjzdDao.count(map);
	}
	
	@Override
	public int save(ZjzdDO zjzd){
		return zjzdDao.save(zjzd);
	}
	
	@Override
	public int update(ZjzdDO zjzd){
		return zjzdDao.update(zjzd);
	}
	
	@Override
	public int remove(Long id){
		return zjzdDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return zjzdDao.batchRemove(ids);
	}
	
}
