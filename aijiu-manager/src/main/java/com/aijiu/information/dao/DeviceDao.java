package com.aijiu.information.dao;

import com.aijiu.information.domain.DeviceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 设备表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-19 10:57:05
 */
@Mapper
public interface DeviceDao {

	DeviceDO get(Integer id);
	
	List<DeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeviceDO device);
	
	int update(DeviceDO device);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
