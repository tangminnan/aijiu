package com.aijiu.information.service.impl;

import com.aijiu.information.dao.MyBingzhengDao;
import com.aijiu.information.domain.MyBingzhengDO;
import com.aijiu.information.service.MyBingzhengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MyBingzhengServiceImpl implements MyBingzhengService {
	@Autowired
	private MyBingzhengDao myBingzhengDao;
	
	@Override
	public MyBingzhengDO get(Long id){
		return myBingzhengDao.get(id);
	}
	
	@Override
	public List<MyBingzhengDO> list(Map<String, Object> map){
		return myBingzhengDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return myBingzhengDao.count(map);
	}
	
	@Override
	public int save(MyBingzhengDO myBingzheng){
		return myBingzhengDao.save(myBingzheng);
	}
	
	@Override
	public int update(MyBingzhengDO myBingzheng){
		return myBingzhengDao.update(myBingzheng);
	}
	
	@Override
	public int remove(Long id){
		return myBingzhengDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return myBingzhengDao.batchRemove(ids);
	}
	
}
