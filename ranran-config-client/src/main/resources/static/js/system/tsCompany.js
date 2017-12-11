function converts(rows){
    function exists(rows, parentNo){
        for(var i=0; i<rows.length; i++){
            if (rows[i].deptParentNo == parentNo) return true;
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
        if(rows.length ==  1){
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

function convertsToTree(rows){
    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
		nodes.push({
			text:row.orgName,
			orgId:row.orgId,
			orgNo:row.orgNo,
			orgName:row.orgName,
			orgParentNo:row.orgParentNo,
			orgParentNameCn:row.orgParentNameCn,
			orgLever:row.orgLever,
			orgStatus:row.orgStatus,
			orgLeadNo:row.orgLeadNo,
			orgLeadName:row.orgLeadName,
			orgTel:row.orgTel,
			orgAreaNo:row.orgAreaNo,
			orgAreaName:row.orgAreaName,
			orgProAreaNo:row.orgProAreaNo,
			orgProAreaName:row.orgProAreaName,
			orgProvince:row.orgProvince,
			orgCity:row.orgCity,
			orgDistrict:row.orgDistrict,
			orgAddress:row.orgAddress,
			orgLon:row.orgLon,
			orgLat:row.orgLat,
			creator:row.creator,
			createTime:row.createTime,
			modifier:row.modifier,
			modifyTime:row.modifyTime,
			recVer:row.recVer
		});
    }
    return nodes;
};
function initComponent(){

    $('#org_province').combobox({
        method: "get",
        //自适应数据高度属性
        // panelHeight:"auto",
        url: "/tsDistrict/selectDistrict.html?distLevel=province",
        valueField:'distCode',
        textField:'distName',
        onLoadSuccess:function (data) {
            if (data.length == 1){
                $(this).combobox('setValue',data[0].distCode);
            }
            return data;
        }
    });
    $('#org_city').combobox({
        method: "get",
        //自适应数据高度属性
        // panelHeight:"auto",
        url: "/tsDistrict/selectDistrict.html?distLevel=city",
        valueField:'distCode',
        textField:'distName',
        onSelect:function(data){
            $('#org_province').combobox('setValue',data.distParentCode);
            $('#org_district').combobox('reload', '/tsDistrict/selectDistrict.html?distLevel=district&distParentCode=' + data.distCode);
        },
        onLoadSuccess:function (data) {
            var oldValue = $(this).combobox('getValue');
            var flag = false;
            for(var i=0; i<data.length; i++){
                if(oldValue == data[i].distCode){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                $(this).combobox('clear');
            }else{
                $(this).combobox('setValue',oldValue);
            }
            if (data.length == 1){
                $(this).combobox('setValue',data[0].distCode);
            }
            return data;
        }
    });
    $('#org_district').combobox({
        method: "get",
        //自适应数据高度属性
        // panelHeight:"auto",
        url: "/tsDistrict/selectDistrict.html?distLevel=district",
        valueField:'distCode',
        textField:'distName',
        onSelect: function(data){
            $('#org_city').combobox('setValue',data.distParentCode);
        },
        onLoadSuccess:function (data) {
            var oldValue = $(this).combobox('getValue');
            var flag = false;
            for(var i=0; i<data.length; i++){
                if(oldValue == data[i].distCode){
                    flag = true;
                    return;
                }
            }
            if(!flag){
                $(this).combobox('clear');
            }
            if (data.length == 1){
                $(this).combobox('setValue',data[0].distCode);
            }
            return data;
        }
    });


    $('#dept_province').combobox({
        method: "get",
        //自适应数据高度属性
        // panelHeight:"auto",
        url: "/tsDistrict/selectDistrict.html?distLevel=province",
        valueField:'distCode',
        textField:'distName',
        onLoadSuccess:function (data) {
            if (data.length == 1){
                $(this).combobox('setValue',data[0].distCode);
            }
            return data;
        }
    });
    $('#dept_city').combobox({
        method: "get",
        //自适应数据高度属性
        // panelHeight:"auto",
        url: "/tsDistrict/selectDistrict.html?distLevel=city",
        valueField:'distCode',
        textField:'distName',
        onSelect:function(data){
            $('#dept_province').combobox('setValue',data.distParentCode);
            $('#dept_district').combobox('reload', '/tsDistrict/selectDistrict.html?distLevel=district&distParentCode=' + data.distCode);
        },
        onLoadSuccess:function (data) {
            var oldValue = $(this).combobox('getValue');
            var flag = false;
            for(var i=0; i<data.length; i++){
                if(oldValue == data[i].distCode){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                $(this).combobox('clear');
            }else{
                $(this).combobox('setValue',oldValue);
            }
            if (data.length == 1){
                $(this).combobox('setValue',data[0].distCode);
            }
            return data;
        }
    });
    $('#dept_district').combobox({
        method: "get",
        //自适应数据高度属性
        // panelHeight:"auto",
        url: "/tsDistrict/selectDistrict.html?distLevel=district",
        valueField:'distCode',
        textField:'distName',
        onSelect: function(data){
            $('#dept_city').combobox('setValue',data.distParentCode);
        },
        onLoadSuccess:function (data) {
            var oldValue = $(this).combobox('getValue');
            var flag = false;
            for(var i=0; i<data.length; i++){
                if(oldValue == data[i].distCode){
                    flag = true;
                    return;
                }
            }
            if(!flag){
                $(this).combobox('clear');
            }
            if (data.length == 1){
                $(this).combobox('setValue',data[0].distCode);
            }
            return data;
        }
    });
    $("#company_tree").tree({
        method: "get",
        url: "/tsCompany/selectByCondition.html",
        onClick:function(node){
            $("#form_resNo").textbox("readonly",true);
            if(node.orgId != undefined){
                $('#org_no').textbox("disable");
            }
            $("#form_company").form("load",node);
            $("#company_dept").tree({
                url:"/tsCompany/loadCompanyDet.html",
                queryParams:{
                    orgId:$("#org_no").textbox("getValue")
                }
            });

        },
        loadFilter:function(rows){
            if(rows.data != undefined)
                return convertsToTree(rows.data);
        }
    });

	$("#company_dept").tree({
		fit:true,
		singleSelect:true,
		cascadeCheck:true,
        onClick:function (node) {
			$('#form_company_dept').form('load',node);
            if(node.orgId != undefined){
                $('#deptNo').textbox("disable");
            }
		},
		loadFilter:function(rows){
			if(rows.data != undefined)
				return converts(rows.data);
		},
		enableHeaderClickMenu: false,        //此属性开启表头列名称右侧那个箭头形状的鼠标左键点击菜单
		enableHeaderContextMenu: true     //此属性开启表头列名称右键点击菜单
	});
};

function btnAddOrgOpt() {
    $('#form_company').form('clear');
    $('#org_no').textbox("enable");

};

function btnSaveOrgOpt() {
    var isValid = $('#form_company').form('validate');
    if (isValid){
        //form表单拼接成Json对象
        var json = '[{'+$("#form_company input").map(function(){
                if($(this).attr("name")!= undefined)
                    return '"'+$(this).attr("name")+'":"'+$(this).val()+'"';
            }).get().join(", ")+'}]';
        $.ajax({
            type: "POST",
            url: "/tsCompany/saveBatch.html",
            data: json,
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                $("#form_company").form("clear");
                $("#form_company_dept").form("clear");
                $('#org_no').textbox("enable");
                $("#company_tree").tree('reload');
                $("#company_dept").tree({'queryParams':{deptId:''}});
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
            }
        });
    }else {
        parent.$.messager.alert("提示信息",'请检查填写信息！');
    }

};
function btnAddCompanyDept() {
    $("#form_company_dept").form("clear");
    $('#deptNo').textbox("enable");

};
function btnSaveCompanyDept() {
    var isValid = $('#form_company_dept').form('validate');
    if (isValid){
        //form表单拼接成Json对象
        var json = '[{'+$("#form_company_dept input").map(function(){
                if($(this).attr("name")!= undefined)
                    return '"'+$(this).attr("name")+'":"'+$(this).val()+'"';
            }).get().join(", ")+'}]';
        $.ajax({
            type: "POST",
            url: "/tsDept/saveBatch.html",
            data: json,
            contentType:"application/json",
            dataType: "json",
            success: function(data){
                $("#form_company_dept").form("clear");
                $('#deptNo').textbox("enable");
                $("#company_dept").tree('reload');
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }
            }
        });
    }else {
        parent.$.messager.alert("提示信息",'请检查填写信息！');
    }
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
	$('#btn_add_org').bind('click', function(){
		btnAddOrgOpt();
	});
	$('#btn_save_org').bind('click', function(){
		btnSaveOrgOpt();
	});
    $('#btn_add_company_dept').bind('click', function(){
        btnAddCompanyDept();
    });
	$('#btn_save_company_dept').bind('click', function(){
        btnSaveCompanyDept();
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
};

$(function(){
	//初始化控件
	initComponent();
	//按钮绑定功能
	btnBindFun();

});