package com.aijiu.information.dao;


import com.aijiu.information.domain.MyDeviceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 我的设备表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 16:47:11
 */
@Mapper
public interface MyDeviceDao {

	MyDeviceDO get(Long id);
	
	List<MyDeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyDeviceDO myDevice);
	
	int update(MyDeviceDO myDevice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
