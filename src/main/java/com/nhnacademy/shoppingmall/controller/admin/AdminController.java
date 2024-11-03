package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = {"/admin/users.do"})
public class AdminController implements BaseController{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        // 데이터베이스에서 유저 목록 가져오기

        // JSP에서 접근할 수 있도록 request에 유저 목록 설정
        req.setAttribute("userList", userList);

        return "shop/admin/users";
    }
}