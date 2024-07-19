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
        <h2 class="studentsystem">科目管理</h2>
        <div class="new"><a href="SubjectCreate.action">新規登録</a></div>

        <c:choose>
            <c:when test="${not empty subjects}">
                <table class="table">
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>
                            <td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
                            <td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <div>科目情報が存在しませんでした。</div>
            </c:otherwise>
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
