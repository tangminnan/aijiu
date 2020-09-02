package com.aijiu.information.dao;

import com.aijiu.information.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-17 14:43:46
 */
@Mapper
public interface UserDODao {

	UserDO get(Long id);
	
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
