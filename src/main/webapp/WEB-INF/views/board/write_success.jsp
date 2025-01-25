<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath}/"/>
<script>
	alert('저장되었습니다.');
	location.href = '${root}class/main?class_info_idx=${class_info_idx}&class_menu_idx=${class_menu_idx}'
</script>