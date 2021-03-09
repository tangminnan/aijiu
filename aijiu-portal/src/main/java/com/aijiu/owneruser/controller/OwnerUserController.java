package com.aijiu.owneruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.aijiu.common.annotation.Log;
import com.aijiu.common.config.BootdoConfig;
import com.aijiu.common.controller.BaseController;
import com.aijiu.common.utils.*;
import com.aijiu.owneruser.domain.OwnerUserDO;
import com.aijiu.owneruser.service.OwnerUserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/owner")
@RestController
public class OwnerUserController extends BaseController {
    @Autowired
    OwnerUserService userService;
    @Autowired
    private BootdoConfig bootdoConfig;

    /**
     * 个人中心
     *
     * @return
     */
    @Log("获取用户信息")
    @GetMapping("/userInfo")
    Map<String, Object> user() {
        Map<String, Object> map = new HashMap<>();
        OwnerUserDO udo = userService.get(getUserId());
        map.put("user", udo);
        return map;
    }


    /**
     * 编辑用户信息
     *
     * @return
     */
    @Log("编辑用户信息")
    @PostMapping("/editInfo")
    Map<String, Object> editInfo(OwnerUserDO user) {
        Map<String, Object> map = new HashMap<>();
        OwnerUserDO userd = userService.get(getUserId());
        if (user.getNickname() != null) {
            userd.setNickname(user.getNickname());
        }
        if (user.getName() != null) {
            userd.setName(user.getName());
        }
      /*  if (user.getCarNum() != null) {
            userd.setCarNum(user.getCarNum());
        }
        if (user.getCarType() != null) {
            userd.setCarType(user.getCarType());
        }
        if (user.getCarSize() != null) {
            userd.setCarSize(user.getCarSize());
        }
        if (user.getCarStatus() != null) {
            userd.setCarStatus(user.getCarStatus());
        }
        if (user.getDesc() != null) {
            userd.setDesc(user.getDesc());
        }*/
        if (user.getAddress() != null) {
            userd.setAddress(user.getAddress());
        }
        if (userService.update(userd) > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "error");
        }
        return map;
    }

    /**
     * 编辑用户头像
     *
     * @return
     */
    @Log("编辑用户头像")
    @PostMapping("/editHeadUrl")
    Map<String, Object> editHeadUrl(OwnerUserDO user) {
        Map<String, Object> map = new HashMap<>();
        OwnerUserDO userd = userService.get(getUserId());
       /* if (user.getFileImg() != null && user.getFileImg().getSize() > 0) {
            MultipartFile sysFile = user.getFileImg();
            String fileName = sysFile.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            try {
                FileUtil.uploadFile(sysFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            userd.setHeardUrl("/files/" + fileName);
            if (userService.update(userd) > 0) {
                map.put("heardUrl", userd.getHeardUrl());
                map.put("msg", "success");
            } else {
                map.put("msg", "error");
            }
        }else{
            map.put("msg", "error");
        }*/
        return map;
    }

}
