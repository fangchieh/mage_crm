function userLogin() {
    var userName = $("#userName").val();
    var userPwd = $("#userPwd").val();
    if (isEmpty(userName)) {
        alert("用户名不能为空！");
        return;
    }
    if (isEmpty(userPwd)) {
        alert("密码不能为空！");
        return;
    }
    var params = {};
    params.userName = userName;
    params.userPwd = userPwd;
    $.ajax({
        type: "post",
        url: ctx + "/user/userLogin",
        data: params,
        dataType: "json",
        success: function (data) {
            if (data.code == 200) {
                var result = data.result;
                $.cookie("userName", result.userName);
                $.cookie("trueName", result.trueName);
                $.cookie("userId", result.userId);
                window.location.href = "main";
            } else {
                alert(data.msg);
            }
        }
    })
}