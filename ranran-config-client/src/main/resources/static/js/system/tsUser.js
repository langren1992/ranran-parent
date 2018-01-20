function convertsoTree(rows){
    function exists(rows, deptParentNo){
        for(var i=0; i<rows.length; i++){
            if (rows[i].deptNo == deptParentNo) return true;
        }
        return false;
    }

    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.deptParentNo)){
            nodes.push({
                text:row.deptName,
                deptId:row.deptId,
                deptNo:row.deptNo,
                deptName:row.deptName,
                deptParentNo:row.deptParentNo,
                deptParentNameCn:row.deptParentNameCn,
                deptLever:row.deptLever,
                deptStatus:row.deptStatus,
                deptLeadNo:row.deptLeadNo,
                deptLeadName:row.deptLeadName,
                deptTel:row.deptTel,
                deptAreaNo:row.deptAreaNo,
                deptAreaName:row.deptAreaName,
                deptProAreaNo:row.deptProAreaNo,
                deptProAreaName:row.deptProAreaName,
                deptProvince:row.deptProvince,
                deptCity:row.deptCity,
                deptDistrict:row.deptDistrict,
                deptAddress:row.deptAddress,
                deptLon:row.deptLon,
                deptLat:row.deptLat,
                deptMegArea:row.deptMegArea,
                deptInsurRate:row.deptInsurRate,
                deptVirtual:row.deptVirtual,
                deptCollectRate:row.deptCollectRate,
                deptDebtAmount:row.deptDebtAmount,
                creator:row.creator,
                createTime:row.createTime,
                modifier:row.modifier,
                modifyTime:row.modifyTime,
                orgId:row.orgId,
                recVer:row.recVer
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
            if( row.deptParentNo == node.deptNo){
                var child = {
                    text:row.deptName,
                    deptId:row.deptId,
                    deptNo:row.deptNo,
                    deptName:row.deptName,
                    deptParentNo:row.deptParentNo,
                    deptParentNameCn:row.deptParentNameCn,
                    deptLever:row.deptLever,
                    deptStatus:row.deptStatus,
                    deptLeadNo:row.deptLeadNo,
                    deptLeadName:row.deptLeadName,
                    deptTel:row.deptTel,
                    deptAreaNo:row.deptAreaNo,
                    deptAreaName:row.deptAreaName,
                    deptProAreaNo:row.deptProAreaNo,
                    deptProAreaName:row.deptProAreaName,
                    deptProvince:row.deptPrivernce,
                    deptCity:row.deptCity,
                    deptDistrict:row.deptDistrict,
                    deptAddress:row.deptAddress,
                    deptLon:row.deptLon,
                    deptLat:row.deptLat,
                    deptMegArea:row.deptMegArea,
                    deptInsurRate:row.deptInsurRate,
                    deptVirtual:row.deptVirtual,
                    deptCollectRate:row.deptCollectRate,
                    deptDebtAmount:row.deptDebtAmount,
                    creator:row.creator,
                    createTime:row.createTime,
                    modifier:row.modifier,
                    modifyTime:row.modifyTime,
                    orgId:row.orgId,
                    recVer:row.recVer
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
};
var dg_user = '#dg_user';
var toolbar_user = '#toolbar_user';
var url_dg_user = './tsUser/selectUser.html';
var url_opt_user = './tsUser/updateUsers.html';
var btn_add_user = '#btn_add_user';
var btn_save_user = '#btn_save_user';
var btn_delete_user = '#btn_delete_user';
var btn_changePwd_user = '#btn_changePwd_user';
var btn_back_user = '#btn_back_user';
//部门树形请求路径
var url_tree_dept = './tsDept/selectDept.html';
//部门树形结构
var tree_dept = '#tree_dept';

var editRow = undefined;
function initComponent(){

    $(tree_dept).tree({
        url:url_tree_dept,
        method:'post',
        fit:true,
        singleSelect:true,
        onClick:function (row) {
            $(dg_user).datagrid({queryParams:{userDeptNo:row.deptNo}});
            editRow = undefined;
        },
        loadFilter:function(rows){
            if(rows.data != undefined)
                return convertsoTree(rows.data);
        }
    });

	$(dg_user).datagrid({
		fit:true,
        rownumbers:true,
		singleSelect:true,
		toolbar:toolbar_user,
		columns: [[
			{ field: 'userId', title: '资源ID',hidden:true},
            { field: 'userNo', title: '用户编码', width: 150, sortable: true,editor:{type:'textbox',options:{required:true}} },
            { field: 'userName', title: '用户名称', width: 120, sortable: true,editor:{type:'textbox',options:{required:true}}},
			{ field: 'userTel', title: '手机号', width: 120, sortable: true,editor:{type:'textbox',options:{required:true}}},
            { field: 'userStatus', title: '状态', width: 120, sortable: true,formatter:function(value,row){
                if(value==0){
                    return '离职';
                }else if(value == 1){
                    return '在职';
                }else{
                    return '离职';
                }
            }},
			{ field: 'userDeptName', title: '所属部门名称', width: 120, sortable: true},
			{ field: 'userDeptNo', title: '所属部门编码', width: 120, sortable: true}
		]],
        queryParams:{
            userDeptNo:''
		},
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            editRow = undefined;
        },
        onBeginEdit:function (i, row) {
            if(row.userNo != "" && row.userNo != undefined){
                var eg =$(dg_user).datagrid('getEditor',{index:i,field:'userNo'});
                $(eg.target).textbox({"readonly":true});
            }
        },
        onClickRow: function (rowIndex, rowData) {
            //双击开启编辑行
            if (editRow != undefined) {
                $(dg_user).datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $(dg_user).datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
        },
        loader : function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : url_dg_user,
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

function btnAddUser() {
    var dept = $(tree_dept).tree("getSelected");
    if (dept == null){
        parent.$.messager.alert("提示信息",'请选择部门数据！',"error");
    }else {
        if(dept.deptNo == 'ZHDP0001'){
            parent.$.messager.alert("提示信息",'公司不能下不能新增！',"error");
        }else{
            //添加列表的操作按钮添加，修改，删除等
            //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
            if (editRow != undefined) {
                $(dg_user).datagrid("endEdit", editRow);
            }
            //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
            if (editRow == undefined) {
                $(dg_user).datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {
                        userDeptNo:dept.deptNo,
                        userDeptName:dept.deptName,
                    }
                });
                //将新插入的那一行开户编辑状态
                $(dg_user).datagrid("beginEdit", 0);
                //给当前编辑的行赋值
                editRow = 0;
            }
        }
    }


};

function btnSaveUser() {
    var dept = $(tree_dept).tree("getSelected");
    /**
     * 没有编辑列直接保存的情况
     */
    if (editRow != undefined){
        $(dg_user).datagrid("endEdit",editRow);
    }
    var json = JSON.stringify($(dg_user).datagrid("getChanges","inserted").concat($(dg_user).datagrid("getChanges","updated")));
    $.ajax({
        type: "POST",
        url: url_opt_user,
        data: json,
        contentType:"application/json;charset=utf-8",
        dataType: "json",
        success: function(data){
            //保存完成后直接刷新 列标识
            editRow = undefined;
            $(dg_user).datagrid({queryParams:{userDeptNo:dept.deptNo}});
            if(data.success){
                parent.$.messager.alert("提示信息",data.message);
            }else {
                parent.$.messager.alert("异常提示",data.message);
            }
        }
    });
};

function btnDeleteUser() {
    if (editRow != undefined){
        $(dg_user).datagrid("endEdit",editRow);
    }
    var dept = $(tree_dept).tree("getSelected");
    if (dept == null){
        parent.$.messager.alert("提示信息",'请选择部门数据！',"error");
    }else {
        var user = $(dg_user).datagrid('getSelected');
        if (user.userStatus != 1){
            parent.$.messager.alert("提示信息",'请选择在职人员！');
        }else {
            //离职状态
            user.userStatus = 0;
            user.userDeptName = '中骅国际物流有限公司';
            user.userDeptNo = 'ZHDP000000';
            var json = '['+JSON.stringify(user)+']';
            $.ajax({
                type: "POST",
                url: url_opt_user,
                data: json,
                contentType:"application/json;charset=utf-8",
                dataType: "json",
                success: function(data){
                    //保存完成后直接刷新 列标识
                    editRow = undefined;
                    $(dg_user).datagrid({queryParams:{userDeptNo:dept.deptNo}});
                    if(data.success){
                        parent.$.messager.alert("提示信息",data.message);
                    }else {
                        parent.$.messager.alert("异常提示",data.message);
                    }
                }
            });
        }
    }
};

function btnChangePwdUser() {

};

function btnBackUser() {

};

function btnBindFun() {
	$(btn_add_user).bind('click', function(){
		btnAddUser();
	});
	$(btn_save_user).bind('click', function(){
		btnSaveUser();
	});
	$(btn_delete_user).bind('click', function(){
		btnDeleteUser();
	});
	$(btn_changePwd_user).bind('click', function(){
		btnChangePwdUser();
	});
	$(btn_back_user).bind('click', function(){
        btnBackUser();
    });
}

$(function(){
	//初始化控件
	initComponent();
	//按钮绑定功能
	btnBindFun();

});