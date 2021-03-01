// pages/yonghuList/yonghuList.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userlist:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     let {leaveId}=options;
     this.getUsersByLeaveId(leaveId);
  },
  async getUsersByLeaveId(leaveId){
      let {userId}= wx.getStorageSync('userinfo');
      const res=await request({url:"/my/getUsersByLeaveId",data:{userId,leaveId}});
      this.setData({
        userlist:[...res.data]
     });
     console.info(this.data.userlist);
  },
  async handleToGuanzhu(e){
    let attentionId=e.currentTarget.dataset.id;
    let userId=wx.getStorageSync('userinfo').userId;
    let params={attentionId,userId}
    const res=await request({url:"/my/saveAttention",method:'POST',data:params});
    if(res.code==0){
      let arry=this.data.userlist;
      for(let i=0;i<arry.length;i++){
        if( arry[i].id==attentionId){
          arry[i].flag=0;
        }
        this.setData({
          userlist: arry
        });
      }
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