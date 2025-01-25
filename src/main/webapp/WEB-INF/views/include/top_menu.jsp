<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 상단 메뉴 부분, nav:navigations links(다른 페이지 또는 현재 페이지의 다른 부분과 연결) -->
	<nav
		class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">
		<a class="navbar-brand" href="${root}/main">홈</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navMenu">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- div: "순수" 컨테이너로서 아무것도 표현하지 않지만 다른 요소들을 묶어 class나 id 속성으로 꾸미기 쉽도록 돕거나, 
		문서의 특정 구역이 다른 언어임을 표시(lang 속성 사용)하는 등의 용도로 사용할 수 있다. -->
		<div class="collapse navbar-collapse" id="navMenu">
		  	<!-- ul:정렬되지않은 목록, ol:정렬된 목록 -->
			<ul class="navbar-nav">
				<c:forEach var='obj' items='${topMenuList }'>
				  	<!-- li:목록의 항목 -->
					<li class="nav-item">
					<a href="${root}/main?class_info_idx=${obj.class_info_idx}"
							class="nav-link">${obj.class_info_name }</a>
					</li>
				</c:forEach>
			</ul>

			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a href="${root}user/login"
					class="nav-link">로그인</a></li>
				<li class="nav-item"><a href="${root}user/join"
					class="nav-link">회원가입</a></li>
				<li class="nav-item"><a href="${root}user/logout"
					class="nav-link">로그아웃</a></li>
			</ul>
		</div>
	</nav>

</body>
</html>