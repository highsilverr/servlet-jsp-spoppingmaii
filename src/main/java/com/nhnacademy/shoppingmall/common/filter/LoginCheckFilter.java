package com.nhnacademy.shoppingmall.common.filter;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = {"/mypage/*"})
public class LoginCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.
        log.debug("login-check-filter:{}",req.getRequestURI());
        HttpSession session = req.getSession(false); // 세션이 없을 때 새로 생성하지 않음
        if (session == null || session.getAttribute("user") == null) {
            // 로그인하지 않은 경우
            log.info("Unauthorized access attempt to: {}", req.getRequestURI());
            res.sendRedirect("/login"); // 로그인 페이지로 리다이렉트
            return;
        }

        // 로그인한 경우 다음 필터 또는 서블릿으로 전달
        chain.doFilter(req, res);
    }
}