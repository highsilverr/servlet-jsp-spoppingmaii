package com.nhnacademy.shoppingmall.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
public class CharacterEncodingFilter  implements Filter {
    private String encoding;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //todo#8 UTF-8 인코딩, initParams의 encoding parameter value값을 charset 으로 지정합니다.
        if (encoding != null && !encoding.isEmpty()) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }

        // 다음 필터로 요청을 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // initParams의 encoding parameter value값을 가져옵니다.
        encoding = filterConfig.getInitParameter("encoding");
    }

}