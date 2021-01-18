package com.aijiu.information.service.impl;

import com.aijiu.information.dao.MyHelpDao;
import com.aijiu.information.domain.MyHelpDO;
import com.aijiu.information.service.MyHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class MyHelpServiceImpl implements MyHelpService {
	@Autowired
	private MyHelpDao myHelpDao;
	
	@Override
	public MyHelpDO get(Long id){
		return myHelpDao.get(id);
	}
	
	@Override
	public List<MyHelpDO> list(Map<String, Object> map){
		return myHelpDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return myHelpDao.count(map);
	}
	
	@Override
	public int save(MyHelpDO myHelp){
		return myHelpDao.save(myHelp);
	}
	
	@Override
	public int update(MyHelpDO myHelp){
		return myHelpDao.update(myHelp);
	}
	
	@Override
	public int remove(Long id){
		return myHelpDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return myHelpDao.batchRemove(ids);
	}
	
}
