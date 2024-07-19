<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="css.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>科目削除</title>
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
        <h2>科目削除</h2>
        <p>本当にこの科目を削除しますか？</p>
        <form action="SubjectDeleteExecute.action" method="post">
            <input type="hidden" name="cd" value="${subject.cd}">
            <input type="hidden" name="school" value="${subject.school}">
            <input type="submit" value="削除">
        </form>
        <div class="back"><a href="SubjectList.action">戻る</a></div>
    </article>
</div>

<footer class="footer">
    <p>@2023 TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>
</html>
