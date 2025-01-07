<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath}/"/>
<script>
	alert('저장되었습니다.');
	location.href = '${root}board/read?class_info_idx=${writeContentBean.content_class_idx}&content_idx=${writeContentBean.content_idx}'
</script>