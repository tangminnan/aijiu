package com.aijiu.information.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;



/**
 * 症状指导
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-02 14:51:01
 */
public class ZjzdDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//症状部位
	private String zjBuwei;
	//症状名称
	private String zjName;
	//详情
	private String zjGx;
	//图像
	private String xueweiUrl;
	//添加时间
	private Date addTime;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	private MultipartFile imgFile;

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
	 * 设置：症状部位
	 */
	public void setZjBuwei(String zjBuwei) {
		this.zjBuwei = zjBuwei;
	}
	/**
	 * 获取：症状部位
	 */
	public String getZjBuwei() {
		return zjBuwei;
	}
	/**
	 * 设置：症状名称
	 */
	public void setZjName(String zjName) {
		this.zjName = zjName;
	}
	/**
	 * 获取：症状名称
	 */
	public String getZjName() {
		return zjName;
	}
	/**
	 * 设置：详情
	 */
	public void setZjGx(String zjGx) {
		this.zjGx = zjGx;
	}
	/**
	 * 获取：详情
	 */
	public String getZjGx() {
		return zjGx;
	}
	/**
	 * 设置：图像
	 */
	public void setXueweiUrl(String xueweiUrl) {
		this.xueweiUrl = xueweiUrl;
	}
	/**
	 * 获取：图像
	 */
	public String getXueweiUrl() {
		return xueweiUrl;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：添加时间
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

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
}
