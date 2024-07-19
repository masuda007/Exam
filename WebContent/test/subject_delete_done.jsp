<<<<<<< HEAD
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目削除完了</title>
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
        <h2>科目削除完了</h2>
        <p>科目が正常に削除されました。</p>
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
=======
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<html>
	<head><meta charset="UTF-8"></head>
</html>
<body>

<header>
    	<h1 class="h1">得点管理システム</h1>
</header>

<div class="container">

<div class="content-wrapper">
 	<jsp:include page="base.jsp" />
		<article>
			<h2>科目情報削除</h2>
			<div class="label"><label>削除が完了しました</label></div>
			<div><a href="SubjectList.action">科目一覧</a></div>
		</article>
</div>

<footer>
    <p>@2023TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>


<style>

	.label{
		background-color: #66CDAA;
		width: 100%;
        padding: 1px;
        margin: 0;
        text-align: center;
	}

</style>
>>>>>>> branch 'master' of https://github.com/masuda007/Exam.git
