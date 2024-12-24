<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>iL</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<!-- Contents -->
<div class="container" style="margin-top:100px">
	<div class="row">
		<c:forEach var="obj" items="${topMenuList}">
			<div class="col-xs-3" style="padding-right: 5px; padding-left: 0px;
					margin-right: auto; margin-left: auto;">
				<a href="${root}/class/main?class_info_idx=${obj.class_info_idx}">
					<img src="image/subject/${obj.class_info_idx}.jpg" width="213px" height="142px">
				</a>
				<br/>
				<a href="${root}/class/main?class_info_idx=${obj.class_info_idx}"
				   		style="text-align: center; line-height: 1.8;">
					${obj.class_info_name}
				</a>
			</div>
		</c:forEach>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>






    