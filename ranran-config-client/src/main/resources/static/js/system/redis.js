
/**
 * **********************************
 * 定义表格列
 * **********************************
 */
var dg_redis = '#dg_redis',
    dg_toolbar_redis = '#dg_toolbar_redis',
    dg_toolbar_form_redis = '#dg_toolbar_form_redis',
    dg_toolbar_form_key = '#dg_toolbar_form_key';
/**
 * **********************************
 * 定义所有访问路径
 * **********************************
 */
var url_dg_redis = './redis/selectRedis.html',
    url_btn_delete_redis = './redis/deleteRedis.html';
/**
 * **********************************
 * 定义所有按钮
 * **********************************
 */
var btn_search_redis = '#btn_search_redis',
    btn_reset_redis = '#btn_reset_redis',
    btn_delete_redis = '#btn_delete_redis';

/**
 * 初始化组件
 */
function initComponent(){
    $(dg_redis).datagrid({
        fit:true,
        idField: 'key',
        rownumbers: true,
        toolbar:dg_toolbar_redis,
        singleSelect:true,
        frozenColumns: [[
            { field: 'key', title: '键', sortable: true, width:500}
        ]],
        columns: [[
            { field: 'value', title: '值',width:500}
        ]],
        queryParams:{
            key:''
        },
        loader : function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : url_dg_redis,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    if (result.error){
                        parent.$.messager.alert("系统异常",result.message,"error");
                    }
                    success(result);
                }
            });
        }
    });
};

/**
 * 查询
 */
function btnSearchRedis(){
    $(dg_redis).datagrid({
        queryParams:{
            key:$(dg_toolbar_form_key).textbox('getText')
        }
    });
};

/**
 * 重置
 */
function btnResetRedis(){

};

/**
 * 删除缓存
 */
function btnDeleteRedis() {
    var redis = $(dg_redis).datagrid('getSelected');
    $.ajax({
        type : 'POST',
        url : url_btn_delete_redis,
        dataType : 'json',
        contentType : 'application/json;charset=utf-8', // 设置请求头信息
        data : JSON.stringify(redis),
        success : function(result) {
            if (result.error){
                parent.$.messager.alert("系统异常",result.message,"error");
            }
            if(result.success){
                parent.$.messager.alert("提示信息",result.message,"info");
            }
            optRefresh();
        }
    });
};

/**
 * 操作后刷新
 */
function optRefresh() {
    $(dg_redis).datagrid('reload');
}

/**
 * 按钮绑定功能
 */
function btnBindFun(){
    $(btn_search_redis).bind('click', function(){
        btnSearchRedis();
    });
    $(btn_reset_redis).bind('click', function(){
        btnResetRedis();
    });
    $(btn_delete_redis).bind('click', function(){
        btnDeleteRedis();
    });
};

$(function(){
    //初始化控件
    initComponent();
    //按钮绑定功能
    btnBindFun();
});