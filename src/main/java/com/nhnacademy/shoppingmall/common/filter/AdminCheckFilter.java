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
@WebFilter(filterName = "adminCheckFilter", urlPatterns = "/admin/*")
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        log.debug("filter-admin-check:{}", req.getRequestURI());

        HttpSession session = req.getSession(false);
        if (session == null || !"ROLE_ADMIN".equals(session.getAttribute("role"))) {
            // 사용자가 ROLE_USER이거나 세션이 없는 경우
            log.info("Unauthorized access attempt to admin area: {}", req.getRequestURI());
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        chain.doFilter(req,res);
    }
}
