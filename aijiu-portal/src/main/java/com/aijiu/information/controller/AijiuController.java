package com.aijiu.information.controller;

import com.aijiu.common.utils.StringUtils;
import com.aijiu.information.domain.*;
import com.aijiu.information.service.*;
import com.oracle.webservices.internal.api.message.PropertySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/getMyDevice")
    public Map<String,Object> getMyDevice(){
        Map<String,Object> paramsMap = new HashMap<>();
        Map<String,Object> resultMap = new HashMap<>();
        paramsMap.put("deleteFlag",0);
        paramsMap.put("userId",101);
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
    public Map<String,Object> getDeviceById(Integer id){
        Map<String,Object> resultMap = new HashMap<>();
        DeviceDO deviceDO = deviceService.get(id);
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
    @GetMapping("/getUsers")
    public Map<String,Object> getUsers(){
       Map<String,Object> resultMap = new HashMap<String,Object>();
       Map<String,Object> paramsMap = new HashMap<String,Object>();
       paramsMap.put("deleteFlag",0);
       List<UserDO> list = userDOService.list(paramsMap);
       if(list.size()>0){
        paramsMap.put("userId",101);
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
     * 我的信息
     * @return
     */
    @GetMapping("/getUserDetail")
    public Map<String,Object> getUserDetail(Long id){
        Map<String,Object> resultMap = new HashMap<>();
        UserDO userDO = userDOService.get(id);
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
            if (StringUtils.isNotBlank(leaveMessageDO.getImg())) {
                String[] imgs = leaveMessageDO.getImg().split("::");
                leaveMessageDO.setImgList(Arrays.asList(imgs));
            }
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("leaveId", id);
            paramsMap.put("deleteFlag", 0);
            paramsMap.put("isEnable", 0);
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
     */
    @GetMapping("/getLeaveMessagesGuanzhu")
    public Map<String,Object> getLeaveMessagesGuanzhu(Long id){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("deleteFlag",0);
        paramsMap.put("userId",id);
        List<AttentionDO> attentionDOS = attentionService.list(paramsMap);//所有的关注
        List<LeaveMessageDO> guanzhu = new ArrayList<LeaveMessageDO>();
        for(AttentionDO a:attentionDOS){
            paramsMap.put("userId",a.getAttentionId());
            List<LeaveMessageDO> leaveMessageDOS = leaveMessageService.list(paramsMap);//关注人发的贴
            for(LeaveMessageDO leaveMessageDO :leaveMessageDOS){
                if(StringUtils.isNotBlank(leaveMessageDO.getImg())){
                    String[] imgs = leaveMessageDO.getImg().split("::");
                    leaveMessageDO.setImgList(Arrays.asList(imgs));
                }
                leaveMessageDO.setLeaveText(getStr(leaveMessageDO.getLeaveText()));
            }
            guanzhu.addAll(leaveMessageDOS);
        }
        resultMap.put("guanzhu",guanzhu);
        return resultMap;
    }

    /**
     * 养生贴吧/推荐
     */
    @GetMapping("/getLeaveMessagesTuijian")
    public Map<String,Object> getLeaveMessagesTuijian(Long id){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<LeaveMessageDO> allLe = leaveMessageService.list(new HashMap<String,Object>());
        for(LeaveMessageDO leaveMessageDO :allLe){
            if(StringUtils.isNotBlank(leaveMessageDO.getImg())){
                String[] imgs = leaveMessageDO.getImg().split("::");
                leaveMessageDO.setImgList(Arrays.asList(imgs));
            }
            leaveMessageDO.setLeaveText(getStr(leaveMessageDO.getLeaveText()));
        }
        List<LeaveMessageDO> tuijian = allLe.stream().filter(a ->a.getTuijianFlag()==1).collect(Collectors.toList());
        resultMap.put("tuijian",tuijian);
        return resultMap;
    }

    /**
     * 养生贴吧/最新
     */
    @GetMapping("/getLeaveMessagesZuiXin")
    public Map<String,Object> getLeaveMessagesZuiXin(Long id){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<LeaveMessageDO> allLe = leaveMessageService.list(new HashMap<String,Object>());
        for(LeaveMessageDO leaveMessageDO :allLe){
            if(StringUtils.isNotBlank(leaveMessageDO.getImg())){
                String[] imgs = leaveMessageDO.getImg().split("::");
                leaveMessageDO.setImgList(Arrays.asList(imgs));
            }
            leaveMessageDO.setLeaveText(getStr(leaveMessageDO.getLeaveText()));
        }
        resultMap.put("zuixin",allLe);
        return resultMap;
    }


    /**
     *  我的收藏
     */
    @GetMapping("/getMyShoucangLeaveMessage")
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

    @GetMapping("/getMyScore")
    public Map<String,Object> getMyScore(Long id){
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("userId",id);
        List<ScoresDO> list= scoresService.list(paramsMap);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code",0);
        resultMap.put("data",list);
        return resultMap;
    }

    /**
     * 我的基本信息
     */
    @PostMapping("/updateUser")
    public Map<String,Object> updateUser(UserDO user){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        int result = userDOService.update(user);
        if(result>0){
            resultMap.put("code",0);
        }else{
            resultMap.put("code",-1);
        }
         return resultMap;
    }

    /**
     * 我的反馈
     */

    @PostMapping("/saveMyHelp")
    public Map<String,Object> saveMyHelp(MyHelpDO myHelpDO){
        myHelpDO.setPublishTime(new Date());
        int result = myHelpService.save(myHelpDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 购买原因
     */

    @PostMapping("/saveGoumaiReasonDO")
    public Map<String,Object> saveGoumaiReasonDO(GoumaiReasonDO goumaiReasonDO){
        goumaiReasonDO.setPublishTime(new Date());
        int result = goumaiReasonService.save(goumaiReasonDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 发表评论
     */
    @PostMapping("/saveLeaveCommentDO")
    public Map<String,Object> saveLeaveCommentDO(LeaveCommentDO leaveCommentDO){
        leaveCommentDO.setAddTime(new Date());
        int result = leaveCommentService.save(leaveCommentDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     * 我的病症
     */

    @PostMapping("/saveMyBingzhengDO")
    public Map<String,Object> saveMyBingzhengDO(MyBingzhengDO myBingzhengDO){
        myBingzhengDO.setPublishTime(new Date());
        int result = myBingzhengService.save(myBingzhengDO);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(result>0){
            resultMap.put("code",0);
        }else{
            resultMap.put("code",-1);
        }
        return resultMap;
    }

    /**
     *  获取我的病症记录
     */

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
    @GetMapping("/getAllXuewei")
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


}
