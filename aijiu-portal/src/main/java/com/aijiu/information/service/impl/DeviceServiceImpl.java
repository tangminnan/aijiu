package com.aijiu.information.service.impl;

import com.aijiu.information.dao.DeviceDao;
import com.aijiu.information.domain.DeviceDO;
import com.aijiu.information.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public DeviceDO get(Integer id){
		return deviceDao.get(id);
	}
	
	@Override
	public List<DeviceDO> list(Map<String, Object> map){
		return deviceDao.list(map);
	}

}
