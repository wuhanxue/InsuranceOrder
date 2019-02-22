<%--
  Created by IntelliJ IDEA.
  User: 950618
  Date: 2019/2/19
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
  <script src="../../js/insurance/accountManage.js"></script>

</head>
<body >
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
    <li class="dropdown">
      <a href="#" title="我的" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span></a>
      <ul class="dropdown-menu">
        <li><a href="">账号管理</a></li>
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
        <li><a class="withripple" href="/orderList"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;订单列表 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/accountManage"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;账号管理 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/basicDate"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span class="iright pull-right">&gt;</span></a></li>
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
        <a class="btn btn-primary btn-xs" onclick="" href=""><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增</a>
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
          <th class="text-center">公司</th>
          <th class="text-center">部门</th>
          <th class="text-center">项目组</th>
          <th class="text-center">创建日期</th>
          <th class="text-center">创建人</th>
          <th class="text-center">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="c" varStatus="st">
          <tr>
            <td class="text-center">
              <label>
                <input class="checkbox" type="checkbox" value="option1" aria-label="..." name="checkbox1">
              </label>
            </td>
            <td class="text-center">${c.id}</td>
            <td class="text-center">${c.userName}</td>
            <td class="text-center">${c.name}</td>
            <td class="text-center">${c.companyDataItem.name}</td>
            <td class="text-center">${c.departmentDataItem.name}</td>
            <td class="text-center">${c.teamDataItem.name}</td>
            <td class="text-center"><fmt:formatDate value="${c.creationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td class="text-center">${c.creator}</td>
            <td class="text-center">
              <a onclick="showEditModal(this);" href="getUserById/${c.id}" title="修改"><span class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
              <a href="deleteUserById/${c.id}" class="delete" title="删除"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span></a>
            </td>
          </tr>
        </c:forEach>
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
              <label for="userName" class="col-sm-4 control-label">用户名 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="userName" >
              </div>
            </div>
            <div class="form-group">
              <label for="password" class="col-sm-4 control-label">新密码 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="password" >
              </div>
            </div>
            <div class="form-group">
              <label for="company" class="col-sm-4 control-label">公司 </label>
              <div class="col-xs-8">
                <select id="company" name="company">

                </select>
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
              <label for="cfmPassword" class="col-sm-4 control-label">确认密码 </label>
              <div class="col-xs-8">
                <input type="password" class="form-control" id="cfmPassword" name="cfmPassword">
              </div>
            </div>
            <div class="form-group">
              <label for="department" class="col-sm-4 control-label">部门 </label>
              <div class="col-xs-8">
               <select id="department" name="department">
               </select>
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-6">
            <div class="form-group">
              <label for="name" class="col-sm-4 control-label">项目组 </label>
              <div class="col-xs-8">
                <select id="team" name="team">
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updateUser();">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
<%--新增模态框--%>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增用户</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="form-horizontal col-md-6">
            <div class="form-group">
              <label for="add_userName" class="col-sm-4 control-label">用户名 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="add_userName" name="userName">
              </div>
            </div>
            <div class="form-group">
              <label for="add_password" class="col-sm-4 control-label">新密码 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="add_password" name="password">
              </div>
            </div>
            <div class="form-group">
              <label for="add_company" class="col-sm-4 control-label">公司 </label>
              <div class="col-xs-8">
                <select id="add_company" name="company">

                </select>
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-6">
            <div class="form-group">
              <label for="add_name" class="col-sm-4 control-label">姓名 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="add_name" name="name">
              </div>
            </div>
            <div class="form-group">
              <label for="add_cfmPassword" class="col-sm-4 control-label">确认密码 </label>
              <div class="col-xs-8">
                <input type="text" class="form-control" id="add_cfmPassword" name="cfmPassword">
              </div>
            </div>
            <div class="form-group">
              <label for="add_department" class="col-sm-4 control-label">部门 </label>
              <div class="col-xs-8">
                <select id="add_department" name="department">
                </select>
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-6">
            <div class="form-group">
              <label for="add_team" class="col-sm-4 control-label">项目组 </label>
              <div class="col-xs-8">
                <select id="add_team" name="team">
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updateUser();">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
