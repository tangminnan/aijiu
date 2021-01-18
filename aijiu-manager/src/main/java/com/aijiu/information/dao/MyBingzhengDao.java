package com.aijiu.information.dao;

import com.aijiu.information.domain.MyBingzhengDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 病症记录表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 17:07:20
 */
@Mapper
public interface MyBingzhengDao {

	MyBingzhengDO get(Long id);
	
	List<MyBingzhengDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyBingzhengDO myBingzheng);
	
	int update(MyBingzhengDO myBingzheng);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
