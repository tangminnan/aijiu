// pages/wodeBingzheng/wodeBingzheng.js
// pages/tieziXQ/tieziXQ.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    text:''
  },

  //处理输入框内容
  handleToInput(e){
    this.setData({
      text:e.detail.value
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  async saveMyBingzhengDO(){
    let userinfo=wx.getStorageSync('userinfo');
    let userName=userinfo.name;
    let userId=userinfo.id;
    let text=this.data.text;
    if(!text.trim()){
      wx.showToast({
        title: '内容不可为空',
        icon: 'none',
        mask: true
      });
      return;
    }
    let params={userId,userName,text};
    const res=await request({url:"/my/saveMyBingzhengDO",method:"POST", data:params});
    if(res.code==0){
      this.setData({
        text:''
      })
      wx.navigateTo({
        url: '/pages/bingliJL/bingliJL'
      })
    }
   
    },
    //我的病症记录
    getMyBL(){
      wx.navigateTo({
        url: '/pages/bingliJL/bingliJL'
      })
    },
  onLoad: function (options) {
  
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