package com.aijiu.information.dao;

import com.aijiu.information.domain.XueweiDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 穴位详情表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-02 11:16:43
 */
@Mapper
public interface XueweiDao {

	XueweiDO get(Long id);
	
	List<XueweiDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(XueweiDO xuewei);
	
	int update(XueweiDO xuewei);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
