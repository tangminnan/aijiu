package com.aijiu.information.service.impl;

import com.aijiu.information.dao.MyDeviceDao;
import com.aijiu.information.domain.MyDeviceDO;
import com.aijiu.information.service.MyDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class MyDeviceServiceImpl implements MyDeviceService {
	@Autowired
	private MyDeviceDao myDeviceDao;
	
	@Override
	public MyDeviceDO get(Long id){
		return myDeviceDao.get(id);
	}
	
	@Override
	public List<MyDeviceDO> list(Map<String, Object> map){
		return myDeviceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return myDeviceDao.count(map);
	}
	
	@Override
	public int save(MyDeviceDO myDevice){
		return myDeviceDao.save(myDevice);
	}
	
	@Override
	public int update(MyDeviceDO myDevice){
		return myDeviceDao.update(myDevice);
	}
	
	@Override
	public int remove(Long id){
		return myDeviceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return myDeviceDao.batchRemove(ids);
	}
	
}
