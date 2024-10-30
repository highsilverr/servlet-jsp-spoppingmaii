package com.nhnacademy.shoppingmall.controller.Mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/mypage.do")
public class Mypage implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "shop/mypage/mypage";
    }

}
