package com.aijiu.owneruser.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class OwnerUserDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//id
	private Long id;
	//头像
	private String heardUrl;
	//登录用户名
	private String username;
	//登录密码
	private String password;
	//绑定微信id
	private String openId;
	//性别：1=男性 2=女性
	private Integer sex;
	//出生年月
	private Date birthday;
	//昵称
	private String nickname;
	//身高
	private Double height;
	//体重
	private Double weight;
	//健身次数
	private Integer exerciseTimes;
	//运动时长  单位（minute）
	private Integer totalTime;
	//最后登录时间
	private Date lastLoginTime;
	//vip等级 0=月卡 1=季卡  2=年卡
	private Integer vipDegree;
	//积分
	private Integer totalScore;
	//手机号
	private String phone;
	//真实姓名
	private String name;
	//注册时间
	private Date registerTime;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	//用户标识0：学员1：教练
	private Integer exerciserFlag;
	//
	private String provinceCode;
	//所在省
	private String provincename;
	//
	private String citycode;
	//所在市
	private String cityname;
	//
	private String areacode;
	//所在区
	private String areaname;
	private String dateStr;
	//所属的门店
	private String store;
	//所属门店的ID
	private Integer storeId;
	private MultipartFile imgFile;
	//教练简介
	private String jianjie;
	//擅长
	private String goodAt;
	//资质附件
	private String images;
	//教练资质证件
	private List<MultipartFile> imgFileList;
	//0=未申请  1=审核中  2=审核未通过 3=审核通过
	private Integer jiaolian;

	private String weiguo;
	//具体地址
	private String address;
	private String[] goodAts;
	private String[] urls;

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	public String[] getGoodAts() {
		return goodAts;
	}

	public void setGoodAts(String[] goodAts) {
		this.goodAts = goodAts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGoodAt() {
		return goodAt;
	}

	public void setGoodAt(String goodAt) {
		this.goodAt = goodAt;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	public String getJianjie() {
		return jianjie;
	}

	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
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
	 * 设置：健身次数
	 */
	public void setExerciseTimes(Integer exerciseTimes) {
		this.exerciseTimes = exerciseTimes;
	}
	/**
	 * 获取：健身次数
	 */
	public Integer getExerciseTimes() {
		return exerciseTimes;
	}
	/**
	 * 设置：运动时长  单位（minute）
	 */
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	/**
	 * 获取：运动时长  单位（minute）
	 */
	public Integer getTotalTime() {
		return totalTime;
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
	 * 设置：vip等级 0=月卡 1=季卡  2=年卡
	 */
	public void setVipDegree(Integer vipDegree) {
		this.vipDegree = vipDegree;
	}
	/**
	 * 获取：vip等级 0=月卡 1=季卡  2=年卡
	 */
	public Integer getVipDegree() {
		return vipDegree;
	}
	/**
	 * 设置：积分
	 */
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	/**
	 * 获取：积分
	 */
	public Integer getTotalScore() {
		return totalScore;
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
	 * 设置：用户标识0：学员1：教练
	 */
	public void setExerciserFlag(Integer exerciserFlag) {
		this.exerciserFlag = exerciserFlag;
	}
	/**
	 * 获取：用户标识0：学员1：教练
	 */
	public Integer getExerciserFlag() {
		return exerciserFlag;
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
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	/**
	 * 获取：所在省
	 */
	public String getProvincename() {
		return provincename;
	}
	/**
	 * 设置：
	 */
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	/**
	 * 获取：
	 */
	public String getCitycode() {
		return citycode;
	}
	/**
	 * 设置：所在市
	 */
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	/**
	 * 获取：所在市
	 */
	public String getCityname() {
		return cityname;
	}
	/**
	 * 设置：
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	/**
	 * 获取：
	 */
	public String getAreacode() {
		return areacode;
	}
	/**
	 * 设置：所在区
	 */
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	/**
	 * 获取：所在区
	 */
	public String getAreaname() {
		return areaname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public List<MultipartFile> getImgFileList() {
		return imgFileList;
	}

	public void setImgFileList(List<MultipartFile> imgFileList) {
		this.imgFileList = imgFileList;
	}

	public Integer getJiaolian() {
		return jiaolian;
	}

	public void setJiaolian(Integer jiaolian) {
		this.jiaolian = jiaolian;
	}

	public String getWeiguo() {
		return weiguo;
	}

	public void setWeiguo(String weiguo) {
		this.weiguo = weiguo;
	}
}
