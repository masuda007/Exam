<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="css.jsp" %>
<html>
	<head><meta charset="UTF-8"></head>
</html>
<body>

<header>
    	<h1 class="h1">システムエラー</h1>
</header>

<div class="container">

	<div class="content-wrapper">
	<%@include file="base.jsp" %>

		<article>
			<h3>エラーが発生しました。</h3>
			<c:if test="${not empty errorMessage}">
			    <div class="error-message">${errorMessage}</div>
			</c:if>
		</article>

	</div>

<footer>
    <p>@2023TIC</p>
    <p>大原学園</p>
</footer>

</div>

</body>