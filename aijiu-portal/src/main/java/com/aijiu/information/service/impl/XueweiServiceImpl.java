package com.aijiu.information.service.impl;

import com.aijiu.information.controller.AijiuController;
import com.aijiu.information.dao.XueweiDao;
import com.aijiu.information.domain.XueweiDO;
import com.aijiu.information.service.XueweiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class XueweiServiceImpl implements XueweiService {
	@Autowired
	private XueweiDao xueweiDao;
	
	@Override
	public XueweiDO get(Long id){
		return xueweiDao.get(id);
	}
	
	@Override
	public List<XueweiDO> list(Map<String, Object> map){
		return xueweiDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return xueweiDao.count(map);
	}
	
	@Override
	public int save(XueweiDO xuewei){
		return xueweiDao.save(xuewei);
	}
	
	@Override
	public int update(XueweiDO xuewei){
		return xueweiDao.update(xuewei);
	}
	
	@Override
	public int remove(Long id){
		return xueweiDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return xueweiDao.batchRemove(ids);
	}

	@Override
	public List<XueweiDO> getXueweiByBuWei(String xueweiBuwei) {
		return xueweiDao.getXueweiByBuWei(xueweiBuwei);
	}

	@Override
	public List<AijiuController.KeySearch> getXueweiByKey(String key) {
		return xueweiDao.getXueweiByKey(key);
	}

}
