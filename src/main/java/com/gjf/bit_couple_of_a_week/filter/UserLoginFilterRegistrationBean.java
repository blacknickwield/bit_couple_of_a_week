package com.gjf.bit_couple_of_a_week.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gjf.bit_couple_of_a_week.myenum.ResultCode;
import com.gjf.bit_couple_of_a_week.util.ResponseResult;
import com.gjf.bit_couple_of_a_week.util.ResponseUtil;
import com.gjf.bit_couple_of_a_week.util.TokenUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class UserLoginFilterRegistrationBean extends FilterRegistrationBean<Filter> {
    @Override
    public Filter getFilter() {
        setOrder(1);
        return new UserLoginFilter();
    }

    class UserLoginFilter implements Filter{

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String servletPath = request.getServletPath();

            // 允许跨域
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");

            if (servletPath.startsWith("/user/login")) {
                filterChain.doFilter(request, servletResponse);
            } else {
                // 检查Token
                // 检查是否已登录
                String token = request.getHeader("token");
                if (token != null) { // 已经登录，检查token
                    Map<String, String> userInfo = TokenUtil.getInfoFromToken(token);
                    if (userInfo != null) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        ResponseUtil.out((HttpServletResponse) servletResponse, new ResponseResult().code(401).message("无效token，请先登录"));
                    }

                } else {
                    ResponseUtil.out((HttpServletResponse) servletResponse, new ResponseResult().code(401).message("未携带token"));
                }
            }
        }
    }
}
