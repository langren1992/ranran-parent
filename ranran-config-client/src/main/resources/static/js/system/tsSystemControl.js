/**
 * **********************************
 * 定义表格列
 * **********************************
 */
var dg_tsSystemControl = '#dg_tsSystemControl',
    toolbar_dg_tsSystemControl = '#toolbar_dg_tsSystemControl',
    form_toolbar_dg_tsSystemControl = '#form_toolbar_dg_tsSystemControl';

/**
 * **********************************
 * 定义弹出框
 * **********************************
 */
var window_tsSystemControl = '#window_tsSystemControl',
    parent_window_tsSystemControl = parent.$("<div></div>").appendTo("body"),
    window_form_tsSystemControl = '#window_form_tsSystemControl',
    text_tscKey = '#text_tscKey',
    window_import_tsSystemControl = '#window_import_tsSystemControl',
    parent_window_import_tsSystemControl = parent.$("<div></div>").appendTo("body"),
    window_form_import_tsSystemControl = '#window_form_import_tsSystemControl',
    file = '#file';

/**
 * **********************************
 * 定义所有访问路径
 * **********************************
 */
var url_dg_tsSystemControl = './tsSystemControl/selectTsSystemControl.html',
    url_window_form_tsSystemControl = './tsSystemControl/updateTsSystemControls.html',
    url_delete_tsSystemControl = './tsSystemControl/deleteTsSystemControls.html',
    url_import_tsSystemControl = './tsSystemControl/importTsSystemControls.html',
    url_export_tsSystemControl = './tsSystemControl/exportTsSystemControls.html',
    url_download_tsSystemControl = './tsSystemControl/downloadTsSystemControl.html';

/**
 * **********************************
 * 定义所有按钮
 * **********************************
 */
var btn_add_tsSystemControl = '#btn_add_tsSystemControl',
    btn_delete_tsSystemControl = '#btn_delete_tsSystemControl',
    btn_search_tsSystemControl = '#btn_search_tsSystemControl',
    btn_reset_tsSystemControl = '#btn_reset_tsSystemControl',
    btn_import_tsSystemControl = '#btn_import_tsSystemControl',
    btn_export_tsSystemControl = '#btn_export_tsSystemControl';


function initComponent(){
    /**
     * 系统控制参数列表
     */
    $(dg_tsSystemControl).datagrid({
        fit:true,
        rownumbers: true,
        idField: 'tscId',
        singleSelect:true,
        pagination:true,
        toolbar:toolbar_dg_tsSystemControl,
        columns: [[
            { field:'ck',checkbox:true },
            { field: 'tscId', width: 120, sortable: true,hidden:true },
            { field: 'tscKey', title: '控制键', width: 120, sortable: true,editor:'textbox' },
            { field: 'tscValue', title: '控制值', width: 120, sortable: true,editor:'textbox' },
            { field: 'tscRemark', title: '描述', width: 160, sortable: true,editor:'textbox' }
        ]],
        queryParams:{
            tscKey: '',
            tscValue: ''
        },
        loader: function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : url_dg_tsSystemControl,
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
            parent_window_tsSystemControl.dialog('setTitle','修改控制参数');
            //打开弹出框
            parent_window_tsSystemControl.dialog('open');
            //填写数据
            parent.$(window_form_tsSystemControl).form('load',row);
            if(row.tscId != null){
                parent.$(text_tscKey).validatebox('readonly');
            }

        }
    });

    /**
     * 系统控制参数弹出框，初始化先关闭
     */
    parent_window_tsSystemControl.dialog({
        width: 600,
        height: 'auto',
        draggable:false,
        modal: true,
        content:$(window_tsSystemControl),
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
                if(parent.$(window_form_tsSystemControl).form('enableValidation').form('validate')){
                    var systemControl = parent.$(window_form_tsSystemControl).getFormData();
                    $.ajax({
                        type: "POST",
                        url: url_window_form_tsSystemControl,
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
    parent_window_import_tsSystemControl.dialog({
        width: 600,
        height: 'auto',
        draggable:false,
        modal: true,
        content:$(window_import_tsSystemControl),
        center:true,
        closed:true,
        closable:false,
        buttons:[{
            text:'下载模板',
            iconCls:'icon-save',
            handler:function(){
                var form = parent.$(window_form_import_tsSystemControl);
                form.attr("action",url_download_tsSystemControl);
                form.submit();
            }
        },{
            text:'上传',
            iconCls:'icon-undo',
            handler:function(){
                if(parent.$(file).val()!=''){
                    var form = parent.$(window_form_import_tsSystemControl);
                    form.attr("action",url_import_tsSystemControl);
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
    })
};

/**
 * 新增控制参数
 */
function btnAddTsSystemControl() {
    //设置标题
    parent_window_tsSystemControl.dialog('setTitle','新增控制参数');
    //打开弹出框
    parent_window_tsSystemControl.dialog('open');
};

/**
 * 刷新数据列表
 */
function reloadComponent() {
    //清空表单
    parent.$(window_form_tsSystemControl).form('clear');
    //关闭弹出框
    parent_window_tsSystemControl.dialog('close');
    //重新加载列表
    $(dg_tsSystemControl).datagrid('reload');
    //取消只读
    parent.$(text_tscKey).validatebox('readonly',false);
    //清空表单
    parent.$(window_form_import_tsSystemControl)[0].reset();
    //关闭弹出框
    parent_window_import_tsSystemControl.dialog('close');
};

/**
 * 删除系统控制参数
 */
function btnDeleteTsSystemControl() {
    //获取选择的数据
    var tsSystemControl = $(dg_tsSystemControl).datagrid('getSelected');
    $.ajax({
        type: "POST",
        url: url_delete_tsSystemControl,
        data:'['+JSON.stringify(tsSystemControl)+']',
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
function btnSearchTsSystemControl() {
    var searchSystemControl = $(form_toolbar_dg_tsSystemControl).getFormData();
    $(dg_tsSystemControl).datagrid({queryParams:searchSystemControl});
};

/**
 * 重置查询条件
 */
function btnResetTsSystemControl() {
    //重置查询条件
    $(form_toolbar_dg_tsSystemControl).form('clear');
};

/**
 * 导入数据
 */
function btnImportTsSystemControl() {
    //添加标题
    parent_window_import_tsSystemControl.dialog('setTitle','导入');
    //显示弹出框
    parent_window_import_tsSystemControl.dialog('open');
};

/**
 * 导出数据
 */
function btnExportTsSystemControl() {
    //导出数据需要使用form，不能用ajax
    $.messager.progress();
    $(form_toolbar_dg_tsSystemControl).form('submit',{url:url_export_tsSystemControl});
    $.messager.progress('close');	// 如果提交成功则隐藏进度条
};

function initBtnBindFun() {
    //新增控制参数
    $(btn_add_tsSystemControl).bind('click', function () {
        btnAddTsSystemControl();
    });
    //删除系统控制参数
    $(btn_delete_tsSystemControl).bind('click', function () {
        btnDeleteTsSystemControl();
    });
    //查询控制参数
    $(btn_search_tsSystemControl).bind('click',function () {
        btnSearchTsSystemControl();
    });
    //重置查询条件
    $(btn_reset_tsSystemControl).bind('click',function () {
        btnResetTsSystemControl();
    });
    //导入数据
    $(btn_import_tsSystemControl).bind('click',function () {
        btnImportTsSystemControl();
    });
    //导出数据
    $(btn_export_tsSystemControl).bind('click',function () {
        btnExportTsSystemControl();
    });
};

$(function(){
	//初始化控件
	initComponent();
	//按钮绑定功能
	initBtnBindFun();
});