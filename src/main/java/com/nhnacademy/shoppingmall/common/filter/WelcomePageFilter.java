package com.nhnacademy.shoppingmall.common.filter;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "welcomePageFilter",urlPatterns = "/", initParams = @WebInitParam(name = "welcomePage", value = "/index.do"))
public class WelcomePageFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#9 /요청이 오면 welcome page인 index.do redirect 합니다.
        log.debug("welcomepage  filter : {}", req.getRequestURI());

        if(req.getServletPath().equals("/")){
            res.sendRedirect(getInitParameter("welcomePage"));
            return;
        }
        chain.doFilter(req,res);

    }
}
