// pages/wodeSC/wodeSC.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath:app.globalData.imgPath,
    shoucang:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.getMyShouCang();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
 async getMyShouCang(){
  let{userId}= wx.getStorageSync('userinfo');
  const res=await request({url:"/my/getMyShoucangLeaveMessage",data:{userId}});
  this.setData({
    shoucang:[...res.data]
  });
  console.info(this.data.shoucang);
},
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