/*
package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求路径
        String requestURI = request.getRequestURI();

        // 判断是否为登录请求,是的话就放行 （判断路径中的是否包含login)
        if(requestURI.contains("/login")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }

        // 获取请求头的token
        String token = request.getHeader("token");

        // 判断token是否存在，不存在就说明用户还未登录，返回401
        if(token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 校验令牌，校验失败就返回401
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //校验通过就放行
        log.info("令牌合法，响应401");
        filterChain.doFilter(request, response);
    }
}
*/
