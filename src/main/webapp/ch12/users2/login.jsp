<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사용자 로그인</title>
    <style>
        td { text-align: center; padding: 3px;}
    </style>
</head>
<body style="margin: 40px;">
    <h1>로그인</h1>
    <hr>
    <form action="/jw/ch12/users2/login" method="post">
        <table>
            <tr>
                <td>아이디:</td>
                <td><input type="text" name="uid" id=""></td>
            </tr>
            <tr>
                <td>비밀번호:</td>
                <td><input type="password" name="pwd" id=""></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="로그인"></td>
            </tr>
        </table>
    </form>
</body>
</html>