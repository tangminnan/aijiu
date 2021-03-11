// pages/fankui/fankui.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
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
  onLoad: function (options) {

  },
  async saveMyHelp(){
    let userinfo=wx.getStorageSync('userinfo');
    let userName=userinfo.nickName;
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
    const res=await request({url:"/my/saveMyHelp",method:"POST", data:params});
    if(res.code==0){
      wx.showToast({
        title: res.data,
        icon: 'success',
        duration: 2000,
        success: function(){
          wx.switchTab({
            url: '/pages/mine/mine'
          })
        }
      });
    }
   
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