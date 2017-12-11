document.onkeydown = function(e){
    var event = e || window.event;
    var code = event.keyCode || event.which || event.charCode;
    if (code == 13) {
        doLogin();
    }
};
function doLogin() {
    //登录表单
    var loginForm = $("#login_form");
    //消息提示栏
    var showMsg = $('#show_msg');
    //记住密码
    var rememberMe = $('#rememberMe');
    if (loginForm.form('validate')){
        $.ajax({
            type: 'POST',
            url: './doLogin.html',
            data: {
                username:$('#username').val(),
                password:$('#password').val(),
                rememberMe: rememberMe.switchbutton("options").checked
            } ,
            success: function(data){
                if(data.success){
                    location.href ='./index.html';
                }else {
                    showMsg.html(data.message);
                }
            },
            error : function(message) {
                showMsg.html('请联系系统管理员！');
            }
        });
    }else {
        showMsg.html('请检查用户名和密码！');
    }
};
function initWindow(){
    //登录弹出框
    var loginWin = $('#login_win');
    loginWin.window({
        title:'',
        width:300,
        height:308,
        modal:true,
        border:false,
        shadow:false
    });
};
function bindBtm() {
    //登录按钮
    var loginBtn = $('#login_btn');
    loginBtn.bind('click',function () {
        doLogin();
    });
};
$(function () {
    /**
     * 初始化弹出框
     * */
    initWindow();
    /**
     * 绑定按钮功能
     */
    bindBtm();
});