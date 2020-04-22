package com.tutu.springboot01helloworldquickly.component;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器,没有登录的用户就不能进入首页，对员工记录进行增删改查
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

   //目标方法执行之前，进行检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session中有没有“username”这个值
         Object user = request.getSession().getAttribute("userName");
         if (user == null){
             //未登录，返回登录页面
             request.setAttribute("msg","没有权限请先登陆");
             request.getRequestDispatcher("/index.html").forward(request,response);//去一个能跳转倒登录页的地址
             return false;
         }else {
             //已登录，放行请求
             return true;
         }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
