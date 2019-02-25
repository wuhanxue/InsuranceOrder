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
  <title>基础数据</title>
  <script src="../../js/jquery/jquery2.0.3/jquery-2.0.3.min.js"></script>
  <script src="../../js/jquery/2.0.0/jquery.min.js"></script>
  <link href="../../css/3.3.6/bootstrap.min.css" rel="stylesheet">
  <script src="../../js/3.3.6/bootstrap.min.js"></script>
  <link href="../../css/navbar.css" rel="stylesheet">
  <script src="../../js/navbar.js"></script>
</head>
<style type="text/css">
  .pull-right{
    float: right !important;
  }
  .pull-left{
    float: left !important;
  }
  table{
    font-family: "微软雅黑",Georgia,Serif;
  }
</style>
<script type="text/javascript">
    function go() {
        window.self.location = "accountManage.jsp"
    }
</script>
<body>
<!--导航条-->
<nav class="navbar navbar-inverse navbar-fixed-top float" id="navbar1" style="height: 50px;">
  <div class="main-title">
    <ul class="nav navbar-nav navbar-left navbar-side">
      <li>
        <a href="#" onclick="$('body').toggleClass('sidebar-collapse');" style="width: 50px;height: 50px">
          <span class="glyphicon glyphicon-menu-hamburger"></span>
        </a>
      </li>
    </ul>
  </div>
  <div class="container navbar-left" style="width: 800px;">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><img src="../../image/logo.jpg"></a>
    </div>
    <div id="navbar" class="collapse navbar-collapse" style="margin-left: 150px;">
      <ul class="nav navbar-nav">
        <li class="active"><a href="">基础数据</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
  <ul class="nav navbar-nav navbar-right">
    <li class="dropdown">
      <a href="#" title="我的" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span></a>
      <ul class="dropdown-menu">
        <li><a href="/account">账号管理</a></li>
        <li><a href="/signin">注销</a></li>
      </ul>
    </li>
  </ul>
</nav>

<div class="container-fluid">
  <div class="row">
    <div class="sidebar">
      <ul class="sidenav animated fadeInUp" style="margin-top: 50px">
        <!--<li><a href="#"><span class="glyphicon glyphicon-backward" aria-hidden="true"></span></a></li>-->
        <li><a class="withripple"><span class="glyphicon glyphicon-th" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;系统首页 </span><span class="iright pull-right">&gt;</span><span class="sr-only">(current)</span></a></li>
        <li><a class="withripple" href="/orderList"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;订单列表 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/accountManage"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;账号管理 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/basicDate"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span class="iright pull-right">&gt;</span></a></li>
      </ul>
    </div>
  </div>

  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top: 6%">
    <br>
      <h2 class="modal-title">信息修改</h2>
      <div class="row">
        <div class="form-horizontal col-md-6">
          <div class="form-group">
            <label for="name" class="col-sm-4 control-label">姓名 </label>
            <div class="col-xs-8">
              <input type="text" class="form-control" id="name" name="name">
            </div>
          </div>
          <div class="form-group">
            <label for="userName" class="col-sm-4 control-label">用户名 </label>
            <div class="col-xs-8">
              <input type="text" class="form-control" id="userName">
            </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-sm-4 control-label">密码 </label>
            <div class="col-xs-8">
              <input type="text" class="form-control" id="password">
            </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-sm-4 control-label">确认密码 </label>
            <div class="col-xs-8">
              <input type="text" class="form-control" id="confirmPassword">
            </div>
          </div>
        </div>
        <div class="form-horizontal col-md-6">
          <div class="form-group">
            <label for="department" class="col-sm-4 control-label">部门 </label>
            <div class="col-xs-8">
              <select id="department" name="department">
              </select>
            </div>
          </div>
          <div class="form-group">
            <label for="company" class="col-sm-4 control-label">公司 </label>
            <div class="col-xs-8">
              <select id="company" name="company"></select>
            </div>
          </div>
          <div class="form-group">
            <label for="team" class="col-sm-4 control-label">项目组 </label>
            <div class="col-xs-8">
              <select id="team" name="team">
              </select>
            </div>
          </div>
        </div>
      </div>
    <div class="text-center">
      <a type="button" class="btn btn-primary" onclick="updateUser()">保存</a>
      <a type="button" class="btn btn-success" onclick="historyBack()">返回</a>
    </div>
  </div>
</div>
<script>
    /**
     * 返回历史页的上一页
     */
  function historyBack() {
      history.back();
  }
</script>
</body>
</html>
