package com.aijiu.information.dao;


import com.aijiu.information.domain.MyShoucangDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 收藏表
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 15:30:13
 */
@Mapper
public interface MyShoucangDao {

	
	List<MyShoucangDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MyShoucangDO myShoucang);
	
	int update(MyShoucangDO myShoucang);
	
	int remove(Long id);


    List<String> countHeaders(Long id);
	List<MyShoucangDO> getShouCangLeaveMessage(Long id);
}
