package com.aijiu.information.dao;

import com.aijiu.information.controller.AijiuController;
import com.aijiu.information.domain.XueweiDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    List<XueweiDO> getXueweiByBuWei(String xueweiBuwei);

	List<AijiuController.KeySearch> getXueweiByKey(String key);
}
