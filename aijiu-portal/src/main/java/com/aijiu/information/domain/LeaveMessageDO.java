package com.aijiu.information.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 发帖记录表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2020-08-18 15:15:55
 */
public class LeaveMessageDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//发帖人id
	private Long userId;
	//发帖人姓名
	private String name;
	//发帖时间
	private Date publishTime;
	//发帖内容
	private String leaveText;
	//审核状态(1待审核 2审核通过 3审核失败)
	private Integer auditStatus;
	//帖子附图
	private String img;
	//收藏数
	private Long shoucangcount;
	//删除标志  0 未删除   1已删除
	private Integer deleteFlag;
	//是否推荐帖  0 不推荐   1推荐
	private Integer tuijianFlag;
	//0公开   1不公开
	private Integer showhide;
	//帖子是否加精 0：否1：是
	private Integer addDigest;
	//帖子是否置顶 0：否1：是
	private Integer topTheme;
	//评论数
	private Integer pingluncount;
	//头像
	private String heardUrl;
	//帖子审核不通过原因
	private String shenheResult;
	//收藏人ids id之间以::拼接
	private String shoucangids;
	private List<String> imgList = new ArrayList<>();
	private List<String> shoucnagHeaders = new ArrayList<>();
	private List<LeaveCommentDO> leaveCommentDOS  = new ArrayList<>();

	public List<String> getShoucnagHeaders() {
		return shoucnagHeaders;
	}

	public void setShoucnagHeaders(List<String> shoucnagHeaders) {
		this.shoucnagHeaders = shoucnagHeaders;
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
	 * 设置：发帖人id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：发帖人id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：发帖人姓名
	 */
	public void  setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：发帖人姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：发帖时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发帖时间
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * 设置：发帖内容
	 */
	public void setLeaveText(String leaveText) {
		this.leaveText = leaveText;
	}
	/**
	 * 获取：发帖内容
	 */
	public String getLeaveText() {
		return leaveText;
	}
	/**
	 * 设置：审核状态(1待审核 2审核通过 3审核失败)
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态(1待审核 2审核通过 3审核失败)
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：帖子附图
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：帖子附图
	 */
	public String getImg() {
		return img;
	}

	/**
	 * 获取收藏数
	 */
	public Long getShoucangcount() {
		return shoucangcount;
	}



	/**
	 * 设置收藏数
	 * @param shoucangcount
	 */
	public void setShoucangcount(Long shoucangcount) {
		this.shoucangcount = shoucangcount;
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
	 * 设置：是否推荐帖  0 不推荐   1推荐
	 */
	public void setTuijianFlag(Integer tuijianFlag) {
		this.tuijianFlag = tuijianFlag;
	}
	/**
	 * 获取：是否推荐帖  0 不推荐   1推荐
	 */
	public Integer getTuijianFlag() {
		return tuijianFlag;
	}
	/**
	 * 设置：0公开   1不公开
	 */
	public void setShowhide(Integer showhide) {
		this.showhide = showhide;
	}
	/**
	 * 获取：0公开   1不公开
	 */
	public Integer getShowhide() {
		return showhide;
	}
	/**
	 * 设置：帖子是否加精 0：否1：是
	 */
	public void setAddDigest(Integer addDigest) {
		this.addDigest = addDigest;
	}
	/**
	 * 获取：帖子是否加精 0：否1：是
	 */
	public Integer getAddDigest() {
		return addDigest;
	}
	/**
	 * 设置：帖子是否置顶 0：否1：是
	 */
	public void setTopTheme(Integer topTheme) {
		this.topTheme = topTheme;
	}
	/**
	 * 获取：帖子是否置顶 0：否1：是
	 */
	public Integer getTopTheme() {
		return topTheme;
	}

	/**
	 * 获取评论数
	 * @return
	 */
	public Integer getPingluncount() {
		return pingluncount;
	}

	/**
	 * 设置评论数
	 * @param pingluncount
	 */
	public void setPingluncount(Integer pingluncount) {
		this.pingluncount = pingluncount;
	}

	public String getHeardUrl() {
		return heardUrl;
	}

	public void setHeardUrl(String heardUrl) {
		this.heardUrl = heardUrl;
	}

	public String getShenheResult() {
		return shenheResult;
	}

	public void setShenheResult(String shenheResult) {
		this.shenheResult = shenheResult;
	}

	public String getShoucangids() {
		return shoucangids;
	}

	public void setShoucangids(String shoucangids) {
		this.shoucangids = shoucangids;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public List<LeaveCommentDO> getLeaveCommentDOS() {
		return leaveCommentDOS;
	}

	public void setLeaveCommentDOS(List<LeaveCommentDO> leaveCommentDOS) {
		this.leaveCommentDOS = leaveCommentDOS;
	}
}
