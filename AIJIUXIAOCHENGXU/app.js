// app.js
import { request } from "/request/index.js";
import regeneratorRuntime from '/lib/runtime/runtime';
App({
  onLaunch() {
    var that=this;
    wx.login({
      success: async function (res) {
          console.info(res);
          let {code}=res;
          const data = await request({url:"/getOpenId",data:{code}});
          that.globalData.openId=data.data;
          
      }
    })
  },
  globalData: {
    openId:'',
    userInfo: null,
    imgPath:"http://localhost:8087"
  },
  
  
})
