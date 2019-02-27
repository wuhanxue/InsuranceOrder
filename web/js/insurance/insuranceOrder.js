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
      if(data==null||data==undefined){
          var data={};
          data.page=page;
      }


         //根据url获取总页数
         // TotalPage('getTotalInsuranceOrder');
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
         });

         TotalPage("searchInsuranceOrderTotal",data);//算查询总数


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
        else {
            insureDate="";
            insureCompanyName="";
            premium=0;
        }
               var tr="<tr><td class='text-center'>"+item.id+"</td>"+"<td class='text-center'>"+getDataFromDate(item.orderStateDataItem)+"</td>"+"<td class='text-center'>"+item.proposer+"</td>"
                   +"<td class='text-center'>"+getDataFromDate(item.departmentDataItem)+"</td>"+"<td class='text-center'>"+insureDate+"</td>"
                   +"<td class='text-center'>"+getDateStr(item.approvalDate)+"<td class='text-center'>"+item.insuredPersonName+"</td>"+"<td class='text-center'>"+getNumber(item.goodsValue,3)+"</td>"
                   +"<td class='text-center'>"+insureCompanyName+"</td>" +"<td class='text-center'>"+getNumber(premium,3)+"</td>"
                   +"<td class='text-center'>"+"<a href='viewInsuranceOrder?id="+item.id+"' title='查看'>" +
                   "<span class='glyphicon glyphicon-search' aria-hidden='true'></span></a>" +
                   "<a href='orderDetail'  title='接单'><span class='glyphicon glyphicon-check' aria-hidden='true'></span></a>" +
                   "<a href='#'  title='投保' onclick='InsureModel(this)'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a>" +
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

/*点击投保按钮*/
function InsureModel(item) {

   $('#save').hide();
    $('#adjust').hide();
    /*清空数据内容*/
    var insuranceOrderItem=$('#insuranceOrderItem');
    var tdArray=$(insuranceOrderItem).find('tr').children('td');
    $.each(tdArray,function (index,item) {
        if(index<=8){
                $(this).html("");
        }
    })


    var id=$(item).parent().parent().children('td').eq(0).html();

     $('#insuranceOrderId').text(id);

    $("#insuranceOrder").modal('show');


     $.ajax({
         type:"POST",
         url:"getInsuranceOrderItemById",
         async: false,
         data:{"id":id},
         dataType:"json",
         // contentType: "application/json; charset=utf-8",
         success:function (result) {
             if (result != undefined && result.status == "success"){
              console.log(result);
                 setOrderDetail(result);

             }
             else {

             }
         },
         error:function (result) {
             alert("服务器异常!")
         }
     })
}

/*设置保单信息*/
function setOrderDetail(result) {
if(result.data==null){
    alert('暂无保单信息');

}
else {
    var obj=eval(result.data);
    var tdArray=$(insuranceOrderItem).find('tr').children('td');
    //保险公司名称
    $(tdArray).eq(0).html(obj.insureCompanyName);
    //保单号
    $(tdArray).eq(1).html(obj.id);
    //投保日期
    $(tdArray).eq(2).html(getDateStr(obj.insureDate));
    //保费
    $(tdArray).eq(3).html(obj.premium.toFixed(3));
    //附件 存放附件的名字

    //创建日期
    $(tdArray).eq(5).html(getDateStr(obj.creationTime));
    //创建人
    $(tdArray).eq(6).html(obj.creator);
    //修改日期
     $(tdArray).eq(7).html(getDateStr(obj.modifyTime));
     //修改人
    $(tdArray).eq(8).html(obj.modifier);
    //状态

}

}

/*新增保单*/
function addModal(item) {

    var id=$('#insuranceOrderId').text();

    $.ajax({
        type:"POST",
        url:"getInsuranceOrderItemById",
        async: false,
        data:{"id":id},
        dataType:"json",
        // contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
                  if(result.data==null){
                      var insuranceOrderItem=$('#insuranceOrderItem');
                      var tdArray=$(insuranceOrderItem).find('tr').children('td');
                      var input="<input class='form-control'>";
                      var dateInput="<input class='form-control' type='date'>";
                      var readOnly="<input class='form-control' readonly='readonly'>";
                      $.each(tdArray,function (index,item) {
                          if(index<=8){
                              if(index==2||index==5){
                                  $(this).html((dateInput));
                              }
                               else if (index==4||index==7||index==8){
                                  $(this).html((readOnly));
                              }

                              else {
                                  $(this).html((input));
                              }
                          }


                      })
                      $('#save').show();
                      $('#adjust').hide();
                  }
                if(result.data!=null) {
                      alert("已有保单信息，无法增加")
                  }
            }
            else {

            }
        },
        error:function (result) {
            alert("服务器异常!")
        }
    })


}

/*修改模态框内容*/
function editModel(item) {
    var id=$('#insuranceOrderId').text();
    $.ajax({
        type:"POST",
        url:"getInsuranceOrderItemById",
        async: false,
        data:{"id":id},
        dataType:"json",
        // contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
                if(result.data==null){
                    alert("无保单信息，无法修改!")
                }
                if(result.data!=null) {

                    var insuranceOrderItem=$('#insuranceOrderItem');
                    var tdArray=$(insuranceOrderItem).find('tr').children('td');
                    var input="<input class='form-control'>";
                    var dateInput="<input class='form-control' type='date'>";
                    var readOnly="<input class='form-control' readonly='readonly'>";
                    if($(tdArray[0]).find('input').length>0){//有input元素
                        $.each(tdArray,function (index,item) {

                            if(index<=8){
                                var content=$(this).find("input").val();
                                $(this).empty();
                                $(this).html(content)

                            }

                        })
                        $('#adjust').hide();
                        $('#save').hide();
                    }
                    //没有input
                    else {
                        $.each(tdArray,function (index,item) {
                            if(index<=8){
                                //日期页面
                                if(index==2||index==7){
                                    var contentDate=$(this).html();
                                    $(this).html($(dateInput).val(contentDate));
                                }
                                else if(index==4||index==5||index==6){
                                    var contentReadOnly=$(this).html();
                                    $(this).html($(readOnly).val(contentReadOnly));
                                }
                                else {
                                    var content=$(this).html();
                                    $(this).html($(input).val(content));
                                }
                            }


                        })
                        $('#adjust').show();
                        $('#save').hide();
                    }


                }
            }
            else {

            }
        },
        error:function (result) {
            alert("服务器异常!")
        }
    })
}

/*确认修改*/
function confirmAdjust() {
    var tdArray=$(insuranceOrderItem).find('tr').children('td');
    var InsuranceOrderItem={};
    InsuranceOrderItem.insureCompanyName=tdArray.eq(0).find('input').val();
    InsuranceOrderItem.id=tdArray.eq(1).find('input').val();
    InsuranceOrderItem.insureDate=tdArray.eq(2).find('input').val();
    InsuranceOrderItem.premium=tdArray.eq(3).find('input').val();
    InsuranceOrderItem.modifyTime=tdArray.eq(7).find('input').val();
    InsuranceOrderItem.modifier=tdArray.eq(8).find('input').val();
    InsuranceOrderItem.orderId=$('#insuranceOrderId').text();

    $.ajax({
        type:"POST",
        url:"updateInsuranceOrderItem",
        async: false,
        data:JSON.stringify(InsuranceOrderItem),
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
                   alert(result.message)
                window.location.reload();
            }
            else {

            }
        },
        error:function (result) {
            alert("服务器异常!")
        }
    })



}

/*保单删除*/
function deleteModel(item) {

    if(confirm("确定删除该保单?")){
        //点击确定后操作
        var id=$(item).parent().parent().children('td').eq(1).html();
        console.log(id)

        $.ajax({
            type:"POST",
            url:"deleteInsuranceOrderItemById",
            async: false,
            data:{"id":id},
            dataType:"json",
            // contentType: "application/json; charset=utf-8",
            success:function (result) {
                if (result != undefined && result.status == "success"){
                    alert(result.message);
                    window.location.reload()
                }
                else {

                }
            },
            error:function (result) {
                alert("服务器异常!")
            }
        })
    }


}

/*保存订单方法*/
function saveInsuranceOrderItem() {
    var insuranceOrderItem=$('#insuranceOrderItem');
    var tdArray=$(insuranceOrderItem).find('tr').children('td');

    var InsuranceOrderItem={};
    InsuranceOrderItem.id=$(tdArray).eq(1).find("input").val();
    InsuranceOrderItem.insureCompanyName=$(tdArray).eq(0).find("input").val();
    InsuranceOrderItem.insureDate=$(tdArray).eq(2).find("input").val();
    InsuranceOrderItem.premium=$(tdArray).eq(3).find("input").val();
    InsuranceOrderItem.creationTime=$(tdArray).eq(5).find("input").val();
    InsuranceOrderItem.creator=$(tdArray).eq(6).find("input").val();
    InsuranceOrderItem.orderId=$('#insuranceOrderId').text();

    $.ajax({
        type:"POST",
        url:"addInsuranceOrderItem",
        async: false,
        data:JSON.stringify(InsuranceOrderItem),
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
              alert(result.message)
                window.location.reload()
            }
            else {

            }
        },
        error:function (result) {
            alert("服务器异常!")
        }
    })
}


//打开上传电子保单模态框
function showUploadInsurancePolicy(item) {
    $('#InsuranceOrderItemId').val($(item).parent().parent().children('td').eq(1).html())
    $("#insuranceOrderFile").modal('show');

}
//电子保单上传
function insuranceOrderFile() {

    //文件对象
    var formData = new FormData();
    formData.append("id", $('#InsuranceOrderItemId').val());
    formData.append("multipartFile",document.getElementById("file").files[0]);
  if(document.getElementById("file").files[0]==undefined){
      alert("文件为空！")
  }
  else {
      $.ajax({
          url: "uploadInsurancePolicy",
          type: "POST",
          data: formData,
          contentType: false,//必须false才会自动加上正确的Content-Type
          processData: false,//必须false才会避开jQuery对 formdata 的默认处理XMLHttpRequest会对 formdata 进行正确的处理
          success:function (result) {
              alert("文件上传成功");
              window.location.reload();
          },
          error:function (result) {
              alert("服务器异常!")
          }
      })
  }





}