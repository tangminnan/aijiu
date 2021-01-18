package com.aijiu.information.service.impl;

import com.aijiu.information.dao.MyShoucangDao;
import com.aijiu.information.domain.MyShoucangDO;
import com.aijiu.information.service.MyShoucangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MyShoucangServiceImpl implements MyShoucangService {
	@Autowired
	private MyShoucangDao myShoucangDao;
	@Override
	public List<MyShoucangDO> list(Map<String, Object> map){
		return myShoucangDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return myShoucangDao.count(map);
	}
	
	@Override
	public int save(MyShoucangDO myShoucang){
		return myShoucangDao.save(myShoucang);
	}
	
	@Override
	public int update(MyShoucangDO myShoucang){
		return myShoucangDao.update(myShoucang);
	}
	
	@Override
	public int remove(Long id){
		return myShoucangDao.remove(id);
	}

	@Override
	public List<String> countHeaders(Long id) {
		return myShoucangDao.countHeaders(id);
	}

	@Override
	public List<MyShoucangDO> getShouCangLeaveMessage(Long id) {
		return myShoucangDao.getShouCangLeaveMessage(id);
	}
	
}
