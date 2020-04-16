package com.example.bj.controller;

import com.example.bj.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiangzhendong
 * @Description 该类用于登录功能实现
 *
 */
@Controller
public class LoginController {

    private final UserMapper userMapper;

    //构造器自己会启用
    public LoginController(UserMapper userMapper) {
        this.userMapper = userMapper;

    }

    private static String getMD5String(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    private String userName;

    @GetMapping("/index")
    public String index() {
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(@RequestParam("loginName") String loginName,
                            @RequestParam("loginPwd") String loginPwd,
                            HttpSession session,
                            Model model,
                            Map<String,Object> map){
        String Pwd = getMD5String(loginPwd);
        userName = userMapper.getUserName(loginName);
        String userPwd = userMapper.getUserPwd(Pwd, loginName);
        if (userName == null || userPwd == null){
            map.put("message","密码错误");
            model.addAttribute("userName",loginName);
            model.addAttribute("isPwd","true");
            model.addAttribute("isName","true");
            return "login.html";
        }else if (userName.equals(loginName) && userPwd.equals(Pwd)){
            session.setAttribute("userName",userName);
            return "redirect:/beipin";
        }else{
            map.put("message","密码错误");
            model.addAttribute("userName",loginName);
            model.addAttribute("isPwd","true");
            model.addAttribute("isName","true");
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


    @PostMapping(value = "/login1")
    @ResponseBody
    public Map<String,Object> login1(HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        String loginName = request.getParameter("loginName");
        userName = userMapper.getUserName(loginName);
        System.out.println(userName);
        if (userName == null )
            map.put("msg","用户不存在");
        else
            map.put("msg","");
        return map;
    }

    @RequestMapping("/success")
    public String success(){
        return "redirect:/beipin";
    }


    @PostMapping("/login123")
    @ResponseBody
    public Object login(String id,String pwd) {
        Map<String, Object> params= new HashMap<>();
        params.put("id", id);
        params.put("pwd", pwd);
        return params;
    }



}
