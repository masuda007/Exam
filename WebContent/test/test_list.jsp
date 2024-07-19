<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>学生管理システム</title>
</head>
<body>

<div class="container">

<header>
    <h1 class="h1">得点管理システム</h1>
</header>

<div class="content-wrapper">
    <jsp:include page="base.jsp" />
    <article>
        <h2 class="studentsystem">成績参照</h2>

        <div class="contents">
            <form action="TestRegist.action" method="get" class="form-inline">
            	<div>科目情報</div>
			    <div class="col-4">
			        <label class="form-label">入学年度</label>
			        <select class="form-select" name="f1">
			            <option value="0">--------</option>
			            <c:forEach var="year" items="${yearSet}">
			                <option value="${year}">${year}</option>
			            </c:forEach>
			        </select>
			    </div>

			    <div class="col-4">
			        <label class="form-label">クラス</label>
			        <select class="form-select" name="f2">
			            <option value="0">--------</option>
			            <c:forEach var="classNum" items="${classNumSet}">
			                <option value="${classNum}">${classNum}</option>
			            </c:forEach>
			        </select>
			    </div>

			    <div class="col-4">
			        <label class="form-label">科目</label>
			        <select class="form-select" name="f3">
			            <option value="0">--------</option>
			            <c:forEach var="subject" items="${subjectsSet}">
			                <option value="${subject}">${subject}</option>
			            </c:forEach>
			        </select>
			    </div>

			    <div class="col-2 button">
			        <button type="submit" class="btn btn-primary">検索</button>
			    </div>
			</form>


			<form action="TestListStudentExecute.action" method="get" class="form-inline">
				<div>学生情報</div>

			    <div class="col-4">
			        <label class="form-label">学生番号</label>
			        <input type="text" required name="studentNo">
			    </div>

			     <div class="col-2 button">
			        <button type="submit" class="btn btn-primary">検索</button>
			    </div>

			</form>

        </div>

                <div>科目情報を選択または学生情報を入力して検索をクリックしてください</div>


    </article>
</div>

<footer class="footer">
    <p>@2023 TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>
</html>
