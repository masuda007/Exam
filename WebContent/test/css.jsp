<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    /* 全体のスタイルリセットと基本設定 */
    body, html {
        width: 100%;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        height: 100%;
    }

    .container {
        display: flex;
        flex-direction: column;
        min-height: 100vh;
    }

    /* ヘッダーのスタイル */
  	h1 {
    background-color: #e0ffff;
    padding: 10px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;

}
.logout {
    position: absolute;
    top: 45px;
    right: 20px;
    bottom: 10px;
    text-decoration: none;
    color: #007bff;
           text-decoration: underline;
    }
.teacher-name {
    position: absolute;
    top: 45px;
    right: 130px;
    bottom: 10px;
    text-decoration: none;
    }


    /* サイドバーとメインコンテンツのラッパー */
    .content-wrapper {
        display: flex;
        flex: 1;
    }
    /* サイドバーのスタイル */
    aside {
        width: 15%;
        border-right: 1px solid #dddddd;
        padding: 10px;
        box-sizing: border-box;
    }
    aside a {
        display: block;
        margin: 5px 0;
        text-decoration: none;
    }

    .submenu {
        margin-left: 20px;
    }

    /* メインコンテンツのスタイル */
    article {
        flex: 1;
        padding: 10px;
    }

    .contents {
        margin: 0 auto;
        border: 2px solid #f1f1f1;
        border-radius: 15px;
        padding: 10px;
    }

    /* 学生管理システムのスタイル */
    h2 {
        background-color: #f1f1f1;
        padding: 10px;
        margin: 10px 0;
    }

    /* 新規登録ボタンのスタイル */
    .new {
        text-align: right;
        margin: 0 10px;
    }

    /* フォームフィールドのスタイル */
    .from-select {
        border: 2px solid #f1f1f1;
        border-radius: 8px;
        padding: 4px 25px;
    }

    .form-inline {
        display: flex;
        flex-wrap: wrap;
        align-items: center;
    }

    .form-inline .col-4, .form-inline .col-2, .form-inline .check, .form-inline .button {
        margin-right: 10px;
        margin-bottom: 10px;
    }

    .form-inline .button {
        margin-top: 0;
    }

    .col-4, .col-2 {
        margin: 10px 0;
    }
    .check {
        display: inline-block;
    }

    .button {
        display: inline-block;
        margin-top: 20px;
        padding: 5px 10px;
    }

    .isAttends {
        text-align: center;
    }

    .table {
        border-collapse: collapse;
        width: 100%;
    }

    .table th, .table td {
        border: 1px solid #f1f1f1;
        text-align: center;
        padding: 8px;
    }

    /* フッターのスタイル */
    footer {
        background-color: #f1f1f1;
        text-align: center;
        padding: 0.1px 0;
        margin-top: auto; /* フッターをページの下部に配置 */
        font-size: 13px;
        line-height: 1;
    }

    /*メニュー*/

    .menu-items {
        display: flex;
        justify-content: space-between;
        width: 100%;
    }

    .menu-item {
        padding: 15px;
        border-radius: 10px;
        text-align: center;
        width: 30%;
        text-decoration: none;
        font-size: 16px;
         margin: 0 10px;
         position: relative;
    }

    .student {
        background-color: #CC6699;
        color: blue;
        text-decoration: underline;
        display: block; /* リンクをブロック要素にする */
        padding: 10px; /* リンク内の余白を設定 */

    }

    .grades {
        background-color: #99CC99;
        position: relative;
    }

    .grades a {
        display: block;
        text-decoration: none;
        padding: 10px;
    }

    .subject {
        background-color: #9999CC;
        color: blue;
        text-decoration: underline;
        display: block; /* リンクをブロック要素にする */
        padding: 10px; /* リンク内の余白を設定 */
    }

    .menu-item a {
        color: blue;
        text-decoration: underline;
        display: block; /* リンクをブロック要素にする */
        padding: 10px; /* リンク内の余白を設定 */
        background-color: transparent; /* 背景を透明に設定 */
    }

    .menu-item a:hover {
        background-color: rgba(255, 255, 255, 0.2); /* ホバー時の背景色変更 */
    }


/* フォーム間の線のスタイル */
.form-divider {
    border: none;
    border-top: 2px solid #f1f1f1;
    margin: 20px 0;
}





/* ボタンの角を丸くするスタイル */
.btn {
    border-radius: 15px;
    padding: 10px 20px;
    border: 1px solid #ddd;
    background-color: #333;
    color: white;
    cursor: pointer;
    text-decoration: none;
}

.btn-primary {
    background-color: #007bff;
    border-color: #007bff;
}

.btn-secondary {
    background-color: #6c757d;
    border-color: #6c757d;
}




    /* 入学年度、クラス、科目、学生番号、検索ボタンのスタイル */
.form-group {
    margin-bottom: 15px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
}

.form-group select,
.form-group input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
}

/* 検索ボタンのスタイル */
button[type="submit"] {
    padding: 8px 15px;
    background-color: #696969;
    color: #fff;
    border: none;
    cursor: pointer;
    border-radius: 10px; /* 角を丸くする */
}

/* 検索ボタンを触ったとき */
button[type="submit"]:hover {
    background-color: #0056b3;
    padding: 8px 15px;
    color: #fff;
    border: none;
    cursor: pointer;
    border-radius: 10px; /* 角を丸くする */
}

/* テーブルのスタイル */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

table th,
table td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: left;
}

.message-blue {
    color: skyblue;
}



</style>