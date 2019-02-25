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
    <title>订单列表</title>
    <link rel="stylesheet" type="text/css" href="/css/page/style.css" media="screen"/>
    <script src="../../js/jquery/jquery2.0.3/jquery-2.0.3.min.js"></script>
    <script src="../../js/jquery/2.0.0/jquery.min.js"></script>
    <link href="../../css/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/3.3.6/bootstrap.min.js"></script>
    <link href="../../css/navbar.css" rel="stylesheet">
    <script src="../../js/navbar.js"></script>
    <script src="../../js/insurance/insuranceOrder.js"></script>
    <script src="../../js/util.js"></script>
</head>
<style type="text/css">

  /*table{*/
    /*font-family: "微软雅黑",Georgia,Serif;*/
  /*}*/
</style>
<body onload="insuranceOrderLoad()">
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
      <a class="navbar-brand" href="#"><img src="image/logo.jpg"></a>
    </div>
    <div id="navbar" class="collapse navbar-collapse" style="margin-left: 150px;">
      <ul class="nav navbar-nav">
        <li class="active"><a href="">订单保险</a></li>
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
        <li><a class="withripple" href="/checkUserIsAdministrator"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;账号管理 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/basicDate"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span class="iright pull-right">&gt;</span></a></li>
      </ul>
    </div>
  </div>

  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="row">
      <div>
        <ol class="breadcrumb">
          <li class="active">订单列表</li>
        </ol>
      </div>
      <h4 class="sub-header">||数据列表</h4>
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
              <label for="search-documentNumber" class="col-sm-4 control-label">单据号</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="search-documentNumber" name="search-documentNumber" placeholder="">
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-3">
            <div class="form-group">
              <label for="search-creator" class="col-sm-4 control-label">创建人</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="search-creator" name="search-creator" placeholder="">
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-3">
            <div class="form-group">
              <label for="search-createDept" class="col-sm-4 control-label">创建部门</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="search-createDept" name="search-createDept" placeholder="">
              </div>
            </div>
          </div>
          <div class="form-horizontal col-md-3">
            <div class="form-group">
              <label for="search-editor" class="col-sm-4 control-label">修改人</label>
              <div class="col-xs-7">
                <input type="text" onkeyup="searchData();" class="form-control" id="search-editor" name="search-editor" placeholder="">
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
              <th class="text-center">订单号</th>
              <th class="text-center">状态</th>
              <th class="text-center">申请人</th>
              <th class="text-center">申请部门</th>
              <th class="text-center">投保日期</th>
              <th class="text-center">审批日期</th>
              <th class="text-center">被投保人名称</th>
              <th class="text-center">货物价值</th>
              <th class="text-center">保险公司名称</th>
              <th class="text-center">保费</th>
              <th class="text-center">操作</th>
          </tr>
          </thead>


                 <tbody id="insuranceOrderList">
          <tr>
              <td class='text-center'></td><%--订单号--%>
              <td class='text-center'></td><%--状态--%>
              <td class='text-center'></td><%--申请人--%>
              <td class='text-center'></td><%--申请部门--%>
              <td class='text-center'></td><%--投保日期--%>
              <td class='text-center'></td><%--审批日期--%>
              <td class='text-center'></td><%--被投保人名称--%>
              <td class='text-center'></td><%--货物价值--%>
              <td class='text-center'></td><%--保险公司名称--%>
              <td class='text-center'></td><%--保费--%>
              <td class='text-center'>
                  <a href="/orderDetail" onclick="" title="查看"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
                  <a href="#" onclick="" title="接单"><span class="glyphicon glyphicon-check" aria-hidden="true"></span></a>
                  <a href="#" onclick="" id="insured" title="投保"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
                  <a href="#" onclick="" title="作废"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                  <a href="#" id="upload" onclick="" title="上传附件"><span class="glyphicon glyphicon-open" aria-hidden="true"></span></a>
                  <a href="#" onclick="" title="查看附件"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span></a>
                  <a href="#" onclick="" title="关闭"><span class="glyphicon glyphicon-off" aria-hidden="true"></span></a>
              </td>
          </tr>
                 </tbody>



                     <%--<td>--%>
                         <%--${insuranceOrderList}--%>
                     <%--</td>--%>
          <%--<tr>--%>
              <%--<td class="text-center">${time}</td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center"></td>--%>
              <%--<td class="text-center">--%>
                <%--<a href="#" onclick="" title="查看"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>--%>
                <%--<a href="#" onclick="" title="接单"><span class="glyphicon glyphicon-check" aria-hidden="true"></span></a>--%>
                <%--<a href="#" onclick="" title="投保"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>--%>
                <%--<a href="#" onclick="" title="作废"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>--%>
                <%--<a href="#" id="upload" onclick="" title="上传附件"><span class="glyphicon glyphicon-open" aria-hidden="true"></span></a>--%>
                <%--<a href="#" onclick="" title="查看附件"><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span></a>--%>
                <%--<a href="#" onclick="" title="关闭"><span class="glyphicon glyphicon-off" aria-hidden="true"></span></a>--%>
              <%--</td>--%>
          <%--</tr>--%>
      </table>
    </div>

  </div>
</div>
<div class="content row" style="height: 50px">
    <div class="demo">
        <div id="demo3"></div>
        <br>
        <form class="form-inline">
            <div class="form-group" style="width: 20%">
               <a class="btn btn-primary" onclick="jump()" style="height: 30px;width: 60px">跳转</a>
                <input type="text"  style="width:30%" id="jumpPage">
                <span>页</span>
            </div>

        <span>当前第</span>
        <span id="currentPage" style="color: green">1</span>
        <span>页</span>
        <span style="display: inline-block">每页显示</span>
        <select  id="count" style="display: inline-block" onchange="switchPageNumber(insuranceOrderLoad);">
            <option value=1>1</option>
            <option selected value=15>15</option>
            <option value=50>50</option>
        </select>
            <span style="display: inline-block">条记录</span>
            <span>总共</span>
            <span id="totalRecord" style="color: red"></span>
            <span>条记录</span>
        </form>
    </div>

<div id="embed"></div>
<div class="modal fade bs-example-modal-lg" id="insure" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header">
                <h3>投保</h3>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <th class="text-center">保险公司名称</th>
                        <th class="text-center">保单号</th>
                        <th class="text-center">投保日期</th>
                        <th class="text-center">保费</th>
                        <th class="text-center">附件</th>
                        <th class="text-center">创建日期</th>
                        <th class="text-center">创建人</th>
                        <th class="text-center">修改日期</th>
                        <th class="text-center">修改人</th>
                        <th class="text-center">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="documents" varStatus="st">
                        <tr>
                            <td class="text-center">${documents.companyName}</td>
                            <td class="text-center">${documents.orderNo}</td>
                            <td class="text-center">${documents.insureTime}</td>
                            <td class="text-center">${documents.insureMoney}</td>
                            <td class="text-center">${documents.file}</td>
                            <td class="text-center"><fmt:formatDate value="${documents.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="text-center">${documents.creator}</td>
                            <td class="text-center"><fmt:formatDate value="${documents.editTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="text-center">${documents.editor}</td>
                            <td class="text-center">${documents.station}</td>
                            <td class="text-center">
                                <a title="增加"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                                <a onclick="showEditModal(this);" title="修改"><span class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                <a class="delete" title="删除"><span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                                <input type="button" id="upload2" value="上传电子保单">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>
<div class="hidden">
  <input type="file" id="file">
  <input type="file" id="file2">
</div>

<script src="../../js/page.js" type="text/javascript"></script>
</div>
</body>
<script>



  $("#upload").click(
      function () {
          $("#file").click();
      });
  $("#upload2").click(
      function () {
          $("#file2").click();
      });
  $("#insured").click(
      function () {
          $("#insure").modal('show')
      }
  )

</script>
</html>
