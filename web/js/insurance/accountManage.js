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
 * 设置模态框下拉框数据
 */
function setSelectDataList(){
    $('.selectpicker').selectpicker({
        language: 'zh_CN',
        size: 4,
    });
    $.ajax({
        type: "POST",                       // 方法类型
        url: "getCompanyAndDepartmentAndTeamList",                  // url
        async: false,                      // 同步：意思是当有返回值以后才会进行后面的js程序
        dataType: "json",
        success: function (result) {
            if (result != undefined && result.status === "success") {
                var company =$("select[name='company']");
                company.children().remove();
                $.each(result.companyList, function (index, item) {
                    var option = $('<option/>');
                    option.val(item.id);
                    option.text(item.name);
                    company.append(option);
                });
                var department =$("select[name='department']");
                department.children().remove();
                $.each(result.departmentList, function (index, item) {
                    var option = $('<option/>');
                    option.val(item.id);
                    option.text(item.name);
                    department.append(option);
                });
                var team =$("select[name='team']");
                team.children().remove();
                $.each(result.teamList, function (index, item) {
                    var option = $('<option/>');
                    option.val(item.id);
                    option.text(item.name);
                    team.append(option);
                });
             //   $('.bootstrap-select').find("button:first").remove();
                $('.selectpicker').selectpicker('refresh');

            } else {
                console.log(result.message);
            }
        },
        error: function (result) {
            console.log(result.message);
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
                console.log(data);
                $("#userName").val(data.userName);
                $("#password").val(data.password);
                $("#name").val(data.name);
                if(data.companyDataItem != null)
                $("#company").selectpicker('val',data.companyDataItem.id);
                if(data.departmentDataItem != null)
                $("#department").selectpicker('val',data.departmentDataItem.id);
                if(data.teamDataItem != null)
                $("#team").selectpicker('val',data.teamDataItem.id);
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
