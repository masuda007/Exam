<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>科目管理システム</title>
</head>
<body>

<div class="container">

<header>
    <h1 class="h1">得点管理システム</h1>
    		<span class="teacher-name">${sessionScope.teacher.name}様</span>
    	<a href="Logout.action" class="logout">ログアウト</a>
</header>

<div class="content-wrapper">
    <jsp:include page="base.jsp" />
    <article>
        <h2 class="subjects">科目管理</h2>
        <div class="new"><a href="SubjectRegister.action">新規登録</a></div>

        <div class="contents">
            <form action="SubjectList.action" method="get" class="form-inline">


            </form>
        </div>

        <c:choose>
            <c:when test="${subjects.size() > 0}">
                <div>検索結果: ${subjects.size()}件</div>
                <table class="table">
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th></th>
                    </tr>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>

                            <td>
                                <a href="SubjectUpdate.action?id=${subject.id}">変更</a>
                                <a href="SubjectDelete.action?id=${subject.id}">削除</a>
                            </td>
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
