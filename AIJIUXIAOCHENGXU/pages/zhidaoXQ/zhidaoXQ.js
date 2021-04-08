// pages/zhidaoXQ/zhidaoXQ.js

import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
import { formatTime } from "../../utils/util.js";

var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath:app.globalData.imgPath,
      zzzdXQ:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const id=options.id;
    const keyword=options.keyword;
    if(keyword!=undefined){
      this.getZjzdDOByKeyWord(keyword);
    }
    if(id!=undefined){
      this.getZjzdDO(id);
    }
  },

  async  getZjzdDO(id){
    const result= await request({url:"/getZjzdDO",data:{id}});
    this.setData({
      zzzdXQ:result.data
    });
    let zzzdXQ =  this.data.zzzdXQ;
    this.saveZy(zzzdXQ);
  },

  async  getZjzdDOByKeyWord(keyword){
    const result= await request({url:"/getZjzdDOByKeyWord",data:{keyword}});
    this.setData({
      zzzdXQ:result.data
    });
    let zzzdXQ =  this.data.zzzdXQ;
    this.saveZy(zzzdXQ);
  },

saveZy(zzzdXQ){
   let url= zzzdXQ.xueweiUrl;
    let name=zzzdXQ.zjName;
    let id=zzzdXQ.id;
    let type="ZJZD";
    let time = formatTime(new Date());
    let arry = wx.getStorageSync('arry')||[];
    arry.push({url,name,time,id,type});
    wx.setStorageSync('arry', arry);
},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})