package com.nhnacademy.shoppingmall.controller.register;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/registerAction.do")
public class RegisterPostController implements BaseController {

    private final UserService userService;

    public RegisterPostController() {
        // UserService와 UserRepository의 구현체를 초기화합니다.
        UserRepository userRepository = new UserRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // 사용자가 제출한 데이터를 가져옵니다.
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String birth = req.getParameter("birth");
        String name = req.getParameter("name");

        // 비밀번호 확인
        if (!password.equals(confirmPassword)) {
            req.setAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "shop/login/register"; // 회원가입 폼으로 다시 이동
        }

        // 사용자 등록 로직
        try {
            User user = new User(id, name ,password, birth, User.Auth.ROLE_USER,1_000_000 , LocalDateTime.now(), null);
            userService.saveUser(user);
            log.debug("사용자 {}가 성공적으로 등록되었습니다.", id);
            return "redirect:/login.do"; // 성공적으로 등록되면 로그인 페이지로 리다이렉트
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생: {}", e.getMessage());
            req.setAttribute("errorMessage", "회원가입 중 오류가 발생했습니다. 다시 시도해 주세요.");
            return "shop/login/register"; // 오류 발생 시 회원가입 폼으로 다시 이동
        }
    }
}
