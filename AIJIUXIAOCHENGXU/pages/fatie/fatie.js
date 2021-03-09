// pages/fatie/fatie.js
import { request } from "../../request/index.js";
import regeneratorRuntime from '../../lib/runtime/runtime';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //图片内容
    chooseImgs: [],
     // 文本域的内容
     leaveText: "",
     title:'',
  
  },
  imgList: [],

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  //点击添加图片
  handleChooseImg(){
    wx.chooseImage({
      count: 2,//一次最多上传两张图片
      sizeType: ['original', 'compressed'],// 图片的格式  原图  压缩
      sourceType: ['album', 'camera'],// 图片的来源  相册  照相机
      success: (result) => {
        this.setData({
          chooseImgs: [...this.data.chooseImgs, ...result.tempFilePaths] // 图片数组 进行拼接 
        })
      }
    });
  },

   // 图片删除
   handleRemoveImg(e) {
    const { index } = e.currentTarget.dataset;
    let { chooseImgs } = this.data;
    chooseImgs.splice(index, 1);
    this.setData({
      chooseImgs
    })
  },

  // 发帖的具体内容
  handleTextInput(e) {
    this.setData({
      leaveText: e.detail.value
    });
  },
  //发帖标题
  handleTitleInput(e) {
    this.setData({
      title: e.detail.value
    });
  },
// 提交按钮的点击
handleFormSubmit() {
  const { leaveText,title, chooseImgs } = this.data;
  if (!title.trim()) {
    wx.showToast({
      title: '发帖内容不可为空',
      icon: 'none',
      mask: true
    });
    return;
  }
  wx.showLoading({
    title: "正在上传中",
    mask: true
  });

  // 判断有没有需要上传的图片数组

  if (chooseImgs.length != 0) {
    chooseImgs.forEach((v, i) => {
      wx.uploadFile({
        url: 'http://localhost:8087/aijiu/v1/my/saveLeaveMessageImg',
        filePath: v,// 被上传的文件的路径
        // 上传的文件的名称 后台来获取文件  file
        name: "file",
        // 顺带的文本信息
        formData: {},
        success: (result) => {
          console.log(result);
          let url = JSON.parse(result.data).img;
          this.imgList.push(url);
          console.info( this.imgList);
          if (i === chooseImgs.length - 1) {
            let imgList=this.imgList
            let userinfo = wx.getStorageSync('userinfo');
            let params={title,leaveText,imgList,...userinfo};
            request({ url: "/my/saveLeaveMessage",method:'POST',data:params})
            .then(result => {
              this.setData({
                textVal: "",
                chooseImgs: []
              });
             });
            wx.hideLoading();
            wx.navigateTo({
              url: '/pages/fabuCG/fabuCG'
            })

          }
        }
      });
    })
  }else{
    let params={title,leaveText}  ;
    request({ url: "/my/saveLeaveMessage",method:'POST',data:params})
    .then(result => {
      this.setData({
          textVal: "",
          chooseImgs: []
      });
    });
    wx.hideLoading();
    wx.navigateTo({
      url: '/pages/fabuCG/fabuCG'
    })
      
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