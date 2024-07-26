<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <h2 class="studentsystem">成績一覧 (科目)</h2>

            <div class="contents">
                <form action="TestListSubjectExecute.action" method="get" class="form-inline">
                    <div class="section-title">科目情報</div>
                    <div class="col-4">
                        <div>入学年度</div>
                        <select class="from-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${yearSet}">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <div>クラス</div>
                        <select class="from-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="classNum" items="${classNumSet}">
                                <option value="${classNum}">${classNum}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <div>科目</div>
                        <select class="from-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjectsSet}">
                                <option value="${subject}">${subject}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2 button">
                        <button type="submit" class="btn btn-primary">検索</button>
                    </div>

	                <c:if test="${not empty error}">
	                    <div class="error">${error}</div>
	                </c:if>

                </form>

                <div class="line"></div>

                <form action="TestListStudentExecute.action" method="get" class="form-inline">
                    <p class="section-title">学生情報</p>

                    <div class="col-4">
                        <div>学生番号</div>
                        <input class="from-select" type="text" required name="f4" maxlength="10" placeholder="学生番号を入力してください">
                    </div>

                    <div class="col-2 button">
                        <button type="submit" class="btn btn-primary">検索</button>
                    </div>
                </form>

            </div>

            <c:choose>
                <c:when test="${not empty testList}">
                    <div>科目: ${selectedSubject}</div>
                    <table class="table">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>1回</th>
                            <th>2回</th>
                        </tr>
                        <c:forEach var="test" items="${testList}">
                            <tr>
                                <td>${test.entYear}</td>
                                <td>${test.classNum}</td>
                                <td>${test.studentNo}</td>
                                <td>${test.studentName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty test.points}">
                                            <c:set var="found" value="false"/>
                                            <c:forEach var="entry" items="${test.points}">
                                                <c:if test="${entry.key == 1}">
                                                    <c:out value="${entry.value}" />
                                                    <c:set var="found" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not found}">
                                                -
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty test.points}">
                                            <c:set var="found" value="false"/>
                                            <c:forEach var="entry" items="${test.points}">
                                                <c:if test="${entry.key == 2}">
                                                    <c:out value="${entry.value}" />
                                                    <c:set var="found" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not found}">
                                                -
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>学生情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </article>

        <!-- パラメータの確認用コード -->
        <c:out value="${param.schoolCd}" />
        <c:out value="${param.entYear}" />
        <c:out value="${param.classNum}" />
        <c:out value="${param.subject}" />

    </div>

    <footer class="footer">
        <p>@2023 TIC</p>
        <p>大原学園</p>
    </footer>

</div>

</body>
</html>

<style>
    .section-title {
        margin-right: 30px;
        margin-left: 10px;
        font-weight: bold;
    }

    .line {
        border-top: 2px solid #f1f1f1;
        margin: 2px 0;
    }

    .error {
        color: #FFCC33;
	    border-color: #ffeeba;
        padding: 2px;
        margin-top: 0px;
        font-size: smaller;
    }
</style>
