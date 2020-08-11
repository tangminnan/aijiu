package com.aijiu.owneruser.service;

import java.util.List;
import java.util.Map;

import com.aijiu.owneruser.domain.OwnerUserDO;


public interface OwnerUserService {
	OwnerUserDO get(Long id);
	
	OwnerUserDO getbyname(String username);

	List<OwnerUserDO> list(Map<String, Object> map);


	
	int save(OwnerUserDO user);

	int update(OwnerUserDO user);



	boolean exit(Map<String, Object> params);

}
