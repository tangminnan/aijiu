
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({
  // 页面的初始数据
  data: {
    imgPath:app.globalData.imgPath,
    inputShowed: false,  //初始文本框不显示内容
    tuijianTieList:[],//推荐贴
    dongtaiTieList:[]//动态贴
    
  },
  tuijian:[],
  dongtai:[],
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     this.gettuijianTieList();
     this.getdongtaiTieList();
  },

  /**
   *  关键字搜索
   */
 inputChange(e){
   let key = e.detail.value;
   wx.navigateTo({
     url: '/pages/sousuo/sousuo?key='+key
   })
   },
/**
 *  微信扫一扫
 */
saoyisao(){
    wx.scanCode({
      success: (res) => {
        console.log(res)
      }
    });
  },
  //获取推荐贴
  gettuijianTieList(){
    request({ url: "/getLeaveMessagesGuanzhu",data:{flag:2}})
    .then(result => {
      this.setData({
        tuijianTieList: result.tuijian.slice(0,2)
      });
      this.tuijian=result.tuijian;
    });
  },
  //获取动态贴
  getdongtaiTieList(){
    request({ url: "/getLeaveMessagesGuanzhu",data:{flag:3}})
    .then(result => {
      console.info(result);
      this.setData({
        dongtaiTieList: result.zuixin.slice(0,2)
      });
      this.dongtai=result.zuixin;
    });
  },
  //查看更多
  handleMore(e){
    let ine=e.currentTarget.dataset.in;
    if(ine==0){
      this.setData({
        tuijianTieList: this.tuijian
      });
    }else if(ine==1){console.info(this.dongtai);
      this.setData({
        dongtaiTieList: this.dongtai
      });
    }
  },

  // 使文本框进入可编辑状态
  showInput: function () {
    this.setData({
      inputShowed: true   //设置文本框可以输入内容
    });
  },
  //跳转到艾灸指导
  handleToZhiDao(){
    wx.switchTab({
      url: '/pages/zhidao/zhidao'
    })
  },
  //跳转硬件设备页面
  handleToHardDevice(){
    wx.switchTab({
      url: "/pages/shebei/shebei"
    })
  },

  //跳转到发帖页面
  handleToFaTie(){
    //验证用户是否登陆小程序
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
  // 取消搜索
  hideInput: function () {
    this.setData({
      inputShowed: false
    });
  }
});