package com.aijiu.information.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 积分表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-01 10:34:25
 */
public class ScoresDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//我的id
	private Integer userId;
	//我的名字
	private String userName;
	//获取时间
	private Date addTime;
	//所获积分
	private Integer scores;
	//获取方式 FATIE_HUOQU=发帖获取   GOUMAI=购买   QIANDAO_YUYUE=签到   FABIAO_PINGJIA=发表评价
	private String type;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：我的id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：我的id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：我的名字
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：我的名字
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：获取时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：获取时间
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：所获积分
	 */
	public void setScores(Integer scores) {
		this.scores = scores;
	}
	/**
	 * 获取：所获积分
	 */
	public Integer getScores() {
		return scores;
	}
	/**
	 * 设置：获取方式 FATIE_HUOQU=发帖获取   GOUMAI=购买   QIANDAO_YUYUE=签到   FABIAO_PINGJIA=发表评价
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：获取方式 FATIE_HUOQU=发帖获取   GOUMAI=购买   QIANDAO_YUYUE=签到   FABIAO_PINGJIA=发表评价
	 */
	public String getType() {
		return type;
	}
}
