package com.aijiu.owneruser.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.aijiu.common.annotation.Log;
import com.aijiu.common.controller.BaseController;
import com.aijiu.common.utils.HttpUtils;
import com.aijiu.common.utils.ShiroUtils;
import com.aijiu.owneruser.comment.SMSContent;
import com.aijiu.owneruser.comment.SMSPlatform;
import com.aijiu.owneruser.comment.SMSTemplate;
import com.aijiu.owneruser.domain.OwnerUserDO;
import com.aijiu.owneruser.service.OwnerUserService;
import com.aijiu.smsservice.service.ISMSService;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

  
    @Autowired
    OwnerUserService userService;
    @Autowired
    private ISMSService sMSService;


    /**
     * type=1 注册 type=2 快速登录 type=3忘记密码 type=4 绑定手机号
     */
    @Log("发送验证码")
    @GetMapping("/getSms")
    @ResponseBody
    Map<String, Object> getSms(String phone, String type) {
        Map<String, Object> message = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        try {
            DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4FoEGySbeiHqHwnwHjPn", "kBtJkLFvUrqkFv6xDF7v3izA920tox");
            IAcsClient client = new DefaultAcsClient(profile);
            Integer templateParam = (int) ((Math.random() * 9 + 1) * 100000);
            System.out.println("=================================" + templateParam + "===================================");
            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("PhoneNumbers", phone);
            request.putQueryParameter("SignName", "静途");
            if ("1".equals(type)) {//注册
                request.putQueryParameter("TemplateCode", "SMS_189761708");
            } else if ("2".equals(type)) {            //登录
                request.putQueryParameter("TemplateCode", "SMS_189761686");
            } else if ("3".equals(type)) {            //忘记密码
                request.putQueryParameter("TemplateCode", "SMS_177538923");
            } else if ("4".equals(type)) {            //忘记密码
                request.putQueryParameter("TemplateCode", "SMS_189830543");
            }
            request.putQueryParameter("TemplateParam", "{\"code\":\"" + templateParam + "\"}");
            CommonResponse response = client.getCommonResponse(request);
            Subject subject = SecurityUtils.getSubject();
            System.out.println("=============================");
            System.out.println(subject);
            System.out.println("=============================");
            subject.getSession().setAttribute("sys.login.check.code", phone + templateParam);
            data.put("sessionId", subject.getSession().getId().toString());
            message.put("code", 0);
            message.put("data", data);
            message.put("msg", "发送成功");
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Log("用户注册")
    @PostMapping("/register")
    Map<String, Object> register(String phone, String codenum) {
        Map<String, Object> message = new HashMap<>();
        message.put("code",-1);
        if (StringUtils.isBlank(phone)) {
            message.put("msg", "手机号码不能为空");
        } else {
            Subject subject = SecurityUtils.getSubject();
            Object object = subject.getSession().getAttribute("sys.login.check.code");
            if (object != null) {
                String captcha =  object.toString();
                if (captcha == null || "".equals(captcha)) {
                    message.put("msg", "验证码已失效，请重新点击发送验证码");
                } else {
                    // session中存放的验证码是手机号+验证码
                    if (!captcha.equalsIgnoreCase(phone+codenum)) {
                        message.put("msg", "手机验证码错误");
                    } else {
                        Map<String, Object> mapP = new HashMap<String, Object>();
                        mapP.put("username", phone);
                        boolean flag = userService.exit(mapP);
                        if (flag) {
                            message.put("msg", "手机号码已存在");
                        } else {
                            OwnerUserDO udo = new OwnerUserDO();
                            udo.setUsername(phone);
                            udo.setPhone(phone);
                            udo.setPassword("111111");
                            udo.setNickname(phone);
                            udo.setExerciserFlag(0);
                            udo.setDeleteFlag(0);
                            udo.setRegisterTime(new Date());
                            message.put("code",0);
                            if (userService.save(udo) > 0) {
                                message.put("msg", "注册成功,请登录...");
                            } else {
                                message.put("msg", "注册失败");
                            }
                        }
                    }
                }
            } else {
               message.put("msg", "手机验证码错误");
            }
        }
        return message;
    }

    /**
     *
     * @param phone    手机号
     * @param password 密码 默认的登录密码是 111111
     * @param codenum  手机验证码
     * @return
     */
    @PostMapping("/login")
    Map<String, Object> login(String phone, String password, String codenum) {
        Map<String, Object> message = new HashMap<>();
        message.put("code",-1);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("=============================");
        System.out.println(subject);
        System.out.println("=============================");
        Object object = subject.getSession().getAttribute("sys.login.check.code");

        if (object != null) {
            String captcha = object.toString();
            try {
                if (captcha == null || "".equals(captcha)) {
                    message.put("msg", "验证码已失效，请重新点击发送验证码");
                } else {
                    if (!captcha.equalsIgnoreCase(phone+codenum)) {
                        message.put("msg", "手机验证码错误");
                    } else {
                        Map<String, Object> mapP = new HashMap<String, Object>();
                        mapP.put("username", phone);
                        boolean flag = userService.exit(mapP);
                        if (!flag) {
                            message.put("msg", "该手机号码未注册");
                        } else {
                            OwnerUserDO udo = userService.getbyname(phone);
                            if (udo == null || udo.getDeleteFlag() == 1) {
                                message.put("msg", "禁止登录，请联系客服");
                            } else {
                                UsernamePasswordToken token = new UsernamePasswordToken(phone, "111111");//当下的默认密码是111111
                                subject.login(token);
                                udo.setLastLoginTime(new Date());
                                userService.update(udo);
                                message.put("udo", udo);
                                message.put("code",0);
                                message.put("msg", "登录成功");
                            }
                        }
                    }
                }
            } catch (AuthenticationException e) {
                message.put("msg", "手机号或验证码错误");
            }
        }
        return message;

    }

    @Log("登出")
    @GetMapping("/logout")
    Map<String, String> logout() {
        Map<String, String> message = new HashMap<>();
        ShiroUtils.logout();
        message.put("msg", "登出成功");
        return message;
    }

    /**
     * 获取微信小程序的openid
     */
    @GetMapping("/weyOpenid")
    public Map<String,Object> weyOpenid(String code){
        //GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        String appid="wxf62bcc830d4b5bbb";
        String secret="7429970342a11ff340722e39dca53ffe";
        String url=  "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        Map<String,Object> map =   HttpUtils.doGet(url);
        return map;
    }

}
