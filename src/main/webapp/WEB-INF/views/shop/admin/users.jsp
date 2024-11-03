<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">
  <h2>사용자 목록</h2>
  <!-- 사용자 목록 테이블 -->
  <table border="1" cellspacing="0" cellpadding="10">
    <thead>
    <tr>
      <th>아이디</th>
      <th>이름</th>
      <th>이메일</th>
      <th>역할</th>
      <th>관리</th>
    </tr>
    </thead>
    <tbody>
    <!-- 사용자 데이터 반복 -->
    <c:forEach var="user" items="${userList}">
      <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td>${user.role}</td>
        <td>
          <a href="<c:url value='/admin/editUser.do?id=${user.id}' />">수정</a> |
          <a href="<c:url value='/admin/deleteUser.do?id=${user.id}' />" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
