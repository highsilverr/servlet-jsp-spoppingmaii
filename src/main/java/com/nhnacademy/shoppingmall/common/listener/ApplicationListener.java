package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        DbConnectionThreadLocal.initialize();

        ServletContext servletContext = sce.getServletContext();

        User admin = new User("admin", "admin", "12345", "020718", User.Auth.ROLE_ADMIN,1_000_000 , LocalDateTime.now()
                , null);
        User user = new User("user", "user", "12345", "020718", User.Auth.ROLE_USER,1_000_000 ,LocalDateTime.now()
                , null);

        User adminCheck = userService.getUser("admin");
        User userCheck = userService.getUser("user");
        if(Objects.isNull(adminCheck)){
            userService.saveUser(admin);
        }

        if(Objects.isNull(userCheck)){
            userService.saveUser(user);
        }


        DbConnectionThreadLocal.reset();
    }
}
