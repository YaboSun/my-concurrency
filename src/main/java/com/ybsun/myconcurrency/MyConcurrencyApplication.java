package com.ybsun.myconcurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Sun
 */
@SpringBootApplication
public class MyConcurrencyApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }

    public static void main(String[] args) {
        SpringApplication.run(MyConcurrencyApplication.class, args);
    }

    /**
     * 用于对自定义的 Filter 进行配置
     * 相当于配置 RequestHolder 中的 set 方法
     * @return registrationBean 实例化对象
     */
    @Bean
    public FilterRegistrationBean httpFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new HttpFilter());
        // 配置要拦截的 url 模式
        registrationBean.addUrlPatterns("/threadLocal/*");
        return registrationBean;
    }
}
