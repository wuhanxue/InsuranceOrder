var isSearch = false;   //初始化查询状态为false

/**
 * 初始加载函数
 */
function onLoadBasicDataDetailList(page, data) {
    //传给page.js方法名
    initFName(onLoadBasicDataDetailList.name);
    //将查询的状态传给page.js
    initisSearch(isSearch);
    /*页面的初始属性
    * 第一次加载时需要
    * */
    if (page == undefined) {
        page = getInitPage();
    }
    if (data == null || data === undefined) {
        data = {};
        data.dataDictionaryId = localStorage.dataDictionaryId; // 外键
        data.page = page;
    }
    // 赋值
    console.log(localStorage.dataDictionaryName+"-"+localStorage.dataDictionaryCode+"-"+localStorage.dataDictionaryId);
    $("#dictionaryName").val(localStorage.dataDictionaryName);
    $("#dictionaryCode").val(localStorage.dataDictionaryCode);
    $("#dictionaryId").text(localStorage.dataDictionaryId);
    //根据url获取总页数
    TotalPage('searchBasicDataDetailTotal', data);
    $.ajax({
        type: "POST",
        url: "searchBasicDataDetail",
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status == "success") {
                console.log(result);
                //删除子元素
                $('#tBody').find(".myClass").remove();
                setDataList(result);
            } else {
                alert(result.message);
            }
        },
        error: function (result) {
            alert(result.message);
        }
    });
}


/**
 * 查询功能
 */
function searchData() {
    initisSearch(true);
    var page = {};
    page.start = parseInt(countValue()) * (parseInt($('#currentPage').text()) - 1);
    page.count = countValue();
    /*高级查询*/
    var data;
    if ($("#senior").is(':visible')) {
        data = {};//代表查询实体
        var code = $.trim($('#search_code').val());   // 编码
        var name = $.trim($('#search_name').val());   // 名称
        data.dataDictionaryId = localStorage.dataDictionaryId;  // 外键
        data.code = code;
        data.name = name;
        data.page = page;
    } else {
        var keywords = $.trim($("#searchContent").val());
        data = {
            page: page,
            keywords: keywords
        }
    }
    console.log("查询条件");
    console.log(data);
    getData(data);   // 传递参数进加载函数
    //将要查询的元素穿个page页面
    /*查询的方法*/
    $.ajax({
        type: "POST",
        url: "searchBasicDataDetail",
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status == "success") {
                console.log(result);
                //删除子元素
                $('#tBody').find(".myClass").remove();
                setDataList(result);
            }else {
                alert(result.message);
            }
        },
        error: function (result) {
            alert(result.message);
        }
    });
    TotalPage("searchBasicDataDetailTotal", data);//算查询总数
}

/**
 * 回车查询
 */
function enterSearch() {
    if (event.keyCode === 13) {   // 如果按下键为回车键，即执行搜素
        searchData();      //
    }
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

/**
 * 设置行数据
 * @param result
 */
function setDataList(result) {
    $.each(result.data, function (index, item) {
        //复杂对象
        var tr = "<tr class=\"myClass\">\n" +
            "                    <td name=\"id\"><input class=\"form-control\" name=\"id\" value=\""+item.id+"\" required></td>\n" +
            "                    <td name=\"oldId\" hidden><input class=\"form-control\" name=\"oldId\" value=\""+item.id+"\"></td>\n" +
            "                    <td name=\"code\"><input class=\"form-control\" name=\"code\" value=\""+item.code+"\"></td>\n" +
            "                    <td name=\"name\"><input class=\"form-control\" name=\"name\" value=\""+item.name+"\"></td>\n" +
            "                    <td name=\"parentId\"><input class=\"form-control\" name=\"parentId\" value=\""+item.parentId+"\"></td>\n" +
            "                    <td class=\"text-center\"><a class='btn btn-default btn-xs' name='delbtn' onclick='delLine(this);'>\n" +
            "                        <span class='glyphicon glyphicon-minus' aria-hidden='true'></span></a></td>\n" +
            "                </tr>";
        $('#plus').before(tr);
    });
}

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
    clonedTr.attr("class","newLine");
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
    data.id = parseInt($.trim($("#dictionaryId").text()));
    data.name = $.trim($("#dictionaryName").val());
    data.code = $.trim($("#dictionaryCode").val());
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
        var itemId = $.trim($(this).children().find("input[name='id']").val());
        if(itemId === "") { // 判断ID是否为空
            alert("明细编号不能为空！");
        }else { // 需要修改的数据
            dataDictionaryItem.id = itemId;
        }
        if($(this).attr("class") === "newLine") { // 新增数据设置creator为-1
            dataDictionaryItem.creator = "-1";
        }else { // 旧数据设置旧ID
            dataDictionaryItem.oldId = $.trim($(this).children().find("input[name='oldId']").val());
        }
        dataDictionaryItem.dataDictionaryId = data.id;  // 外键
        dataDictionaryItem.name = $.trim($(this).children().find("input[name='name']").val());
        dataDictionaryItem.code = $.trim($(this).children().find("input[name='code']").val());
        dataDictionaryItem.parentId = $.trim($(this).children().find("input[name='parentId']").val());
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