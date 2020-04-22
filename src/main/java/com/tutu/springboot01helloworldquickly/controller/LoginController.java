package com.tutu.springboot01helloworldquickly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping({"/main.html"})
    public String index() {
        return "dashboard";
    }

    @PostMapping("/user/login")//表示要映射一个post请求
    /*@RequestParam("username") 从请求参数里获取username的值*/
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //用户只要登录了就会存在在session中
            session.setAttribute("userName",username);
            //登陆成功，防止表单重复提交，可以从定向到主页(重定向只能是重定向到一个路径，不能是页面)
            return "redirect:/main.html";
        }else {
            //登陆失败
            session.invalidate();//清空session
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

}
