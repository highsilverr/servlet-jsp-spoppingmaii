<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.nhnacademy.shoppingmall.user.domain.User" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<div class="container">
    <h2>마이 페이지</h2>

    <div class="profile-info">
        <p><strong>id:</strong> ${user.userId}</p>
        <p><strong>이름:</strong> ${user.userName}</p>
        <p><strong>생일:</strong> ${user.userBirth}</p>
        <p><strong>포인트: </strong> ${user.userPoint}</p>
    </div>

    <div class="profile-actions">
        <button onclick="location.href='editprofile.do'" class="btn btn-primary">프로필 수정</button>
        <button onclick="location.href='logout.do'" class="btn btn-secondary">로그아웃</button>
    </div>
</div>

</body>
</html>
