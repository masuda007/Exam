<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
</header>

<div class="content-wrapper">
    <jsp:include page="base.jsp" />
    <article>
        <h2 class="studentsystem">学生管理</h2>
        <div class="new"><a href="StudentCreate.action">新規登録</a></div>

        <div class="contents">
            <form action="StudentList.action" method="get" class="form-inline">
                <div class="col-4">
                    <label class="from-label">入学年度</label>
                    <select class="from-select" name="f1">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-4">
                    <label class="from-label">クラス</label>
                    <select class="from-select" name="f2">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-2 check">
                    <label class="label-check">在学中
                        <input class="check-input" type="checkbox" name="f3" value="t"
                        <c:if test="${not empty f3}">checked</c:if> />
                    </label>
                </div>

                <div class="col-2 button">
                    <button class="button">絞り込み</button>
                </div>
                <div class="btn btn-secondary">${errors['f1']}</div>
            </form>
        </div>

        <c:choose>
            <c:when test="${students.size() > 0}">
                <div>検索結果: ${students.size()}件</div>
                <table class="table">
                    <tr>
                        <th>入学年度</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th class="text-center">在学中</th>
                        <th></th>
                    </tr>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.entYear}</td>
                            <td>${student.no}</td>
                            <td>${student.name}</td>
                            <td>${student.classNum}</td>
                            <td class="isAttends">
                                <c:choose>
                                    <c:when test="${student.isAttend()}">
                                        ○
                                    </c:when>
                                    <c:otherwise>
                                        ×
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <div>学生情報が存在しませんでした。</div>
            </c:otherwise>
        </c:choose>
    </article>
</div>

<footer class="footer">
    <p>@2023 TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>
</html>
