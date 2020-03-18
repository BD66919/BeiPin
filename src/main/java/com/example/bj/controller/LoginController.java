package com.example.bj.controller;

import com.example.bj.mapper.StockMapper;
import com.example.bj.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Jiangzhendong
 * @Description 该类用于登录功能实现
 *
 */
@Controller
public class LoginController {

    public static String getMD5String(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    StockMapper stockMapper;

    String userName;
    String userPwd;

    @GetMapping("/index")
    public String index() {
        return "login";
    }

    @PostMapping(value = "/userLogin")
    public String userLogin(@RequestParam("loginName") String loginName,
                            @RequestParam("loginPwd") String loginPwd,
                            HttpSession session,
                            Map<String,Object> map){
        String Pwd = getMD5String(loginPwd);
        userName = userMapper.getUserName(loginName);
        userPwd = userMapper.getUserPwd(Pwd,loginName);
        System.out.println(Pwd);
        if (userName == null || userPwd == null){
            map.put("message","用户名或者密码错误");
            return "login.html";
        }else if (userName.equals(loginName) && userPwd.equals(Pwd)){
            session.setAttribute("userName",userName);
            return "redirect:/beipin";
        }else{
            map.put("message","用户名或者密码错误");
            return "login.html";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "login.html";
    }


}
