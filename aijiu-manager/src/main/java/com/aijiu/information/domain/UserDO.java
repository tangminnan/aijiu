package com.aijiu.information.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户信息表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-17 14:43:46
 */
public class UserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//头像
	private String heardUrl;
	//真实姓名
	private String name;
	//手机号
	private String phone;
	//身高
	private Double height;
	//体重
	private Double weight;
	//邮箱
	private String email;
	//性别：1=男性 2=女性
	private Integer sex;
	//出生年月
	private Date birthday;
	//昵称
	private String nickname;
	//登录用户名
	private String username;
	//登录密码
	private String password;
	//绑定微信id
	private String openId;
	//最后登录时间
	private Date lastLoginTime;
	//注册时间
	private Date registerTime;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	//
	private String provinceCode;
	//所在省
	private String provinceName;
	//
	private String cityCode;
	//所在市
	private String cityName;
	//
	private String areaCode;
	//所在区
	private String areaName;
	private MultipartFile imgFile;
	private String jianjie;

	public String getJianjie() {
		return jianjie;
	}

	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

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
	 * 设置：头像
	 */
	public void setHeardUrl(String heardUrl) {
		this.heardUrl = heardUrl;
	}
	/**
	 * 获取：头像
	 */
	public String getHeardUrl() {
		return heardUrl;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：身高
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * 获取：身高
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * 设置：体重
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * 获取：体重
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：性别：1=男性 2=女性
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别：1=男性 2=女性
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：出生年月
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：出生年月
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：登录用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：登录用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：登录密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：绑定微信id
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：绑定微信id
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * 获取：最后登录时间
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegisterTime() {
		return registerTime;
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
	 * 设置：
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	/**
	 * 获取：
	 */
	public String getProvinceCode() {
		return provinceCode;
	}
	/**
	 * 设置：所在省
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：所在省
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * 设置：
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * 获取：
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * 设置：所在市
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：所在市
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：所在区
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：所在区
	 */
	public String getAreaName() {
		return areaName;
	}
}
