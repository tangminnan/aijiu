package com.aijiu.information.service.impl;

import com.aijiu.information.dao.ScoresDao;
import com.aijiu.information.domain.ScoresDO;
import com.aijiu.information.service.ScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ScoresServiceImpl implements ScoresService {
	@Autowired
	private ScoresDao scoresDao;
	
	@Override
	public ScoresDO get(Long id){
		return scoresDao.get(id);
	}
	
	@Override
	public List<ScoresDO> list(Map<String, Object> map){
		return scoresDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoresDao.count(map);
	}
	
	@Override
	public int save(ScoresDO scores){
		return scoresDao.save(scores);
	}
	
	@Override
	public int update(ScoresDO scores){
		return scoresDao.update(scores);
	}
	
	@Override
	public int remove(Long id){
		return scoresDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoresDao.batchRemove(ids);
	}

	@Override
	public Integer total(Long userId) {
		return scoresDao.total(userId);
	}

}
