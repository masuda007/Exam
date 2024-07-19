<<<<<<< HEAD
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>科目追加完了</title>
    <style>
        /* 必要に応じてスタイルを追加 */
    </style>
</head>
<body>
    <h2>科目追加完了</h2>

    <c:choose>
        <c:when test="${createSuccess}">
            <p>科目の追加が完了しました。</p>
        </c:when>
        <c:otherwise>
            <p>科目の追加に失敗しました。再度お試しください。</p>
        </c:otherwise>
    </c:choose>

    <!-- 戻るリンク -->
    <div>
        <a href="SubjectList.action">科目一覧に戻る</a>
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
			<h2>科目情報登録</h2>
			<div class="label"><label>登録が完了しました</label></div>
			<div><a href="StudentCreate.action">戻る</a></div>
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
