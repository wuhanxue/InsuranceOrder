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
    <li><a href="#" title="提醒"><span class="glyphicon glyphicon-bell"></span></a></li>
    <li><a href="#" title="事项"><span class="glyphicon glyphicon-envelope"></span></a></li>
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
        <li><a class="withripple"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;数据字典 </span><span class="iright pull-right">&gt;</span></a></li>
      </ul>
    </div>
  </div>

  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="row">
      <div>
        <ol class="breadcrumb">
          <li class="active">基础数据</li>
        </ol>
      </div>
      <h4 class="sub-header">数据列表</h4>
    </div>
    <div class="row">
      <div>
        <!--操作按钮-->
        <div class="pull-left col-md-6">
          <a class="btn btn-primary btn-xs" href="" id="function_-298" onclick=""><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增</a>
          <a class="btn btn-primary btn-xs" href="" id="function_-299" onclick=""><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span> 导入</a>
          <a class="btn btn-primary btn-xs" href="" id="function_-300" onclick=""><span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span> 导出</a>
        </div>
        <!--查询框-->
        <div class="input-group col-md-4 pull-right">
          <input type="text" class="form-control" placeholder="搜索..." id="searchContent" onkeyup="searchData();">
          <span class="input-group-btn">
              <a class="btn btn-default" onclick="searchData();"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询</a>
              <a class="btn btn-default" onclick="$('#senior').toggle();"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 高级查询</a>
              <a class="btn btn-default" onclick="reset();"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 重置</a>
          </span>
        </div>
      </div>
    </div>
    <br>
    <div class="panel panel-default" id="senior" style="display: none;">
      <div class="panel-body">
        <div class="row">
          <div class="form-horizontal col-md-3">
            <div class="form-group">
              <label for="number" class="col-sm-4 control-label">编号</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="number" name="number" placeholder="">
              </div>
            </div>
            <div class="form-group">
              <label for="createTime" class="col-sm-4 control-label">创建时间</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="createTime" name="createTime" placeholder="">
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-3">
            <div class="form-group">
              <label for="code" class="col-sm-4 control-label">字典类型编码</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="code" name="code" placeholder="">
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-3">
            <div class="form-group">
              <label for="name" class="col-sm-4 control-label">字典类型名称</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="name" name="name" placeholder="">
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-3">
            <div class="form-group">
              <label for="creator" class="col-sm-4 control-label">创建人</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="creator" name="creator" placeholder="">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th class="text-center">
                    <label>
                        <input class="checkbox" type="checkbox" value="option1" aria-label="..." name="allSel"
                               id="allSel" onclick="allSelect();">
                    </label>
                </th>
                <th class="text-center">编号</th>
                <th class="text-center">字典类型编码</th>
                <th class="text-center">字典类型名称</th>
                <th class="text-center">创建人</th>
                <th class="text-center">创建时间</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr id="clonedTr1" ondblclick="toView1(this)">
                <td class="text-center">
                    <label>
                        <input name="select" class="checkbox" type="checkbox" id="blankCheckbox" value="option1"
                               aria-label="...">
                    </label>
                </td>
                <td class="text-center">${list.number}</td>
                <td class="text-center">${list.code}</td>
                <td class="text-center">${list.name}</td>
                <td class="text-center">${list.creator}</td>
                <td class="text-center">${list.time}</td>
                <td class="text-center">
                    <a href="viewBasicDate?id=${list.number}" title="编辑" onclick=""><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
  </div>
</div>
<div id="embed"></div>
<div class="hidden">
  <input type="file" id="file">
</div>
</body>
</html>
