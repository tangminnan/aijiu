package com.aijiu.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 收藏表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 15:30:13
 */
public class MyShoucangDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//帖子id
	private String leaveId;
	//用户id
	private String userId;
	//创建时间
	private Date createTime;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	//用户姓名
	private String userName;
	//帖子的内容
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
	 * 设置：帖子id
	 */
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	/**
	 * 获取：帖子id
	 */
	public String getLeaveId() {
		return leaveId;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
