package com.aijiu.information.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;



/**
 * 穴位详情表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-09-02 11:16:43
 */
public class XueweiDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//穴位名称
	private String xueweiName;
	//名称拼音
	private String xueweiPy;
	//国际编码
	private String ggCode;
	//定位
	private String xueweiDingwei;
	//取穴
	private String xueweiQuxue;
	//功效与作用
	private String xueweiGx;
	//穴位图像
	private String xueweiUrl;
	//添加时间
	private Date addTime;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	private MultipartFile imgFile;
	//穴位部位
	private String xueweiBuwei;

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
	 * 设置：穴位名称
	 */
	public void setXueweiName(String xueweiName) {
		this.xueweiName = xueweiName;
	}
	/**
	 * 获取：穴位名称
	 */
	public String getXueweiName() {
		return xueweiName;
	}
	/**
	 * 设置：名称拼音
	 */
	public void setXueweiPy(String xueweiPy) {
		this.xueweiPy = xueweiPy;
	}
	/**
	 * 获取：名称拼音
	 */
	public String getXueweiPy() {
		return xueweiPy;
	}
	/**
	 * 设置：国际编码
	 */
	public void setGgCode(String ggCode) {
		this.ggCode = ggCode;
	}
	/**
	 * 获取：国际编码
	 */
	public String getGgCode() {
		return ggCode;
	}
	/**
	 * 设置：定位
	 */
	public void setXueweiDingwei(String xueweiDingwei) {
		this.xueweiDingwei = xueweiDingwei;
	}
	/**
	 * 获取：定位
	 */
	public String getXueweiDingwei() {
		return xueweiDingwei;
	}
	/**
	 * 设置：取穴
	 */
	public void setXueweiQuxue(String xueweiQuxue) {
		this.xueweiQuxue = xueweiQuxue;
	}
	/**
	 * 获取：取穴
	 */
	public String getXueweiQuxue() {
		return xueweiQuxue;
	}
	/**
	 * 设置：功效与作用
	 */
	public void setXueweiGx(String xueweiGx) {
		this.xueweiGx = xueweiGx;
	}
	/**
	 * 获取：功效与作用
	 */
	public String getXueweiGx() {
		return xueweiGx;
	}
	/**
	 * 设置：穴位图像
	 */
	public void setXueweiUrl(String xueweiUrl) {
		this.xueweiUrl = xueweiUrl;
	}
	/**
	 * 获取：穴位图像
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

	public String getXueweiBuwei() {
		return xueweiBuwei;
	}

	public void setXueweiBuwei(String xueweiBuwei) {
		this.xueweiBuwei = xueweiBuwei;
	}
}
