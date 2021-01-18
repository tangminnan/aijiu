package com.aijiu.information.dao;

import com.aijiu.information.domain.AttentionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-09 10:21:12
 */
@Mapper
public interface AttentionDao {

	AttentionDO get(Long id);
	
	List<AttentionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AttentionDO attention);
	
	int update(AttentionDO attention);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
