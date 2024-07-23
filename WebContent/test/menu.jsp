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
			<h2>メニュー</h2>
			<div class="menu-items">
				<a class="menu-item student" href="StudentList.action">学生管理</a>
				<div class="menu-item grades">成績管理
       				<a href="TestRegist.action">成績登録</a>
       				<a href="TestList.action">成績参照</a>
   				</div>
    			<a class="menu-item subject" href="SubjectList.action">科目管理</a>
    		</div>
		</article>
</div>

<footer>
    <p>@2023TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>