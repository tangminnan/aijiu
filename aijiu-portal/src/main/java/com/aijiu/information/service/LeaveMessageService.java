package com.aijiu.information.service;

import com.aijiu.information.domain.LeaveMessageDO;

import java.util.List;
import java.util.Map;

/**
 * 发帖记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-18 15:15:55
 */
public interface LeaveMessageService {
	
	LeaveMessageDO get(Long id);
	
	List<LeaveMessageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeaveMessageDO leaveMessage);
	
	int update(LeaveMessageDO leaveMessage);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);


}
