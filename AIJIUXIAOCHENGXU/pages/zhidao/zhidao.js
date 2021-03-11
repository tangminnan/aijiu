// pages/zhidao/zhidao.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgPath:app.globalData.imgPath,
    xuewei:[],
    zjzd:[],
    currentId: '1',
    section: [{
      name: '穴位指导',
      typeId: '1'
    }, {
      name: '适应症指导',
      typeId: '2'
    }],


    xwbw: "TOU_BU",
    zz:"TOU_BU",
    title:[
        {
            "id":0,
            "name":"头部穴位",
            "xueweiBuwei":"TOU_BU"
        },
        {
            "id": 1,
            "name": "胸腹穴位",
            "xueweiBuwei":"XIONG_FU"
        },
        {
            "id": 2,
            "name": "背腹穴位",
            "xueweiBuwei":"FU_BEI"
        },
        {
            "id": 3,
            "name": "上肢穴位",
            "xueweiBuwei":"SHANG_ZHI"
        },
        {
            "id": 4,
            "name": "下肢穴位",
            "xueweiBuwei":"XIA_ZHI"
        }
    ],
    title2:[
      {
          "id":0,
          "name":"头部疾病",
          "zjBuwei":"TOU_BU"
      },
      {
          "id": 1,
          "name": "胸部疾病",
          "zjBuwei":"XIONG_BU"
      },
      {
          "id": 2,
          "name": "腹部疾病",
          "zjBuwei":"FU_BU"
      },
      {
          "id": 3,
          "name": "背腹疾病",
          "zjBuwei":"FU_BEI"
      },
      {
          "id": 4,
          "name": "上肢疾病",
          "zjBuwei":"SHANG_ZHI"
      },
      {
        "id": 5,
        "name": "下肢疾病",
        "zjBuwei":"XIA_ZHI"
    },
    {
      "id": 6,
      "name": "手部疾病",
      "zjBuwei":"SHOU_BU"
  },
  {
    "id": 7,
    "name": "脚部疾病",
    "zjBuwei":"JIAO_BU"
}
  ]
  },
  //点击每个导航的点击事件
  handleTap: function(e) {
    let id = e.currentTarget.id;
    if(id){
      this.setData({
        currentId:id
      })
    }
    if(id==1){
      this.getAllXuewei("TOU_BU");
      this.setData({
        xwbw:"TOU_BU"
      });
    }
    if(id==2){
      this.getAllZjzdDO("TOU_BU");
      this.setData({
        zz:"TOU_BU"
      });
    }
  },

 /**
    穴位指导
  */
  switchRightTab(e){
    let xueweiBuwei = e.currentTarget.dataset.id;
    this.setData({
      xwbw:xueweiBuwei
    });
    this.getAllXuewei(xueweiBuwei);
  },

  async getAllXuewei(xueweiBuwei){
    const result=await request({url:"/getAllXuewei",data:{xueweiBuwei}});
    console.info(result);
    this.setData({
      xuewei: result.data
    });
  },


  
  /**
    适应症指导
  */
 switchRightTab2(e){
  let zjBuwei = e.currentTarget.dataset.id;
  this.setData({
    zz:zjBuwei
  });
  this.getAllZjzdDO(zjBuwei);
},

async getAllZjzdDO(zjBuwei){
  console.info(zjBuwei);
  const result=await request({url:"/getAllZjzdDO",data:{zjBuwei}});
  console.info(result);
  this.setData({
    zjzd: result.data
  });
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.getAllXuewei("TOU_BU");
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