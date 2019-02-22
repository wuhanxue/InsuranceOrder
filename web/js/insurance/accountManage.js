/**
 * 显示模态框修改
 * @param e
 */
function showEditModal(e){
    var id = parseInt($(e).parent().parent().children().eq(1).text());
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
                console.log("执行完1");
            } else {
               alert(result.message);
            }
        },
        error: function (result) {
            alert(result.message);
        }
    });
    console.log("执行完2");
}
/**
 * 保存修改的账号信息
 * @param e
 */
function updateUser() {

}

/**
 * 删除账号
 * @param e
 */
function deleteUser(e) {

}