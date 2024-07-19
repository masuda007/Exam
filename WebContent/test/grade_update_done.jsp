<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
</head>
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
			<h2>成績管理</h2>
			<h3>登録が完了しました</h3>

      <a href="return" style="display: inline-block; margin-top: 100px;">戻る</a>
<a href="grade_reference" style="display: inline-block; margin-top: 0px; margin-left: 100px;">成績参照</a>

		</article>

</div>


</body>
<style>

    .grade_management {
        width: 100%;
        font-size: 15px;
       padding: 10px;
        margin: 10px 0;
    }

    h2 {
        background-color: #f1f1f1;
        margin-top: 0; /* h2要素の上側のマージンを0に設定 */
        padding-top: 10px; /* h2要素の上側に適切なパディングを追加 */
    }

    h3 {
        background-color: #009900;
        margin-top: 0; /* h3要素の上側のマージンを0に設定 */
        padding-top: 5px; /* h3要素の上側に適切なパディングを追加 */
    }


    .return {
		  line-height: 500%;
       padding: 0px;


</style>
</html>
