package com.aijiu.information.dao;


import java.util.List;
import java.util.Map;

import com.aijiu.information.domain.MyShoucangDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收藏表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 15:30:13
 */
@Mapper
public interface MyShoucangDao {

	MyShoucangDO get(Long id);
	
	List<MyShoucangDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyShoucangDO myShoucang);
	
	int update(MyShoucangDO myShoucang);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
