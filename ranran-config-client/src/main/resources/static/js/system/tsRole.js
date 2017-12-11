
var editRow = undefined;

function initComponent(){

    $('#add_user_dialog').dialog({
        title: '添加用户',
        width: 800,
        height: 450,
        closed: true,
        draggable:false,
        cache: false,
        modal: true
    });

    $("#form_roleStatus").combobox({
        //自适应数据高度属性
        panelHeight:"auto",
        //值
        valueField:'value',
        //名称
        textField: 'textName',
        data: [{
            textName: '可用',
            value: "1"
        },{
            textName: '停用',
            value: "0"
        }]
    });

    $("#form_roleState_res").combobox({
        //自适应数据高度属性
        panelHeight:"auto",
        //值
        valueField:'value',
        //名称
        textField: 'textName',
        data: [{
            textName: '可用',
            value: "1"
        },{
            textName: '停用',
            value: "0"
        }]
    });

    $("#role_list").datagrid({
        url:"./tsRole/selectByCondition.html",
        queryParams:{
            roleNo:$("#form_roleNo").textbox("getValue"),
            roleName:$("#form_roleName").textbox("getValue"),
            roleStatus:$("#form_roleStatus").combobox("getValue"),
        },
        fit:true,
        idField: 'ID',
        rownumbers: true,
        pagination: true,
        toolbar:'#role_list_tool',
        singleSelect:true,
        method:"POST",
        frozenColumns: [[
            // { field: 'ck', checkbox: true },
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
        pageSize:30,
        pageList:[30,90,210,390,750],
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            editRow = undefined;
        },
        onBeginEdit:function (i, row) {
            if(row.roleId != "" && row.roleId != undefined){
                var eg =$('#role_list').datagrid('getEditor',{index:i,field:'roleNo'});
                $(eg.target).textbox({"readonly":true});
            }
        },
        onDblClickRow: function (rowIndex, rowData) {
            //双击开启编辑行
            if (editRow != undefined) {
                $('#role_list').datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $('#role_list').datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
            $("#form_roleRes").form("load",rowData);
            // $('#roleRes_list').tree('loadData',{_parentId:""});
            /*记载角色资源*/
            $("#roleRes_list").tree({
                url:"./tsRole/roleResource.html",
                queryParams:{
                    roleNo:rowData.roleNo
                }
            });
            $("#role_user_list").datagrid({
                url:"./tsRole/roleUser.html",
                queryParams:{
                    roleNo:rowData.roleNo
                }
            });
        },
        enableHeaderClickMenu: false,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
        enableHeaderContextMenu: true     //此属性开启表头列名称右键点击菜单
    });



    $("#roleRes_list").tree({
        url: "./tsRole/resource.html",
        fit:true,
        singleSelect:true,
        checkbox:true,
        cascadeCheck:true,
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
        loadFilter:function(rows){
            if(rows.resultData != undefined)
                return converts(rows.resultData);
        },
        enableHeaderClickMenu: false,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
        enableHeaderContextMenu: true     //此属性开启表头列名称右键点击菜单
    });
    //角色用户清单
    $("#role_user_list").datagrid({
        fit:true,
        idField: 'userId',
        title:"已有用户",
        rownumbers: true,
        singleSelect:true,
        method:"POST",
        frozenColumns: [[
            { field:'ck',checkbox:true },
            { field: 'userNo', title: '用户编号', sortable: true, width:80,sortName:"role_no"}
        ]],
        columns: [[
            { field: 'userId', title: '角色ID',hidden:true,width:80 },
            { field: 'userName', title: '名称', sortable: true,width:100},
            { field: 'userDeptName', title: '所属部门', sortable: true,width:100},
            { field: 'userTel', title: '手机号', sortable: true,width:200 }
        ]]
    });

    //角色用户清单
    $("#role_non_user_list").datagrid({
        fit:true,
        idField: 'userId',
        title:"其他用户",
        rownumbers: true,
        singleSelect:true,
        method:"POST",
        frozenColumns: [[
            {field:'checkNo',checkbox:true},
            { field: 'userNo', title: '用户编号', sortable: true, width:80,sortName:"role_no"}
        ]],
        columns: [[
            { field: 'userId', title: '角色ID',hidden:true,width:80 },
            { field: 'userName', title: '名称', sortable: true,width:100},
            { field: 'userDeptName', title: '所属部门', sortable: true,width:100},
            { field: 'userTel', title: '手机号', sortable: true,width:200 }
        ]]
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
                checked:row.checked,
                checkState:row.checkState,
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
        $('#role_list').datagrid("endEdit", editRow);
    }
    //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
    if (editRow == undefined) {
        $('#role_list').datagrid("insertRow", {
            index: 0, // index start with 0
            row: {
                roleStatus:0
            }
        });
        //将新插入的那一行开户编辑状态
        $('#role_list').datagrid("beginEdit", 0);
        //给当前编辑的行赋值
        editRow = 0;
    }
};


function btnSaveOpt() {
    $('#role_list').datagrid("endEdit",editRow);
    var arr = $('#role_list').datagrid('getChanges','inserted').concat($('#role_list').datagrid('getChanges','updated'));
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
            url: "/tsRole/saveBatch.html",
            data: JSON.stringify(arr),
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
    }
};

function btnEnableOpt() {
    var tsRole=$('#role_list').datagrid("getSelected");
    if(tsRole.roleStatus != 1){
        //状态修改为启用
        tsRole.roleStatus = 1;
        var tsRoles = new Array(tsRole);
        $.ajax({
            type: "POST",
            url: "/tsRole/updateBatch.html",
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
    var tsRole=$('#role_list').datagrid("getSelected");
    //状态修改为启用
    tsRole.roleStatus = 0;
    var tsRoles = new Array(tsRole);
    $.ajax({
        type: "POST",
        url: "/tsRole/updateBatch.html",
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

};

function btnRemoveOpt() {
    var tsRole=$('#role_list').datagrid("getSelected");
    //状态修改为启用
    tsRole.roleStatus = 0;
    var tsRoles = new Array(tsRole);
    $.ajax({
        type: "POST",
        url: "/tsRole/deleteBatch.html",
        data:JSON.stringify(tsRoles),
        contentType:"application/json",
        dataType: "json",
        success: function(data){
            if(data.success){
                parent.$.messager.alert("提示信息",data.message);
            }
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
    $("#role_list").datagrid({
        queryParams:{
            roleNo:$("#form_roleNo").textbox("getValue"),
            roleName:$("#form_roleName").textbox("getValue"),
            roleStatus:$("#form_roleStatus").combobox("getValue"),
        }
    });
};

function btnResetOpt() {
    $('#form_condi').form('reset');
};

function btnSaveRoleResOpt() {
    if($("#form_roleNo_res").textbox("getValue") != ""){
        var roleResHas = $('#roleRes_list').tree("getChecked",["checked","indeterminate"]);
        var roleResNull = [];
        for (var i = 0; i < roleResHas.length; i++){
            roleResNull.push({
                resId:roleResHas[i].resId,
                resNo:roleResHas[i].resNo,
                resName:roleResHas[i].resName,
                checked:roleResHas[i].checked,
                checkState:roleResHas[i].checkState
            });
        }
        var json = '{'+$("#form_roleRes input").map(function(){
                if($(this).attr("name")!= undefined)
                    return '"'+$(this).attr("name")+'":"'+$(this).val()+'"';
            }).get().join(", ")+',"roleResList":'+JSON.stringify(roleResNull)+'}';

        $.ajax({
            type: "POST",
            url: "./role/addRoleRes",
            data: json,
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                $("#form_roleRes").form("clear");
                $("#roleRes_list").tree({url: "./res/resList"});
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
            }
        });
    }
};

function btnBindFun() {
    $('#btn_add').bind('click', function(){
        btnAddOpt();
    });
    $('#btn_save').bind('click', function(){
        btnSaveOpt("btnSearch");
    });
    $('#btn_enable').bind('click', function(){
        btnEnableOpt("btnAddRole");
    });
    $('#btn_disable').bind('click', function(){
        btnDisableOpt("btnAddRole");
    });
    $('#btn_remove').bind('click', function(){
        btnRemoveOpt("btnAddRole");
    });
    $('#btn_search').bind('click', function(){
        btnSearchOpt("btnAddRole");
    });
    $('#btn_reset').bind('click', function(){
        btnResetOpt("btnAddRole");
    });
    $('#btn_save_RoleRes').bind('click', function(){
        btnSaveRoleResOpt("btnAddRole");
    });
    $('#btn_add_user').bind('click',function(){
        parent.$('#index_body').layout('collapse','west');
        $('#add_user_dialog').dialog('open');
    });
}

$(function(){
    //初始化控件
    initComponent();
    //按钮绑定功能
    btnBindFun();
});