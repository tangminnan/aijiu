package com.aijiu.information.service;


import com.aijiu.information.domain.MyDeviceDO;

import java.util.List;
import java.util.Map;

/**
 * 我的设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 16:47:11
 */
public interface MyDeviceService {
	
	MyDeviceDO get(Long id);
	
	List<MyDeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyDeviceDO myDevice);
	
	int update(MyDeviceDO myDevice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
