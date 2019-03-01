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
  <title>订单明细</title>
  <link href="css/util/mark.css">
  <script src="js/jquery/jquery2.0.3/jquery-2.0.3.min.js"></script>
  <script src="js/jquery/2.0.0/jquery.min.js"></script>
  <link href="css/3.3.6/bootstrap.min.css" rel="stylesheet">
  <script src="js/3.3.6/bootstrap.min.js"></script>
  <script src="js/util.js"></script>
  <link href="css/navbar.css" rel="stylesheet">
  <script src="js/navbar.js"></script>
</head>
<style type="text/css">
  .pull-right{
    float: right !important;
  }
  .pull-left{
    float: left !important;
  }
  .wrap {
    width:50px;
    margin-bottom:10px;
    position:relative;
  }
  .wrap1 {
    /*width:50px;*/
    margin-bottom:10px;
    position:relative;/*相对定位*/
  }
  .notice {
    width:20px;
    height:20px;/*notice宽高*/
    line-height:20px;/*行高*/
    font-size:10px;
    color:#fff;
    text-align:center;
    background-color:#f00;
    border-radius:50%;/*notice弧度大小*/
    position:absolute;/*绝对定位*/
    right:10px;
    /*top:10px;*/
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
        <li><a class="withripple"><span class="glyphicon glyphicon-th" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;系统首页 </span><span class="iright pull-right">&gt;</span><span class="sr-only">(current)</span></a></li>
        <li><a class="withripple" href="/orderList"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;订单列表 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/accountManage"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;账号管理 </span><span class="iright pull-right">&gt;</span></a></li>
        <li><a class="withripple" href="/basicData"><span class="glyphicon glyphicon-signal" aria-hidden="true"></span><span class="sidespan">&nbsp;&nbsp;基础数据 </span><span class="iright pull-right">&gt;</span></a></li>
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
            <input type="text" class="form-control" id="name" name="name" placeholder="" value="${insuranceOrder.proposer}">
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
            <input type="text" class="form-control" id="department" name="department" placeholder="" value="${insuranceOrder.departmentDataItem.name}">
          </div>
        </div>
        <div class="form-group">
          <label for="quantity" class="col-sm-4 control-label">包装件数：</label>
          <div class="col-xs-7">
            <input type="number" class="form-control" id="quantity" name="quantity" placeholder="" value="${insuranceOrder.packageNumber}">
          </div>
        </div>
      </div>
      <div class="form-horizontal col-md-3">
        <div class="form-group">
          <label for="name2" class="col-sm-4 control-label">被保险人名称：</label>
          <div class="col-xs-7">
            <input type="text" class="form-control" id="name2" name="name2" placeholder="" value="${insuranceOrder.insuredPersonName}">
          </div>
        </div>
        <div class="form-group">
          <label for="weight" class="col-sm-4 control-label">包装重量：</label>
          <div class="col-xs-7">
            <input type="text" class="form-control" id="weight" name="weight" value="${insuranceOrder.packageWeight}">
          </div>
        </div>
      </div>
      <div class="form-horizontal col-md-3">
        <div class="form-group">
          <label for="goodsName" class="col-sm-4 control-label">货物名称：</label>
          <div class="col-xs-7">
            <input type="text" class="form-control" id="goodsName" name="goodsName" placeholder="" value="${insuranceOrder.goodsName}">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-4 control-label">实际承运人：</label>
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
            <input type="text" onkeyup="searchData();" class="form-control" id="transportStart" name="transportStart" placeholder="" value="${insuranceOrder.originalPlace}">
          </div>
        </div>
        <div class="form-group">
          <label for="courseName" class="col-sm-4 control-label">航名：</label>
          <div class="col-xs-7">
            <input type="text" onkeyup="searchData();" class="form-control" id="courseName" name="courseName" placeholder="" value="${insuranceOrder.flightName}">
          </div>
        </div>
        <div class="form-group">
          <label for="licensePlateNumber" class="col-sm-4 control-label">车牌号：</label>
          <div class="col-xs-7">
            <input type="text" onkeyup="searchData();" class="form-control" id="licensePlateNumber" name="licensePlateNumber" placeholder="" value="${insuranceOrder.licensePlate}">
          </div>
        </div>
      </div>
      <div class="form-horizontal col-md-3">
        <div class="form-group">
          <label for="goodsTime" class="col-sm-4 control-label">提货时间：</label>
          <div class="col-xs-7">
            <input type="date" onkeyup="searchData();" class="form-control" id="goodsTime" name="goodsTime" placeholder="" value='<fmt:formatDate value="${insuranceOrder.receiveTime}" pattern="yyyy-MM-dd" />' >
          </div>
        </div>
        <div class="form-group">
          <label for="voyage" class="col-sm-4 control-label">航次：</label>
          <div class="col-xs-7">
            <input type="text" onkeyup="searchData();" class="form-control" id="voyage" name="voyage" placeholder="" value="${insuranceOrder.flightShift}">
          </div>
        </div>
        <div class="form-group">
          <label for="goodsCost" class="col-sm-4 control-label">货物价值：</label>
          <div class="col-xs-7">
            <input type="text" onkeyup="searchData();" class="form-control" id="goodsCost" name="goodsCost" placeholder="" value="${insuranceOrder.goodsValue}">
          </div>
        </div>
      </div>
      <div class="form-horizontal col-md-3">
        <div class="form-group">
          <label for="destination" class="col-sm-4 control-label">目的地：</label>
          <div class="col-xs-7">
            <input type="text" onkeyup="searchData();" class="form-control" id="destination" name="destination" placeholder="" value="${insuranceOrder.destination}">
          </div>
        </div>
        <div class="form-group">
          <label for="flightNo" class="col-sm-4 control-label">航班号：</label>
          <div class="col-xs-7">
            <input type="text" onkeyup="searchData();" class="form-control" id="flightNo" name="flightNo" placeholder="" value="${insuranceOrder.flightNumber}">
          </div>
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
      </div>
      <div class="form-horizontal col-md-3">
        <div class="form-group" style="height: 34px">
          <label for="goodsCost" class="col-sm-4 control-label"></label>
          <div class="col-xs-7"></div>
        </div>
        <div class="form-group">
          <label for="trains" class="col-sm-4 control-label">车次：</label>
          <div class="col-xs-7">
            <input type="text" class="form-control" id="trains" name="trains" placeholder="" value="${insuranceOrder.truckShift}">
          </div>
        </div>
        <div class="form-group" style="height: 34px">
          <label for="goodsCost" class="col-sm-4 control-label"></label>
          <div class="col-xs-7"></div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="form-horizontal col-md-4">
        <div class="form-group">
          <label class="col-md-3 control-label">保险报价：</label>
          <div class="col-xs-4" style="display: inline-block">
            <label for="customer" style="display: inline-block">客户承担：</label>
            <input type="text" style="width: 60px;display: inline-block;padding: 2px" class="form-control" id="customer" name="customer" value="${insuranceOrder.truckShift}">
          </div>
          <span style="display: inline-block;margin-top: 5px">人民币</span>
        </div>
      </div>
      <div class="form-horizontal col-md-4">
        <%--<div class="form-group">--%>
          <%--<span style="display: inline-block">货物价值*</span>--%>
          <%--<input style="width: 50px;display: inline-block" type="text" class="form-control" id="rate" name="rate" placeholder="" >--%>
          <%--<span style="display: inline-block">%(费率)*</span>--%>
          <%--<input style="width: 50px;display: inline-block" type="text" class="form-control" id="exchangeRate" name="exchangeRate" placeholder="">--%>
          <%--<span style="display: inline-block">汇率</span>--%>
        <%--</div>--%>
      </div>
      <div class="form-horizontal col-md-4">
        <%--<div class="form-group">--%>
          <%--<span style="display: inline-block">(MIN：</span>--%>
          <%--<input style="width: 50px;display: inline-block" type="text" class="form-control" id="RNB" name="RNB" placeholder="">--%>
          <%--<span style="display: inline-block">人民币)</span>--%>
        <%--</div>--%>
      </div>
    </div>
    <div class="row">
      <div class="form-horizontal col-md-4">
        <div class="form-group">
          <label class="col-md-3 control-label"></label>
          <div class="col-xs-10" style="margin-left: 25%">
            <label style="display: inline-block">部门承担：</label>
            <span style="display: inline-block">(以最终保单上成本结算)</span>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="form-horizontal col-md-4">
        <div class="form-group">
          <label class="col-sm-4 control-label">投保金额：</label>
          <div class="col-xs-7">
            <span style="display: inline-block">国际货运：</span>
            <input style="display: inline-block;width: 100px" type="text" class="form-control" id="internationalTrans" name="internationalTrans" placeholder="">
          </div>
        </div>
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
        <div class="form-group">
          <label for="insuranceCompanyName" class="col-sm-4 control-label">保险公司名称：</label>
          <div class="col-xs-7">
            <input type="text" class="form-control" id="insuranceCompanyName" name="insuranceCompanyName" placeholder="" value="${insuranceOrder.insuranceOrderItem.insureCompanyName}">
          </div>
        </div>
      </div>
      <div class="form-horizontal col-md-4">
        <div class="form-group">
          <div class="col-xs-7" style="display: inline-block;margin-left: 100px">
            <span style="display: inline-block">国内货运：</span>
            <input style="display: inline-block;width: 100px" type="text" class="form-control" id="domesticTrans" name="domesticTrans" placeholder="">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-4 control-label">电子保单查看：</label>
          <div class="col-xs-7">
            <%--<input type="button" id="view" name="view" value="查看" placeholder="">--%>
            <input type="button" id="download" name="download" value="下载" placeholder="">
            <%--<input type="button" id="fileUpload" name="fileUpload" value="文件上传" placeholder="">--%>
          </div>
        </div>
        <div class="form-group">
          <label for="insuranceMoney" class="col-sm-4 control-label">保费：</label>
          <div class="col-xs-7">
            <input type="text" class="form-control" id="insuranceMoney" name="insuranceMoney" placeholder="" value="${insuranceOrder.insuranceOrderItem.premium}">
          </div>
        </div>
      </div>
      <div class="form-horizontal col-md-4">
        <div class="form-group" style="height: 34px"></div>
        <div class="form-group">
          <label class="col-sm-4 control-label">保单需求：</label>
          <div class="col-xs-8">
            <input type="radio" id="radio1" name="radio" placeholder="">正本
            <input type="radio" id="radio2" name="radio" placeholder="">复印
            <input type="radio" id="radio3" name="radio" placeholder="">不需要
            <input type="button" id="invoice" name="invoice" value="发票下载" placeholder="">
            <input type="button" id="packingList" name="packingList" value="箱单下载" placeholder="">
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
        <div class="form-group">
          <label for="insuranceNumber" class="col-sm-4 control-label">保单号：</label>
          <div class="col-xs-7">
            <input type="text" class="form-control" id="insuranceNumber" name="insuranceNumber" placeholder="" value="${insuranceOrder.insuranceOrderItem.id}">
          </div>
        </div>
      </div>
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
