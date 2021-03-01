import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    login_pic:'',
    shareuId:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
    
   
  },
  bindGetUserInfo: function (e) {
    
    //console.info(e.detail.userInfo);
    if (e.detail.userInfo) {
      console.info("允许授权");
      //插入登录的用户的相关信息到数据库
      let params={
      heardUrl:e.detail.userInfo.avatarUrl,
        cityName:e.detail.userInfo.city,
        sex: e.detail.userInfo.gender,
        nickname:e.detail.userInfo.nickName,
        openId:app.globalData.openId
     }
      request({ url: "/saveUser",method:'POST',data:params})
      .then(result => {
        console.info(result);
        wx.setStorageSync("userinfo",result.data);
         wx.navigateBack({
           delta: 1
         });
       });
      
      
      
      
      
      
      
      
      
      
      
      // wx.request({
      //   url: 'https://testh5.server012.com/api/info/addHgUserInfo', 
      //   data: data,
      //   header: {
      //     'content-type': 'application/x-www-form-urlencoded' // 默认值
      //   },
      //   method:'post',
      //   success:(res)=> {
      //     console.info(res.data)
      //     if (res.data.code==0){
      //       getApp().globalData.userInfo = res.data.data
      //       wx.switchTab({
      //         url: '/pages/mine/mine'
      //       })
      //     }else{
      //       //console.log("写入失败")
      //       getApp().globalData.userInfo == null
      //       wx.showToast({
      //         title: '请稍后重试',
      //         icon:'none',
      //         duration:2000
      //       })
      //       wx.switchTab({
      //         url: '/pages/index/index'
      //       })
      //     }
      //   }
      // })
      //授权成功后，跳转进入小程序首页
      
    } else {
      //用户按了拒绝按钮
      wx.showModal({
        title: '警告',
        content: '您点击了拒绝授权，小程序的部分功能将无法使用，请授权!!!',
        showCancel: false,
        confirmText: '返回授权',
        success: function (res) {
         
        }
      })
    }
  }
})