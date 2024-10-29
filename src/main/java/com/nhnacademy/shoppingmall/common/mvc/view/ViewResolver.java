package com.nhnacademy.shoppingmall.common.mvc.view;

import java.util.Objects;

public class ViewResolver {

    public static final String DEFAULT_PREFIX="/WEB-INF/views/";
    public static final String DEFAULT_POSTFIX=".jsp";
    public static final String REDIRECT_PREFIX="redirect:";
    public static final String DEFAULT_SHOP_LAYOUT="/WEB-INF/views/layout/shop.jsp";
    public static final String DEFAULT_ADMIN_LAYOUT="/WEB-INF/views/layout/admin.jsp";
    public static final String LAYOUT_CONTENT_HOLDER = "layout_content_holder";

    private final String prefix;
    private final String postfix;

    public ViewResolver(){
        this(DEFAULT_PREFIX,DEFAULT_POSTFIX);
    }
    public ViewResolver(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public  String getPath(String viewName){
        //todo#6-1  postfix+viewNAme+postfix 반환 합니다.
        String formattedPrefix = prefix.endsWith("/") ? prefix : prefix + "/";
        String formattedViewName = viewName.startsWith("/") ? viewName.substring(1) : viewName;
        String path = formattedPrefix + formattedViewName + postfix;

        return path.replaceAll("//+", "/");
    }

    public boolean isRedirect(String viewName){
        //todo#6-2 REDIRECT_PREFIX가 포함되어 있는지 체크 합니다.
        // viewName이 null인 경우 false 반환
        if (viewName == null) {
            return false; // null인 경우 리다이렉트 아님
        }

        // viewName에 REDIRECT_PREFIX가 포함되어 있는지 체크
        return viewName.toLowerCase().startsWith(REDIRECT_PREFIX);
    }

    public String getRedirectUrl(String viewName){
        //todo#6-3 REDIRECT_PREFIX를 제외한 url을 반환 합니다.

        if (viewName == null || viewName.isEmpty()) {
            return ""; // 빈 문자열 반환
        }

        // REDIRECT_PREFIX가 뷰 이름에 포함되어 있으면 제거
        if (viewName.toLowerCase().startsWith(REDIRECT_PREFIX)) {
            return viewName.substring(REDIRECT_PREFIX.length()); // prefix 제거한 문자열 반환
        }

        // REDIRECT_PREFIX가 없으면 원래 뷰 이름 반환
        return viewName;
    }

    public String getLayOut(String viewName){

        /*todo#6-4 viewName에
           /admin/경로가 포함되었다면 DEFAULT_ADMIN_LAYOUT 반환 합니다.
           /admin/경로가 포함되어 있지않다면 DEFAULT_SHOP_LAYOUT 반환 합니다.
        */

        // viewName이 null이거나 빈 문자열인 경우 기본 레이아웃 반환
        if (viewName == null || viewName.isEmpty()) {
            return DEFAULT_SHOP_LAYOUT; // 기본 레이아웃 반환
        }

        // viewName에 /admin/ 경로가 포함되어 있는지 확인
        if (viewName.contains("/admin/")) {
            return DEFAULT_ADMIN_LAYOUT; // 관리자 레이아웃 반환
        }

        // 기본 쇼핑 레이아웃 반환
        return DEFAULT_SHOP_LAYOUT;
    }
}
