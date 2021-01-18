package com.aijiu.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-09 10:21:12
 */
public class AttentionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//受关注人头像
	private String heardUrl;
	//受关注人姓名
	private String name;
	//受关注人id
	private Long attentionId;
	//添加关注时间
	private Date attentionTime;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	private String userName;//用户姓名
	private Long userId;//用户id


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
	 * 设置：受关注人头像
	 */
	public void setHeardUrl(String heardUrl) {
		this.heardUrl = heardUrl;
	}
	/**
	 * 获取：受关注人头像
	 */
	public String getHeardUrl() {
		return heardUrl;
	}
	/**
	 * 设置：受关注人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：受关注人姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：添加关注时间
	 */
	public void setAttentionTime(Date attentionTime) {
		this.attentionTime = attentionTime;
	}
	/**
	 * 获取：添加关注时间
	 */
	public Date getAttentionTime() {
		return attentionTime;
	}
	/**
	 * 设置：删除标志  0 未删除   1已删除
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：删除标志  0 未删除   1已删除
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public Long getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(Long attentionId) {
		this.attentionId = attentionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
