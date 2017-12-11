
var editRow = undefined;

function initComponent(){

	$("#form_groupStatus").combobox({
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

	$("#form_groupState_role").combobox({
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

	$("#group_list").datagrid({
		fit:true,
		idField: 'ID',
		rownumbers: true,
		pagination: true,
		enableRowContextMenu: true,         //此属性开启表头列名称右键点击菜单
		pagingMenu: { submenu: false},//开启行右键菜单的翻页功能，此属性可丰富配置，详情见 API 文档
		exportMenu:{submenu: false},
		toolbar:'#group_list_tool',
		singleSelect:true,
		method:"POST",
		frozenColumns: [[
			{ field: 'ck', checkbox: true },
			{ field: 'groupNo', title: '用户组编码', sortable: true, width:120,editor:{type:'textbox',options:{required:true}}}
		]],
		columns: [[
			{ field: 'groupId', title: '用户组ID',hidden:true,width:80,editor:'textbox' },
			{ field: 'groupName', title: '用户组名称', sortable: true,width:100,editor:'textbox'
			},{
			    field: 'groupStatus',
                title: '可用状态',
                sortable: true,
                width:100,
                //行新增某列不能编辑直接掉editor
                // editor:{
			    //     type:"combobox",
                 //    options:{//自适应数据高度属性
                 //        panelHeight:"auto",
                 //        //值
                 //        valueField:'value',
                 //        //名称
                 //        textField: 'textName',
                 //        data: [{
                 //            textName: '可用',
                 //            value: "1"
                 //        },{
                 //            textName: '停用',
                 //            value: "0"
				 //        }]
			    //     }
			    // },
                formatter:function(value,row){
				    if(value==0){
				        return '停用';
                    }else if(value == 1){
                        return '可用';
                    }else{
                        return null;
                    }
                }
			}
			// ,
			// { field: 'groupDescribe', title: '描述', sortable: true,width:200,editor:'textbox' }

		]],
        cascadeCheck:true,
		pageSize:40,
		pageList:[40,80,120],
        loadFilter:function (data) {
            data.total = data.total;
            data.rows = data.list;
          return data;
        },
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            editRow = undefined;
        },
        onBeginEdit:function (i, row) {
            if(row.groupId != "" && row.groupId != undefined){
                var eg =$('#group_list').datagrid('getEditor',{index:i,field:'groupNo'});
                $(eg.target).textbox({"readonly":true});
            }
        },
        onDblClickRow: function (rowIndex, rowData) {
            //双击开启编辑行
            if (editRow != undefined) {
                $('#group_list').datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $('#group_list').datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
            $("#form_grouprole").form("load",rowData);
            // $('#grouprole_list').tree('loadData',{_parentId:""});
            $("#grouprole_list").tree({
                url:"./group/loadGroupRole",
                queryParams:{
                    groupNo:rowData.groupNo
                }
            });
            // $.post('./group/loadgrouproleTree',{groupNo:rowData.groupNo},function(data){
            //     $('#grouprole_list').tree('load', data);
            // },'json');
        },
		enableHeaderClickMenu: false,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
		enableHeaderContextMenu: true     //此属性开启表头列名称右键点击菜单
	});

	$("#group_role_list").tree({
        url: "./group/groupRoleList",
		fit:true,
		singleSelect:true,
        checkbox:true,
        cascadeCheck:true,
        columns: [[
            { field: 'roleName', title: '角色名称', width: 120, sortable: true},
            { field: 'roleNo', title: '角色编码', width: 150, sortable: true},
            { field: 'roleDescribe', title: '描述', width: 160, sortable: true},
            { field: 'roleId', title: '资源ID',hidden:true},
            { field: 'roleType', title: '类型', width: 120,hidden:true}
        ]],
        onClickRow:function (row) {
            console.log(row);
        },
        loadFilter:function(rows){
            if(rows.dataList != undefined)
            return converts(rows.dataList);
        },
		enableHeaderClickMenu: false,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
		enableHeaderContextMenu: true     //此属性开启表头列名称右键点击菜单
	});

};

function converts(rows){
    function exists(rows, roleParentNo){
        for(var i=0; i<rows.length; i++){
            if (rows[i].roleNo == roleParentNo) return true;
        }
        return false;
    }

    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.roleParentNo)){
            nodes.push({
                roleId:row.roleId,
                roleParentNo:row.roleParentNo,
                roleParentName:row.roleParentName,
                roleName:row.roleName,
                text:row.roleName,
                roleNo:row.roleNo,
                roleType:row.roleType,
                rolePermission:row.rolePermission,
                roleStatus:row.roleStatus,
                isLeaf:false,
                roleUrl:row.roleUrl,
                roleIconcls:row.roleIconcls,
                roleDescribe:row.roleDescribe,
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
            if( row.roleParentNo == node.roleNo){
                var child = {
                    roleId:row.roleId,
                    roleParentNo:row.roleParentNo,
                    roleParentName:row.roleParentName,
                    roleName:row.roleName,
                    text:row.roleName,
                    roleNo:row.roleNo,
                    roleType:row.roleType,
                    rolePermission:row.rolePermission,
                    roleStatus:row.roleStatus,
                    isLeaf:true,
                    roleUrl:row.roleUrl,
                    roleIconcls:row.roleIconcls,
                    roleDescribe:row.roleDescribe,
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
        $('#group_list').datagrid("endEdit", editRow);
    }
    //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
    if (editRow == undefined) {
        $('#group_list').datagrid("insertRow", {
            index: 0, // index start with 0
            row: {
                groupStatus:0
            }
        });
        //将新插入的那一行开户编辑状态
        $('#group_list').datagrid("beginEdit", 0);
        //给当前编辑的行赋值
        editRow = 0;
    }
};


function btnSaveOpt() {
    $('#group_list').datagrid("endEdit",editRow);
    var arr = $('#group_list').datagrid('getChanges','inserted').concat($('#group_list').datagrid('getChanges','updated'));
    $.ajax({
        type: "POST",
        url: "./group/save",
        data: JSON.stringify(arr),
        contentType:"application/json",
        dataType: "json",
        success: function(data){
            if(data.success){
                parent.$.messager.alert("提示信息",data.message);
            }
        }
    });
};

function btnEnableOpt() {
	
};

function btnDisableOpt() {

};

function btnRemoveOpt() {
	if (editIndex == undefined){return}
	$('#role_btn').datagrid('cancelEdit', editIndex)
		.datagrid('deleteRow', editIndex);
	editIndex = undefined;
};

function btnSearchOpt() {
    $("#group_list").datagrid({
        url:"./group/list",
        queryParams:{
            groupNo:$("#form_groupNo").textbox("getValue"),
            groupName:$("#form_groupName").textbox("getValue"),
            groupStatus:$("#form_groupStatus").combobox("getValue"),
        }
    });
};

function btnroleetOpt() {

};

function btnSavegrouproleOpt() {
    if($("#form_groupNo_role").textbox("getValue") != ""){
        var grouproleHas = $('#grouprole_list').tree("getChecked",["checked","indeterminate"]);
        var grouproleNull = [];
        for (var i = 0; i < grouproleHas.length; i++){
            grouproleNull.push({
                roleId:grouproleHas[i].roleId,
                roleNo:grouproleHas[i].roleNo,
                roleName:grouproleHas[i].roleName,
                checked:grouproleHas[i].checked,
                checkState:grouproleHas[i].checkState
            });
        }
        var json = '{'+$("#form_grouprole input").map(function(){
                        if($(this).attr("name")!= undefined)
                            return '"'+$(this).attr("name")+'":"'+$(this).val()+'"';
        }).get().join(", ")+',"grouproleList":'+JSON.stringify(grouproleNull)+'}';
        
        $.ajax({
            type: "POST",
            url: "./group/addgrouprole",
            data: json,
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                $("#form_grouprole").form("clear");
                $("#grouprole_list").tree({url: "./role/roleList"});
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
		btnEnableOpt("btnAddgroup");
	});
	$('#btn_disable').bind('click', function(){
		btnDisableOpt("btnAddgroup");
	});
	$('#btn_remove').bind('click', function(){
		btnRemoveOpt("btnAddgroup");
	});
	$('#btn_search').bind('click', function(){
		btnSearchOpt("btnAddgroup");
	});
	$('#btn_roleet').bind('click', function(){
		btnroleetOpt("btnAddgroup");
	});
	$('#btn_save_grouprole').bind('click', function(){
		btnSavegrouproleOpt("btnAddgroup");
	});
}

$(function(){
	//初始化控件
	initComponent();
    //初始化加载数据
    $("#group_list").datagrid({
        url:"./group/list",
        queryParams:{
            groupNo:$("#form_groupNo").textbox("getValue"),
            groupName:$("#form_groupName").textbox("getValue"),
            groupStatus:$("#form_groupStatus").combobox("getValue"),
        }
    });
	//按钮绑定功能
	btnBindFun();

});