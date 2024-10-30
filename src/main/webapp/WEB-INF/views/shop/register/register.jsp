<%--
  Created by IntelliJ IDEA.
  User: eun
  Date: 24. 10. 29.
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .container {
      margin-top: 50px;
      max-width: 500px;
    }
    h2 {
      margin-bottom: 30px;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>회원가입</h2>
  <form action="registerAction.do" method="post">
    <div class="form-group">
      <label for="id">id:</label>
      <input type="text" class="form-control" id="id" name="id" required>
    </div>
    <div class="form-group">
      <label for="password">비밀번호:</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <div class="form-group">
      <label for="confirmPassword">비밀번호 확인:</label>
      <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
    </div>
    <div class="form-group">
      <label for="name">이름:</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="form-group">
      <label for="birth">생일</label>
      <input type="text" class="form-control" id="birth" name="birth" required>
    </div>
    <button type="submit" class="btn btn-primary">회원가입</button>
  </form>
  <div class="mt-3">
    <a href="login.jsp">이미 회원이신가요? 로그인하기</a>
  </div>
</div>
</body>
</html>

