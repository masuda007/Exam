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
