<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>学生管理システム</title>
		<span class="teacher-name">${sessionScope.teacher.name}様</span>
    	<a href="Logout.action" class="logout">ログアウト</a>
</head>
<body>

<div class="container">

<header>
    <h1 class="h1">得点管理システム</h1>
</header>

<div class="content-wrapper">
    <jsp:include page="base.jsp" />
    <article>
        <h2 class="studentsystem">成績登録</h2>

        <div class="contents">
            <form action="TestListSubjectExecute.action" method="get" class="form-inline">
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

			    <div class="col-4">
			        <label class="form-label">回数</label>
			        <select class="form-select" name="f4">
			            <option value="0">--------</option>
			            <c:forEach var="num" items="${numSet}">
			                <option value="${num}">${num}</option>
			            </c:forEach>
			        </select>
			    </div>

			    <div class="col-2 button">
			        <button type="submit" class="btn btn-primary">検索</button>
			    </div>
			</form>
        </div>

        <c:choose>
            <c:when test="${not empty testList}">
                <div>科目: ${f3} (${f4}回)</div>
                <table class="table">
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>点数</th>
                    </tr>
                    <c:forEach var="test" items="${testList}">
                        <tr>
                            <td>${test.entYear}</td>
                            <td>${test.classNum}</td>
                            <td>${test.no}</td>
                            <td>${test.student}</td>
                            <td><input type="text" name="point_${test.testNum}" value="${test.point}"></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>

    </article>
</div>

<footer class="footer">
    <p>@2023 TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>
</html>
