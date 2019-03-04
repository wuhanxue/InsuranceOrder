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
    <link rel="stylesheet" type="text/css" href="css/page/style.css" media="screen"/>
    <script src="js/jquery/jquery2.0.3/jquery-2.0.3.min.js"></script>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/3.3.6/bootstrap.min.js"></script>
    <link href="css/navbar.css" rel="stylesheet">
    <script src="js/navbar.js"></script>
    <script src="js/util.js"></script>
    <script src="js/insurance/basicDataDetail.js"></script>
</head>
<style type="text/css">
    table {
        font-family: "微软雅黑", Georgia, Serif;
    }
</style>
<body onload="passwordModifyMark();onLoadBasicDataDetailList();">
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
            <a class="navbar-brand" href="#"><img src="image/logo1.png"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse" style="margin-left: 150px;">
            <ul class="nav navbar-nav">
                <li class="active"><a href="">基础数据</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
            <a href="#" title="我的" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false"><span class="glyphicon glyphicon-user"></span></a>
            <ul class="dropdown-menu">
                <li><a href="account">账号管理</a></li>
                <li><a href="cleanUserInfo">注销</a></li>
            </ul>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="sidebar">
            <ul class="sidenav animated fadeInUp" style="margin-top: 50px">
                <!--<li><a href="#"><span class="glyphicon glyphicon-backward" aria-hidden="true"></span></a></li>-->
                <li><a class="withripple" href="orderList"><span class="glyphicon glyphicon-th-list"
                                                                  aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;订单列表 </span><span
                        class="iright pull-right">&gt;</span></a></li>
                <li onclick="jumpToAccountManage()"><a class="withripple" ><span class="glyphicon glyphicon-th-list"
                                                                      aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;账号管理 </span><span
                        class="iright pull-right">&gt;</span></a></li>
                <li><a class="withripple" href="basicData"><span class="glyphicon glyphicon-signal"
                                                                  aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span
                        class="iright pull-right">&gt;</span></a></li>
            </ul>
        </div>
    </div>

    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top: 2%">
        <div class="row">
            <h4 class="sub-header">基础数据修改</h4>
        </div>
        <div class="row">
            <div>
                <!--查询框-->
                <div class="input-group col-md-4 pull-right">
                    <input type="text" class="form-control" placeholder="搜索..." id="searchContent"
                           onkeyup="enterSearch();">
                    <span class="input-group-btn">
              <a class="btn btn-default" onclick="searchData();"><span class="glyphicon glyphicon-search"
                                                                       aria-hidden="true"></span> 查询</a>
              <a class="btn btn-default" onclick="$('#senior').toggle();"><span class="glyphicon glyphicon-cog"
                                                                                aria-hidden="true"></span> 高级查询</a>
              <a class="btn btn-default" onclick="reset();"><span class="glyphicon glyphicon-cog"
                                                                  aria-hidden="true"></span> 重置</a>
          </span>
                </div>
            </div>
        </div>
        <br>
        <div class="panel panel-default" id="senior" style="display: none;">
            <div class="panel-body">
                <div class="row">
                    <div class="form-horizontal col-md-4">
                        <div class="form-group">
                            <label for="search_id" class="col-sm-4 control-label">明细编号</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch();" class="form-control" id="search_id"
                                       name="id"
                                       placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-md-4">
                        <div class="form-group">
                            <label for="search_code" class="col-sm-4 control-label">明细编码</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch();" class="form-control" id="search_code"
                                       name="code"
                                       placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-md-4">
                        <div class="form-group">
                            <label for="search_name" class="col-sm-4 control-label">明细名称</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch();" class="form-control" id="search_name"
                                       name="name"
                                       placeholder="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div>
            <div class="row">
                <div class="col-md-6">
                    <p style="display: inline">字典类型编码:<input class="form-control" style="width: 35%;display: inline"
                                                             id="dictionaryCode" value=""></p>
                </div>
                <div class="col-md-6">
                    <p style="display: inline">字典类型名称:<input class="form-control" type="text"
                                                             style="width: 35%;display: inline" id="dictionaryName"
                                                             value="">
                    </p>
                </div>
                <span id="dictionaryId" hidden></span>
            </div>
            <table class="table table-striped table-hover table-condensed">
                <thead>
                <tr>
                    <th>明细编号</th>
                    <th hidden>旧明细编号</th>
                    <th>明细编码</th>
                    <th>明细名称</th>
                    <th>父类编号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tBody">
                <tr class="myClass">
                    <td name="id"><input class="form-control" name="id" value="" required></td>
                    <td name="oldId" hidden><input class="form-control" name="oldId" value=""></td>
                    <td name="code"><input class="form-control" name="code" value=""></td>
                    <td name="name"><input class="form-control" name="name" value=""></td>
                    <td name="parentId"><input class="form-control" name="parentId" value=""></td>
                    <td class="text-center"><a class='btn btn-default btn-xs' name='delbtn' onclick='delLine(this);'>
                        <span class='glyphicon glyphicon-minus' aria-hidden='true'></span></a></td>
                </tr>
                <tr id="plus">
                    <td>
                        <a class="btn btn-default btn-xs" onclick="addNewLine(this);"><span
                                class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
                    </td>
                    <td></td>
                </tr>
                </tbody>
            </table>
            <div class="text-center">
                <button type="button" class="btn btn-primary" onclick="modifyDetail();">修改</button>
                <button type="button" class="btn btn-danger" onclick="historyBack()">返回</button>
            </div>
        </div>
    </div>
</div>
<%--分页--%>
<div class="content row" style="height: 50px">
    <div class="demo">
        <div id="demo3"></div>
        <br>
        <form class="form-inline">
            <div class="form-group" style="width: 20%">
                <a class="btn btn-primary" onclick="jump()" style="height: 30px;width: 60px">跳转</a>
                <input type="text" style="width:30%" id="jumpPage">
                <span>页</span>
            </div>

            <span>当前第</span>
            <span id="currentPage" style="color: green">1</span>
            <span>页</span>
            <span style="display: inline-block">每页显示</span>
            <select id="count" style="display: inline-block" onchange="switchPageNumber(onLoadBasicDataDetailList);">
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
    <script src="js/page.js" type="text/javascript"></script>
</div>
</body>
</html>
