package com.bysj.wywx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bysj.wywx.bean.User;
import com.bysj.wywx.service.UserService;
import com.bysj.wywx.utils.HttpUtils;
import com.bysj.wywx.utils.Result;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@RestController
@Slf4j
public class LoginController {

    @Resource
    UserService userService;

    String APPID = "wxb04d5f4a11b3a3e4";
    String APP_SECRET = "b5e4aab44bef179de33a5c1623e7dd9d";
    @GetMapping("/user/login")
    public Object Login(String code) throws IOException {
        //GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET
        // &js_code=JSCODE&grant_type=authorization_code
//        System.out.println(code);
        String urlHeader ="https://api.weixin.qq.com/sns/jscode2session" ;
        Map<String,String> urlParms = new HashMap<>();
        urlParms.put("appid",APPID);
        urlParms.put("secret",APP_SECRET);
        urlParms.put("js_code",code);
        urlParms.put("grant_type","authorization_code");
        String httpResult = HttpUtils.getResponse(urlHeader,urlParms);
        System.out.println(httpResult);
        //推荐使用fastjson，先把json格式的String转换成json对象，然后根据key，就可以获取对应的value，例：
        JSONObject obj = JSONObject.parseObject(httpResult);
        String openid = obj.getString("openid");
//        System.out.println(openid);
        if(userService.findUserById(openid) == null){
            Date date = new Date();
            User user = new User(openid,"","",date);
            userService.insertUser(user);
        };
        return Result.success(httpResult);
    }
}
