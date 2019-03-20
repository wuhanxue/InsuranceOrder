<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 950618
  Date: 2019/3/2
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单明细</title>
    <script src="js/jquery/jquery2.0.3/jquery-2.0.3.min.js"></script>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/3.3.6/bootstrap.min.js"></script>
    <script src="js/util.js"></script>
    <link href="css/navbar.css" rel="stylesheet">
    <script src="js/navbar.js"></script>
    <link rel="icon" href="image/favicon.ICO" type="image/x-icon"/>
</head>
<style type="text/css">
    .pull-right{
        float: right !important;
    }
    .pull-left{
        float: left !important;
    }
</style>
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
            <a class="navbar-brand" href="#"><img src="image/logo1.png"></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse" style="margin-left: 150px;">
            <ul class="nav navbar-nav">
                <li class="active"><a href="">订单明细</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="#" title="提醒"><span class="glyphicon glyphicon-bell"></span></a></li>
        <li><a href="#" title="事项"><span class="glyphicon glyphicon-envelope"></span></a></li>
        <li class="dropdown">
            <a href="#" title="我的" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span></a>
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
                <li><a class="withripple" href="orderList"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;订单列表 </span><span class="iright pull-right">&gt;</span></a></li>
                <li onclick="jumpToAccountManage()"><a class="withripple"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;账号管理 </span><span class="iright pull-right">&gt;</span></a></li>
                <li><a class="withripple" href="basicData"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span class="iright pull-right">&gt;</span></a></li>
            </ul>
        </div>
    </div>

    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top: 2%">
        <div class="row">
            <h4 class="sub-header">订单详细</h4>
        </div>
        <br>
        <div class="row">
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="name" class="col-sm-4 control-label">申请人：</label>
                    <div class="col-xs-7">
                        <input type="text" class="form-control" id="name" name="name" placeholder="" value="${insuranceOrder.proposer}" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">货物类别：</label>
                    <div class="col-xs-7">
                        <input type="radio" id="type1" name="type" placeholder="" >普通货物
                        <input type="radio" id="type2" name="type" placeholder="">特殊货物
                    </div>
                    <c:if test="${insuranceOrder.goodsType==1}">
                        <script>
                            $('#type1').attr('checked','checked');
                        </script>
                    </c:if>
                    <c:if test="${insuranceOrder.goodsType==2}">
                        <script>
                            $('#type2').attr('checked','checked');
                        </script>
                    </c:if>
                </div>
            </div>
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="department" class="col-sm-4 control-label">申请部门：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" class="form-control" id="department" name="department" placeholder="" value="${insuranceOrder.departmentDataItem.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="quantity" class="col-sm-4 control-label">包装件数：</label>
                    <div class="col-xs-7">
                        <input readonly type="number" class="form-control" id="quantity" name="quantity" placeholder="" value="${insuranceOrder.packageNumber}">
                    </div>
                </div>
            </div>
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="name2" class="col-sm-4 control-label">被保险人名称：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" class="form-control" id="name2" name="name2" placeholder="" value="${insuranceOrder.insuredPersonName}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="weight" class="col-sm-4 control-label">包装重量：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" class="form-control" id="weight" name="weight" value="${insuranceOrder.packageWeight}">
                    </div>
                </div>
            </div>
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="goodsName" class="col-sm-4 control-label">货物名称：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" class="form-control" id="goodsName" name="goodsName" placeholder="" value="${insuranceOrder.goodsName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">承运人类型：</label>
                    <div class="col-xs-7">
                        <input type="radio" id="person1" name="person" placeholder="">我司
                        <input type="radio" id="person2" name="person" placeholder="">供方
                        <input type="radio" id="person3" name="person" placeholder="">第三方
                        <c:if test="${insuranceOrder.actualCarrier==1}">
                            <script>
                                $('#person1').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.actualCarrier==2}">
                            <script>
                                $('#person2').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.actualCarrier==3}">
                            <script>
                                $('#person3').attr('checked','checked');
                            </script>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-md-1 control-label" style="margin-left: 30px">业务范围：</label>
                <div class="col-md-6">
                    <label id="international">国际运输：</label>
                    <input type="radio" id="sea1" name="type1" placeholder="">海运进口&nbsp;&nbsp;
                    <input type="radio" id="sea2" name="type1" placeholder="">海运出口&nbsp;&nbsp;
                    <input type="radio" id="air1" name="type1" placeholder="">空运进口&nbsp;&nbsp;
                    <input type="radio" id="air2" name="type1" placeholder="">空运出口&nbsp;&nbsp;
                    <input type="radio" id="road1" name="type1" placeholder="">陆运进口&nbsp;&nbsp;
                    <input type="radio" id="road2" name="type1" placeholder="">陆运出口&nbsp;&nbsp;
                    <input type="radio" id="rail1" name="type1" placeholder="">铁路进口&nbsp;&nbsp;
                    <input type="radio" id="rail2" name="type1" placeholder="">铁路出口
                    <c:if test="${insuranceOrder.internationalFreightDataItem!=null}">
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='003'}">
                            <script>
                                $('#sea1').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='004'}">
                            <script>
                                $('#sea2').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='001'}">
                            <script>
                                $('#air1').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='002'}">
                            <script>
                                $('#air2').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='005'}">
                            <script>
                                $('#road1').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='006'}">
                            <script>
                                $('#road2').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='011'}">
                            <script>
                                $('#rail1').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.internationalFreightDataItem.id=='012'}">
                            <script>
                                $('#rail2').attr('checked','checked');
                            </script>
                        </c:if>
                    </c:if>
                </div>
                <%--<div class="col-md-6">--%>
                    <%--<label>实际承运人：</label>--%>
                    <%--<div class="col-xs-4">--%>
                        <%--<input readonly type="text" class="form-control"  name="goodsName" placeholder="" value="${insuranceOrder.bearfees}">--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>
        <div class="row">
            <div class="form-group">
                <label class="col-md-1 control-label" style="margin-left: 30px"></label>
                <div class="col-md-6">
                    <label id="domestic">国内运输：</label>
                    <input type="radio" id="address1" name="type2" placeholder="">快递&nbsp;&nbsp;
                    <input type="radio" id="address2" name="type2" placeholder="">水运&nbsp;&nbsp;
                    <input type="radio" id="address3" name="type2" placeholder="">空运&nbsp;&nbsp;
                    <input type="radio" id="address4" name="type2" placeholder="">陆运&nbsp;&nbsp;
                    <input type="radio" id="address5" name="type2" placeholder="">铁路运输
                    <c:if test="${insuranceOrder.domesticFreightDataItem!=null}">
                        <c:if test="${insuranceOrder.domesticFreightDataItem.id=='200005'}">
                            <script>
                                $('#address5').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.domesticFreightDataItem.id=='200003'}">
                            <script>
                                $('#address2').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.domesticFreightDataItem.id=='200002'}">
                            <script>
                                $('#address3').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.domesticFreightDataItem.id=='200001'}">
                            <script>
                                $('#address4').attr('checked','checked');
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.domesticFreightDataItem.id=='200004'}">
                            <script>
                                $('#address5').attr('checked','checked');
                            </script>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="transportStart" class="col-sm-4 control-label">启运地：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" onkeyup="searchData();" class="form-control" id="transportStart" name="transportStart" placeholder="" value="${insuranceOrder.originalPlace}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="courseName" class="col-sm-4 control-label">航名：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" onkeyup="searchData();" class="form-control" id="courseName" name="courseName" placeholder="" value="${insuranceOrder.flightName}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="licensePlateNumber" class="col-sm-4 control-label">车牌号：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" onkeyup="searchData();" class="form-control" id="licensePlateNumber" name="licensePlateNumber" placeholder="" value="${insuranceOrder.licensePlate}">
                    </div>
                </div>
            </div>
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="goodsTime" class="col-sm-4 control-label">提货时间：</label>
                    <div readonly class="col-xs-7">
                        <input readonly type="date" onkeyup="searchData();" class="form-control" id="goodsTime" name="goodsTime" placeholder="" value='<fmt:formatDate value="${insuranceOrder.receiveTime}" pattern="yyyy-MM-dd" />' >
                    </div>
                </div>
                <div class="form-group">
                    <label for="voyage" class="col-sm-4 control-label">航次：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" onkeyup="searchData();" class="form-control" id="voyage" name="voyage" placeholder="" value="${insuranceOrder.flightShift}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="trains" class="col-sm-4 control-label">车次：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" class="form-control" id="trains" name="trains" placeholder="" value="${insuranceOrder.truckShift}">
                    </div>
                </div>
            </div>
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="destination" class="col-sm-4 control-label">目的地：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" onkeyup="searchData();" class="form-control" id="destination" name="destination" placeholder="" value="${insuranceOrder.destination}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="flightNo" class="col-sm-4 control-label">航班号：</label>
                    <div class="col-xs-7">
                        <input readonly type="text" onkeyup="searchData();" class="form-control" id="flightNo" name="flightNo" placeholder="" value="${insuranceOrder.flightNumber}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">投保金额：</label>
                    <div class="col-xs-7">
                        <span style="display: inline-block">国际货运：</span>
                        <input readonly style="display: inline-block;width: 100px" type="text" class="form-control" id="internationalTrans" name="internationalTrans" placeholder="" value="${insuranceOrder.internationalInsuranceMoney}">
                    </div>
                    <c:if test="${insuranceOrder.yHTGoods==true}">
                        <script>
                            $('#goods1').attr("checked","checked");
                        </script>
                    </c:if>
                    <c:if test="${insuranceOrder.yHTGoods==false}">
                        <script>
                            $('#goods2').attr("checked","checked");
                        </script>
                    </c:if>
                </div>
            </div>
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label class="col-sm-4 control-label">费用承担：</label>
                    <div class="col-xs-7">
                        <input type="radio" id="customer" name="cost" placeholder="">客户承担
                        <input type="radio" id="depart" name="cost" placeholder="">部门承担
                    </div>
                    <c:if test="${insuranceOrder.feeCostType==1}">
                        <script>
                            $('#customer').attr("checked","checked");
                        </script>
                    </c:if>
                    <c:if test="${insuranceOrder.feeCostType==2}">
                        <script>
                            $('#depart').attr("checked","checked");
                        </script>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">港澳台货物：</label>
                    <div class="col-xs-7">
                        <input type="radio" id="goods1" name="goods" placeholder="" >是
                        <input type="radio" id="goods2" name="goods" placeholder="">否
                    </div>
                    <c:if test="${insuranceOrder.yHTGoods==true}">
                        <script>
                            $('#goods1').attr("checked","checked");
                        </script>
                    </c:if>
                    <c:if test="${insuranceOrder.yHTGoods==false}">
                        <script>
                            $('#goods2').attr("checked","checked");
                        </script>
                    </c:if>
                </div>
                <div class="form-group" style="margin-top: 28px">
                    <div class="col-xs-7">
                        <span style="display: inline-block">国内货运：</span>
                        <input readonly style="display: inline-block;width: 100px" type="text" class="form-control" id="domesticInsuranceMoney" name="domesticTrans" placeholder="" value="${insuranceOrder.domesticInsuranceMoney}">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-horizontal col-md-3">
                <div class="form-group">
                    <label for="trains" class="col-sm-4 control-label">附加险：</label>
                    <div class="col-xs-7">
                        <input type="radio" id="insurance1" name="insurance" placeholder="">吊装险
                        <input type="radio" id="insurance2" name="insurance" placeholder="">其他险种
                    </div>
                    <c:if test="${insuranceOrder.fileInsurance==1}">
                        <script>
                            $('#insurance1').attr('checked','checked');
                        </script>
                    </c:if>
                    <c:if test="${insuranceOrder.fileInsurance==2}">
                        <script>
                            $('#insurance2').attr('checked','checked');
                        </script>
                    </c:if>
                </div>
            </div>
            <%--<div class="form-horizontal col-md-3">--%>
                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="col-xs-7" style="display: inline-block;margin-left: 100px">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<span style="display: inline-block">国内货运：</span>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<input readonly style="display: inline-block;width: 100px" type="text" class="form-control" id="domesticInsuranceMoney" name="domesticTrans" placeholder="" value="${insuranceOrder.domesticInsuranceMoney}">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<label class="col-sm-4 control-label">电子保单查看：</label>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="col-xs-7">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;<input type="button" id="view" name="view" value="查看" placeholder="">&ndash;%&gt;&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<input type="button" id="download" name="download" value="下载" placeholder="">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;&lt;%&ndash;<input type="button" id="fileUpload" name="fileUpload" value="文件上传" placeholder="">&ndash;%&gt;&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--</div>--%>
            <div class="form-horizontal col-md-4">
                <div class="form-group">
                    <label class="col-sm-4 control-label">保单需求：</label>
                    <div class="col-xs-8">
                        <input type="radio" id="radio1" name="radio" placeholder="">正本
                        <input type="radio" id="radio2" name="radio" placeholder="">复印
                        <input type="radio" id="radio3" name="radio" placeholder="">不需要
                        <input class="hidden">
                        <input type="button" id="invoice" name="invoice" value="发票下载" placeholder="" onclick="downLoad('${insuranceOrder.invoiceUrl}')">
                        <input class="hidden">
                        <input type="button" id="packingList" name="packingList" value="箱单下载" placeholder="" onclick="downLoad('${insuranceOrder.boxUrl}')">
                        <c:if test="${insuranceOrder.insuranceOrderRequirement==1}">
                            <script>
                                $('#radio1').attr('checked','checked')
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.insuranceOrderRequirement==2}">
                            <script>
                                $('#radio2').attr('checked','checked')
                            </script>
                        </c:if>
                        <c:if test="${insuranceOrder.insuranceOrderRequirement==3}">
                            <script>
                                $('#radio3').attr('checked','checked')
                            </script>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="form-horizontal col-md-3">
                <div class="form-group">
                <label for="licensePlateNumber" class="col-sm-4 control-label">实际承运人：</label>
                <div class="col-xs-7">
                    <input readonly type="text" onkeyup="searchData();" class="form-control"  name="licensePlateNumber" placeholder="" value="${insuranceOrder.bearfees}">
                </div>
                </div>
            </div>

        </div>
        <div class="row">
            <%--<div class="form-horizontal col-md-2"></div>--%>
            <div class="form-horizontal col-md-12">
                <table class="table table-striped table-hover table-condensed">
                    <c:forEach items="${insuranceOrder.goodsValues}" var="item">


                        <tr>
                            <td class="text-center" style="width: 8.3%">货物价值</td>
                            <td class="text-center" style="width: 8.3%">
                                <input readonly type="text" onkeyup="searchData();" class="form-control" id="goodsCost" name="goodsCost" placeholder="" value="${item.value}">
                            </td>
                            <td class="text-center" style="width: 8.3%">币种</td>
                            <td class="text-center" style="width: 8.3%">
                                <input readonly type="text" onkeyup="searchData();" class="form-control"  name="goodsCost" placeholder="" value="${item.currencyDataItem.name}">
                            </td>

                            <td class="text-center" style="width: 8.3%">保险公司名称</td>
                            <td class="text-center" style="width: 8.3%">
                                <input readonly type="text" class="form-control" id="insuranceCompanyName" name="insuranceCompanyName" placeholder="" value="${item.insuranceOrderItem.insureCompanyName}">
                            </td>
                            <td class="text-center" style="width: 8.3%">保费</td>
                            <td class="text-center" style="width: 8.3%">
                                <input readonly type="text" class="form-control" id="insuranceMoney" name="insuranceMoney" placeholder="" value="${item.insuranceOrderItem.premium}">
                            </td>
                            <td class="text-center" style="width: 8.3%">保单号</td>
                            <td class="text-center" style="width: 8.3%">
                                <input readonly type="text" class="form-control" id="insuranceNumber" name="insuranceNumber" placeholder="" value="${item.insuranceOrderItem.id}">
                            </td>
                            </td>
                            <td class="text-center" style="width: 8.3%">电子保单下载</td>
                            <td class="text-center" style="width: 8.3%">
                                <input  type="button"   name="insuranceNumber" placeholder="" value="下载" onclick="downLoad('${item.insuranceOrderItem.fileUrl}')">
                            </td>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${insuranceOrder.insuranceOrderItemList}" var="item" >
                    <%--<tr>--%>
                        <%--<td class="text-center" style="width: 17%">保险公司名称</td>--%>
                        <%--<td class="text-center" style="width: 17%">--%>
                            <%--<input readonly type="text" class="form-control" id="insuranceCompanyName" name="insuranceCompanyName" placeholder="" value="${item.insureCompanyName}">--%>
                        <%--</td>--%>
                        <%--<td class="text-center" style="width: 16%">保费</td>--%>
                        <%--<td class="text-center" style="width: 16%">--%>
                            <%--<input readonly type="text" class="form-control" id="insuranceMoney" name="insuranceMoney" placeholder="" value="${item.premium}">--%>
                        <%--</td>--%>
                        <%--<td class="text-center" style="width: 17%">保单号</td>--%>
                        <%--<td class="text-center" style="width: 17%">--%>
                            <%--<input readonly type="text" class="form-control" id="insuranceNumber" name="insuranceNumber" placeholder="" value="${item.id}">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    </c:forEach>

                </table>
            </div>
            <%--<div class="form-horizontal col-md-2"></div>--%>
        </div>
        <div class="text-center">
            <a type="button" class="btn btn-success" onclick="historyBack()">返回</a>
        </div>
    </div>
</div>
<div class="hidden">
    <input type="file" id="file1" name="file1" value="文件上传" placeholder="">
    <input type="file" id="file2" name="file2" value="发票下载" placeholder="">
    <input type="file" id="file3" name="file3" value="箱单下载" placeholder="">
</div>
<div id="embed"></div>
</body>
<%--<script>--%>
<%--$("#fileUpload").click(--%>
<%--function file1Click() {--%>
<%--$("#file1").click();--%>
<%--})--%>
<%--$("#invoice").click(--%>
<%--function file1Click() {--%>
<%--$("#file2").click();--%>
<%--})--%>
<%--$("#packingList").click(--%>
<%--function file1Click() {--%>
<%--$("#file3").click();--%>
<%--})--%>
<%--</script>--%>
</html>
