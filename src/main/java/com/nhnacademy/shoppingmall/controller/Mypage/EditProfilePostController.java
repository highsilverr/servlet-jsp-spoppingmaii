package com.nhnacademy.shoppingmall.controller.Mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.dto.UserUpdateDto;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/updateProfile.do")
public class EditProfilePostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("userId");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String birth = req.getParameter("userBirth");
        String name = req.getParameter("userName");


        UserUpdateDto userUpdateDto = new UserUpdateDto(id,name,password,birth);
        userService.updateUser(userUpdateDto);
        return "shop/mypage/mypage";
    }
}