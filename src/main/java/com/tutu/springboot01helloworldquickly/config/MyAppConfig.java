package com.tutu.springboot01helloworldquickly.config;

import com.tutu.springboot01helloworldquickly.component.LoginHandlerInterceptor;
import com.tutu.springboot01helloworldquickly.component.MyLocaleResolver;
import com.tutu.springboot01helloworldquickly.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Configuration 指明当前类是一个配置类（就是来代替之前的Spring配置文件）
 * @Bean 将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名（就是来代替之前Spring配置文件中的<bean></bean>标签）
 */
@Configuration
public class MyAppConfig implements WebMvcConfigurer {
    @Bean
    public HelloService helloService() {
        System.out.println("配置类@Bean给容器中添加组件了");
        return new HelloService();
    }

    //将自己编写的LocalResolver（国际化配置文件）加到容器中
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("login");
    }

    //让拦截器生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                    .addPathPatterns("/**")
                    .excludePathPatterns("/","/index.html","/user/login");
        //SpringBoot已经做好了静态资源映射，所以不用处理静态资源，也能正常访问
//                    .excludePathPatterns("/assets/**");

    }
}
