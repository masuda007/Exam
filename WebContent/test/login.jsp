<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<header>
    <h1 class="h1">得点管理システム</h1>
</header>

<div class="login">
    <h2>ログイン</h2>
    <c:if test="${not empty errorMessage}">
        <p style="color: red">${errorMessage}</p>
    </c:if>
    <form action="LoginExecute.action" method="post">

        <input type="text" class="id" name="id"  placeholder="半角でご入力ください" maxlength="20" pattern="[A-Za-z0-9]*" required> <br>

        <input type="password" class="password" name="password"   id="password" placeholder="20文字以内の半角英数字で入力ください" maxlength="20" pattern="[A-Za-z0-9]*" required> <br>

        <label class="label-password">
            <input class="check-input" type="checkbox" id="showPassword"> パスワードを表示
        </label> <br>
        <div class="button">
            <button type="submit" id="loginButton">ログイン</button>
        </div>
    </form>
</div>

<footer>
    <p>@2023TIC</p>
    <p>大原学園</p>
</footer>

<script>
    const showPasswordCheckbox = document.getElementById('showPassword');
    const passwordField = document.getElementById('password');

    showPasswordCheckbox.addEventListener('change', function() {
        if (this.checked) {
            passwordField.type = 'text'; // パスワードを表示する
        } else {
            passwordField.type = 'password'; // パスワードを非表示にする
        }
    });
</script>

</body>
</html>




<style>
    /*ログインのレイアウト*/

    /* ヘッダーのスタイル */
    header {
        background-color: #D7EEFF;;
        padding: 14px;
        padding-right: 60px;
        height: 40px;
        line-height: 0px;

    }

    h2 {
	    background-color: #f1f1f1;
	    margin-top: 0; /* h2要素の上側のマージンを0に設定 */
	    padding-top: 10px; /* h2要素の上側に適切なパディングを追加 */
	}

    .login {
	    margin: 30px auto; /* 上下に20pxの余白を追加し、左右は自動中央揃え */
	    width: 50%;
	    border: 2px solid #f1f1f1;
	    border-radius: 5px;
	    padding: 0px; /* フォーム内の要素から枠までの間隔 */
	    text-align: center;
	    box-sizing: border-box; /* paddingが幅に含まれるようにする */
	}

	.id, .password {
	    width: 75%; /* 入力欄の幅を100%に設定 */
	    padding: 10px; /* 入力欄の内側の余白を設定 */
	    margin-bottom: 10px; /* 入力欄の下に余白を設定 */
	}

	.check-input {
		 margin-bottom: 15px; /* 入力欄の下に余白を設定 */
	}

	.button button {
	    background-color: #007bff; /* 背景色を青に設定 */
	    width: 150px;
	    color: white; /* 文字色を白に設定 */
	    border: none; /* ボーダーをなしに設定（任意） */
	    padding: 8px 20px; /* ボタンの内側の余白を設定 */
	    border-radius: 5px; /* ボタンの角を丸くする */
	    cursor: pointer; /* マウスを乗せたときにポインターを変更 */
	    font-size: 14px; /* 文字サイズを設定 */
	}

	.button button:hover {
	    background-color: #0056b3; /* マウスを乗せたときの背景色を設定 */
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