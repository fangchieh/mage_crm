function searchCustomer() {
    $("#dg").datagrid("load", {
        khno: $("#s_khno").val(),
        customerName: $("#s_name").val()
    })
}

function openCustomerAddDialog() {
    $("#dlg").dialog("open");
}

function closeCustomerDialog() {
    initFormData();
    $("#dlg").dialog("close");
}

function saveOrUpdateCustomer() {
    var id = $("#id").val();
    var save_update_url = ctx + "/customer/insert";
    $("#fm").form("submit", {
        url: save_update_url,
        onSubmit: function () {
            return $("#fm").form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            $.messager.alert("crm", data.msg, "info");
            if (data.code == 200) {
                $("#dlg").dialog("close");
                initFormData();
                searchCustomer();
            }
        }
    })
}

function initFormData() {
    $("#id").val("");
    $("#name").val("");
    $("#area").combobox("clear");
    $("#cusManager").combobox("clear");
    $("#level").combobox("clear");
    $("#myd").combobox("clear");
    $("#xyd").combobox("clear");
    $("#postCode").val("");
    $("#phone").val("");
    $("#fax").val("");
    $("#webSite").val("");
    $("#address").val("");
    $("#yyzzzch").val("");
    $("#fr").val("");
    $("#zczj").val("");
    $("#nyye").val("");
    $("#khyh").val("");
    $("#khzh").val("");
    $("#dsdjh").val("");
    $("#gsdjh").val("");
}

function openCustomerModifyDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("crm", "请先选中一条记录", "info");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("crm", "不能同时修改多条记录", "info");
        return;
    }
    $("#fm").form("load", rows[0])
    $("#dlg").dialog("open").dialog("setTitle", "修改营销机会信息");
}

function saveOrUpdateCustomer() {
    var id = $("#id").val();
    var save_update_url = ctx + "/customer/update";
    if (isEmpty(id)) {
        save_update_url = ctx + "/customer/insert";
    }
    $("#fm").form("submit", {
        url: save_update_url,
        onSubmit: function () {
            return $("#fm").form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            $.messager.alert("crm", data.msg, "info");
            if (data.code == 200) {
                $("#dlg").dialog("close");
                initFormData();
                searchCustomer();
            }
        }
    })
}

function deleteCustomer() {
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
                url: ctx + "/customer/delete",
                data: mydata,
                dataType: "json",
                success: function (data) {
                    $.messager.alert("crm", data.msg, "info");
                    if (data.code == 200) {
                        searchCustomer();
                    }
                }
            })
        }
    })
}

$(function () { //第二种解决方法: 使用dialog关闭事件,重置表单数据;
    $('#dlg').dialog({
        onClose: function () {
            $("#fm")[0].reset();
        }
    });
});