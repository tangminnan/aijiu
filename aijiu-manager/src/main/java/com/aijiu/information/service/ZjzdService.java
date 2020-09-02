package com.aijiu.information.service;

import com.aijiu.information.domain.ZjzdDO;

import java.util.List;
import java.util.Map;

/**
 * 症状指导
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-02 14:51:01
 */
public interface ZjzdService {
	
	ZjzdDO get(Long id);
	
	List<ZjzdDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ZjzdDO zjzd);
	
	int update(ZjzdDO zjzd);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
