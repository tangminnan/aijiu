package com.aijiu.information.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 设备表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-19 10:57:05
 */
public class DeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//图片
	private String icon;
	//名称
	private String name;
	//网址链接
	private String link;
	//二维码
	private String ecode;
	//0 未删除   1已删除
	private Integer deleted;
	//价格
	private BigDecimal price;
	//添加时间
	private Date addTime;
	private MultipartFile imgFile;
	//产品型号
	private String type;
	//设备详情页图片展示
	private String detailImg;
	private MultipartFile detailFile;
	// 产品介绍
	private String instruction;

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：图片
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：图片
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：网址链接
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * 获取：网址链接
	 */
	public String getLink() {
		return link;
	}
	/**
	 * 设置：二维码
	 */
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	/**
	 * 获取：二维码
	 */
	public String getEcode() {
		return ecode;
	}
	/**
	 * 设置：0 未删除   1已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：0 未删除   1已删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}

	public MultipartFile getDetailFile() {
		return detailFile;
	}

	public void setDetailFile(MultipartFile detailFile) {
		this.detailFile = detailFile;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
}
