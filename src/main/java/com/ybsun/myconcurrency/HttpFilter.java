package com.ybsun.myconcurrency;

import com.ybsun.myconcurrency.examples.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Sun
 */
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 通常使用 HttpServletRequest 进行url的获取与操作
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // 打印日志方便和后文验证
        log.info("do filter, {}, {}", Thread.currentThread().getId(), httpServletRequest.getServletPath());

        // 设置当前线程值
        RequestHolder.set(Thread.currentThread().getId());
        // 保证拦截器执行完，同时保证请求继续被处理
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
