package com.aijiu.information.service;

import com.aijiu.information.domain.MyBingzhengDO;

import java.util.List;
import java.util.Map;

/**
 * 病症记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 17:07:20
 */
public interface MyBingzhengService {
	
	MyBingzhengDO get(Long id);
	
	List<MyBingzhengDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyBingzhengDO myBingzheng);
	
	int update(MyBingzhengDO myBingzheng);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
