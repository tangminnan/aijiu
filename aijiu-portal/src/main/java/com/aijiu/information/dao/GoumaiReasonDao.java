package com.aijiu.information.dao;


import java.util.List;
import java.util.Map;

import com.aijiu.information.domain.GoumaiReasonDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购买原因
 * @author wjl
 * @email bushuo@163.com
 * @date 2021-01-19 11:40:11
 */
@Mapper
public interface GoumaiReasonDao {

	int save(GoumaiReasonDO goumaiReason);
}
