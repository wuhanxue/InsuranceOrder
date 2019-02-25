/*订单保险脚本*/
//初始化查询状态为false
var isSearch=false;
function insuranceOrderLoad(page,data) {
    //传给page.js方法名
    initFName(insuranceOrderLoad.name);

    //将查询的状态传给page.js
    initisSearch(isSearch);

      /*页面的初始属性
      * 第一次加载时需要
      * */
     if(page==undefined){
         page=getInitPage();
     }

      //普通加载
     if(!isSearch){
         initisSearch(false);
         //根据url获取总页数
         TotalPage('getTotalInsuranceOrder');
         $.ajax({
             type:"POST",
             url:"listInsuranceOrder",
             async: false,
             data:JSON.stringify(page),
             dataType:"json",
             contentType: "application/json; charset=utf-8",
             success:function (result) {
                 if (result != undefined && result.status == "success"){
                     console.log(result);
                     //删除子元素
                     $('#insuranceOrderList').empty();
                     setInsuranceOrderList(result)
                 }
             },
             error:function (result) {

             }
         });
     }
     //查询加载
     if(isSearch){
         $.ajax({
             type:"POST",
             url:"searchInsuranceOrder",
             async: false,
             data:JSON.stringify(data),
             dataType:"json",
             contentType: "application/json; charset=utf-8",
             success:function (result) {
                 if (result != undefined && result.status == "success"){
                     console.log(result);
                     //删除子元素
                     $('#insuranceOrderList').empty();
                     setInsuranceOrderList(result)
                 }
             },
             error:function (result) {

             }
         })

         TotalPage("searchInsuranceOrderTotal",data);//算查询总数
     }

}





/*设置列表数据*/
function setInsuranceOrderList(result) {

    $.each(result.data,function (index,item) {
        //复杂对象
        //投保日期
        var insureDate;
        var insureCompanyName;
        var premium;
        if(item.insuranceOrderItem!=null){
            insureDate=getDateStr(item.insuranceOrderItem.insureDate);
            insureCompanyName=item.insuranceOrderItem.insureCompanyName;
            premium=item.insuranceOrderItem.premium;
        }
               var tr="<tr><td class='text-center'>"+item.id+"</td>"+"<td class='text-center'>"+getDataFromDate(item.orderStateDataItem)+"</td>"+"<td class='text-center'>"+item.proposer+"</td>"
                   +"<td class='text-center'>"+getDataFromDate(item.departmentDataItem)+"</td>"+"<td class='text-center'>"+insureDate+"</td>"
                   +"<td class='text-center'>"+getDateStr(item.approvalDate)+"<td class='text-center'>"+item.insuredPersonName+"</td>"+"<td class='text-center'>"+item.goodsValue.toFixed(3)+"</td>"
                   +"<td class='text-center'>"+insureCompanyName+"</td>" +"<td class='text-center'>"+premium.toFixed(3)+"</td>"
                   +"<td class='text-center'>"+"<a href='/viewInsuranceOrder?id="+item.id+"' title='查看'><span class='glyphicon glyphicon-search' aria-hidden='true'></span></a>" +
                   "<a href='orderDetail'  title='接单'><span class='glyphicon glyphicon-check' aria-hidden='true'></span></a>" +
                   "<a href='orderDetail'  title='投保'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a>" +
                   "<a href='orderDetail'  title='作废'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>" +
                   "<a href='orderDetail'  title='上传附件'><span class='glyphicon glyphicon-open' aria-hidden='true'></span></a>" +
                   "<a href='orderDetail'  title='查看附件'><span class='glyphicon glyphicon-zoom-in' aria-hidden='true'></span></a>" +
                   "<a href='orderDetail'  title='关闭'><span class='glyphicon glyphicon-off' aria-hidden='true'></span></a>" +
                   "</td></tr>";
        $('#insuranceOrderList').append(tr)
    })



}


$(document).ready(function () {//页面载入是就会进行加载里面的内容
    var last;
    $('#searchContent').keyup(function (event) { //给Input赋予onkeyup事件
        last = event.timeStamp;//利用event的timeStamp来标记时间，这样每次的keyup事件都会修改last的值，注意last必需为全局变量
        setTimeout(function () {
            if (last - event.timeStamp == 0) {
                searchData();
            } else if (event.keyCode === 13) {   // 如果按下键为回车键，即执行搜素
                searchData();      //
            }
        }, 600);
    });
});

/*查询*/
function searchData() {
    initisSearch(true);
    var page = {};
    page.start = parseInt(countValue()) * (parseInt($('#currentPage').text()) - 1);
    page.count = countValue();
   /*高级查询*/
    var data;
    if ($("#senior").is(':visible')) {
         data={};//代表查询实体
        /*订单编号*/
        var id=$('#search-id').val();
        /*保险公司名称*/
        var insureCompanyName=$('#search-insureCompanyName').val();
        /*被投保人名称*/
        var insuredPersonName=$('#search-insuredPersonName').val();
        /*保险费*/
        var premium=$('#search-premium').val();
        /*申请人*/
        var proposer=$('#search-proposer').val();
        /*货物价值*/
        var goodsValue=$('#search-goodsValue').val();
        /*投保日期开始日期*/
        var insureDateBegin=$('#search-insureDateBegin').val();
        /*投保日期结束日期*/
        var insureDateEnd=$('#search-insureDateEnd').val();
        /*审批日期开始日期*/
        var approvalDateBegin=$('#search-approvalDateBegin').val();
        /*审批日期结束日期*/
        var approvalDateEnd=$('#search-approvalDateEnd').val();

        data.id=id;
        var insuranceOrderItem={};
        insuranceOrderItem.insureCompanyName=insureCompanyName;
        data.insuredPersonName=insuredPersonName;
        insuranceOrderItem.premium=premium;
        data.proposer=proposer;
        data.goodsValue=goodsValue;
        insuranceOrderItem.insureDateBegin=insureDateBegin;
        insuranceOrderItem.insureDateEnd=insureDateEnd;
        data.approvalDateBegin=approvalDateBegin;
        data.approvalDateEnd=approvalDateEnd;
        data.insuranceOrderItem=insuranceOrderItem;
        data.page=page;
        console.log(data);


    }

    /*输入框查询*/
    else {
        var keywords = $.trim($("#searchContent").val());
         data={
            page: page,
            keywords: keywords
        }
    }

    getData(data);
    //将要查询的元素穿个page页面
    /*查询的方法*/
    $.ajax({
        type:"POST",
        url:"searchInsuranceOrder",
        async: false,
        data:JSON.stringify(data),
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
                console.log(result);
                //删除子元素
                $('#insuranceOrderList').empty();
                setInsuranceOrderList(result)
            }
        },
        error:function (result) {
            
        }
    })

    TotalPage("searchInsuranceOrderTotal",data);//算查询总数
}

/**
 * 回车查询
 */
function enterSearch() {
    if (event.keyCode === 13) {   // 如果按下键为回车键，即执行搜素
        searchData();      //
    }
}