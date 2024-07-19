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
<<<<<<< HEAD
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
=======
<h1 class="h1">得点管理システム</h1>
</header>

<div class="content-wrapper">
<jsp:include page="base.jsp" />
	<article>
	<h2>科目情報削除</h2>
		<form action="SubjectDeleteExecute.action" method="post">
			<p>「${subject.name}(${subject.cd})」を削除してもよろしいですか</p>


				<input type="hidden" name="cd" value="${subject.cd}">
				<input type="hidden" name="name" value="${subject.name}">


	        <div class="col-2 button">
				<button type="submit" class="button" name="delete">削除</button>
			</div>
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
>>>>>>> branch 'master' of https://github.com/masuda007/Exam.git
