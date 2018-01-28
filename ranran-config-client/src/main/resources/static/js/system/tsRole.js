/************************************所有组件ID定义************************************************/
var add_user_dialog = '#add_user_dialog';
var form_roleStatus = '#form_roleStatus';
var form_roleState_res = '#form_roleState_res';
var role_list = '#role_list';
var role_list_tool = '#role_list_tool';
var roleRes_list = '#roleRes_list';
var roleRole_list_tool = '#roleRole_list_tool';
var roleRes_btn_list = '#roleRes_btn_list';
var  roleRes_btn_tool= '#roleRes_btn_tool';
var form_roleRes = '#form_roleRes';
var role_user_list = '#role_user_list';
var role_non_user_list = '#role_non_user_list';
var form_condi = '#form_condi';
var form_roleNo_res = '#form_roleNo_res';
var btn_roleRes_add = '#btn_roleRes_add';
/*******************************所有访问路径定义****************************************/
//角色列表访问路径
var role_list_url = './tsRole/selectByCondition.html';
//角色资源树访问路径
var roleRes_list_url = './tsRole/selectRoleResource.html';
//角色资源权限（按钮）列表访问路径
var roleRes_btn_list_url = './tsRole/selectRoleResPermi.html';

var role_user_list_url = './tsRole/selectRoleUser.html';

var role_non_user_url = './tsRole/selectRoleNotUser.html';

var role_update_url = './tsRole/updateRoles.html';
/**********************************所有按钮定义*********************************************************/

var  btn_add = '#btn_add';
var  btn_save = '#btn_save';
var  btn_enable = '#btn_enable';
var  btn_disable = '#btn_disable';
var  btn_remove = '#btn_remove';
var  btn_search = '#btn_search';
var  btn_reset = '#btn_reset';
var  btn_save_RoleRes = '#btn_save_RoleRes';
var  btn_insert_user = '#btn_insert_user';
var  btn_delete_user = '#btn_delete_user';


var form_roleNo = '#form_roleNo';
var form_roleName = '#form_roleName';
var editRow = undefined;
function initComponent(){
    $(add_user_dialog).dialog({
        title: '添加用户',
        width: 800,
        height: 450,
        closed: true,
        draggable:false,
        cache: false,
        modal: true
    });

    $(form_roleStatus).combobox({
        panelHeight:"auto",
        valueField: 'tdCode',
        textField: 'tdName',
        queryParams:{
            tdKey:'ROLE_STATUS',
            tdType:2
        },
        loader: function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : './tsDict/selectTsDict.html',
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result.data);
                }
            });
        }
    });

    $(form_roleState_res).combobox({
        panelHeight:"auto",
        valueField: 'tdCode',
        textField: 'tdName',
        queryParams:{
            tdKey:'ROLE_STATUS',
            tdType:2
        },
        loader: function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : './tsDict/selectTsDict.html',
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result.data);
                }
            });
        }
    });

    $(role_list).datagrid({
        fit:true,
        idField: 'roleNo',
        rownumbers: true,
        pagination: true,
        toolbar:role_list_tool,
        singleSelect:true,
        frozenColumns: [[
            { field: 'roleNo', title: '角色编码', sortable: true, width:120,sortName:"role_no",editor:{type:'textbox',options:{required:true}}}
        ]],
        columns: [[
            { field: 'roleId', title: '角色ID',hidden:true,width:80,editor:'textbox' },
            { field: 'roleName', title: '名称', sortable: true,width:100,editor:'textbox'},
            { field: 'roleStatus', title: '可用状态', sortable: true, width:100, formatter:function(value,row){
                    if(value==0){
                        return '停用';
                    }else if(value == 1){
                        return '可用';
                    }else{
                        return null;
                    }
                }
            },
            { field: 'roleDescribe', title: '描述', sortable: true,width:200,editor:'textbox' }
        ]],
        pageSize:50,
        pageList:[50,100,500],
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            editRow = undefined;
        },
        onBeginEdit:function (i, row) {
            if(row.roleId != "" && row.roleId != undefined){
                var eg =$(role_list).datagrid('getEditor',{index:i,field:'roleNo'});
                $(eg.target).textbox({"readonly":true});
            }
        },
        onClickRow: function (rowIndex, rowData) {
            //双击开启编辑行
            if (editRow != undefined) {
                $(role_list).datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $(role_list).datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
            $(form_roleRes).form("load",rowData);
            /**
             * 查询选择角色资源信息
             * */
            $(roleRes_list).tree({
                queryParams:{
                    roleNo:rowData.roleNo
                }
            });
            $(roleRes_btn_list).datagrid('loadData',{total:0,rows:[]});

            $(role_user_list).datagrid({
                queryParams:{
                    roleNo:rowData.roleNo
                }
            });
            $(role_non_user_list).datagrid({
                queryParams:{
                    roleNo:rowData.roleNo
                }
            });
        },
        queryParams:{
            roleNo:'',
            roleName:'',
            roleStatus:''
        },
        loader : function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : role_list_url,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result);
                }
            });
        },
        enableHeaderClickMenu: false,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
        enableHeaderContextMenu: true     //此属性开启表头列名称右键点击菜单
    });


    /**
     * 获取角色资源列表
     */
    $(roleRes_list).tree({
        fit:true,
        checkbox:true,
        columns: [[
            { field: 'resName', title: '名称', width: 120, sortable: true},
            { field: 'resNo', title: '编码', width: 150, sortable: true},
            { field: 'resPermission', title: '权限项', width: 120, sortable: true},
            { field: 'resDescribe', title: '描述', width: 160, sortable: true},
            { field: 'resId', title: '资源ID',hidden:true},
            { field: 'resParentNo', title: '父编码', width: 120, sortable: true,hidden:true},
            { field: 'resParentName', title: '父名称', width: 120, sortable: true,hidden:true},
            { field: 'resType', title: '类型', width: 120,hidden:true}
        ]],
        toolbar:roleRole_list_tool,
        loadFilter:function(rows){
            if(rows.data != undefined)
                return converts(rows.data);
        },
        onClick: function (node) {
            var roleDg = $(role_list);
            var role = roleDg.datagrid('getSelected');
            var roleNo = '';
            if (role != null){
                roleNo = role.roleNo;
            }
            var rolesChecked = $(roleRes_list).tree('getChecked', ['checked','indeterminate']);
            if (rolesChecked.length != 0){
                $(roleRes_btn_list).datagrid({queryParams:{roleNo:roleNo, resNo:node.resNo}});
            }else{
                parent.$.messager.alert('提示信息','请选择操作角色！');
            }
        },
        queryParams:{
            roleNo:''
        },
        loader:function (param, success, error) {
            $.ajax({
                type : 'POST',
                url : roleRes_list_url,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result);
                }
            });
        }
    });

    /**
     * 获取资源权限
     */
    $(roleRes_btn_list).datagrid({
        fit:true,
        idField: 'resNo',
        columns: [[
            { field:'ck',checkbox:true },
            { field: 'resNo', title: '资源编码', sortable: true },
            { field: 'resName', title: '资源名称', sortable: true},
            { field: 'resPermission', title: '权限项'},
            { field: 'resType', title: '类型'},
            { field: 'resDescribe', title: '描述'}
        ]],
        toolbar:roleRes_btn_tool,
        // SelectOnCheck:true,
        queryParams:{
            roleNo:'',
            resNo:''
        },
        loader:function (param, success, error) {
            $.ajax({
                type : 'POST',
                url : roleRes_btn_list_url,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    //先赋值
                    success(result);
                    //重新选择
                    var results = result.data;
                    $(roleRes_btn_list).datagrid('clearChecked');
                    for (var i = 0,length = results.length; i <= length; i++){
                        var res = results[i];
                        if(res != undefined){
                            if (res.checked!= undefined && res.checked){
                                $(roleRes_btn_list).datagrid('selectRecord',res.resNo)
                            }
                        }
                    }
                }
            });
        }
    });

    //角色用户清单
    $(role_user_list).datagrid({
        fit:true,
        idField: 'userNo',
        title:"已有用户",
        rownumbers: true,
        singleSelect:true,
        method:"POST",
        columns: [[
            { field:'ck',checkbox:true },
            { field: 'userNo', title: '用户编号', sortable: true, width:80,sortName:"role_no"},
            { field: 'userId', title: '角色ID',hidden:true,width:80 },
            { field: 'userName', title: '名称', sortable: true,width:100},
            { field: 'userDeptName', title: '所属部门', sortable: true,width:100},
            { field: 'userTel', title: '手机号', sortable: true,width:200 }
        ]],
        queryParams:{
            roleNo:''
        },
        loader:function (param, success, error) {
            $.ajax({
                type : 'POST',
                url : role_user_list_url,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result);
                }
            });
        }
    });

    //角色用户清单
    $(role_non_user_list).datagrid({
        fit:true,
        idField: 'userNo',
        title:"其他用户",
        rownumbers: true,
        singleSelect:true,
        method:"POST",
        columns: [[
            {field:'checkNo',checkbox:true},
            { field: 'userNo', title: '用户编号', sortable: true, width:80,sortName:"role_no"},
            { field: 'userId', title: '角色ID',hidden:true,width:80 },
            { field: 'userName', title: '名称', sortable: true,width:100},
            { field: 'userDeptName', title: '所属部门', sortable: true,width:100},
            { field: 'userTel', title: '手机号', sortable: true,width:200 }
        ]],
        queryParams:{
            roleNo:''
        },
        loader:function (param, success, error) {
            $.ajax({
                type : 'POST',
                url : role_non_user_url,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result);
                }
            });
        }
    });

};

function converts(rows){
    function exists(rows, resParentNo){
        for(var i=0; i<rows.length; i++){
            if (rows[i].resNo == resParentNo) return true;
        }
        return false;
    }

    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.resParentNo)){
            nodes.push({
                resId:row.resId,
                resParentNo:row.resParentNo,
                resParentName:row.resParentName,
                resName:row.resName,
                text:row.resName,
                resNo:row.resNo,
                resType:row.resType,
                resPermission:row.resPermission,
                resStatus:row.resStatus,
                isLeaf:false,
                resUrl:row.resUrl,
                resIconcls:row.resIconcls,
                resDescribe:row.resDescribe,
                checked:null,
                checkState:null,
                createTime:row.createTime,
                creator:row.creator,
                modifier:row.modifier,
                modifyTime:row.modifyTime
            });
        }
    }

    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while (toDo.length){
        var node = toDo.shift();
        for(var i = 0; i < rows.length; i++){
            var row = rows[i];
            if( row.resParentNo == node.resNo){
                var child = {
                    resId:row.resId,
                    resParentNo:row.resParentNo,
                    resParentName:row.resParentName,
                    resName:row.resName,
                    text:row.resName,
                    resNo:row.resNo,
                    resType:row.resType,
                    resPermission:row.resPermission,
                    resStatus:row.resStatus,
                    isLeaf:true,
                    resUrl:row.resUrl,
                    resIconcls:row.resIconcls,
                    resDescribe:row.resDescribe,
                    checked:row.checked,
                    checkState:row.checkState,
                    createTime:row.createTime,
                    creator:row.creator,
                    modifier:row.modifier,
                    modifyTime:row.modifyTime
                };
                if(node.children){
                    node.children.push(child);
                }else{
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}


function btnAddOpt() {
    //添加列表的操作按钮添加，修改，删除等
    //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
    if (editRow != undefined) {
        $(role_list).datagrid("endEdit", editRow);
    }
    //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
    if (editRow == undefined) {
        $(role_list).datagrid("insertRow", {
            index: 0, // index start with 0
            row: {
                roleStatus:0
            }
        });
        //将新插入的那一行开户编辑状态
        $(role_list).datagrid("beginEdit", 0);
        //给当前编辑的行赋值
        editRow = 0;
    }
};


function btnSaveOpt() {
    $(role_list).datagrid("endEdit",editRow);
    var arr = $(role_list).datagrid('getChanges','inserted').concat($(role_list).datagrid('getChanges','updated'));
    var flag = true;
    for (var i = 0,size = arr.length; i < size;i++){
        if(arr[i].roleNo == '' || arr[i].roleNo == undefined){
            flag = false;
            parent.$.messager.alert("提示信息",'角色编号为空！',"error");
        }
    }
    if (flag){
        $.ajax({
            type: "POST",
            url: role_update_url,
            data: JSON.stringify(arr),
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
                btnSearchOpt();
                clearComponentData();
            },
            error : function(message) {
                if (message.responseJSON.message.indexOf("Duplicate")){
                    parent.$.messager.alert("错误信息","保存失败，请保证编号唯一！","error");
                }else{
                    parent.$.messager.alert("错误信息","保存失败，错误信息："+message.responseJSON.message,"error");
                }
            }
        });
    }
};

function btnEnableOpt() {
    var tsRole=$(role_list).datagrid("getSelected");
    if(tsRole.roleStatus != 1){
        //状态修改为启用
        tsRole.roleStatus = 1;
        var tsRoles = new Array(tsRole);
        $.ajax({
            type: "POST",
            url: role_update_url,
            data:JSON.stringify(tsRoles),
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
                btnSearchOpt();
            },
            error : function(message) {
                if (message.responseJSON.message.indexOf("Duplicate")){
                    parent.$.messager.alert("错误信息","保存失败，请保证编号唯一！","error");
                }else{
                    parent.$.messager.alert("错误信息","保存失败，错误信息："+message.responseJSON.message,"error");
                }
            }
        });
    }else{
        parent.$.messager.alert("提示信息",'该数据已经启用','info');
    }
};

function btnDisableOpt() {
    var tsRole=$(role_list).datagrid("getSelected");
    //状态修改为启用
    tsRole.roleStatus = 0;
    var tsRoles = new Array(tsRole);
    $.ajax({
        type: "POST",
        url: role_update_url,
        data:JSON.stringify(tsRoles),
        contentType:"application/json",
        dataType: "json",
        success: function(data){
            if(data.success){
                parent.$.messager.alert("提示信息",data.message);
            }
            btnSearchOpt();
            clearComponentData();
        },
        error : function(message) {
            if (message.responseJSON.message.indexOf("Duplicate")){
                parent.$.messager.alert("错误信息","保存失败，请保证编号唯一！","error");
            }else{
                parent.$.messager.alert("错误信息","保存失败，错误信息："+message.responseJSON.message,"error");
            }
        }
    });

};

function btnRemoveOpt() {
    var tsRole=$(role_list).datagrid("getSelected");
    //状态修改为启用
    tsRole.roleStatus = 2;
    var tsRoles = new Array(tsRole);
    $.ajax({
        type: "POST",
        url: role_update_url,
        data:JSON.stringify(tsRoles),
        contentType:"application/json",
        dataType: "json",
        success: function(data){
            if(data.success){
                parent.$.messager.alert("提示信息",data.message);
            }
            clearComponentData();
        },
        error : function(message) {
            if (message.responseJSON.message.indexOf("Duplicate")){
                parent.$.messager.alert("错误信息","保存失败，请保证编号唯一！","error");
            }else{
                parent.$.messager.alert("错误信息","保存失败，错误信息："+message.responseJSON.message,"error");
            }
        }
    });
    btnSearchOpt();
};

function btnSearchOpt() {
    /**
     * easyui中datagrid Content-Type:application/x-www-form-urlencoded; charset=UTF-8的时候 HttpServletRequest从getParameter中获取参数,
     * 当contentType : 'application/json;charset=utf-8', // 设置请求头信息从HttpServletRequest从getInputStream中获取参数；
     * */
    $(role_list).datagrid({
        queryParams:{
            roleNo:$(form_roleNo).textbox('getText'),
            roleName:$(form_roleName).textbox('getText'),
            roleStatus:$(form_roleStatus).combobox('getValue')
        },
    });
};

function btnResetOpt() {
    $(form_condi).form('reset');
};

function btnSaveRoleResOpt() {
    if($(form_roleNo_res).textbox("getValue") != ""){
        var roleResHas = $(roleRes_list).tree("getChecked",["checked","indeterminate"]);
        var roleResNull = [];
        for (var i = 0; i < roleResHas.length; i++){
            roleResNull.push({
                roleNo:$(form_roleNo_res).textbox("getValue"),
                resId:roleResHas[i].resId,
                resNo:roleResHas[i].resNo,
                resName:roleResHas[i].resName,
                checked:roleResHas[i].checked,
                checkState:roleResHas[i].checkState
            });
        }
        $.ajax({
            type: "POST",
            url: "./tsRole/optRoleResRal.html",
            data: JSON.stringify(roleResNull),
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
                clearComponentData();
            }
        });
    }
};

function optRoleResPermiRal() {
    //获取单选的行数据
    var tsRole = $(role_list).datagrid('getSelected');
    //获取勾选的行数据
    var roleResBtnList = $(roleRes_btn_list).datagrid('getChecked');
    //获取勾选的行数据
    var roleResBtnList = $(roleRes_btn_list).datagrid('getChecked');
    //获取选中的资源树
    var roleRes = $(roleRes_list).tree('getSelected');

    if(tsRole == null || roleRes == null && roleRes.resParentNo == null){
        parent.$.messager.alert("提示信息",'请选择角色和资源菜单！');
    }else{
        var roleResNull = [];
        for (var i = 0; i < roleResBtnList.length; i++){
            roleResNull.push({
                roleNo:$(form_roleNo_res).textbox("getValue"),
                resId:roleResBtnList[i].resId,
                resNo:roleResBtnList[i].resNo,
                resParentNo:roleRes.resNo,
                resName:roleResBtnList[i].resName,
                checked:roleResBtnList[i].checked,
                checkState:roleResBtnList[i].checkState
            });
        }
        $.ajax({
            type: "POST",
            url: "./tsRole/optRoleResPermiRal.html",
            data: JSON.stringify(roleResNull),
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
                clearComponentData();
            }
        });
    }


};


function clearComponentData() {
    // 清空表单
    $(form_roleRes).form("clear");
    // 清空资源树数据
    $(roleRes_list).tree({queryParams:{roleNo:''}});
    // 清空按钮数据
    $(roleRes_btn_list).datagrid('loadData',{total:0,rows:[]});
    //清空角色列表选择数据
    $(role_list).datagrid('unselectAll');
};

/**
 * 添加角色用户按钮
 */
function btnInsertUser() {
    //获取单选的行数据
    var tsRole = $(role_list).datagrid('getSelected');
    //获取勾选的行数据
    var roleResBtnList = $(role_non_user_list).datagrid('getChecked');
    if (roleResBtnList.length >=1){
        var json = '{"roleNo":"'+tsRole.roleNo+'","optType":"ADD","users":'+JSON.stringify(roleResBtnList)+'}';
        $.ajax({
            type: "POST",
            url: "./tsRole/optRoleUserRal.html",
            data: json,
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
                $(role_user_list).datagrid({
                    queryParams:{
                        roleNo:tsRole.roleNo
                    }
                });
                $(role_non_user_list).datagrid({
                    queryParams:{
                        roleNo:tsRole.roleNo
                    }
                });
            }
        });
    }

};

/**
 * 删除角色用户按钮
 */
function btnDeleteUser() {
    //获取单选的行数据
    var tsRole = $(role_list).datagrid('getSelected');
    //获取勾选的行数据
    var roleResBtnList = $(role_non_user_list).datagrid('getChecked');
    if (roleResBtnList.length >=1){
        var json = '{"roleNo":"'+tsRole.roleNo+'","optType":"DELETE","users":'+JSON.stringify(roleResBtnList)+'}';
        $.ajax({
            type: "POST",
            url: "./tsRole/optRoleUserRal.html",
            data: json,
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
                $(role_user_list).datagrid({
                    queryParams:{
                        roleNo:tsRole.roleNo
                    }
                });
                $(role_non_user_list).datagrid({
                    queryParams:{
                        roleNo:tsRole.roleNo
                    }
                });
            }
        });
    }

};
function btnBindFun() {
    /**
     *
     */
    $(btn_add).bind('click', function(){
        btnAddOpt();
    });
    $(btn_save).bind('click', function(){
        btnSaveOpt();
    });
    $(btn_enable).bind('click', function(){
        btnEnableOpt();
    });
    $(btn_disable).bind('click', function(){
        btnDisableOpt();
    });
    $(btn_remove).bind('click', function(){
        btnRemoveOpt();
    });
    $(btn_search).bind('click', function(){
        btnSearchOpt();
    });
    $(btn_reset).bind('click', function(){
        btnResetOpt();
    });
    $(btn_save_RoleRes).bind('click', function(){
        btnSaveRoleResOpt();
    });
    /**
     * 添加用户角色按钮
     */
    $(btn_insert_user).bind('click',function(){
        btnInsertUser();
    });
    /**
     * 删除用户角色按钮
     */
    $(btn_delete_user).bind('click',function(){
        btnDeleteUser();
    });
    /**
     * 角色添加按钮功能
     */
    $(btn_roleRes_add).bind('click',function () {
        optRoleResPermiRal();
    });
}

$(function(){
    //初始化控件
    initComponent();
    //按钮绑定功能
    btnBindFun();
});