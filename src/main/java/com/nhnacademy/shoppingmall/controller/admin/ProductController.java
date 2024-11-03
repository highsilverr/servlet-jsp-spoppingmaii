package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET,value = {"/admin/products.do"})
public class ProductController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        return "shop/admin/products";
    }
}
