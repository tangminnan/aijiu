package com.aijiu.information.controller;

import com.aijiu.common.config.BootdoConfig;
import com.aijiu.common.utils.FileUtil;
import com.aijiu.common.utils.R;
import com.aijiu.common.utils.StringUtils;
import com.aijiu.information.domain.*;
import com.aijiu.information.service.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oracle.webservices.internal.api.message.PropertySet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aijiu")
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
     * 添加、取消关注
     */
    @PostMapping("/my/saveAttention")
    public Map<String,Object> saveAttention(AttentionDO attentionDO){
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
    @GetMapping("/my/getLeaveMessageDetail")
    public Map<String,Object>  getLeaveMessageDetail(Long id){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        LeaveMessageDO leaveMessageDO = leaveMessageService.get(id);
        if(leaveMessageDO!=null) {
            operateLeaveMessage(Arrays.asList(leaveMessageDO));
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
    @GetMapping("/my/getLeaveMessagesGuanzhu")
    public Map<String,Object> getLeaveMessagesGuanzhu(Long userId,Integer flag){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String, Object> paramsMap = new HashMap<>();
        if(flag==0 || flag==1) {
            paramsMap.put("userId", userId);
            List<AttentionDO> attentionDOS = attentionService.list(paramsMap);//所有的关注
            List<LeaveMessageDO> guanzhu = new ArrayList<LeaveMessageDO>();
            for (AttentionDO a : attentionDOS) {
                paramsMap.put("userId", a.getAttentionId());
                List<LeaveMessageDO> leaveMessageDOS = leaveMessageService.list(paramsMap);//关注人发的贴
                operateLeaveMessage(leaveMessageDOS);
                guanzhu.addAll(leaveMessageDOS);
            }
            resultMap.put("guanzhu",guanzhu);
        }
        if(flag==0||flag==2){//推荐
            paramsMap = new HashMap<>();
            paramsMap.put("tuijianFlag",1);
            List<LeaveMessageDO> tuijian = leaveMessageService.list(paramsMap);
            operateLeaveMessage(tuijian);
            resultMap.put("tuijian",tuijian);
        }
        if(flag==0 || flag==3){//最新
            List<LeaveMessageDO> zuixin = leaveMessageService.list(new HashMap<String,Object>());
            operateLeaveMessage(zuixin);
            resultMap.put("zuixin",zuixin);
        }
        return resultMap;
    }



    /**
     *  我的收藏
     */
    @GetMapping("/my/getMyShoucangLeaveMessage")
    public Map<String,Object> getMyShoucangLeaveMessage(Long id){
        List<MyShoucangDO> l = myShoucangService.getShouCangLeaveMessage(id);
        for(MyShoucangDO myShoucangDO:l){
            if(StringUtils.isNotBlank(myShoucangDO.getImg())){
                String s = myShoucangDO.getImg().split("::")[0];
                myShoucangDO.setImg(s);
            }
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",l);
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
     * 我的反馈
     */

    @PostMapping("/my/saveMyHelp")
    public Map<String,Object> saveMyHelp(MyHelpDO myHelpDO){
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
    public Map<String,Object> saveGoumaiReasonDO(GoumaiReasonDO goumaiReasonDO){
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
    public Map<String,Object> saveMyBingzhengDO(MyBingzhengDO myBingzhengDO){
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
    public Map<String,Object> saveLeaveMessage(LeaveMessageDO leaveMessageDO){
        leaveMessageDO.setUserId(111L);
        leaveMessageDO.setName("匿名");
        leaveMessageDO.setHeardUrl("...");
        leaveMessageDO.setPublishTime(new Date());
        leaveMessageDO.setAuditStatus(1);//默认待审核
        leaveMessageDO.setDeleteFlag(0);
        leaveMessageDO.setShowhide(0);//默认公开
        leaveMessageDO.setTuijianFlag(0);//默认不推荐
        leaveMessageDO.setAddDigest(0);//默认不加精
        leaveMessageDO.setTopTheme(0);//默认不置顶
        List<String> str = leaveMessageDO.getImgList().stream().filter(i->!"".equals(i)).collect(Collectors.toList());
        leaveMessageDO.setImg(str.toString());


        Map<String,Object> resultMap = new HashMap<String,Object>();
        int result = leaveMessageService.save(leaveMessageDO);
         resultMap.put("code",0);
         resultMap.put("data","数据保存成功");
         return  resultMap;

    }



    public String getStr(String str){
        if(StringUtils.isBlank(str))
            return "";
        if(str.length()<200){
            return str;
        }else{
            return str.substring(0,200)+"...";
        }
    }

    public static class KeySearch{
        private String type;
        private Integer id;
        private String name;
        private String picture;

        public KeySearch(String type, Integer id, String name, String picture) {
            this.type = type;
            this.id = id;
            this.name = name;
            this.picture = picture;
        }
    }


    public  void operateLeaveMessage(List<LeaveMessageDO> leaveMessageDOS){
        for(LeaveMessageDO leaveMessageDO :leaveMessageDOS){
            if (StringUtils.isNotBlank(leaveMessageDO.getImg())) {
                String str = leaveMessageDO.getImg();
                List<String> strings = JSONObject.parseArray(str, String.class);
                leaveMessageDO.setImgList(strings);
            }
            //获取收藏数和评论数
           Map<String,Object> paramsMap = new HashMap<>();
            paramsMap.put("leaveId",leaveMessageDO.getId());
            long pinglun = leaveCommentService.count(paramsMap);
            long shoucang = myShoucangService.count(paramsMap);
            leaveMessageDO.setShoucangcount(shoucang);
            leaveMessageDO.setPingluncount(pinglun);
            leaveMessageDO.setLeaveText(getStr(leaveMessageDO.getLeaveText()));
        }
    }
}
