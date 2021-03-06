package com.aijiu.information.dao;

import com.aijiu.information.domain.LeaveCommentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 帖子评价表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-27 14:14:30
 */
@Mapper
public interface LeaveCommentDao {

	LeaveCommentDO get(Long id);
	
	List<LeaveCommentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LeaveCommentDO leaveComment);
	
	int update(LeaveCommentDO leaveComment);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
