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
</header>

<div class="content-wrapper">
    <jsp:include page="base.jsp" />
    <article>
<<<<<<< HEAD
        <h2 class="studentsystem">学生情報登録</h2>

        <div class="contents">
            <form action="StudentCreateExecite.action" method="post" class="form-i">

                <!-- 全体のエラーメッセージ -->
                <c:if test="${not empty errors}">
                    <div class="alert alert-danger">${errors.db_error}</div>
                </c:if>


                <div class="col-4">
                    <label class="from-label">入学年度</label><br>
                    <select class="from-select" name="ent_year">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                    <!-- 入学年度のエラーメッセージ -->
                    <c:if test="${not empty errors.ent_year}">
                        <div class="alert alert-danger">${errors.ent_year}</div>
                    </c:if>
                </div>


                <div class="col-4">
                    <label class="from-label">学生番号</label><br>
                    <input type="text" required class="from-select" name="no" value="${no}" placeholder="学生番号を入力してください。">
                    <!-- 学生番号のエラーメッセージ -->
                    <c:if test="${not empty errors.no}">
                        <div class="alert alert-danger">${errors.no}</div>
                    </c:if>
                </div>


                <div class="col-4">
                    <label class="from-label">氏名</label><br>
                    <input type="text" required class="from-select" name="name" value="${name}" placeholder="氏名を入力してください。">
                    <!-- 氏名のエラーメッセージ -->
                    <c:if test="${not empty errors.name}">
                        <div class="alert alert-danger">${errors.name}</div>
                    </c:if>
                </div>


                <div class="col-4">
                    <label class="from-label">クラス</label><br>
                    <select class="from-select" name="class_num">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == classNum}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>


                <div class="col-2 button">
                    <button class="button" name="end">登録して終了</button>
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




<style>
	alert {
	    color: #856404;
	    background-color: #fff3cd;
	    border-color: #ffeeba;
	    padding: 8px;
	    margin-top: 5px;
	}
=======
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
>>>>>>> branch 'master' of https://github.com/masuda007/Exam.git
</style>
