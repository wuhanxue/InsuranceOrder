var isSearch = false;   //初始化查询状态为false

/**
 * 初始加载函数
 */
function onLoadBasicDataList(page, data) {
    //传给page.js方法名
    initFName(onLoadBasicDataList.name);
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
        data.page = page;
    }
    //根据url获取总页数
    TotalPage('searchBasicDataTotal', data);
    $.ajax({
        type: "POST",
        url: "searchBasicData",
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status == "success") {
                console.log(result);
                //删除子元素
                $('#tBody').empty();
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
        var creator = $.trim($('#search_creator').val());  // 创建人
        data.code = code;
        data.name = name;
        data.creator = creator;
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
        url: "searchBasicData",
        async: false,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status == "success") {
                console.log(result);
                //删除子元素
                $('#tBody').empty();
                setDataList(result);
            }else {
                alert(result.message);
            }
        },
        error: function (result) {
            alert(result.message);
        }
    });
    TotalPage("searchBasicDataTotal", data);//算查询总数
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
        var tr = "<tr>\n" +
            "                        <td class=\"text-center\">\n" +
            "                            <label>\n" +
            "                                <input name=\"select\" class=\"checkbox\" type=\"checkbox\" id=\"blankCheckbox\" value=\"option1\"\n" +
            "                                       aria-label=\"...\">\n" +
            "                            </label>\n" +
            "                        </td>\n" +
            "                        <td class=\"text-center\" name=\"id\">"+item.id+"</td>\n" +
            "                        <td class=\"text-center\" name=\"code\">"+item.code+"</td>\n" +
            "                        <td class=\"text-center\" name=\"name\">"+item.name+"</td>\n" +
            "                        <td class=\"text-center\" name=\"creator\">"+item.creator+"</td>\n" +
            "                        <td class=\"text-center\" name=\"creationTime\">"+getDateStr(item.creationTime)+"</td>\n" +
            "                        <td class=\"text-center\">\n" +
            "                            <a onclick=\"jumpToDetail(this)\" title=\"编辑\" ><span\n" +
            "                                    class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></a>\n" +
            "                        </td>\n" +
            "                    </tr>";
        $('#tBody').append(tr);
    });
}

/**
 * 跳转至编辑页面
 * @param e
 */
function jumpToDetail(e) {
    localStorage.dataDictionaryId = $(e).parent().prevAll().find("td[name='id']").text();  // 设置外键
    localStorage.dataDictionaryCode = $(e).parent().prevAll().find("td[name='code']").text();  // 设置外键
    localStorage.dataDictionaryName = $(e).parent().prevAll().find("td[name='name']").text();  // 设置外键
    window.location.href = "/basicDataDetail";
}