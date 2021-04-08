// pages/tansuo/tansuo.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
const app = getApp()
Page({
  data: {
      winWidth: 0,
      winHeight: 0,
      currentTab: 0,
      navbar: ['关注', '推荐', '最新'],
      imgPath:app.globalData.imgPath,
      guanzhu:[],
      zuixin:[]
  },
  //切换bar
  navbarTap: function (e) {
    this.setData({
      currentTab: e.currentTarget.dataset.idx
    })
    //全局变量
    app.globalData.currentTab = e.currentTarget.dataset.idx;
  },
  onShow() {
    this.setData({
      currentTab: app.globalData.currentTab
    })
  },

  swiperChange: function (e) {
    this.setData({
      currentTab: e.detail.current,
    })
    //全局变量
    app.globalData.currentTab = e.detail.current;
  },
  //高度
  onLoad: function () {
    var that = this
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          clientHeight: res.windowHeight - 50
        });
      }
    });
    this.getLeaveMessagesGuanzhu();
  },

  async getLeaveMessagesGuanzhu(){
    let userinfo = wx.getStorageSync('userinfo');
    if(!userinfo){
      wx.navigateTo({
        url: '/pages/login/login'
      })
    }
    let params={};
    params.userId=userinfo.userId;
    params.flag=0;
    console.info(params);
    const res=await request({url:"/getLeaveMessagesGuanzhu",data:params});
    console.info(res);
    this.setData({
      guanzhu:res.guanzhu,
      zuixin:res.zuixin
    });
    console.info("==========================");
    console.info(this.data.zuixin);
    console.info("===========================");
  },
  bindChange: function( e ) {

      var that = this;
      that.setData( { currentTab: e.detail.current });

  },

})