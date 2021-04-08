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
          console.info("++++++++++++++++++");
          console.info(data.data);
          console.info("++++++++++++++++++");
          that.globalData.openId=data.data;
          }
    });
  
    let flag=1;
    setInterval(() => {
      let openId = that.globalData.openId;
      request({url:"/getFromRedis",data:{openId,flag}}).then(
        res=>{
          console.info(res);
          if(res.code==0){
              wx.navigateTo({
                url: '/pages/zhidaoXQ/zhidaoXQ?keyword='+res.msg,
              })
          }
        }
      );
    }, 5000);
  },
  globalData: {
    openId:'',
    userInfo: null,
    imgPath:"http://localhost:8087"
  }
  
})
