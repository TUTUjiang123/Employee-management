package com.tutu.springboot01helloworldquickly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//这个类的所有方法返回的数据直接写给浏览器（如果是对象转化为json数据）[RestController]
@Controller
public class IndexController {


    @RequestMapping({"/", "/index.html"})
    public String index() {
        return "login";
    }
}

