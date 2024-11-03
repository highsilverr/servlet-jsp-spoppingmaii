<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>관리자 페이지</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<header>
  <h1>관리자 대시보드</h1>
  <nav>
    <ul>
      <li><a href="<c:url value='/index.do' />">홈</a></li>
      <li><a href="<c:url value='/admin/users.do' />">사용자 관리</a></li>
      <li><a href="<c:url value='/admin/products.do' />">상품 관리</a></li>
      <li><a href="<c:url value='/admin/orders.do' />">주문 관리</a></li>
      <li><a href="<c:url value='/logout.do' />">로그아웃</a></li>
    </ul>
  </nav>
</header>

<div class="container">
  <aside>
    <h2>메뉴</h2>
    <ul>
      <li><a href="<c:url value='/admin/users.do' />">사용자 관리</a></li>
      <li><a href="<c:url value='/admin/products.do' />">상품 관리</a></li>
      <li><a href="<c:url value='/admin/orders.do' />">주문 관리</a></li>
      <li><a href="<c:url value='/admin/statistics.do' />">통계</a></li>
    </ul>
  </aside>

  <main>
    <div class="album py-5 bg-light">
      <div class="container">
        <jsp:include page="${layout_content_holder}" />
      </div>
    </div>
  </main>
</div>

<footer>
  <p>© 2024 쇼핑몰. 모든 권리 보유.</p>
</footer>
</body>
</html>
