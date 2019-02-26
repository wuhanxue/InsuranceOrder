var delIdList = [];   // 要删除的详细信息的Id集合

/**
 * 加行
 * @param item
 */
function addNewLine(item) {
    // 获取母元素
    var tr = $("#plus").prev();
    if (tr.find("a[name='delbtn']").length === 0) {  // 如果上一行没有减行按钮则新增
        var delBtn = "<a class='btn btn-default btn-xs' name='delbtn' onclick='delLine(this);'><span class='glyphicon glyphicon-minus' aria-hidden='true'\"></span></a>&nbsp;";
        tr.children("td:eq(3)").prepend(delBtn);   // 添加减行按钮
    }
    // 克隆tr，每次遍历都可以产生新的tr
    var clonedTr = tr.clone();
    // 克隆后清空新克隆出的行数据
    clonedTr.children().find("input").val("");
    clonedTr.insertAfter(tr);
}

/**
 * 删除行
 * @param e
 */
function delLine(e) {
    var tr = e.parentElement.parentElement;
    tr.parentNode.removeChild(tr);
    if ($("#plus").prevAll().length === 1) {  // 如果只有一行则不允许删除
        $("a[name='delbtn']").remove();
    }
    var itemId = $(e).parent().prevAll().find("input[name='id']").val();
    if(itemId !== ""){  // 如果ID非空：原始行，非新增的行
        delIdList.push(itemId);   // 将需要删除的数据的ID插入到集合中
    }
}

/**
 * 修改信息功能
 */
function modifyDetail() {
    var data = {};
    // 获取最新数据
    data.id = parseInt($("#dictionaryId").text());
    data.name = $("#dictionaryName").val();
    data.type = $("#dictionaryType").val();
    var dataDictionaryItemList = [];  // 详细数据集合
    for(var index in delIdList) {   // 将要删除的数据存储到集合中
        var dataDictionaryItem = {};
        dataDictionaryItem.id = delIdList[index];
        dataDictionaryItem.name = "_needDeleteDataDictionaryItem_";
        dataDictionaryItemList.push(dataDictionaryItem);
    }
    $($("#plus").prevAll()).each(function () {  // 遍历所有数据行
        var dataDictionaryItem = {};
        // 获取详细条目数据
        var itemId = $(this).children().find("input[name='id']").val();
        if(itemId === "") { // 新增数据设置ID为-1
            dataDictionaryItem.id = "-1";
        }else { // 需要修改的数据
            dataDictionaryItem.id = itemId;
        }
        dataDictionaryItem.dataDictionaryId = data.id;  // 外键
        dataDictionaryItem.name = $(this).children().find("input[name='name']").val();
        dataDictionaryItem.type = $(this).children().find("input[name='type']").val();
        dataDictionaryItemList.push(dataDictionaryItem);
    });
    data.dataDictionaryItemList = dataDictionaryItemList;
    console.log("修改的数据");
    console.log(data);
    $.ajax({
        type: "POST",                       // 方法类型
        url: "/modifyDictionaryData",                  // url
        async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status === "success") {
                alert("修改成功!");
                window.location.reload();
            } else {
                alert(result.message);
            }
        },
        error: function (result) {
            alert(result.message);
        }
    });
}