var userId;

/**
 * 打开新增模态框
 */
function showAddModal(){
    $("#addModal").modal('show');
}

/**
 * 新增账号
 */
function addUser(){
    var data = {};
    data.userName = $("#add_userName").val();
    data.password = $("#add_password").val();
    data.name = $("#add_name").val();
    var companyDataItem = {};
    companyDataItem.id = $("#add_company").find("option:selected").val();
    data.companyDataItem = companyDataItem;
    var departmentDataItem = {};
    departmentDataItem.id = $("#add_department").find("option:selected").val();
    data.departmentDataItem = departmentDataItem;
    var teamDataItem = {};
    teamDataItem.id = $("#add_team").find("option:selected").val();
    data.teamDataItem = teamDataItem;
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
 * 显示模态框修改
 * @param e
 */
function showEditModal(e){
    var id = parseInt($(e).parent().parent().children().eq(1).text());  //获取ID
    userId = id;
    console.log("id="+id);
    $.ajax({
        type: "POST",                       // 方法类型
        url: "getUserById",                  // url
        async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
        data: {
           id : id
        },
        dataType: "json",
       // contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != undefined && result.status === "success") {
                // 赋值
                var data = eval(result.data);
                $("#userName").val(data.userName);
                $("#password").val(data.password);
                $("#name").val(data.name);
                if(data.companyDataItem != null)
                $("#company").val(data.companyDataItem.id);
                if(data.departmentDataItem != null)
                $("#department").val(data.departmentDataItem.id);
                if(data.teamDataItem != null)
                $("#team").val(data.teamDataItem.id);
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
    // 获取最新数据
    var data = {};
    data.id = userId;
    data.userName = $("#userName").val();
    data.password = $("#password").val();
    data.name = $("#name").val();
    var companyDataItem = {};
    companyDataItem.id = $("#company").find("option:selected").val();
    data.companyDataItem = companyDataItem;
    var departmentDataItem = {};
    departmentDataItem.id = $("#department").find("option:selected").val();
    data.departmentDataItem = departmentDataItem;
    var teamDataItem = {};
    teamDataItem.id = $("#team").find("option:selected").val();
    data.teamDataItem = teamDataItem;
    $.ajax({
        type: "POST",                       // 方法类型
        url: "updateUserById",                  // url
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
