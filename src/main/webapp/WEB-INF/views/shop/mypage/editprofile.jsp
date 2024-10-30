<%--
  Created by IntelliJ IDEA.
  User: eun
  Date: 24. 10. 30.
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.nhnacademy.shoppingmall.user.domain.User" %>



<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>프로필 수정</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<div class="container">
    <h2>프로필 수정</h2>
    <form action="updateProfile.do" method="post">
        <input type="hidden" name="userId" value="${user.userId}">
        <div class="form-group">
            <label for="username">이름:</label>
            <input type="text" id="username" name="userName" value="${user.userName}" required>
        </div>
        <div class="form-group">
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">비밀번호 확인:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>
        <div class="form-group">
            <label for="birth">생일</label>
            <input type="text" id="birth" name="userBirth" value="${user.userBirth}" required>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">수정 완료</button>
            <button type="button" onclick="location.href='mypage.do'" class="btn btn-secondary">취소</button>
        </div>
    </form>
</div>

</body>
</html>

