package com.aijiu.information.service;


import com.aijiu.information.domain.MyShoucangDO;

import java.util.List;
import java.util.Map;

/**
 * 收藏表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 15:30:13
 */
public interface MyShoucangService {
	
	MyShoucangDO get(Long id);
	
	List<MyShoucangDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyShoucangDO myShoucang);
	
	int update(MyShoucangDO myShoucang);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
