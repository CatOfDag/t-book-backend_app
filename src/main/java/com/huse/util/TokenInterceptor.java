package com.huse.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //token验证
        if (token != null && JwtToken.analysis(token)){

            return true;
        }else {
            System.out.println("error");
            response.sendRedirect("/basic/error");
            return false;
        }
    }
}