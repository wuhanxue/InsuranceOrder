
/**
 * 加行
 * @param item
 */
function addNewLine(item) {
    // 获取母元素
    var tr = $("#plus").prev();
    if(tr.find("a[name='delbtn']").length === 0) {  // 如果上一行没有减行按钮则新增
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
    if($("#plus").prevAll().length === 1){  // 如果只有一行则不允许删除
        $("a[name='delbtn']").remove();
    }
}