package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointChannelRequest extends ChannelRequest {

    private final ServletContext servletContext;

    public PointChannelRequest(ServletContext servletContext){
        this.servletContext = servletContext;
    }

    @Override
    public void execute() {
        DbConnectionThreadLocal.initialize();
        //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.

        try{
            User user = (User) servletContext.getAttribute("user");
            int pointsToadd = (int) servletContext.getAttribute("points");

            log.debug("Accruing points for user: {}, Points: {}", user.getUserId(), pointsToadd);

            user.setUserPoint(user.getUserPoint() + pointsToadd);
            log.info("Points accrued successfully for user: {}", user.getUserId());
        }  catch (Exception e){
            log.error("Error during point accrual: ", e);
        } finally {
            DbConnectionThreadLocal.reset();
        }
        log.debug("pointChannel execute");
    }
}