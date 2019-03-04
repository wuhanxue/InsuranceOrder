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

    getDataToSelect();









}







/*设置列表数据*/
function setInsuranceOrderList(result) {

    $.each(result.data,function (index,item) {
    //货物类别
        var goodsType;
        if(item.goodsType==1){
            goodsType="普通货物"
        }
        else {
            goodsType="特殊货物"
        }
        var actualCarrier;
        if(item.actualCarrier==1){
            actualCarrier="我司承运"
        }
        if(item.actualCarrier==2){
            actualCarrier="供方承运"
        }
        if(item.actualCarrier==3){
            actualCarrier="第三方承运"
        }
        //港澳台货物
        var yHTGoods;
        if(item.yHTGoods==true){
            yHTGoods='是'
        }
        else {
            yHTGoods='否'
        }
        //附件险
        var fileInsurance;
        if(item.fileInsurance==1){
            fileInsurance='吊装险'
        }
        else {
            fileInsurance='其它'
        }
        //保单要求
        var insuranceOrderRequirement;
        if(item.insuranceOrderRequirement==1){
            insuranceOrderRequirement='正本'
        }
        if(item.insuranceOrderRequirement==2){
            insuranceOrderRequirement='复印件'
        }
        if(item.insuranceOrderRequirement==3){
            insuranceOrderRequirement='不需要'
        }
               var tr="<tr><td class='text-center'>"+item.id+"</td>"+"<td class='text-center'>"+getDataFromDate(item.orderStateDataItem)+"</td>"+"<td class='text-center'>"+item.proposer+"</td>"
                   +"<td class='text-center'>"+getDataFromDate(item.departmentDataItem)+"</td>"+"<td class='text-center'>"+getDateStr(item.approvalDate)+"</td>"
                   +"<td class='text-center'>"+item.insuredPersonName+"<td class='text-center'>"+goodsType+"</td>"+"<td class='text-center'>"+actualCarrier+"</td>"
                   +"<td class='text-center'>"+yHTGoods+"</td>" +"<td class='text-center'>"+fileInsurance+"</td>" +"<td class='text-center'>"+insuranceOrderRequirement+"</td>"
                   +"<td class='text-center'>"+"<a href='viewInsuranceOrder?id="+item.id+"' title='查看'>" +
                   "<span class='glyphicon glyphicon-search' aria-hidden='true'></span></a>" +
                   "<a   title='接单' onclick='Receipt(this)'><span class='glyphicon glyphicon-check' aria-hidden='true'></span></a>" +
                   "<a href='#'  title='投保' onclick='InsureModel(this)'><span class='glyphicon glyphicon-edit' aria-hidden='true'></span></a>" +
                   "<a   title='作废' onclick='cancel(this)'><span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>" +
                   // "<a href='orderDetail'  title='上传附件'><span class='glyphicon glyphicon-open' aria-hidden='true'></span></a>" +
                   "<a onclick='downLoadModal(this)' title='下载附件'><span class='glyphicon glyphicon-download-alt' aria-hidden='true' ></span></a>" +
                   "<a  title='关闭' onclick='ShutDown(this)'><span class='glyphicon glyphicon-off' aria-hidden='true'></span></a>" +
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
        /*被投保人名称*/
        var insuredPersonName=$('#search-insuredPersonName').val();
        /*申请人*/
        var proposer=$('#search-proposer').val();
        /*审批日期开始日期*/
        var approvalDateBegin=$('#search-approvalDateBegin').val();
        /*审批日期结束日期*/
        var approvalDateEnd=$('#search-approvalDateEnd').val();
        /*状态*/
        var orderStateDataItem={};
        orderStateDataItem.id=$('#search-state').val();
        data.orderStateDataItem=orderStateDataItem;
        /*申请部门*/
        var departmentDataItem={};
        departmentDataItem.id=$('#search-department').val();
        data.departmentDataItem=departmentDataItem;
        data.id=id;
        /*货物类别*/
        var goodsType=$('input[name="goodsType"]:checked').val();
        /*实际承运人*/
        var actualCarrier=$('input[name="actualCarrier"]:checked').val();
        /*是否粤港台货物*/
        var yHTGoods=$('input[name="yHTGoods"]:checked').val();
        /*附件险*/
        var fileInsurance=$('input[name="fileInsurance"]:checked').val();
        /*保单要求*/
        var insuranceOrderRequirement=$('input[name="insuranceOrderRequirement"]:checked').val();
        data.goodsType=goodsType;
        data.actualCarrier=actualCarrier;
        data.yHTGoods=yHTGoods;
        data.fileInsurance=fileInsurance;
        data.insuranceOrderRequirement=insuranceOrderRequirement;
        data.insuredPersonName=insuredPersonName;
        data.proposer=proposer;
        data.approvalDateBegin=approvalDateBegin;
        data.approvalDateEnd=approvalDateEnd;

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
    var state=$(item).parent().parent().children('td').eq(1).html();

     if(state=='已作废'){
        alert("该订单已作废,无法投保")
    }
    else  if(state=='关闭'){
        alert("该订单已关闭,无法接单")
    }
    else {
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
             url:"getGoodsValueById",
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


}

/*设置保单信息*/
function setOrderDetail(result) {

    if(result.data!=null) {
    var obj=eval(result.data);
    var insuranceOrderItem=$('#insuranceOrderItem');
        insuranceOrderItem.empty();
        $.each(obj,function (index,item) {
            var id;
            var insureCompanyName;
            var insureDate;
            var premium;
            var state;
            if(item.insuranceOrderItem!=null){
                    id=item.insuranceOrderItem.id;
                    insureCompanyName=item.insuranceOrderItem.insureCompanyName;
                 insureDate=getDateStr(item.insuranceOrderItem.insureDate);
                 premium= getNumber(item.insuranceOrderItem.premium,3);
                state=getDataFromDate(item.insuranceOrderItem.orderStateDataItem);
            }
            else {
                id='';
                insureCompanyName="";
                insureDate="";
                premium="";
                state="";
            }

            var tr="<tr class='myclass'><td class='text-center'>"+id+"</td><td class='text-center'>"+insureCompanyName+"</td><td class='text-center'>"
                +insureDate+"</td><td class='text-center'>"+premium+"</td><td class='text-center'>"+getDataFromDate(item.currencyDataItem)+"</td>"
                +"<td class='text-center'>"+state+"</td>"+"<td class='hidden'>"+id+"</td>"+"<td class='hidden'>"+item.id+"</td>"+"<td class='text-center'> <a title=\"增加/修改\" onclick=\"addOrEditModal(this)\"><span class=\"glyphicon glyphicon-plus\"\n" +
                "                                                                             aria-hidden=\"true\"></span></a>" +


                "                                <a class=\"delete\" title=\"删除\" onclick=\"deleteModel(this)\"><span\n" +
                "                                        class=\"glyphicon glyphicon glyphicon-remove\" aria-hidden=\"true\"></span></a>\n" +
                "                                <a  id=\"upload2\" title=\"上传电子保单\" onclick=\"showUploadInsurancePolicy(this)\" ><span\n" +
                "                                        class=\"glyphicon glyphicon-arrow-up\" aria-hidden=\"true\"></span></a>\n" +
                "                                <a href=\"#\" onclick=\"info2(this)\" title=\"异常\"><span class=\"glyphicon glyphicon-exclamation-sign\"\n" +
                "                                                                        aria-hidden=\"true\"></span></a></td>"
                "</tr>"

            insuranceOrderItem.append(tr)
        })


}

}

/*新增保单*/
function addOrEditModal(item) {

    var id=$('#insuranceOrderId').text();

    var tdArray=$(item).parent().parent().children('td');

    var input="<input class='form-control'/>"
    var dateInput="<input class='form-control' type='date'/>";
    var readOnly="<input class='form-control' readonly='readonly'/>";
    //有input
    if($(tdArray[0]).find('input').length>0){
        $.each(tdArray,function (index,item) {

            if(index<=3){
                var content=$(this).find("input").val();
                $(this).empty();
                $(this).html(content)

            }

        })
        var trArray=$('#insuranceOrderItem').find('tr');
        $('#adjust').hide();
        $(trArray).each(function (index,item) {
            console.log($(this).find('td').eq(0).children('input').length>0)
            if($(this).find('td').eq(0).find('input').length>0){
                $('#adjust').show();
            }
            // else {
            //     $('#adjust').hide();
            // }
        })
    }
    //没有
    else {
        $(tdArray).each(function (index) {
            if(index==0||index==1||index==3){
                var content=$(this).html();
                $(this).html($(input).val(content));
            }
            if(index==2){
                var contentDate=$(this).html();
                $(this).html($(dateInput).val(contentDate));
            }

        })
        $('#adjust').show();


    }




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
    $('.myclass').each(function () {
        if($(this).find('td').eq(0).children('input').length>0) {
            var InsuranceOrderItem = {
                newId: $(this).find('td').eq(0).children('input').val(),
                insureCompanyName: $(this).find('td').eq(1).children('input').val(),
                insureDate: $(this).find('td').eq(2).children('input').val(),
                premium: $(this).find('td').eq(3).children('input').val(),
                orderId: $('#insuranceOrderId').text(),
                id:$(this).find('td').eq(6).html(),
                goodsValueId:$(this).find('td').eq(7).html(),
            }
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

    })





}

/*保单删除*/
function deleteModel(item) {

    if(confirm("确定删除该保单?")){
        //点击确定后操作
        var id=$(item).parent().parent().children('td').eq(0).html();
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
                    PushOperationTracking($('#insuranceOrderId').text());
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
                PushOperationTracking($('#insuranceOrderId').text());
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
    $('#InsuranceOrderItemId').val($(item).parent().parent().children('td').eq(0).html())
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
              PushOperationTracking($('#insuranceOrderId').text());
              window.location.reload();
          },
          error:function (result) {
              alert("服务器异常!")
          }
      })
  }





}

/*接单*/
function Receipt(item) {




    if(confirm("确定接单?")){
        //点击确定后操作
        var id=$(item).parent().parent().children('td').eq(0).html();
        var state=$(item).parent().parent().children('td').eq(1).html();
        if(state=='已接单'){
            alert("该订单已接单")
        }
       else if(state=='已作废'){
            alert("该订单已作废,无法接单")
        }
        else  if(state=='关闭'){
            alert("该订单已关闭,无法接单")
        }
        else {
            $.ajax({
                type:"POST",
                url:"receiptById",
                async: false,
                data:{'id':id},
                dataType:"json",
                // contentType: "application/json; charset=utf-8",
                success:function (result) {
                    if (result != undefined && result.status == "success"){
                        alert(result.message);
                        PushOperationTracking(id);
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


    }

}

/*作废*/
function cancel(item) {
    if(confirm("确定作废该订单?")){
        var state=$(item).parent().parent().children('td').eq(1).html();
        if(state=='关闭'){
            alert('该订单已关闭，无法作废')
        }
        else {
            //点击确定后操作
            var id=$(item).parent().parent().children('td').eq(0).html();
            $.ajax({
                type:"POST",
                url:"cancelById",
                async: false,
                data:{'id':id},
                dataType:"json",
                // contentType: "application/json; charset=utf-8",
                success:function (result) {
                    if (result != undefined && result.status == "success"){
                        alert(result.message);
                        PushOperationTracking(id)
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

    }
}

/*关闭*/
function ShutDown(item) {
    if(confirm("确定关闭该订单?")){
        var state=$(item).parent().parent().children('td').eq(1).html();
        if(state=='已作废'){
            alert('改订单已作废，无法关闭')
        }
        else {
            //点击确定后操作
            var id=$(item).parent().parent().children('td').eq(0).html();
            $.ajax({
                type:"POST",
                url:"shutDownById",
                async: false,
                data:{'id':id},
                dataType:"json",
                // contentType: "application/json; charset=utf-8",
                success:function (result) {
                    if (result != undefined && result.status == "success"){
                        alert(result.message);
                        PushOperationTracking(id);
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

    }
}

/*获取数据字典做成下拉框*/
function getDataToSelect() {

    /*状态*/
    $.ajax({
        type:"POST",
        url:"getStateData",
        async: false,
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
             console.log(result)
                if(result.data!=null){
                 var state=$('#search-state');
                    state.empty();
                    for(var i in result.data){
                        if(result.data[i].id!=3&&result.data[i].id!=6&&result.data[i].id!=7&&result.data[i].id!=8&&result.data[i].id!=9){


                                           state.append("<option value="+result.data[i].id+">"+result.data[i].name+"</option>>")
                        }
                    }
                    state.get(0).selectedIndex=-1;
                    }
            }
            else {

            }
        },
        error:function (result) {
            alert("服务器异常!")
        }
    })

    /*部门*/
    $.ajax({
        type:"POST",
        url:"getDepartmentData",
        async: false,
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
                console.log(result)
                if(result.data!=null){
                    var department=$('#search-department');
                    department.empty();
                    for(var i in result.data){
                        department.append("<option value="+result.data[i].id+">"+result.data[i].name+"</option>>")
                    }
                    department.get(0).selectedIndex=-1;
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

/*显示异常框*/
function info2(item) {
    var id=$("#insuranceOrderId").text();//订单号
    var InsuranceOrderItemId=$(item).parent().parent().children('td').eq(0).html();//保单号
      $('#abnormalId').text(InsuranceOrderItemId)
    //根据保单号获取保单信息
    $.ajax({
        type:"POST",
        url:"getInsuranceOrderItemByItemId",
        async: false,
        data:{"id":InsuranceOrderItemId},
        dataType:"json",
        // contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
                        console.log(result);
                        $('#abnormal').val(result.data.abnormalInfo);
                     $('#abnormalInfo').val(result.data.abnormalPerson);
            }
            else {

            }
        },
        error:function (result) {
            alert("服务器异常!")
        }
    })

    $("#infoModal").modal('show');
   
    $.ajax({
        type:"POST",
        url:"getStateData",
        async: false,
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success:function (result) {

        },
        error:function (result) {
            
        }
    })
    
   
    
    
}

function getAbnormal() {

    if(confirm("确定生成异常单?")){
        //点击确定后操作
       var id=$('#abnormalId').text();
       var abnormalPerson= $('#abnormal').val();
       var abnormalInfo= $('#abnormalInfo').val();
        var data={};
        data.id=id;
        data.abnormalPerson=abnormalPerson;
        data.abnormalInfo=abnormalInfo;
        $.ajax({
            type:"POST",
            url:"getAbnormal",
            data:JSON.stringify(data),
            async: false,
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            success:function (result) {
                if (result != undefined && result.status == "success"){
                    alert(result.message)
                            PushOperationTracking($('#insuranceOrderId').text())
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

}

/*
* 附件下载模态框
* */
function downLoadModal(item) {

    var id=$(item).parent().parent().children('td').eq(0).html();

    $.ajax({
        type:"POST",
        url:"getInsuranceOrderById",
        async: false,
        data:{"id":id},
        dataType:"json",
        // contentType: "application/json; charset=utf-8",
        success:function (result) {
            if (result != undefined && result.status == "success"){
                         console.log(result)
                              $('#invoiceUrl').html(result.data.invoiceUrl);
                              $('#boxUrl').html(result.data.boxUrl)
                  $('.electronicPolicy').remove();



                   if(result.data.insuranceOrderItemList!=null){
                       $.each(result.data.insuranceOrderItemList,function (index,item) {
                                var tr= "<tr class='electronicPolicy'><td class='text-center'>"+item.insureCompanyName+"的电子保单"+"</td><td class='hidden'>"+item.fileUrl+"</td><td><a title='下载' onclick=\"downloadFile(this)\"><span class='glyphicon glyphicon-download-alt' aria-hidden='true' ></span></a></td></tr>"
                               $('#policy').append(tr)
                       })

                   }




            }
            else {

            }
        },
        error:function (result) {
            alert("服务器异常!")
        }
    });

   $('#downLoadModal').modal('show');



}

/*附件下载*/
function downloadFile(item) {
    var fileUrl=$(item).parent().prev().html();
    console.log(fileUrl);
    if(fileUrl.length>0){
        window.open("downloadFile?fileName="+fileUrl);
    }
    else {
        alert("未上传文件!")
    }

}

