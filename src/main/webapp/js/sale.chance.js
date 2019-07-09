function formatterState(val) {
    if (val == 0) {
        return "未分配";
    } else if (val == 1) {

        return "已分配";
    } else {
        return "未定义";
    }
}

function searchSaleChances() {
    $("#dg").datagrid("load", {
        createMan: $("#createMan").val(),
        customerName: $("#customerName").val(),
        createDate: $("#createDate").datebox("getValue"),
        state: $("#state").combobox("getValue")
    })
}

$(function () {
    searchSaleChances();
})

function openAddAccountDialog() {
    // $("#fm")[0].reset(); //第一种解决方法: 在openAddAccountDialog()方法中首先执行表单重置
    $("#dlg").dialog("open").dialog("setTitle", "添加营销机会信息");
}

function closeDialog() {
    $("#dlg").dialog("close");
}

function saveAccount() {
    var id = $("#id").val();
    var save_update_url = ctx + "/sale_chance/update";
    if (isEmpty(id)) {
        save_update_url = ctx + "/sale_chance/insert";
    }
    $("#fm").form("submit", {
        url: save_update_url,
        onSubmit: function (params) {
            params.createMan = $.cookie("trueName");
            return $("#fm").form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.code == 200) {
                $.messager.alert("crm", data.msg, "info");
                closeDialog();
                searchSaleChances();
            } else {
                alert(data.msg);
            }
        }
    });
}

function openModifyAccountDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("crm", "请先选中一条记录", "info");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("crm", "不能同时修改多条记录", "info");
        return;
    }
    $("#fm").form("load", rows[0]);
    $("#dlg").dialog("open").dialog("setTitle", "修改营销机会信息");
}

function deleteAccount() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("crm", "请先选中一条记录", "info");
        return;
    }
    var mydata = "ids=";
    for (var i = 0; i < rows.length; i++) {
        if (i < rows.length - 1) {
            mydata = mydata + rows[i].id + "&ids=";
        } else {
            mydata = mydata + rows[i].id
        }
    }
    $.messager.confirm("crm", "确定要删除所选中的数据么？", function (r) {
        if (r) {
            $.ajax({
                type: "post",
                url: ctx + "/sale_chance/delete",
                data: mydata,
                dataType: "json",
                success: function (data) {
                    $.messager.alert("crm", data.msg, "info");
                    if (data.code == 200) {
                        searchSaleChances();
                    }
                }
            })
        }
    })
}

/*$(document).ready(function(){});  简写方案 $(function () {})
只需要等待网页中的DOM结构加载完毕，就能执行JS代码,可以执行多次，第N次都不会被上一次覆盖*/

$(function () { //第二种解决方法: 使用dialog关闭事件,重置表单数据;
    $('#dlg').dialog({
        onClose: function () {
            $("#fm")[0].reset();
        }
    });
});

