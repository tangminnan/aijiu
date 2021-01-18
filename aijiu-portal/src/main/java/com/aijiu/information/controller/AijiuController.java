package com.aijiu.information.controller;

import com.aijiu.common.utils.StringUtils;
import com.aijiu.information.domain.*;
import com.aijiu.information.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 养生贴吧
     */
    @GetMapping("/getLeaveMessages")
    public Map<String,Object> getLeaveMessages(Long id){
        /**
         *  关注
         */
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


    public String getStr(String str){
        if(StringUtils.isBlank(str))
            return "";
        if(str.length()<200){
            return str;
        }else{
            return str.substring(0,200)+"...";
        }
    }


}
