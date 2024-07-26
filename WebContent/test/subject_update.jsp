<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="css.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>科目情報変更</title>
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
            <h2 class="studentsystem">科目情報変更</h2>

            <div class="contents">
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty message}">
                    <div class="message">${message}</div>
                </c:if>

                <form action="SubjectUpdateExecute.action" method="post" class="form-i">

                    <div class="col-4">
                        <label class="from-label">科目コード</label><br>
                        <input type="text" class="from-select" name="cd" value="${cd}" readonly>
                    </div>

                    <div class="col-4">
                        <label class="from-label">科目名</label><br>
                        <input type="text" required class="from-select" maxlength="20" name="name" value="${subject.name}">
                    </div>

                    <div class="col-2 button">
                        <button type="submit" class="button" name="update">変更</button>
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
