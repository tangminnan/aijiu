
// 同时发送异步代码的次数
export const request=(params)=>{

  // 显示加载中 效果
  wx.showLoading({
    title: "加载中",
    mask: true
  });
    

  // 定义公共的url
  const baseUrl="http://localhost:8087/aijiu/v1";
  return new Promise((resolve,reject)=>{
    wx.request({
     ...params,
     url:baseUrl+params.url,
     success:(result)=>{
       resolve(result.data);
     },
     fail:(err)=>{
       reject(err);
     },
     complete:()=>{
        //  关闭正在等待的图标
        wx.hideLoading();
     }
    });
  })
}