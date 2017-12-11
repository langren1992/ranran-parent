function initComponent(){

	$("#user_list").treegrid({
		fit:true,
		singleSelect:true,
		checkbox:true,
		cascadeCheck:true,
		toolbar:"#user_list_tool",
		columns: [[
			{ field: 'resId', title: '资源ID',hidden:true},
			{ field: 'resName', title: '用户名称', width: 120, sortable: true},
			{ field: 'resNo', title: '用户编码', width: 150, sortable: true},
			{ field: 'resPermission', title: '手机号', width: 120, sortable: true},
			{ field: 'resPermission', title: '所属部门名称', width: 120, sortable: true},
			{ field: 'resPermission', title: '所属部门编码', width: 120, sortable: true},
			{ field: 'resDescribe', title: '描述', width: 160, sortable: true}
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

function btnSaveOpt() {

};

function btnEnableOpt() {
	
};

function btnDisableOpt() {

};

function btnRemoveOpt() {

};

function btnSearchOpt() {

};

function btnResetOpt() {

};

function btnSaveRoleResOpt() {

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
}

$(function(){
	//初始化控件
	initComponent();

	//按钮绑定功能
	btnBindFun();

});