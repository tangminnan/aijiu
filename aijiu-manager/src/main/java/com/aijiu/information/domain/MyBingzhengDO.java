package com.aijiu.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 病症记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 17:07:20
 */
public class MyBingzhengDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//用户id
	private Long userId;
	//用户姓名
	private String userName;
	//发表时间
	private Date publishTime;
	//发帖内容
	private String text;

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
	 * 设置：用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：发表时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发表时间
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * 设置：发帖内容
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * 获取：发帖内容
	 */
	public String getText() {
		return text;
	}
}
