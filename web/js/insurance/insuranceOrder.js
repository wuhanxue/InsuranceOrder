/*订单保险脚本*/

function insuranceOrderLoad(page) {
    console.log(page)
     if(page==undefined){
         page=getInitPage();
     }
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

$(function () {
    $('#selected').click(function () {
        alert(123)
    })
})



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

findPageumber(insuranceOrderLoad);