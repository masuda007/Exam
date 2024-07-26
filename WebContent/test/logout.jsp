<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
<h1 class="h1">得点管理システム</h1>
</header>

	<div class="logout">
		<h2>ログアウト</h2>
	</div>

	<div class="logout2">
		<h3>ログアウトしました</h3>
	</div>

   <span><a href="login.jsp">ログイン</a>
	</span>
<footer>
<p>@2023TIC</p>
<p>大原学園</p>
</footer>


<style>
    /*ログインのレイアウト*/

    /* ヘッダーのスタイル */
    header {
        background-color: #D7EEFF;
        padding: 14px;
        padding-right: 60px;
        height: 40px;
        line-height: 0px;
    }


    h2 {
        background-color: #f1f1f1;
        width: 100%;
        margin-top: 0; /* h2要素の上側のマージンを0に設定 */
        padding-top: 10px; /* h2要素の上側に適切なパディングを追加 */
    }

    .logout {
        margin: 30px auto; /* 上下に30pxの余白を追加し、左右は自動中央揃え */
        width: 50%;
        font-size:  20px;
        padding: 0px; /* フォーム内の要素から枠までの間隔 */
        text-align: left;
        }

   h3 {
        background-color: #66CDAA;
        width: 100%;
        padding: 1px;
        margin: 0;
        text-align: center;
    }



	.logout2{
	    margin: 30px auto; /* 上下に20pxの余白を追加し、左右は自動中央揃え */
        width: 50%;
        padding: 0px; /* フォーム内の要素から枠までの間隔 */
        text-align: center;
        }

    span {
    display: flex;
    padding-left: 320px;
    }

    footer {
        background-color: #f1f1f1;
        text-align: center;
        padding: 0.1px 0;
        margin-top: auto; /* フッターをページの下部に配置 */
        font-size: 13px;
        line-height: 1;
    }
</style>