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
<%
  String userName = "";
  String password = "";
  //获取当前站点的所有Cookie
  Cookie[] cookies = request.getCookies();
  if(cookies != null && cookies.length > 0) {
    for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
      if ("userName".equals(cookies[i].getName())) {
        userName = cookies[i].getValue();
      } else if ("password".equals(cookies[i].getName())) {
        password = cookies[i].getValue();
      }
    }
  }
%>
<!--登录表单-->
<form class="form-signin gap" id="loginForm" method="post" action="CheckUserInfo">
  <h2 class="form-signin-heading">保险登录</h2>
  <label for="userName" class="sr-only">账号</label>
  <input type="text"  class="form-control" value="<%=userName%>" id="userName" name="userName" placeholder="账号" required autofocus>
  <label for="password" class="sr-only">密码</label>
  <input type="password"  class="form-control" value="<%=password%>" id="password" name="password" placeholder="密码" required>
  <br>
  <input name="remember" type="checkbox" id="remember" value="1" /> 记住密码
  <button type="submit" class="btn btn-lg btn-primary btn-block" id="submit" onclick="">登录</button>
</form>
</body>
<script type="text/javascript">
    if('${status}' !=null && '${status}' === 'fail'){  // 账号或密码错误提示
        alert('${message}');
    }
</script>
</html>
