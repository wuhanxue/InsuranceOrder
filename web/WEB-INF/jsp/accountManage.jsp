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
  <title>账号管理</title>
  <script src="../../js/jquery/jquery2.0.3/jquery-2.0.3.min.js"></script>
  <script src="../../js/jquery/2.0.0/jquery.min.js"></script>
  <link href="../../css/3.3.6/bootstrap.min.css" rel="stylesheet">
  <script src="../../js/3.3.6/bootstrap.min.js"></script>
  <link href="../../css/navbar.css" rel="stylesheet">
  <script src="../../js/navbar.js"></script>
</head>
<script type="text/javascript">
  function go() {
      window.self.location = "accountManage.jsp"
  }
</script>
<body onload="loadUserList();">
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
  <div class="container navbar-left" style="width: 70%;">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><img src="../../image/logo.jpg"></a>
    </div>
    <div id="navbar" class="collapse navbar-collapse" style="margin-left: 150px;">
      <ul class="nav navbar-nav">
        <li><a href="">账号管理</a></li>
      </ul>
    </div><!--/.nav-collapse -->
  </div>
  <ul class="nav navbar-nav navbar-right">
    <li><a href="#" title="提醒"><span class="glyphicon glyphicon-bell"></span></a></li>
    <li><a href="#" title="事项"><span class="glyphicon glyphicon-envelope"></span></a></li>
    <li class="dropdown">
      <a href="#" title="我的" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span></a>
      <ul class="dropdown-menu">
        <li><a href="">账号管理</a></li>
        <li><a href="#">待办事项</a></li>
        <li><a href="">注销</a></li>
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
        <li><a class="withripple"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;订单列表 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/account"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;账号管理 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;数据字典 </span><span class="iright pull-right">&gt;</span></a></li>
      </ul>
    </div>
  </div>

  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="row">
      <div>
        <ol class="breadcrumb"><!--历史记录导航栏-->

        </ol>
      </div>
      <h4 class="sub-header">账号列表</h4>
    </div>
    <div>
      <!--操作按钮-->
      <div class="pull-left">
        <a class="btn btn-primary btn-xs" id="function_-376" onclick="checkAuthority($(this))" href=""><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增</a>
      </div>
    </div>
    <div class="table">
      <table class="table table-striped table-hover table-condensed">
        <thead>
        <tr>
          <th class="text-center">
            <label>
              <input class="checkbox" type="checkbox" value="option1" aria-label="..." name="allSel" id="allSel" onclick="allSelect();">
            </label>
          </th>
          <th class="text-center">编号</th>
          <th class="text-center">账号</th>
          <th class="text-center">姓名</th>
          <th class="text-center">角色</th>
          <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <tr id="cloneTr">
          <td class="text-center">
            <label>
              <input class="checkbox" type="checkbox" value="option1" aria-label="..." name="checkbox1">
            </label>
          </td>
          <td class="text-center" name="id">0001</td>
          <td class="text-center">root</td>
          <td class="text-center">小李</td>
          <td class="text-center">管理员</td>
          <td class="text-center">
            <a href="#" id="function_-377" onclick="showEditModal(this);" title="修改"><span class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
            <a href="#" id="function_-378" onclick="check(this); function check(e){if(!checkAuthority($(e))) return false; showRoleModal(e);}" title="分配角色"><span class="glyphicon glyphicon glyphicon-user" aria-hidden="true"></span></a>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

  </div>
</div>
<!-- 修改信息模态框 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">信息修改</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="form-horizontal col-md-6">
            <div class="form-group">
              <label for="username" class="col-sm-4 control-label">用户名 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="username" name="username">
              </div>
            </div>
            <div class="form-group">
              <label for="password" class="col-sm-4 control-label">新密码 </label>
              <div class="col-xs-8">
                <input type="password" class="form-control" id="password" name="password">
              </div>
            </div>
            <div class="form-group">
              <label for="cfmPassword" class="col-sm-4 control-label">确认密码 </label>
              <div class="col-xs-8">
                <input type="password" class="form-control" id="cfmPassword" name="cfmPassword">
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-6">
            <div class="form-group">
              <label for="name" class="col-sm-4 control-label">姓名 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="name" name="name">
              </div>
            </div>
            <div class="form-group">
              <label for="sex" class="col-sm-4 control-label">性别 </label>
              <div class="col-xs-8">
                <input type="radio" id="sex" name="sex" value="true">男
                <input type="radio" id="sex2" name="sex" value="false">女
              </div>
            </div>
            <div class="form-group">
              <label for="age" class="col-sm-4 control-label">年龄 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="age" name="age">
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updateUserInfo();">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- 分配角色模态框 -->
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">分配角色</h4>
      </div>
      <div class="modal-body">
        <select id="role"></select>
      </div>
      <div class="modal-footer" id="appoint">
        <button type="button" class="btn btn-primary" id="saveRole">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<div id="embed"></div>
</body>
</html>
