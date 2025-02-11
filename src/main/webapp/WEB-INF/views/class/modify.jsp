<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var='root' value="${pageContext.request.contextPath}/"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script>


    function add_file(){
        // $("#_file").append("<br>"+"<input type='file' name='upload_file" + cnt + "'/>");
        $("#_file").append("<br>"+"<input type='file' name='upload_file'/>");
    }

    function del_file() {
        $("#_file").remove();
        $("#_file").append("<br>"+"<input type='file' name='upload_file'/>");
    }


</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style = "margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card shadow" style="background-color:#FF8C00">
                <div class="card-body text-center">
                    <h4>${classInfoName}(${classMenuName})</h4>
                </div>
            </div>

            <div class="card shadow">
                <div class="card-body">
                    <form:form action='${root}class/modify_post' method='post' modelAttribute="modifyAssistContentsBean"
                               enctype="multipart/form-data">
                        <form:hidden path="assist_contents_info_idx" />
                        <input type='hidden' name='class_menu_idx' value='${class_menu_idx}'/>
                        <input type='hidden' name='class_info_idx' value='${class_info_idx}'/>
                        <input type='hidden' name='assist_contents_idx' value='${assist_contents_idx}'/>
                        <input type='hidden' name='origin_file_name' value='${readAssistDataList[0].assist_data_filename}'/>

                        <div class="form-group">
                            <form:label path="assist_contents_title">제목</form:label>
                            <form:input path="assist_contents_title" class="form-control" />
                            <form:errors path="assist_contents_title" style="color:red" />
                        </div>

                        <div class="form-group">
                            <form:label path="assist_contents_text">내용</form:label>
                            <form:input path="assist_contents_text" class="form-control" rows="10" />
                            <form:errors path="assist_contents_text" style="color:red" />
                        </div>

                        <div class="form-group">
                            <c:choose>
                                <c:when test="${readAssistDataList == null}">
                                    <div class="form-group-append" id="_file">
                                        <button type ="button" class="btn btn-light"
                                                onclick='add_file()'>첨부파일 추가 </button>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var='obj' items="${readAssistDataList}">
                                        <div class="form-group" id="_file">
                                            ${obj.assist_data_filename} &nbsp;
                                            <span style="color: #FF0000" onclick="del_file()">X</span>
                                        </div>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            <div id ="_file"></div>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <form:button class='btn btn-primary'> 수정완료 </form:button>
                                <a href="${root}class/read?class_info_idx=${class_info_idx}&assist_contents_idx=${assist_contents_idx}&class_menu_idx=${class_menu_idx}"
                                   class="btn btn-info">취소</a>
                            </div>
                        </div>

                    </form:form>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>