<%--
  Created by IntelliJ IDEA.
  User: 950618
  Date: 2019/2/19
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>用户登录</title>
  <script src="js/jquery/2.0.0/jquery.min.js"></script>
  <link href="css/3.3.6/bootstrap.min.css" rel="stylesheet">
  <script src="js/3.3.6/bootstrap.min.js"></script>
  <link href="css/signin.css" rel="stylesheet">
</head>
<style>
  .gap {
    margin-top: 10%;
  }
</style>
<body>
<!--登录表单-->
<form class="form-signin gap" id="loginForm" method="post">
  <h2 class="form-signin-heading">保险登录</h2>
  <label for="inputAccount" class="sr-only">账号</label>
  <input type="text" id="inputAccount" class="form-control" name="username" placeholder="账号" required autofocus>
  <label for="inputPassword" class="sr-only">密码</label>
  <input type="password" id="inputPassword" class="form-control" name="password" placeholder="密码" required>
  <button type="button" class="btn btn-lg btn-primary btn-block" id="input1" onclick="login()">登录</button>
</form>
</body>
</html>
