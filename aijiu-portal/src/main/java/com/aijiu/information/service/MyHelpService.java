package com.aijiu.information.service;


import com.aijiu.information.domain.MyHelpDO;

import java.util.List;
import java.util.Map;

/**
 * 帮助与反馈
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-30 09:49:12
 */
public interface MyHelpService {
	
	MyHelpDO get(Long id);
	
	List<MyHelpDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyHelpDO myHelp);
	
	int update(MyHelpDO myHelp);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
