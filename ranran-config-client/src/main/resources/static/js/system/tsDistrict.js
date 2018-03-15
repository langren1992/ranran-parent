/**
* **********************************
* 定义表格列
* **********************************
*/
var dg_tsDistrict = '#dg_tsDistrict',
    toolbar_dg_tsDistrict = '#toolbar_dg_tsDistrict',
    form_toolbar_dg_tsDistrict = '#form_toolbar_dg_tsDistrict';

/**
* **********************************
* 定义弹出框
* **********************************
*/
var window_tsDistrict = '#window_tsDistrict',
    parent_window_tsDistrict = parent.$("<div></div>").appendTo("body"),
    window_form_tsDistrict = '#window_form_tsDistrict',
    text_tscKey = '#text_tscKey',
    window_import_tsDistrict = '#window_import_tsDistrict',
    parent_window_import_tsDistrict = parent.$("<div></div>").appendTo("body"),
    window_form_import_tsDistrict = '#window_form_import_tsDistrict',
    file = '#file';

/**
* **********************************
* 定义所有访问路径
* **********************************
*/
var url_dg_tsDistrict = './tsDistrict/selectTsDistrict.html',
    url_window_form_tsDistrict = './tsDistrict/updateTsDistricts.html',
    url_delete_tsDistrict = './tsDistrict/deleteTsDistricts.html',
    url_import_tsDistrict = './tsDistrict/importTsDistricts.html',
    url_export_tsDistrict = './tsDistrict/exportTsDistricts.html',
    url_download_tsDistrict = './tsDistrict/downloadTsDistrict.html',
    url_sync_map_tsDistrict = './tsDistrict/syncMapTsDistrict.html';

/**
* **********************************
* 定义所有按钮
* **********************************
*/
var btn_add_tsDistrict = '#btn_add_tsDistrict',
    btn_delete_tsDistrict = '#btn_delete_tsDistrict',
    btn_search_tsDistrict = '#btn_search_tsDistrict',
    btn_reset_tsDistrict = '#btn_reset_tsDistrict',
    btn_import_tsDistrict = '#btn_import_tsDistrict',
    btn_export_tsDistrict = '#btn_export_tsDistrict',
    btn_sync_map_tsDistrict = '#btn_sync_map_tsDistrict';

function initComponent(){
    /**
    * 列表
    */
    $(dg_tsDistrict).datagrid({
        fit:true,
        rownumbers: true,
        idField: 'distId',
        singleSelect:true,
        pagination:true,
        toolbar:toolbar_dg_tsDistrict,
        pageList:[50,100,250,500],
        pageSize:50,
        columns: [[
            { field:'ck',checkbox:true },
            { field: 'distId',title: '区域id', width: 120, sortable: true ,hidden:true},
            { field: 'distCode',title: '区域编码', width: 120, sortable: true },
            { field: 'distName', title: '区域名称', width: 120, sortable: true,editor:'textbox' },
            { field: 'distParentCode', title: '父级编码', width: 120, sortable: true,editor:'textbox' },
            { field: 'distParentName', title: '父级名称', width: 120, sortable: true,editor:'textbox' },
            { field: 'distLonlat', title: '区域经纬', width: 120, sortable: true,editor:'textbox' },
            { field: 'distLevel', title: '区域等级', width: 160, sortable: true,editor:'textbox' }
        ]],
        queryParams:{
            tscKey: '',
            tscValue: ''
        },
        loader: function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : url_dg_tsDistrict,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result);
                }
            });
        },
        onDblClickRow:function (i,row) {
            //设置标题
            parent_window_tsDistrict.dialog('setTitle','修改控制参数');
            //打开弹出框
            parent_window_tsDistrict.dialog('open');
            //填写数据
            parent.$(window_form_tsDistrict).form('load',row);
            if(row.tscId != null){
                parent.$(text_tscKey).validatebox('readonly');
            }
        }
    });
    
    /**
    * 弹出框，初始化先关闭
    */
    parent_window_tsDistrict.dialog({
        width: 600,
        height: 'auto',
        draggable:false,
        modal: true,
        content:$(window_tsDistrict),
        center:true,
        closed:true,
        closable:false,
        buttons:[{
            text:'保存',
            iconCls:'icon-save',
            handler:function(){
                /**
                * 如果将form表单放在父页面进行填写，所有操作需要在父页面进行
                * @type {string}
                */
                if(parent.$(window_form_tsDistrict).form('enableValidation').form('validate')){
                    var systemControl = parent.$(window_form_tsDistrict).getFormData();
                    $.ajax({
                        type: "POST",
                        url: url_window_form_tsDistrict,
                        data:'['+JSON.stringify(systemControl)+']',
                        contentType:"application/json",
                        dataType: "json",
                        success: function(data){
                            if (data.error){
                                parent.$.messager.alert("异常信息",data.message);
                            }
                            if(data.success){
                               parent.$.messager.alert("提示信息",data.message);
                            }
                            reloadComponent();
                        }
                    });
                }else {
                    parent.$.messager.alert("提示信息",'请检查提交数据！');
                }
            }
        },{
            text:'关闭',
            iconCls:'icon-no',
            handler:function(){
                reloadComponent();
            }
        }]
    });
    
    /**
    * 导入数据弹出框
    */
    parent_window_import_tsDistrict.dialog({
        width: 600,
        height: 'auto',
        draggable:false,
        modal: true,
        content:$(window_import_tsDistrict),
        center:true,
        closed:true,
        closable:false,
        buttons:[{
            text:'下载模板',
            iconCls:'icon-save',
            handler:function(){
                var form = parent.$(window_form_import_tsDistrict);
                form.attr("action",url_download_tsDistrict);
                form.submit();
            }
        },{
            text:'上传',
            iconCls:'icon-undo',
            handler:function(){
                if(parent.$(file).val()!=''){
                    var form = parent.$(window_form_import_tsDistrict);
                    form.attr("action",url_import_tsDistrict);
                    form.ajaxSubmit({
                        type:'post',
                        success:function(data){
                            if (data.error){
                                parent.$.messager.alert("提示信息",data.message);
                            }
                            if(data.success){
                                parent.$.messager.alert("提示信息",data.message);
                                reloadComponent();
                            }
                        }
                    });
                }else{
                     parent.$.messager.alert("提示信息",'请选择上传文件！');
                }
            }
        },{
            text:'关闭',
            iconCls:'icon-no',
            handler:function(){
                reloadComponent();
            }
        }]
    });
};

/**
* 新增控制参数
*/
function btnAddTsDistrict() {
    //设置标题
    parent_window_tsDistrict.dialog('setTitle','新增控制参数');
    //打开弹出框
    parent_window_tsDistrict.dialog('open');
};

/**
* 刷新数据列表
*/
function reloadComponent() {
    //清空表单
    parent.$(window_form_tsDistrict).form('clear');
    //关闭弹出框
    parent_window_tsDistrict.dialog('close');
    //重新加载列表
    $(dg_tsDistrict).datagrid('reload');
    //取消只读
    parent.$(text_tscKey).validatebox('readonly',false);
    //清空表单
    parent.$(window_form_import_tsDistrict)[0].reset();
    //关闭弹出框
    parent_window_import_tsDistrict.dialog('close');
};

/**
* 删除
*/
function btnDeleteTsDistrict() {
    //获取选择的数据
    vartsDistrict = $(dg_tsDistrict).datagrid('getSelected');
    $.ajax({
        type: "POST",
        url: url_delete_tsDistrict,
        data:'['+JSON.stringify(tsDistrict)+']',
        contentType:"application/json",
        dataType: "json",
        success: function(data){
            if (data.error){
                parent.$.messager.alert("提示信息",data.message);
            }
            if(data.success){
                parent.$.messager.alert("提示信息",data.message);
            }
            reloadComponent();
        }
    });
};

/**
* 查询控制参数
*/
function btnSearchTsDistrict() {
    var searchSystemControl = $(form_toolbar_dg_tsDistrict).getFormData();
    $(dg_tsDistrict).datagrid({queryParams:searchSystemControl});
};

/**
* 重置查询条件
*/
function btnResetTsDistrict() {
    //重置查询条件
    $(form_toolbar_dg_tsDistrict).form('clear');
};

/**
* 导入数据
*/
function btnImportTsDistrict() {
    //添加标题
    parent_window_import_tsDistrict.dialog('setTitle','导入');
    //显示弹出框
    parent_window_import_tsDistrict.dialog('open');
};

/**
* 导出数据
*/
function btnExportTsDistrict() {
    //导出数据需要使用form，不能用ajax
    $.messager.progress();
    $(form_toolbar_dg_tsDistrict).form('submit',{url:url_export_tsDistrict});
    $.messager.progress('close');	// 如果提交成功则隐藏进度条
};

/**
 * 通过地图应用同步省市区县数据
 */
function btnSyncMapTsDistrict() {
    $.ajax({
        type: "POST",
        url: url_sync_map_tsDistrict,
        contentType:"application/json",
        dataType: "json",
        success: function(data){
            if (data.error){
                parent.$.messager.alert("提示信息",data.message);
            }
            if(data.success){
                parent.$.messager.alert("提示信息",data.message);
            }
            reloadComponent();
        }
    });
};

/**
 * 初始化按钮功能
 */
function initBtnBindFun() {
    //新增控制参数
    $(btn_add_tsDistrict).bind('click', function () {
        btnAddTsDistrict();
    });
    //删除
    $(btn_delete_tsDistrict).bind('click', function () {
        btnDeleteTsDistrict();
    });
    //查询控制参数
    $(btn_search_tsDistrict).bind('click',function () {
        btnSearchTsDistrict();
    });
    //重置查询条件
    $(btn_reset_tsDistrict).bind('click',function () {
        btnResetTsDistrict();
    });
    //导入数据
    $(btn_import_tsDistrict).bind('click',function () {
        btnImportTsDistrict();
    });
    //导出数据
    $(btn_export_tsDistrict).bind('click',function () {
        btnExportTsDistrict();
    });
    //同步第三方数据
    $(btn_sync_map_tsDistrict).bind('click',function () {
        btnSyncMapTsDistrict();
    });
};

$(function(){
    //初始化控件
    initComponent();
    //按钮绑定功能
    initBtnBindFun();
});