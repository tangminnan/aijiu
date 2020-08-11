package com.aijiu.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aijiu.common.domain.LogDO;
import com.aijiu.common.domain.PageDO;
import com.aijiu.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
