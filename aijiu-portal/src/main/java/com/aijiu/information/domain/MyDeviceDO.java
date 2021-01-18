package com.aijiu.information.domain;

import java.io.Serializable;


/**
 * 我的设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-12-29 16:47:11
 */
public class MyDeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//用户id
	private Integer userId;
	//用户姓名
	private String userName;
	//设备id
	private Integer deviceId;
	//设备名
	private String deviceName;
	//设备图
	private String icon;
	//设备型号
	private String type;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;

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
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
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
	 * 设置：设备id
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备id
	 */
	public Integer getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：设备名
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * 获取：设备名
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * 设置：设备图
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：设备图
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：设备型号
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：设备型号
	 */
	public String getType() {
		return type;
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
}
