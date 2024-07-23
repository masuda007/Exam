<%@page contentType="text/html; charset=UTF-8" %>
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
    	<span class="teacher-name">${sessionScope.teacher.name}様</span>
    	<a href="Logout.action" class="logout">ログアウト</a>
</header>

<div class="content-wrapper">
    <jsp:include page="base.jsp" />
    <article>
        <h2 class="studentsystem">科目情報登録</h2>

        <div class="contents">
            <form action="SubjectCreateExecute.action" method="post" class="form-i">

                <!-- 全体のエラーメッセージ -->
                <c:if test="${not empty errors}">
                    <div class="alert alert-danger">${errors.db_error}</div>
                </c:if>

                <div class="col-4">
                    <label class="from-label">科目コード</label><br>
                    <input type="text" required class="from-select" name="cd" value="${cd}" placeholder="科目コードを入力してください。">
                    <!-- 科目コードのエラーメッセージ -->
                    <c:if test="${not empty errors.cd}">
                        <div class="alert alert-danger">${errors.cd}</div>
                    </c:if>
                </div>

                <div class="col-4">
                    <label class="from-label">科目名</label><br>
                    <input type="text" required class="from-select" name="name" value="${name}" placeholder="科目名を入力してください。">
                    <!-- 科目名のエラーメッセージ -->
                    <c:if test="${not empty errors.name}">
                        <div class="alert alert-danger">${errors.name}</div>
                    </c:if>
                </div>

                <div class="col-2 button">
                    <button class="button" name="end">登録</button>
                </div>

                <div><a href="SubjectList.action">戻る</a></div>

            </form>
        </div>

    </article>
</div>

<footer class="footer">
    <p>@2023 TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>
</html>

<style>
    .alert {
        color: #856404;
        border-color: #ffeeba;
        padding: 8px;
        margin-top: 5px;
    }
</style>
