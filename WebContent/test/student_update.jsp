<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="css.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報更新</title>
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
            <h2 class="studentsystem">学生情報変更</h2>

            <div class="contents">
                <form action="StudentUpdateExecute.action" method="post" class="form-i">

                    <div class="col-4">
                        <label class="from-label">入学年度</label><br>
                        <input type="text" class="from-select" name="ent_year" value="${student.entYear}">
                    </div>

                    <div class="col-4">
                        <label class="from-label">学生番号</label><br>
                        <input type="text" class="from-select" name="no" value="${student.no}" readonly>
                    </div>

                    <div class="col-4">
                        <label class="from-label">氏名</label><br>
                        <input type="text" required class="from-select" name="name" value="${student.name}">
                    </div>

                    <div class="col-4">
                        <label class="from-label">クラス</label><br>
                        <select class="from-select" name="class_num">
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == student.classNum}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <label class="label-check">
                            在学中
                            <input type="checkbox"  class="from-select" name="is_attend" >
                        </label>
                    </div>

                    <div class="col-2 button">
                        <button type="submit" class="button" name="update">変更</button>
                    </div>

                    <div><a href="StudentList.action">戻る</a></div>
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

