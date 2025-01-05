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
<!-- 클래스 게시글 리스트 -->
<div class="container-fluid" style="margin-top:100px; text-align: center; ">
    <div class="container" style="padding-right: 00px; padding-left: 0px; margin-right: auto; margin-left: auto;
text-align: center; margin-top:100px;margin-bottom:25px; ">
        <h4>${classInfoName }(${classMenuName })</h4>
    </div>
    <div class="row">
        <!-- 왼쪽 메뉴 리스트 -->
        <div class='col-sm-3'>
            <table class="table table-hover board_list" style="text-align: center;">
                <thead>
                <tr>
                    <th class="text-center d-none d-md-table-cell">메뉴</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var='obj' items="${classMenuList }">
                    <tr>
                        <th><a class="nav-link" href='${root }class/main?class_info_idx=${class_info_idx}
                                &class_menu_idx=${obj.class_menu_idx}'>${obj.class_menu_name }</a></th>
                    </tr>
                </c:forEach>
                <tr>
                    <th><a href="#"><img src="${root}image/subject/${class_info_idx}.jpg"
                    							style="display: block; width:100%;"/></a></th>
                </tr>
                </tbody>
            </table>
        </div>
        
        <!-- 오른쪽 글 리스트 -->
        <div class='col-sm-9' style="padding-right: 05px; padding-left: 0px; margin-right: auto; margin-left: auto;">
            <table class="table table-hover board_list">
                <thead>
                <tr>
                    <th class="text-center d-none d-md-table-cell">글번호</th>
                    <th class="w-50">제목</th>
                    <th class="text-center d-none d-md-table-cell">작성자</th>
                    <th class="text-center d-none d-md-table-cell">작성날짜</th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach var='obj' items="${assistContentsList}">
                	<tr>
                		<td class="text-center d-none d-md-table-cell"> ${obj.assist_contents_idx}</td>
                		<td> <a href ='${root}class/read?class_info_idx=${class_info_idx}
                		&assist_contents_idx=${obj.assist_contents_idx}&class_menu_idx=${class_menu_idx}'>
       						${obj.assist_contents_title}</a></td>
                		<td class="text-center d-none d-md-table-cell">${obj.assist_contents_writer_name}</td>
                		<td class="text-center d-none d-md-table-cell">${obj.assist_contents_date} </td>
                	</tr>
                	</c:forEach>
                	<tr><td></td><td></td><td></td><td></td></tr>
                </tbody>
                
            </table>
            <c:if test="${loginStudentBean.student_idx == 1 }">
                <div class="text-right">
                    <a href="${root}class/write?class_info_idx=${class_info_idx}&class_menu_idx=${class_menu_idx}\"
                       class="btn btn-primary">자료올리기</a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>
</body>
</html>
