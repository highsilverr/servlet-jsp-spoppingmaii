package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.

        // 사용자 입력 정보
        String userId = req.getParameter("user_id");
        String password = req.getParameter("user_password");

        // 입력 값 null 또는 비어있는지 확인
        if (userId == null || userId.isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("loginError", "User ID and password must not be empty.");
            return "shop/login/login_form";  // 빈 입력에 대한 처리
        }

        // 사용자 인증 확인
        if (userService.doLogin(userId, password) != null) {
            // 세션 생성 및 설정
            HttpSession session = req.getSession(true);  // 세션이 없을 경우 새로 생성
            session.setAttribute("user", userService.doLogin(userId, password));
            log.debug("세션생성: {}", session.getAttribute("user"));
            session.setMaxInactiveInterval(60 * 60);  // 세션 유효 시간 설정 (60분)

            return "redirect:/index.do";  // 로그인 성공 후 리다이렉트
        } else {
            req.setAttribute("loginError", "Invalid user ID or password.");
            return "shop/login/login_form";  // 로그인 실패 시 로그인 폼 페이지로 이동
        }
    }
}
