<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath}/"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
		
		<div class="card shadow" style="background-color:#FF8C00">
			<div class="card-body text-center"> 
				<h4>${classInfoName} (${classMenuName})</h4>
			</div>
		</div>
		
		<div class="card shadow">
			<div class="card-body">
				<div class="form-group">
					<label for="assist_contents_writer_name">작성자</label>
					<input type="text" id="assist_contents_writer_name" name="assist_contents_writer_name"
					class="form-control" value="${readAssistContentsBean.assist_contents_writer_name}" disabled="disabled"/>
				</div>
				<div class="form-group">
					<label for="assist_contents_date">작성날짜</label>
					<input type="text" id="assist_contents_date" name="assist_contents_date" class="form-control"
					value="${readAssistContentsBean.assist_contents_date}" disabled="disabled"/>
				</div>
				<div class="form-group">
					<label for="assist_contents_title">제목</label>
					<input type="text" id="assist_contents_title" name="assist_contents_title" class="form-control"
					value="${readAssistContentsBean.assist_contents_title}" disabled="disabled"/>
				</div>
				<div class="form-group">
					<label for="assist_contents_text">내용</label>
					<input type="text" id="assist_contents_text" name="assist_contents_text" class="form-control"
					value="${readAssistContentsBean.assist_contents_text}" disabled="disabled"/>
				</div>
				
				<c:if test="${readAssistDataList != null}">
					<div class="form-group">
						<label for="board_file">첨부 파일: </label>
						<c:forEach var='obj' items="${readAssistDataList}">
						<br/>
							<a href="${root}class/download?class_info_idx=${class_info_idx}
							&assist_contents_idx=${assist_contents_idx}
							&assist_data_idx=${obj.assist_data_idx}
							&page=${page}&class_menu_idx=${class_menu_idx}">${obj.assist_data_filename}</a>
						</c:forEach>
					</div>
				</c:if>
				<div class="form-group">
					<div class="text-right">
						<a href="${root}class/main?class_info_idx=${class_info_idx}&page=${page}&class_menu_idx=${class_menu_idx}"
							class="btn btn-primary">학습자료 목록보기</a>
<%--						<c:if test="${loginStudentBean.student_idx == readAssistContentsBean.assist_contents_writer_idx}">--%>
							<a href="${root}class/modify?class_info_idx=${class_info_idx}&assist_contents_idx=${assist_contents_idx}&page=${page}&class_menu_idx=${class_menu_idx}"
							class="btn btn-info">수정하기</a>
							<a href="${root}class/delete?class_info_idx=${class_info_idx}&assist_contents_idx=${assist_contents_idx}&page=${page}&class_menu_idx=${class_menu_idx}"
							class="btn btn-danger">삭제하기</a>
<%--						</c:if>--%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-3"></div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>
</body>
</html>
