var isSearch = false;   //初始化查询状态为false

/**
 * 初始加载函数
 */
function onLoadAccountList(page, data) {
    //传给page.js方法名
    initFName(onLoadAccountList.name);
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
    TotalPage('searchAccountDataTotal', data);
    $.ajax({
        type: "POST",
        url: "searchAccountData",
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
        var userName = $.trim($('#search_userName').val());   // 编码
        var name = $.trim($('#search_name').val());   // 名称
        data.userName = userName;
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
        url: "searchAccountData",
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
    TotalPage("searchAccountDataTotal", data);//算查询总数
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
        var company = "";
        var department = "";
        var team = "";
        if(item.companyDataItem != null) {
            company = item.companyDataItem.name;
        }
        if(item.departmentDataItem != null) {
            department = item.departmentDataItem.name;
        }
        if(item.teamDataItem != null) {
            team = item.teamDataItem.name;
        }
        var tr = "<tr class='myClass'>\n" +
            "                        <td class=\"text-center\">\n" +
            "                            <label>\n" +
            "                                <input class=\"checkbox\" type=\"checkbox\" value=\"option1\" aria-label=\"...\"\n" +
            "                                       name=\"checkbox1\">\n" +
            "                            </label>\n" +
            "                        </td>\n" +
            "                        <td class=\"text-center\" name=\"id\">"+item.id+"</td>\n" +
            "                        <td class=\"text-center\" name=\"userName\">"+item.userName+"</td>\n" +
            "                        <td class=\"text-center\" name=\"name\">"+item.name+"</td>\n" +
            "                        <td class=\"text-center\">"+company+"</td>\n" +
            "                        <td class=\"text-center\">"+department+"</td>\n" +
            "                        <td class=\"text-center\">"+team+"</td>\n" +
            "                        <td class=\"text-center\">"+getDateStr(item.creationTime)+"</td>\n" +
            "                        <td class=\"text-center\">"+item.creator+"</td>\n" +
            "                        <td class=\"text-center\">\n" +
            "                            <button onclick=\"showEditModal(this);\" type=\"button\" href=\"\" title=\"修改\"><span\n" +
            "                                    style=\"color: #2e6da4\"\n" +
            "                                    class=\"glyphicon glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span></button>\n" +
            "                            <button onclick=\"deleteById(this);\"\n" +
            "                                    class=\"delete\" title=\"删除\"><span style=\"color: #2e6da4\"\n" +
            "                                                                    class=\"glyphicon glyphicon glyphicon-remove\"\n" +
            "                                                                    aria-hidden=\"true\"></span></button>\n" +
            "                        </td>\n" +
            "                    </tr>";
        $('#tBody').append(tr);
    });
}

var userId;
var isE = false;   // 账号是否存在

/**
 * 打开新增模态框
 */
function showAddModal() {
    $("#addModal").modal('show');
}

/**
 * 新增账号
 */
function addUser() {
    if (!checkPassWord($("#add_password").val())) {// 检验密码格式
        alert("密码格式为英文大小写、数字、符号至少三种组合，长度在8至30之间！");
        $("input[name='password']").focus();  // 鼠标焦点定位到密码输入框
        return false;
    }
    // 获取数据
    var data = {};
    data.userName = $("#add_userName").val();
    if (data.userName === "") {
        alert("账号不能为空！");
        return false;
    }
    checkUserName($("#add_userName").val());   // 检验该账号是否存在
    if (isE) {
        alert("该账号已存在，请重新输入！");
        $("input[name='userName']").focus();  // 鼠标焦点定位到账号输入框
        return false;
    }
    data.password = $("#add_password").val();
    data.name = $("#add_name").val();
    if (data.name === "") {
        alert("姓名不能为空！");
        return false;
    }
    var companyId = $("#add_company").find("option:selected").val();
    if(companyId !== undefined) {
        var companyDataItem = {};
        companyDataItem.id = companyId;
        data.companyDataItem = companyDataItem;
    }
    var departmentId = $("#add_department").find("option:selected").val();
    if(departmentId !== undefined) {
        var departmentDataItem = {};
        departmentDataItem.id = departmentId;
        data.departmentDataItem = departmentDataItem;
    }
    var teamId = $("#add_team").find("option:selected").val();
    if(teamId !== undefined) {
        var teamDataItem = {};
        teamDataItem.id = teamId;
        data.teamDataItem = teamDataItem;
    }
    console.log(data);
    $.ajax({
        type: "POST",                       // 方法类型
        url: "addUser",                  // url
        async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status === "success") {
                alert("新增成功!");
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

/**
 * 检验账号是否存在
 * @param name
 */
function checkUserName(name) {
    $.ajax({
        type: "POST",                       // 方法类型
        url: "checkUserNameIsExist",                  // url
        async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
        data: {
            userName: name
        },
        dataType: "json",
        success: function (result) {
            if (result != undefined && result.status === "success") {
                isE = result.data;
                return result.data;
            } else {
                alert(result.message);
                return false;
            }
        },
        error: function (result) {
            alert(result.message);
            return false;
        }
    });
}

/**
 * 显示模态框修改
 * @param e
 */
function showEditModal(e) {
    var id = parseInt($(e).parent().parent().children().eq(1).text());  //获取ID
    userId = id;
    console.log("id=" + id);
    $.ajax({
        type: "POST",                       // 方法类型
        url: "getUserById",                  // url
        async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
        data: {
            id: id
        },
        dataType: "json",
        // contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status === "success") {
                // 赋值
                var data = eval(result.data);
                console.log(data);
                $("#userName").val(data.userName);
                $("#password").val(data.password);
                $("#name").val(data.name);
                if (data.companyDataItem != null){
                    $("#company").val(data.companyDataItem.id);
                }
                if (data.departmentDataItem != null){
                    setDepartmentSelectData($("#company"));  // 设置部门下拉框数据
                    $("#department").val(data.departmentDataItem.id);
                }
                if (data.teamDataItem != null){
                    setTeamSelectData($("#department"));   // 设置项目组下拉框数据
                    $("#team").val(data.teamDataItem.id);
                }
                $("#editModal").modal('show');
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
 * 保存修改的账号信息
 * @param e
 */
function updateUser() {
    if (!checkPassWord($("#password").val())) {// 检验密码格式
        alert("密码格式为英文大小写、数字、符号至少三种组合，长度在8至30之间！");
        $("input[name='password']").focus();  // 鼠标焦点定位到密码输入框
        return false;
    }
    // 获取最新数据
    var data = {};
    data.id = userId;
    data.userName = $("#userName").val();
    data.password = $("#password").val();
    data.name = $("#name").val();
    if (data.name === "") {
        alert("姓名不能为空！");
        return false;
    }
    var companyId = $("#company").find("option:selected").val();
    if(companyId !== undefined) {
        var companyDataItem = {};
        companyDataItem.id = companyId;
        data.companyDataItem = companyDataItem;
    }
    var departmentId = $("#department").find("option:selected").val();
    if(departmentId !== undefined) {
        var departmentDataItem = {};
        departmentDataItem.id = departmentId;
        data.departmentDataItem = departmentDataItem;
    }
    var teamId = $("#team").find("option:selected").val();
    if(teamId !== undefined) {
        var teamDataItem = {};
        teamDataItem.id = teamId;
        data.teamDataItem = teamDataItem;
    }
    console.log(data);
    $.ajax({
        type: "POST",                       // 方法类型
        url: "updateUserById",                  // url
        async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result !== undefined && result.status === "success") {
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

/**
 * 删除功能
 */
function deleteById(){
    if(confirm('确认删除？')){
        var id = parseInt($(e).parent().parent().children().eq(1).text());  //获取ID
        console.log("id=" + id);
        $.ajax({
            type: "POST",                       // 方法类型
            url: "deleteUserById",                  // url
            async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
            data: {
                id: id
            },
            dataType: "json",
            // contentType: "application/json; charset=utf-8",
            success: function (result) {
                if (result != undefined && result.status === "success") {
                    // 赋值
                    alert("删除成功！");
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
}