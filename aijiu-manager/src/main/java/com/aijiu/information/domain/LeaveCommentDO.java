package com.aijiu.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 帖子评价表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-27 14:14:30
 */
public class LeaveCommentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//帖子id
	private Long leaveId;
	//评价时间
	private Date addTime;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	//评价人
	private Integer userId;
	//评价人姓名
	private String userName;
	//评价人头像
	private String headerUrl;
	//评价内容
	private String pingjia;
	//评价图片
	private String images;
	//0=可见  1=不可见
	private Integer isEnable;
	//类型 0=好评  1=中评 2=差评 3=有图
	private String type;
	//
	private String product;

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
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}
	/**
	 * 获取：帖子id
	 */
	public Long getLeaveId() {
		return leaveId;
	}
	/**
	 * 设置：评价时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：评价时间
	 */
	public Date getAddTime() {
		return addTime;
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
	/**
	 * 设置：评价人
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：评价人
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：评价人姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：评价人姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：评价人头像
	 */
	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}
	/**
	 * 获取：评价人头像
	 */
	public String getHeaderUrl() {
		return headerUrl;
	}
	/**
	 * 设置：评价内容
	 */
	public void setPingjia(String pingjia) {
		this.pingjia = pingjia;
	}
	/**
	 * 获取：评价内容
	 */
	public String getPingjia() {
		return pingjia;
	}
	/**
	 * 设置：评价图片
	 */
	public void setImages(String images) {
		this.images = images;
	}
	/**
	 * 获取：评价图片
	 */
	public String getImages() {
		return images;
	}
	/**
	 * 设置：0=可见  1=不可见
	 */
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * 获取：0=可见  1=不可见
	 */
	public Integer getIsEnable() {
		return isEnable;
	}
	/**
	 * 设置：类型 0=好评  1=中评 2=差评 3=有图
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型 0=好评  1=中评 2=差评 3=有图
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * 获取：
	 */
	public String getProduct() {
		return product;
	}
}
