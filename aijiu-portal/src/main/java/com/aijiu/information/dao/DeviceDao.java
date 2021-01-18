package com.aijiu.information.dao;

import com.aijiu.information.domain.DeviceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
}
