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
    <script src="https://edi.jd-link.cn/Api/GetToken?userName=admin&password=001"></script>
    <link rel="stylesheet" type="text/css" href="css/page/style.css" media="screen"/>
    <script src="js/jquery/jquery2.0.3/jquery-2.0.3.min.js"></script>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/3.3.6/bootstrap.min.js"></script>
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/util/mark.css">
    <script src="js/navbar.js"></script>
    <script src="js/insurance/insuranceOrder.js"></script>
    <script src="js/util.js"></script>
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
            <a class="navbar-brand" href="#"><img src="image/logo1.png"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse" style="margin-left: 150px;">
            <ul class="nav navbar-nav">
                <li class="active"><a href="">订单保险</a></li>
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
                <li><a class="withripple"><span class="glyphicon glyphicon-th" aria-hidden="true"></span><span
                        class="sidespan">&nbsp;&nbsp;系统首页 </span><span class="iright pull-right">&gt;</span><span
                        class="sr-only">(current)</span></a></li>
                <li><a class="withripple" href="orderList"><span class="glyphicon glyphicon-th-list"
                                                                  aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;订单列表 </span><span
                        class="iright pull-right">&gt;</span></a></li>
                <li><a class="withripple" href="accountManage"><span class="glyphicon glyphicon-th-list"
                                                                                 aria-hidden="true"></span><span
                        class="sidespan">&nbsp;&nbsp;账号管理 </span><span class="iright pull-right">&gt;</span></a></li>
                <li><a class="withripple" href="basicData"><span class="glyphicon glyphicon-signal"
                                                                  aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span
                        class="iright pull-right">&gt;</span></a></li>
            </ul>
        </div>
    </div>

    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top: 2%">
        <div class="row">
            <h4 class="sub-header">订单列表</h4>
        </div>
        <div class="row">
            <div>
                <!--操作按钮-->
                <%--<div class="pull-left col-md-6">--%>
                    <%--<a class="btn btn-primary btn-xs" href="" id="function_-298" onclick=""><span--%>
                            <%--class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增</a>--%>
                    <%--<a class="btn btn-primary btn-xs" href="" id="function_-299" onclick=""><span--%>
                            <%--class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span> 导入</a>--%>
                    <%--<a class="btn btn-primary btn-xs" href="" id="function_-300" onclick=""><span--%>
                            <%--class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span> 导出</a>--%>
                <%--</div>--%>
                <!--查询框-->
                <div class="input-group col-md-4 pull-right">
                    <input type="text" class="form-control" placeholder="搜索..." id="searchContent">
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
                    <div class="form-horizontal col-md-3">
                        <div class="form-group">
                            <label for="search-id" class="col-sm-4 control-label">订单号</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch();" class="form-control" id="search-id"
                                       name="search-documentNumber" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-state" class="col-sm-4 control-label">状态</label>
                            <div class="col-xs-7">
                                <select type="text" onchange="searchData()" class="form-control" id="search-state"
                                        name="search-documentNumber" placeholder=""></select>
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-md-3">
                        <div class="form-group">
                            <label for="search-department" class="col-sm-4 control-label">申请部门</label>
                            <div class="col-xs-7">
                                <select type="text" onchange="searchData()" class="form-control" id="search-department"
                                        name="search-creator" placeholder=""></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-insuredPersonName" class="col-sm-4 control-label">被投保人名称</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch()" class="form-control"
                                       id="search-insuredPersonName" name="search-creator" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-md-3">
                        <div class="form-group">
                            <label for="search-insureCompanyName" class="col-sm-4 control-label">保险公司名称</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch();" class="form-control"
                                       id="search-insureCompanyName" name="search-createDept" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-premium" class="col-sm-4 control-label">保险费</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch();" class="form-control" id="search-premium"
                                       name="search-editor" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-md-3">
                        <div class="form-group">
                            <label for="search-proposer" class="col-sm-4 control-label">申请人</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch()" class="form-control" id="search-proposer"
                                       name="search-documentNumber" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-goodsValue" class="col-sm-4 control-label">货物价值</label>
                            <div class="col-xs-7">
                                <input type="text" onkeyup="enterSearch();" class="form-control" id="search-goodsValue"
                                       name="search-createDept" placeholder="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-horizontal col-md-4 text-center">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">审批日期 </label>
                            <div class="input-group">
                                <input type="date" class="input-sm form-control" id="search-approvalDateBegin" title=""
                                       onkeydown="" onkeyup="enterSearch()"/>
                                <span class="input-group-addon">-</span>
                                <input type="date" class="input-sm form-control" id="search-approvalDateEnd" title=""
                                       onkeydown="" onkeyup="enterSearch()"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-md-2 pull-left"></div>
                    <div class="form-horizontal col-md-4 pull-left">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">投保日期 </label>
                            <div class="input-group">
                                <input type="date" class="input-sm form-control" id="search-insureDateBegin" title=""
                                       onkeydown="" onkeyup="enterSearch()"/>
                                <span class="input-group-addon">-</span>
                                <input type="date" class="input-sm form-control" id="search-insureDateEnd" title=""
                                       onkeydown="" onkeyup="enterSearch()"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-horizontal col-md-2 pull-left"></div>
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
                    <td class='text-center'></td>
                    <%--订单号--%>
                    <td class='text-center'></td>
                    <%--状态--%>
                    <td class='text-center'></td>
                    <%--申请人--%>
                    <td class='text-center'></td>
                    <%--申请部门--%>
                    <td class='text-center'></td>
                    <%--投保日期--%>
                    <td class='text-center'></td>
                    <%--审批日期--%>
                    <td class='text-center'></td>
                    <%--被投保人名称--%>
                    <td class='text-center'></td>
                    <%--货物价值--%>
                    <td class='text-center'></td>
                    <%--保险公司名称--%>
                    <td class='text-center'></td>
                    <%--保费--%>
                    <td class='text-center'>
                        <a href="/orderDetail" onclick="" title="查看"><span class="glyphicon glyphicon-search"
                                                                           aria-hidden="true"></span></a>
                        <a href="#" onclick="" title="接单"><span class="glyphicon glyphicon-check"
                                                                aria-hidden="true"></span></a>
                        <a href="#" onclick="" id="insured" title="投保"><span class="glyphicon glyphicon-edit"
                                                                             aria-hidden="true"></span></a>
                        <a href="#" onclick="" title="作废"><span class="glyphicon glyphicon-remove"
                                                                aria-hidden="true"></span></a>
                        <a href="#" id="upload" onclick="" title="上传附件"><span class="glyphicon glyphicon-open"
                                                                              aria-hidden="true"></span></a>
                        <a href="#" onclick="" title="查看附件"><span class="glyphicon glyphicon-zoom-in"
                                                                  aria-hidden="true"></span></a>
                        <a href="#" onclick="" title="关闭"><span class="glyphicon glyphicon-off"
                                                                aria-hidden="true"></span></a>
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
                <input type="text" style="width:30%" id="jumpPage">
                <span>页</span>
            </div>

            <span>当前第</span>
            <span id="currentPage" style="color: green">1</span>
            <span>页</span>
            <span style="display: inline-block">每页显示</span>
            <select id="count" style="display: inline-block" onchange="switchPageNumber();">
                <option value=1 >1</option>
                <option  value=15 selected>15</option>
                <option value=50>50</option>
            </select>
            <span style="display: inline-block">条记录</span>
            <span>总共</span>
            <span id="totalRecord" style="color: red"></span>
            <span>条记录</span>
        </form>
    </div>

    <div id="embed"></div>
    <%--投保模态框--%>
    <div class="modal fade bs-example-modal-lg" id="insuranceOrder" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document" style="width: 80%">
            <div class="modal-content">
                <div class="modal-header">
                    <h3>投保</h3>
                    <h3>保险订单号</h3><span id="insuranceOrderId"></span>

                </div>
                <div class="modal-body">
                    <table class="table table-striped table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="text-center">保险公司名称</th>
                            <th class="text-center">保单号</th>
                            <th class="text-center">投保日期</th>
                            <th class="text-center">保费</th>
                            <th class="text-center hidden">附件</th>
                            <th class="text-center">创建日期</th>
                            <th class="text-center">创建人</th>
                            <th class="text-center">修改日期</th>
                            <th class="text-center">修改人</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody id="insuranceOrderItem">
                        <tr>
                            <td class="text-center"></td>
                            <td class="text-center"></td>
                            <td class="text-center"></td>
                            <td class="text-center"></td>
                            <td class="text-center hidden"></td>
                            <td class="text-center"></td>
                            <td class="text-center"></td>
                            <td class="text-center"></td>
                            <td class="text-center"></td>
                            <td class="text-center"></td>
                            <td class="text-center">
                                <a title="增加" onclick="addModal(this)"><span class="glyphicon glyphicon-plus"
                                                                             aria-hidden="true"></span></a>
                                <a onclick="editModel(this)" title="修改"><span
                                        class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
                                <a class="delete" title="删除" onclick="deleteModel(this)"><span
                                        class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span></a>
                                <a  id="upload2" title="上传电子保单" onclick="showUploadInsurancePolicy(this)" ><span
                                        class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span></a>
                                <a href="#" onclick="info2(this)" title="异常"><span class="glyphicon glyphicon-exclamation-sign"
                                                                        aria-hidden="true"></span></a>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer text-center">
                    <a class="btn btn-primary" id="save" onclick="saveInsuranceOrderItem()">保存</a>
                    <a class="btn btn-danger" id="adjust" onclick="confirmAdjust()">修改</a>

                </div>
            </div>
        </div>
    </div>
<%--异常模态框--%>
    <div class="modal fade bs-example" id="infoModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" >
            <div class="modal-content">
                <div class="modal-header">
                    <h3>异常</h3>
                    <span class="hidden" id="abnormalId"></span>
                </div>
                <div class="modal-body">
                    <div class="info row">
                        <div class="form-group">
                            <label for="abnormal" class="col-sm-4 control-label">异常负责人</label>
                            <div class="col-xs-7">
                                <input type="text" class="form-control" id="abnormal" name="abnormal" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="abnormalInfo" class="col-sm-4 control-label">异常信息</label>
                            <div class="col-xs-7">
                                <textarea class="form-control" id="abnormalInfo" name="abnormalInfo" style="resize: none"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer text-center">
                    <a class="btn btn-primary" id="birth" onclick="getAbnormal()">生成异常单</a>
                </div>
            </div>
        </div>
    </div>
   <%--文件上传模态框--%>
    <div class="modal fade bs-example-modal-lg" id="insuranceOrderFile" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg" role="document" >
            <div class="modal-content">
                <div class="modal-header">
                    <h3>电子保单上传</h3>
                </div>
                <div class="modal-body">
                    <input id="InsuranceOrderItemId" class="hidden"/>
                    <input type="file" name="" id="file"/>
                </div>

            <div class="modal-footer text-center">
                <a class="btn btn-danger" data-dismiss="modal">取消上传</a>
                <a class="btn btn-success" onclick='insuranceOrderFile()'>确认上传</a>
            </div>
        </div>
        </div>
        </div>
<%--文件下载模态框--%>
    <div class="modal fade bs-example" id="downLoadModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width: 20%">
            <div class="modal-content">
                <div class="modal-header">
                    <h3>文件下载</h3>
                </div>
                <div class="modal-body">
                    <table style="border: 0;width: 100%">
                        <tr><td class="text-center">发票</td><td >  <input class="hidden" id="invoiceUrl"><a title='下载' onclick="downloadFile(this)"><span class='glyphicon glyphicon-download-alt' aria-hidden='true' ></span></a>
                        </td></tr>
                        <tr><td class="text-center">箱单</td><td ><input class="hidden" id="boxUrl"><a title='下载' onclick="downloadFile(this)"><span class='glyphicon glyphicon-download-alt' aria-hidden='true' ></span></a></td></tr>
                        <tr><td class="text-center"> 电子保单</td><td ><input class="hidden" id="fileUrl"><a title='下载' onclick="downloadFile(this)"><span class='glyphicon glyphicon-download-alt' aria-hidden='true' ></span></a></td></tr>
                    </table>
                  <%--<div class="row">--%>
                      <%--<div class="form-group">--%>
                          <%--<label for="search-id" class="col-sm-6 control-label text-center">发票</label>--%>
                          <%--<input class="hidden" id="invoiceUrl">--%>
                          <%--<div class="col-xs-6">--%>
                          <%--</div>--%>
                      <%--</div>--%>
                      <%--<div class="form-group">--%>
                          <%--<label for="search-id" class="col-sm-6 control-label text-center">箱单</label>--%>

                          <%--<div class="col-xs-6">--%>
                              <%--<a  title='下载' onclick="downloadFile(this)"><span class='glyphicon glyphicon-download-alt' aria-hidden='true' ></span></a>--%>
                          <%--</div>--%>
                      <%--</div>--%>
                      <%--<div class="form-group">--%>
                          <%--<label for="search-id" class="col-sm-6 control-label text-center">电子保单</label>--%>

                          <%--<div class="col-xs-6">--%>
                              <%--<a  title='下载' onclick="downloadFile(this)"><span class='glyphicon glyphicon-download-alt' aria-hidden='true' ></span></a>--%>
                          <%--</div>--%>
                      <%--</div>--%>
                  <%--</div>--%>
                </div>
                <div class="modal-footer text-center">

                </div>
            </div>
        </div>
    </div>
    <div >
        <%--<input type="file" id="file">--%>
        <input type="file" id="file2">
    </div>

    <script src="js/page.js" type="text/javascript"></script>
</div>


</body>
<script>
    /**
     * 密码修改提示
     */
    $(function (){
        console.log("执行");
        if("${modifyPasswordMark}" === "yes") {  // 设置密码提示标记
            localStorage.modifyPasswordMark = "yes";
        }else {
            localStorage.modifyPasswordMark = "no";
        }
        passwordModifyMark();
    });

    $("#upload").click(
        function () {
            $("#file").click();
        });



</script>
<style>
    .table-hover > tbody > tr:hover {
        background-color: #8ec9ff;
    }
    .wrap {
        width:50px;
        margin-bottom:10px;
        position:relative;
    }
    .wrap1 {
        /*width:50px;*/
        margin-bottom:10px;
        position:relative;
    }
    .notice {
        width:20px;
        height:20px;
        line-height:20px;/*行高*/
        font-size:10px;
        color:#fff;
        text-align:center;
        background-color:#f00;
        border-radius:50%;
        position:absolute;/*绝对定位*/
        right:10px;
        /*top:10px;*/
    }

</style>
</html>
