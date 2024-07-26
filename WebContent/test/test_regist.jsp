<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="css.jsp" %>
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
        <jsp:include page="base.jsp"/>
        <article>
            <h2 class="studentsystem">成績登録</h2>

            <div class="contents">

                <form action="TestRegistExecute.action" method="get" class="form-inline">
                    <!-- 入学年度 -->
                    <div class="col-4">
                        <th>入学年度</th>
                        <select class="from-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${yearSet}">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- クラス -->
                    <div class="col-4">
                        <th>クラス</th>
                        <select class="from-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="classNum" items="${classNumSet}">
                                <option value="${classNum}">${classNum}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 科目 -->
                    <div class="col-4">
                        <th>科目</th>
                        <select class="from-select" name="f3">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subjectsSet}">
                                <option value="${subject}">${subject}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 回数 -->
                    <div class="col-4">
                        <th>回数</th>
                        <select class="from-select" name="f4">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${numSet}">
                                <option value="${num}">${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 検索ボタン -->
                    <div class="col-2 button">
                        <button type="submit" class="btn btn-primary">検索</button>
                    </div>
                </form>

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>

            </div>

            <c:choose>
                <c:when test="${not empty testList}">
                    <div>科目: ${f3} (${f4}回)</div>
                    <form action="TestExecute.action" method="post">
		                    <input type="hidden" name="f1" value="${f1}">
						    <input type="hidden" name="f2" value="${f2}">
						    <input type="hidden" name="f3" value="${f3}">
						    <input type="hidden" name="f4" value="${f4}">
                        <table class="table">
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                            <c:forEach var="test" items="${testList}">
                                <tr>
                                    <td>${test.student.entYear}</td>
                                    <td>${test.classNum}</td>
                                    <td>${test.student.no}</td>
                                    <td>${test.student.name}</td>
                                    <td>
                                        <input type="hidden" name="studentNo" value="${test.student.no}">
                                        <input type="hidden" name="classNum" value="${test.classNum}">
                                        <input type="hidden" name="subjectCd" value="${test.subject}">
                                        <input type="hidden" name="testNo" value="${f4}">
                                        <input type="text" name="point" value="${test.point}">
	                                        <c:if test="${not empty pointerror}">
							                    <div class="pointerror">${pointerror}</div>
							                </c:if>
                                    </td>
                                </tr>
                            </c:forEach>


                        </table>
                        <div class="col-2 button">
                            <button type="submit" class="btn btn-primary">登録して終了</button>
                        </div>

                    </form>
                </c:when>
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


<style>
        .error {
	        color: #856404;
	        border-color: #ffeeba;
	        padding: 8px;
	        margin-top: 5px;
	    }

	    .pointerror {
	        color: #FFCC33;
	        border-color: #ffeeba;
	        padding: 2px;
	        margin-top: 2px;
	        font-size: smaller;
	    }
</style>