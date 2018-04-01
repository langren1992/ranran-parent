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
                deptLevel:row.deptLevel,
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
                    deptLevel:row.deptLevel,
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

//部门树形请求路径
var url_tree_dept = './tsDept/selectDept.html';
//部门树形请求路径
var url_opt_dept = './tsDept/updateDepts.html';
//部门树形结构
var tree_dept = '#tree_dept';
//部门表单
var form_dept = '#form_dept';
var form_deptNo = '#form_deptNo';
var form_deptName = '#form_deptName';
var combo_deptStatus = '#combo_deptStatus';
var combo_deptLevel = '#combo_deptLevel';
var comb_deptProvince = '#comb_deptProvince';
var comb_deptCity = '#comb_deptCity';
var comb_deptDistrict = '#comb_deptDistrict';
//新增部门按钮
var btn_add_dept = '#btn_add_dept';
//更新部门按钮
var btn_update_dept = '#btn_update_dept';
//删除部门按钮
var btn_delete_dept = '#btn_delete_dept';

function initComponent(){
    $(combo_deptStatus).combobox({
        panelHeight:"auto",
        valueField: 'tdCode',
        textField: 'tdName',
        queryParams:{
            tdKey:'DEPT_STARUS',
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

    $(combo_deptLevel).combobox({
        panelHeight:"auto",
        valueField: 'tdCode',
        textField: 'tdName',
        queryParams:{
            tdKey:'DEPT_LEVEL',
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
    var dv;
    var city;
    $(comb_deptProvince).combobox({
        valueField: 'distCode',
        textField: 'distName',
        queryParams:{
            distLevel:'province'
        },
        onSelect:function (record) {
            $(comb_deptCity).combobox({queryParams:{distLevel:'city',distParentCode:record.distCode}});
            $(comb_deptDistrict).combobox('setValue','');

        },
        loader: function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : './tsDistrict/getProvCityDist.html',
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result.data);
                }
            });
        }
    });

    var flag = true;
    $(comb_deptCity).combobox({
        valueField: 'distCode',
        textField: 'distName',
        queryParams:{
            distLevel:'city'
        },
        onSelect:function (record) {
            var dv = $(comb_deptDistrict).combobox('getValue');
            if(dv == ""){
                $(comb_deptDistrict).combobox({queryParams:{distLevel:'district',distParentCode:record.distCode}});
            }
            $(comb_deptProvince).combobox('setValue',record.distParentCode);
        },
        loader: function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : './tsDistrict/getProvCityDist.html',
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    city = result.data;
                    success(result.data);
                }
            });
        },
        onChange:function (newValue,oldValue) {
            //区县
            var dv = $(comb_deptDistrict).combobox('getValue');
            $(comb_deptDistrict).combobox({queryParams:{distLevel:'district',distParentCode:newValue}});
            $(comb_deptDistrict).combobox('setValue',dv);
        }
    });

    $(comb_deptDistrict).combobox({
        valueField: 'distCode',
        textField: 'distName',
        queryParams:{
            distLevel:'district'
        },
        onSelect:function (record) {
            $(comb_deptCity).combobox('setValue',record.distParentCode);
        },
        loader: function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : './tsDistrict/getProvCityDist.html',
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result.data);
                }
            });
        }
        ,
        onLoadSuccess:function () {
            var dates = $(comb_deptDistrict).combobox('getData');
            //区县编码
            var dv = $(comb_deptDistrict).combobox('getValue');
            var flag = true;
            for (var i = 0; i < dates.length; i++){
                if (dates[i].distCode == dv){
                    flag = false;
                }
            }
            if(flag){
                $(comb_deptDistrict).combobox('setValue','');
            }
        }
    });

	$(tree_dept).tree({
        url:url_tree_dept,
        method:'post',
		fit:true,
		singleSelect:true,
        onClick:function (row) {
			$(form_dept).form('load',row);
		},
		loadFilter:function(rows){
			if(rows.data != undefined)
				return convertsoTree(rows.data);
		}
	});

};

/**
 * 新增数据
 */
function btnAddDept() {
    $(form_dept).form('reset');
    var deptParent = $(tree_dept).tree('getSelected');
    if (deptParent!=undefined){
        $(form_dept).form('load',{deptParentNo:deptParent.deptNo,deptParentNameCn:deptParent.deptName});
    }else {
        $(form_dept).form('load',{});
    }
    $(form_deptNo).textbox({"required":true,"readonly":false});
    $(form_deptName).textbox({"required":true,"readonly":false});

};

function btnUpdateDept() {
    var flag = $(form_dept).form('validate');
    if(flag){
        var json = '[{'+$("#form_dept input").map(function(){
            if($(this).attr("name")!= undefined)
                return '"'+$(this).attr("name")+'":"'+$(this).val()+'"';
        }).get().join(", ")+'}]';
        $.ajax({
            type: "POST",
            url: url_opt_dept,
            data: json,
            contentType:"application/json;charset=utf-8",
            dataType: "json",
            success: function(data){
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }else {
                    parent.$.messager.alert("异常提示",data.message);
                }
                reloadComponent();
            }
        });
    }else {
        parent.$.messager.alert('提示信息','请检查数据！');
    }
};

function reloadComponent() {
    $(tree_dept).tree('reload');
    $(form_dept).form('reset');
};

function btnDeleteDept() {

};

function initBtnBindFun() {
	$(btn_add_dept).bind('click', function(){
		btnAddDept();
	});
	$(btn_update_dept).bind('click', function(){
		btnUpdateDept();
	});

	$(btn_delete_dept).bind('click', function(){
		btnDeleteDept();
	});
}

$(function(){
	//初始化控件
	initComponent();
	//按钮绑定功能
	initBtnBindFun();
});