package com.aijiu.information.controller;

import com.aijiu.common.config.BootdoConfig;
import com.aijiu.common.utils.FileUtil;
import com.aijiu.common.utils.HttpUtils;
import com.aijiu.common.utils.StringUtils;
import com.aijiu.information.domain.*;
import com.aijiu.information.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aijiu/v1")
public class AijiuController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UserDOService userDOService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private MyDeviceService myDeviceService;
    @Autowired
    private LeaveMessageService leaveMessageService;
    @Autowired
    private MyShoucangService myShoucangService;
    @Autowired
    private LeaveCommentService leaveCommentService;
    @Autowired
    private ScoresService scoresService;
    @Autowired
    private  MyHelpService myHelpService;
    @Autowired
    private MyBingzhengService myBingzhengService;
    @Autowired
    private XueweiService xueweiService;
    @Autowired
    private ZjzdService zjzdService;
    @Autowired
    private GoumaiReasonService goumaiReasonService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String APP_ID="";
    private static final String APP_SECRET="";
    private static final String PRE_IMGPATH="http://localhost:8087";

    /**
     * 微信小程序获取openid
     */
    @GetMapping("/getOpenId")
    public Map<String,Object> getOpenId(String code){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(StringUtils.isBlank(code)){
            resultMap.put("code",-1);
            resultMap.put("data","code不能为空");
            return resultMap;
        }
        //获取openid
        String url= "https://api.weixin.qq.com/sns/jscode2session?appid="+APP_ID+"&secret="+APP_SECRET+"&js_code="+code+"&grant_type=authorization_code";
        Map<String, Object> map = HttpUtils.doGet(url);

        if((Double)map.get("errcode")==0){
            String openId=(String) map.get("openid");
//            UserDO userDO = userDOService.getUserDO(openId);
//            if(userDO==null){
//                userDO = new UserDO();
//                userDO.setOpenId(openId);
//                userDOService.save(userDO);
//            }
            resultMap.put("code",0);
            resultMap.put("data",openId);
            return resultMap;
        }else{
            resultMap.put("code",-1);
            resultMap.put("data","wx121o2pop12o12o1-");
            return resultMap;
        }
    }


    /**
     * 用户登陆
     */
    @PostMapping("/saveUser")
    public Map<String,Object> saveUser(@RequestBody UserDO userDO){

        UserDO userDO2 = userDOService.getUserDO(userDO.getOpenId());
        if(userDO2==null){
            userDOService.save(userDO);
        }else{
            userDO.setId(userDO2.getId());
            userDOService.update(userDO);
        }
        userDO.setUserId(userDO.getId());
        if(userDO.getName()==null) userDO.setName("用户名");
       Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",userDO);
        return resultMap;
    }

    /**
     *  获取所有的设备
     * @return
     */
    @GetMapping("/getDevices")
    public Map<String,Object> getDevices(){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("deleted",0);
        List<DeviceDO> list = deviceService.list(paramsMap);
        if(list.size()==0){
            resultMap.put("code",-1);
            resultMap.put("data",null);
        }else{
            resultMap.put("code",0);
            resultMap.put("data",list);
        }
        return  resultMap;
    }

    /**
     *  获取我的硬件设备
     * @return
     */
    @GetMapping("/my/getMyDevice")
    public Map<String,Object> getMyDevice(Integer userId){
        Map<String,Object> paramsMap = new HashMap<>();
        Map<String,Object> resultMap = new HashMap<>();
        paramsMap.put("deleteFlag",0);
        paramsMap.put("userId",userId);
        List<MyDeviceDO> list = myDeviceService.list(paramsMap);
        if(list.size()>0){
            resultMap.put("code",0);
            resultMap.put("data",list);
        }else{
            resultMap.put("code",-1);
            resultMap.put("data",null);
        }
        return  resultMap;
    }

    /**
     * 查看设备详情
     * @return
     */
    @GetMapping("/getDeviceById")
    public Map<String,Object> getDeviceById(Integer deviceId){
        Map<String,Object> resultMap = new HashMap<>();
        DeviceDO deviceDO = deviceService.get(deviceId);
        if(deviceDO==null){
            resultMap.put("code",-1);
        }else{
            resultMap.put("code",0);
        }
        resultMap.put("data",deviceDO);
        return resultMap;
    }





    /**
     *  获取所有的用户
     * @return
     */
    @GetMapping("/my/getUsers")
    public Map<String,Object> getUsers(Integer userId){
       Map<String,Object> resultMap = new HashMap<String,Object>();
       Map<String,Object> paramsMap = new HashMap<String,Object>();
       paramsMap.put("deleteFlag",0);
       List<UserDO> list = userDOService.list(paramsMap);
        List<UserDO> list2=list.stream().filter(u ->u.getId().intValue()!=userId.intValue()).collect(Collectors.toList());
       if(list2.size()>0){
        paramsMap.put("userId",userId);paramsMap.remove("deleteFlag");
        List<AttentionDO> attentionDOS = attentionService.list(paramsMap);
        attentionDOS.forEach(a ->{
            Long attentionId = a.getAttentionId();
            list2.forEach(l ->{
                if(l.getId().longValue() ==attentionId.longValue()){
                    l.setFlag(0);//已关注
                }
            });
        });
        resultMap.put("code",0);
        resultMap.put("data",list2);
       }else{
            resultMap.put("code",-1);
            resultMap.put("data",null);
       }
       return resultMap;
    }

    /**
     *  根据帖子的id获取收藏的用户
     * @return
     */
    @GetMapping("/my/getUsersByLeaveId")
    public Map<String,Object> getUsersByLeaveId(Integer userId,Integer leaveId){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<UserDO> list = userDOService.listByShouCangLeaveId(leaveId);
        if(list.size()>0){
            Map<String,Object> paramsMap = new HashMap<String,Object>();
            paramsMap.put("userId",userId);
            List<AttentionDO> attentionDOS = attentionService.list(paramsMap);
            attentionDOS.forEach(a ->{
                Long attentionId = a.getAttentionId();
                list.forEach(l ->{
                    if(l.getId().longValue() ==attentionId.longValue()){
                        l.setFlag(0);//已关注
                    }
                });
            });
            resultMap.put("code",0);
            resultMap.put("data",list);
        }else{
            resultMap.put("code",-1);
            resultMap.put("data",null);
        }
        return resultMap;
    }

    /**
     * 添加、取消关注
     */
    @PostMapping("/my/saveAttention")
    public Map<String,Object> saveAttention(@RequestBody AttentionDO attentionDO){
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("userId",attentionDO.getUserId());
        paramsMap.put("attentionId",attentionDO.getAttentionId());
        List<AttentionDO> attentionDOS = attentionService.list(paramsMap);
        if(attentionDOS.size()>0){//取消关注
            attentionService.remove(attentionDOS.get(0).getId());
        }else{//添加关注
            attentionDO.setAttentionTime(new Date());
            attentionService.save(attentionDO);
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data","操作成功");
        return resultMap;
    }

    /**
     * 我的信息
     * @return
     */
    @GetMapping("/getUserDetail")
    public Map<String,Object> getUserDetail(Long userId){
        Map<String,Object> resultMap = new HashMap<>();
        UserDO userDO = userDOService.get(userId);
        if(userDO==null){
            resultMap.put("code",-1);
        }else{
            resultMap.put("code",0);
        }
        resultMap.put("data",userDO);
        return resultMap;
    }

    /**
     * 关注人主页
     * 此接口可能存在一些问题
     * @return
     */
    @GetMapping("/getAttentionMain")
    public Map<String,Object> getAttentionMain(Long attentionId){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        //基本信息获取
        UserDO userDO = userDOService.get(attentionId);
        if(userDO!=null){
            Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("deleteFlag",0);
            paramsMap.put("userId",attentionId);
            List<AttentionDO> attentionDOS = attentionService.list(paramsMap);//所有的关注
            List<LeaveMessageDO> leaveMessageDOS = leaveMessageService.list(paramsMap);//所有的动态
            for(LeaveMessageDO leaveMessageDO :leaveMessageDOS){
                if(StringUtils.isNotBlank(leaveMessageDO.getImg())){
                    String[] imgs = leaveMessageDO.getImg().split("::");
                    leaveMessageDO.setImgList(Arrays.asList(imgs));
                }
                leaveMessageDO.setLeaveText(getStr(leaveMessageDO.getLeaveText()));
            }
            paramsMap.remove("userId");
            paramsMap.put("attentionId",attentionId);
            List<AttentionDO> attentionDOS1 = attentionService.list(paramsMap);//所有的粉丝
            resultMap.put("code",0);
            resultMap.put("guanzhu",attentionDOS.size());
            resultMap.put("fans",attentionDOS1.size());
            resultMap.put("dongtai",leaveMessageDOS.size());
            resultMap.put("data",leaveMessageDOS);

        }else{
            resultMap.put("code",-1);
        }
        return  resultMap;
    }

    /**
     * 帖子详情
     */
    @GetMapping("/getLeaveMessageDetail")
    public Map<String,Object>  getLeaveMessageDetail(Long id){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        LeaveMessageDO leaveMessageDO = leaveMessageService.get(id);
        if(leaveMessageDO!=null) {
            operateLeaveMessage(Arrays.asList(leaveMessageDO),1);
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("leaveId", id);
            List<LeaveCommentDO> leaveCommentDOS = leaveCommentService.list(paramsMap);//评论
            leaveMessageDO.setLeaveCommentDOS(leaveCommentDOS);
            List<String> headers = myShoucangService.countHeaders(id);
            leaveMessageDO.setShoucnagHeaders(headers);
            resultMap.put("code",0);
            resultMap.put("data",leaveMessageDO);
        }else{
            resultMap.put("code",-1);
            resultMap.put("data",null);
        }
        return  resultMap;
    }

    /**
     * 养生贴吧/关注
     * flag=0 获取 关注 推荐  最新的所有数据
     * flag=1 获取关注数据
     * flag=2 获取推荐数据
     * flag=3 获取最新数据
     */
    @GetMapping("/getLeaveMessagesGuanzhu")
    public Map<String,Object> getLeaveMessagesGuanzhu(@RequestParam(required = false,value = "userId") Long userId, Integer flag){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String, Object> paramsMap = new HashMap<>();
        if(flag==0 || flag==1) {
            paramsMap.put("userId", userId);
            List<AttentionDO> attentionDOS = attentionService.list(paramsMap);//所有的关注
            List<LeaveMessageDO> guanzhu = new ArrayList<LeaveMessageDO>();
            for (AttentionDO a : attentionDOS) {
                paramsMap.put("userId", a.getAttentionId());
                List<LeaveMessageDO> leaveMessageDOS = leaveMessageService.list(paramsMap);//关注人发的贴
                operateLeaveMessage(leaveMessageDOS,0);
                guanzhu.addAll(leaveMessageDOS);
            }
            resultMap.put("guanzhu",guanzhu);
        }
        if(flag==0||flag==2){//推荐
            paramsMap = new HashMap<>();
            paramsMap.put("tuijianFlag",1);
            List<LeaveMessageDO> tuijian = leaveMessageService.list(paramsMap);
            operateLeaveMessage(tuijian,0);
            resultMap.put("tuijian",tuijian);
        }
        if(flag==0 || flag==3){//最新
            List<LeaveMessageDO> zuixin = leaveMessageService.list(new HashMap<String,Object>());
            operateLeaveMessage(zuixin,0);
            resultMap.put("zuixin",zuixin);
        }
        return resultMap;
    }



    /**
     *  我的收藏
     */
    @GetMapping("/my/getMyShoucangLeaveMessage")
    public Map<String,Object> getMyShoucangLeaveMessage(Long userId){
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("userId",userId);
        List<MyShoucangDO> list = myShoucangService.list(paramsMap);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        return resultMap;
    }

    /**
     * 收藏、取消收藏接口
     */
    @PostMapping("/my/saveShouCang")
    public Map<String,Object> saveShouCang(MyShoucangDO myShoucangDO){
        String leaveId = myShoucangDO.getLeaveId();
        String userId = myShoucangDO.getUserId();
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("leaveId",leaveId);
        paramsMap.put("userId",userId);
        List<MyShoucangDO> list = myShoucangService.list(paramsMap);
        if(list.size()>0){//取消收藏
            Long id = list.get(0).getId();
            myShoucangService.remove(id);
        }else{//收藏
            myShoucangDO.setCreateTime(new Date());
            myShoucangService.save(myShoucangDO);
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data","操作成功");
        return resultMap;
    }

    /**
     * 关注人主页接口
     */
    @GetMapping("/my/getGuanzhu")
    public Map<String,Object> getGuanzhu(Long attentionId){
        //关注   粉丝   动态
        Map<String,Object> resultMap = new HashMap<String,Object>();
        UserDO userDO = userDOService.get(attentionId);
        if(userDO!=null) {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("userId", attentionId);
            int dongtai = leaveMessageService.count(paramsMap);//动态数量(这一步可以不要，先留着)
            List<LeaveMessageDO> list = leaveMessageService.list(paramsMap);
            operateLeaveMessage(list,0);
            int guanzhu = attentionService.count(paramsMap);//关注数量
            paramsMap.remove("userId");
            paramsMap.put("attentionId", attentionId);
            int fans = attentionService.count(paramsMap);//粉丝数量
            resultMap.put("id", userDO.getId());
            resultMap.put("heardUrl", userDO.getHeardUrl());
            resultMap.put("guanzhu", guanzhu);
            resultMap.put("fans", fans);
            resultMap.put("dongtai", dongtai);
            resultMap.put("list", list);
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 我的积分
     */

    @GetMapping("/my/getMyScore")
    public Map<String,Object> getMyScore(Long userId){
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("userId",userId);
        List<ScoresDO> list= scoresService.list(paramsMap);
        int sum = list.stream().mapToInt(ScoresDO::getScores).sum();
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        resultMap.put("sum",sum);
        return resultMap;
    }

    @PostMapping("/my/saveMyScore")
    public Map<String,Object> saveMyScore(ScoresDO scoresDO){
        scoresDO.setAddTime(new Date());
        int result = scoresService.save(scoresDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
            resultMap.put("data","数据保存成功");
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 我的基本信息
     */
    @PostMapping("/my/updateUser")
    public Map<String,Object> updateUser(UserDO user){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        int result = userDOService.update(user);
        if(result>0){
            resultMap.put("code",0);
            resultMap.put("data","基本信息修改成功");
        }else{
            resultMap.put("code",-1);
        }
         return resultMap;
    }

    /**
     * 获取我的接口
     * @return
     */
    @GetMapping("/my/getMy")
    public Map<String,Object> getMy(Long userId){
        UserDO userDO  = userDOService.get(userId);
        Map<String,Object> resultMap = new HashMap<>();
        if(userDO!=null){
            resultMap.put("code",0);
            //关注   粉丝   动态
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("userId", userId);
            int dongtai = leaveMessageService.count(paramsMap);//动态数量(这一步可以不要，先留着)
            int guanzhu = attentionService.count(paramsMap);//关注数量
            paramsMap.remove("userId");
            paramsMap.put("attentionId", userId);
            int fans = attentionService.count(paramsMap);//粉丝数量
            Integer total = scoresService.total(userId);//积分
            resultMap.put("id", userDO.getId());
            resultMap.put("heardUrl", userDO.getHeardUrl());
            resultMap.put("guanzhu", guanzhu);
            resultMap.put("fans", fans);
            resultMap.put("dongtai", dongtai);
            resultMap.put("total",total==null?0:total);
           resultMap.put("age",getAge(userDO.getBirthday()));
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 我的反馈
     */

    @PostMapping("/my/saveMyHelp")
    public Map<String,Object> saveMyHelp(@RequestBody  MyHelpDO myHelpDO){
        myHelpDO.setPublishTime(new Date());
        int result = myHelpService.save(myHelpDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
            resultMap.put("data","数据保存成功");
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 购买原因
     */

    @PostMapping("/my/saveGoumaiReasonDO")
    public Map<String,Object> saveGoumaiReasonDO(@RequestBody GoumaiReasonDO goumaiReasonDO){
        goumaiReasonDO.setPublishTime(new Date());
        int result = goumaiReasonService.save(goumaiReasonDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
            resultMap.put("data","数据保存成功");
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 发表评论
     */
    @PostMapping("/my/saveLeaveCommentDO")
    public Map<String,Object> saveLeaveCommentDO(LeaveCommentDO leaveCommentDO){
        leaveCommentDO.setAddTime(new Date());
        leaveCommentDO.setDeleteFlag(0);
        leaveCommentDO.setIsEnable(0);
        int result = leaveCommentService.save(leaveCommentDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
            resultMap.put("data","数据保存成功");
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 我的病症
     */

    @PostMapping("/my/saveMyBingzhengDO")
    public Map<String,Object> saveMyBingzhengDO(@RequestBody MyBingzhengDO myBingzhengDO){
        myBingzhengDO.setPublishTime(new Date());
        int result = myBingzhengService.save(myBingzhengDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
            resultMap.put("data","保存成功");
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     *  获取我的病症记录
     */
    @GetMapping("/my/getMyBingZhengHistory")
    public Map<String,Object> getMyBingZhengHistory(Long userId){
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("userId",userId);
        List<MyBingzhengDO> list = myBingzhengService.list(paramsMap);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        return resultMap;
    }


    /**
     * 穴位详情
     */
    @GetMapping("/getXuewei")
    public Map<String,Object>  getXuewei(Long id){
        XueweiDO xueweiDO = xueweiService.get(id);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(xueweiDO!=null) resultMap.put("code",0);
        else resultMap.put("code",-1);
        resultMap.put("data",xueweiDO);
        return resultMap;
    }

    /**
     * 适应症详情
     */
    @GetMapping("/getZjzdDO")
    public Map<String,Object>  getZjzdDO(Long id){
        ZjzdDO zjzdDO = zjzdService.get(id);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(zjzdDO!=null) resultMap.put("code",0);
        else resultMap.put("code",-1);
        resultMap.put("data",zjzdDO);
        return resultMap;
    }

    /**
     * 获取某一部位的所有穴位
     */
    @GetMapping("/getAllXuewei")
    public Map<String,Object> getAllXuewei(String xueweiBuwei){
        List<XueweiDO> list = xueweiService.getXueweiByBuWei(xueweiBuwei);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        return resultMap;
    }

    /**
     * 获取某一部位的症状
     */
    @GetMapping("/getAllZjzdDO")
    public Map<String,Object> getAllZjzdDO(String zjBuwei){
        List<ZjzdDO> list = zjzdService.getZjzdDOByBuWei(zjBuwei);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        return resultMap;
    }

    /**
     * 关键字搜索穴位和病症
     */
    @GetMapping("/searchXB")
    public Map<String,Object> searchXB(String key){
        List<KeySearch> keySearches = xueweiService.getXueweiByKey(key);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",keySearches);
        return  resultMap;
    }

    /**
     * 保存帖子的图片
     */
    @PostMapping("/my/saveLeaveMessageImg")
    public Map<String,Object> saveLeaveMessageImg(MultipartFile file){
        String path="";
        if(file!=null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                fileName = FileUtil.renameToUUID(fileName);
                FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
                path = "/files/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("img",path);
        return resultMap;
    }

    /**
     * 发帖
     */
    @PostMapping("/my/saveLeaveMessage")
    public Map<String,Object> saveLeaveMessage(@RequestBody LeaveMessageDO leaveMessageDO){
        leaveMessageDO.setPublishTime(new Date());
        leaveMessageDO.setAuditStatus(1);//默认待审核
        leaveMessageDO.setDeleteFlag(0);
        leaveMessageDO.setShowhide(0);//默认公开
        leaveMessageDO.setTuijianFlag(0);//默认不推荐
        leaveMessageDO.setAddDigest(0);//默认不加精
        leaveMessageDO.setTopTheme(0);//默认不置顶
        List<String> str = leaveMessageDO.getImgList().stream().filter(i->!"".equals(i)).collect(Collectors.toList());
        leaveMessageDO.setImg(JSONArray.toJSONString(str));


        Map<String,Object> resultMap = new HashMap<String,Object>();
        int result = leaveMessageService.save(leaveMessageDO);
         resultMap.put("code",0);
         resultMap.put("data","数据保存成功");
         return  resultMap;

    }

    /**
     * 获取硬件过来的数据
     * @return
     */
    @GetMapping("getInfoFromC")
    public Map<String,Object> getInfoFromC(String deviceId,String keyWords){
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isBlank(deviceId)){
            resultMap.put("code",-2);
            resultMap.put("msg","传输的数据中必须包含设备id");
            return resultMap;
        }
        if(StringUtils.isBlank(keyWords)){
            resultMap.put("code",-3);
            resultMap.put("msg","传输的数据中必须包含语义文字");
            return resultMap;
        }
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("deleteFlag",0);
        paramsMap.put("deviceId",deviceId);
        List<MyDeviceDO> list = myDeviceService.list(paramsMap);
        if(list.size()>0){
            MyDeviceDO myDeviceDO=list.get(0);
            if(myDeviceDO.getUserId()!=null) {
                /**
                 *  数据保存到redis中
                 */
                redisTemplate.opsForValue().set(myDeviceDO.getUserId().toString(),keyWords,15,TimeUnit.SECONDS);
                System.out.println(redisTemplate.opsForValue().get(myDeviceDO.getUserId().toString()));
                resultMap.put("code", 0);
                resultMap.put("msg", "请求成功，数据已缓存到后台,15s内有效");
                return resultMap;
            }
        }else{
            resultMap.put("code",-4);
            resultMap.put("msg","小程序后台获取用户失败！");
            return resultMap;
        }

        return null;
    }



    public String getStr(String str){
        if(StringUtils.isBlank(str))
            return "";
        if(str.length()<35){
            return str;
        }else{
            return str.substring(0,35)+"...";
        }
    }

    public static class KeySearch {
        private String type;
        private Integer id;
        private String name;
        private String picture;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public KeySearch(String type, Integer id, String name, String picture) {
            this.type = type;
            this.id = id;
            this.name = name;
            this.picture = picture;
        }

        public KeySearch() {
        }
    }



    public  void operateLeaveMessage(List<LeaveMessageDO> leaveMessageDOS,int flag){
        for(LeaveMessageDO leaveMessageDO :leaveMessageDOS){
            if (StringUtils.isNotBlank(leaveMessageDO.getImg())) {
                String str = leaveMessageDO.getImg();
                List<String> strings = JSONObject.parseArray(str,String.class);

                leaveMessageDO.setImgList(strings);
            }
            //获取收藏数和评论数
           Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("leaveId",leaveMessageDO.getId());
            long pinglun = leaveCommentService.count(paramsMap);
            long shoucang = myShoucangService.count(paramsMap);
            leaveMessageDO.setShoucangcount(shoucang);
            leaveMessageDO.setPingluncount(pinglun);
            if(flag==0)
                leaveMessageDO.setLeaveText(getStr(leaveMessageDO.getLeaveText()));
        }
    }

    public long getAge(Date date){
        long age=18L;
       if(date!=null){
            Date currentDate = new Date();
            age  = (currentDate.getTime()-date.getTime())/1000/60/60/24/365;
        }
        return age;
    }
}
