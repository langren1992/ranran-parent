function convertsoTree(rows){
    function exists(rows, deptParentNo){
        for(var i=0; i<rows.length; i++){
            if (rows[i].deptNo == deptParentNo) return true;
        }
        return false;
    }

    var orgNode = {
        text:rows.orgName,
        orgId:rows.orgId,
        orgNo:rows.orgNo,
        orgName:rows.orgName,
        orgParentNo:rows.orgParentNo,
        orgParentNameCn:rows.orgParentNameCn,
        orgLever:rows.orgLever,
        orgStatus:rows.orgStatus,
        orgLeadNo:rows.orgLeadNo,
        orgLeadName:rows.orgLeadName,
        orgTel:rows.orgTel,
        orgAreaNo:rows.orgAreaNo,
        orgAreaName:rows.orgAreaName,
        orgProAreaNo:rows.orgProAreaNo,
        orgProAreaName:rows.orgProAreaName,
        orgPrivernce:rows.orgPrivernce,
        orgCity:rows.orgCity,
        orgDistrict:rows.orgDistrict,
        orgAddress:rows.orgAddress,
        orgLon:rows.orgLon,
        orgLat:rows.orgLat,
        creator:rows.creator,
        createTime:rows.createTime,
        modifier:rows.modifier,
        modifyTime:rows.modifyTime,
        recVer:rows.recVer
    };

    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.deptList.length; i++){
        var row = rows.deptList[i];
        if (!exists(rows.deptList, row.deptParentNo)){
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
                deptPrivernce:row.deptPrivernce,
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
            if( row.resParentNo == node.resNo){
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
                    deptPrivernce:row.deptPrivernce,
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
    orgNode.children = nodes;
    var result = [orgNode];
    return result;
}


function initComponent(){

	$("#dept_tree").tree({
        url:'/tsDept/loadCompanyDept.html',
        method:'get',
		fit:true,
		singleSelect:true,
		// checkbox:true,
		cascadeCheck:true,
		onClickRow:function (row) {
			console.log(row);
		},
		loadFilter:function(rows){
			if(rows.data != undefined)
				return convertsoTree(rows.data);
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

function initBtnBindFun() {
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

function initData() {

}

$(function(){
	//初始化控件
	initComponent();
	//按钮绑定功能
	initBtnBindFun();
	//初始化
	initData();
});