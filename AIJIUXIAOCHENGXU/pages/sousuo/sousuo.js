// pages/sousuo/sousuo.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath:app.globalData.imgPath,
    keySearches:[],
    keydata:''
  },

  setKeyData(e){
    this.setData({
      keydata:e.detail.value
    });
  },
  keydatasearch(){
    let key = this.data.keydata;
     if(""!==key)
      this.keySearch(key);
  },
  /**
   *  详情页面查看
   */
  thiskeysearch(e){
    console.info(e);
    let keyid=e.currentTarget.dataset.pid;
    if(keyid!=null && keyid.includes("--")){
      let type = keyid.split("--")[0];
      let id = keyid.split("--")[1];
      if(type=="XUEWEI"){
        wx.navigateTo({
          url: '/pages/xueweiXQ/xueweiXQ?id='+id
        })
      }
      else if(type=="ZJZD"){
        wx.navigateTo({
          url: '/pages/zhidaoXQ/zhidaoXQ?id='+id
        })
      }
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let key = options.key;
    this.keySearch(key);
  },

  async keySearch(key){
    const res=await request({url:"/searchXB",data:{key}});
    this.setData({
      keySearch:res.keySearches
    });
    console.info(this.data.keySearch);
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