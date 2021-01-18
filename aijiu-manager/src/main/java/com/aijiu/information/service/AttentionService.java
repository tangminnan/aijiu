package com.aijiu.information.service;

import com.aijiu.information.domain.AttentionDO;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-09 10:21:12
 */
public interface AttentionService {
	
	AttentionDO get(Long id);
	
	List<AttentionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttentionDO attention);
	
	int update(AttentionDO attention);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
