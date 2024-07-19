<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<html>
	<head><meta charset="UTF-8"></head>
</html>
<body>

<header>
    	<h1 class="h1">得点管理システム</h1>
    			<span class="teacher-name">${sessionScope.teacher.name}様</span>
    	<a href="Logout.action" class="logout">ログアウト</a>
</header>

<div class="container">

<div class="content-wrapper">
 	<jsp:include page="base.jsp" />
		<article>
			<h2>学生情報変更</h2>
			<div class="label"><label>変更が完了しました</label></div>
			<div><a href="StudentList.action">学生一覧</a></div>
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