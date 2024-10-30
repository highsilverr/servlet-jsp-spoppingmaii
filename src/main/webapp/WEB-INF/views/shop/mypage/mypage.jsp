<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.nhnacademy.shoppingmall.user.domain.User" %>

<%
    // 세션에서 사용자 정보를 가져오기
    session = request.getSession();
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp"); // 로그인 필요 시 로그인 페이지로 리디렉션
        return;
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <link rel="stylesheet" href="styles.css"> <%-- 필요한 스타일 파일 추가 --%>
</head>
<body>

<div class="container">
    <h2>마이 페이지</h2>

    <div class="profile-info">
        <p><strong>id:</strong> <%= user.getUserId() %></p>
        <p><strong>이름:</strong> <%= user.getUserName() %></p>
        <p><strong>생일:</strong> <%= user.getUserBirth() %></p>
        <p><strong>포인트: </strong> <%= user.getUserPoint() %></p>
    </div>

    <div class="profile-actions">
        <button onclick="location.href='editprofile.do'" class="btn btn-primary">프로필 수정</button>
        <button onclick="location.href='logout.do'" class="btn btn-secondary">로그아웃</button>
    </div>
</div>

</body>
</html>
