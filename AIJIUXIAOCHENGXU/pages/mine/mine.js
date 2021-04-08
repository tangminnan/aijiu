// pages/mine/mine.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    showModal: false,
    guanzhu:0,
    dongtai:0,
    fans:0,
    total:0,
    age:0
   
  },
 
  // 外面的弹窗
  KFbtn: function () {
    this.setData({
      showModal: true
    })
  },
 
  // 禁止屏幕滚动
  preventTouchMove: function () {
  },
 
  // 弹出层里面的弹窗
  close: function () {
    this.setData({
      showModal: false
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getMy();
  },
  async getMy(){
    let userinfo = wx.getStorageSync("userinfo");
    if(!userinfo){
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
    let userId = userinfo.userId;
    const res=await request({url:"/my/getMy",data:{userId}});
    console.info(res);
    this.setData({
     guanzhu:res.guanzhu,
     dongtai:res.dongtai,
     fans:res.fans,
     age:res.age,
     total:res.total
    });
    console.info(this.data.mydata);
  },
  
  /**
   *   微信小程序联系客服
   */
  lianxikefugn(){
    this.setData({
      showModal: false
    });
    wx.makePhoneCall({
      phoneNumber: '13280812452',
    })
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