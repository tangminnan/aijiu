// pages/tieziXQ/tieziXQ.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath:app.globalData.imgPath,
    tixiXO:{}
  },
  leaveId:'',

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let {id} = options;
    this.leaveId=options.id;
    this.getTieziXQ(id);
  },

  async getTieziXQ(id){
    const res=await request({url:"/getLeaveMessageDetail",data:{id}});
    this.setData({
      tixiXO: res.data
    });
    console.info(this.data.tixiXO);
  },
  //帖子的评论
  handleToComment(){
    var userinfo = wx.getStorageSync('userinfo');
    if(userinfo){
      wx.navigateTo({
        url: '/pages/fatie/fatie'
      })
    }else{
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
  },
  //帖子的收藏
  addShouCang(){
    let userinfo = wx.getStorageSync('userinfo');
    if(userinfo){
     let userId = userinfo.userId;
     let userName = userinfo.name;
     let headerUrl = userinfo.headerUrl;
     let leaveId=this.data.tixiXO.id;
     let text=this.data.tixiXO.leaveText;
     let img = this.data.tixiXO.imgList[0];
     if(img==undefined) img=null;
     let params={userId,userName,headerUrl,leaveId,text,img};
     request({ url: "/my/saveShouCang",method:'POST',data:params})
     .then(result => {
      wx.showToast({
        title: result.data,
        icon: 'success',
        duration: 2000
      })
      
      
      });
    }else{
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
    
  },

  //收藏人信息
  handleToShouCang(){
    var userinfo = wx.getStorageSync('userinfo');
    if(!userinfo){
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
    
   let leaveId=this.leaveId;
    wx.navigateTo({
      url: '/pages/yonghuList/yonghuList?leaveId='+leaveId,
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