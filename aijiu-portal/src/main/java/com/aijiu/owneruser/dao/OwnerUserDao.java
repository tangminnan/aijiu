package com.aijiu.owneruser.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.aijiu.owneruser.domain.OwnerUserDO;

/**
 * 
 * @author tmn
 */
@Mapper
public interface OwnerUserDao {

	OwnerUserDO get(Long userId);
	
	OwnerUserDO getbyname(String username);
	
	List<OwnerUserDO> list(Map<String,Object> map);
	
	int save(OwnerUserDO user);
	

	
	int update(OwnerUserDO user);
	

	

}
