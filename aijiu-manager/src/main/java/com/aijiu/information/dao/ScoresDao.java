package com.aijiu.information.dao;

import com.aijiu.information.domain.ScoresDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 积分表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-01 10:34:25
 */
@Mapper
public interface ScoresDao {

	ScoresDO get(Long id);
	
	List<ScoresDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoresDO scores);
	
	int update(ScoresDO scores);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
