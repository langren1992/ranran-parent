function convert(rows){
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
};
var dg_dict = '#dg_dict';
var dg_dict_value = '#dg_dict_value';
var toolbar_dict = '#toolbar_dict';
var toolbar_dict_value = '#toolbar_dict_value';
var url_dg_dict = './tsDict/selectTsDict.html';
var url_opt_dict = './tsDict/updateTsDicts.html';
var btn_add_dict = '#btn_add_dict';
var btn_save_dict = '#btn_save_dict';
var btn_delete_dict = '#btn_delete_dict';
var btn_add_dict_value = '#btn_add_dict_value';
var btn_delete_dict_value = '#btn_delete_dict_value';
//部门树形请求路径
var url_tree_dict_resource = './tsDict/selectTsDictTsResource.html';
//部门树形结构
var tree_dict_resource = '#tree_dict_resource';



var editRow = undefined;
var editRowValue = undefined;
function initComponent(){

    $(tree_dict_resource).tree({
        url:url_tree_dict_resource,
        method:'post',
        fit:true,
        singleSelect:true,
        onClick:function (row) {
            $(dg_dict).datagrid({queryParams:{tdResCode:row.resNo}});
            $(dg_dict_value).datagrid({queryParams:{tdResCode:'A'}});
            editRow = undefined;
        },
        loadFilter:function(rows){
            if(rows.data != undefined)
                return convert(rows.data);
        }
    });

	$(dg_dict).datagrid({
		fit:true,
        rownumbers:true,
		singleSelect:true,
		toolbar:toolbar_dict,
		columns: [[
			{ field: 'tdId', title: '资源ID',hidden:true},
            { field: 'tdCode', title: '编号', width: 150, sortable: true,editor:{type:'textbox',options:{required:true}} },
            { field: 'tdName', title: '名称', width: 120, sortable: true,editor:{type:'textbox',options:{required:true}}},
			{ field: 'tdKey', title: '索引', width: 120, sortable: true,editor:{type:'textbox',options:{required:true}}},
            { field: 'tdType', title: '字典类型', width: 120, sortable: true,formatter:function(value,row){
                if(value==1){
                    return '索引';
                }else if(value == 2){
                    return '值';
                }}
            },
			{ field: 'tdResCode', title: '所属资源编码', width: 120, sortable: true},
			{ field: 'tdResName', title: '所属资源名称', width: 120, sortable: true}
		]],
        queryParams:{
            tdResCode:'A'
        },
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            editRow = undefined;
        },
        onBeginEdit:function (i, row) {
            if(row.tdId != "" && row.tdId != undefined){
                var eg =$(dg_dict).datagrid('getEditor',{index:i,field:'tdCode'});
                $(eg.target).textbox({"readonly":true});
                var e =$(dg_dict).datagrid('getEditor',{index:i,field:'tdKey'});
                $(e.target).textbox({"readonly":true});
            }
            editRow = i;
        },
        onClickRow: function (rowIndex, row) {
            //双击开启编辑行
            if (editRow != undefined) {
                $(dg_dict).datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $(dg_dict).datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
            $(dg_dict_value).datagrid({queryParams:{tdKey:row.tdKey,tdType:2}});
        },
        loader : function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : url_dg_dict,
                dataType : 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                data : JSON.stringify(param),
                success : function(result) {
                    success(result);
                }
            });
        }
	});


    $(dg_dict_value).datagrid({
        fit:true,
        rownumbers:true,
        singleSelect:true,
        toolbar:toolbar_dict_value,
        columns: [[
            { field: 'tdId', title: '资源ID',hidden:true},
            { field: 'tdCode', title: '索引编码', width: 150, sortable: true,editor:{type:'textbox',options:{required:true}} },
            { field: 'tdName', title: '索引名称', width: 120, sortable: true,editor:{type:'textbox',options:{required:true}}},
            { field: 'tdKey', title: '索引', width: 120, sortable: true},
            { field: 'tdType', title: '字典类型', width: 120, sortable: true,formatter:function(value,row){
                if(value==1){
                    return '索引';
                }else if(value == 2){
                    return '值';
                }}
            }
        ]],
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            editRowValue = undefined;
        },
        onBeginEdit:function (i, row) {
            editRowValue = i;
            // if(row.tdCode != "" && row.tdCode != undefined){
            //     var eg =$(dg_dict_value).datagrid('getEditor',{index:i,field:'tdCode'});
            //     $(eg.target).textbox({"readonly":true});
            // }
        },
        queryParams:{
            tdResCode:'A'
        },
        onClickRow: function (rowIndex, rowData) {
            //双击开启编辑行
            if (editRowValue != undefined) {
                $(dg_dict_value).datagrid("endEdit", editRowValue);
            }
            if (editRowValue == undefined) {
                $(dg_dict_value).datagrid("beginEdit", rowIndex);
                editRowValue = rowIndex;
            }
        },
        loader : function(param, success, error) {
            $.ajax({
                type : 'POST',
                url : url_dg_dict,
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

function btnAddTsDict() {
    var dictRresource = $(tree_dict_resource).tree("getSelected");
    if (dictRresource == null){
        parent.$.messager.alert("提示信息",'请选择资源数据！',"error");
    }else {
        if(dictRresource.children != null){
            parent.$.messager.alert("提示信息",'请选择子资源数据！',"error");
        }else{
            //添加列表的操作按钮添加，修改，删除等
            //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
            if (editRow != undefined) {
                $(dg_dict).datagrid("endEdit", editRow);
            }
            //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
            if (editRow == undefined) {
                $(dg_dict).datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {
                        tdResCode:dictRresource.resNo,
                        tdResName:dictRresource.resName,
                        //字典类型为索引
                        tdType:1,
                    }
                });
                //将新插入的那一行开户编辑状态
                $(dg_dict).datagrid("beginEdit", 0);
                //给当前编辑的行赋值
                editRow = 0;
            }
        }
    }
};

function btnSaveTsDict() {
    /**
     * 没有编辑列直接保存的情况
     */
    if (editRow != undefined){
        $(dg_dict).datagrid("endEdit",editRow);
    }
    if (editRowValue != undefined){
        $(dg_dict_value).datagrid("endEdit",editRowValue);
    }
    var dict = $(dg_dict).datagrid("getChanges","inserted").concat($(dg_dict).datagrid("getChanges","updated"));
    var dictValue = $(dg_dict_value).datagrid("getChanges","inserted").concat($(dg_dict_value).datagrid("getChanges","updated"));
    var json = JSON.stringify(dict.concat(dictValue));
    if(dict.length != 0 && dictValue.length !=0){
        $.ajax({
            type: "POST",
            url: url_opt_dict,
            data: json,
            contentType:"application/json;charset=utf-8",
            dataType: "json",
            success: function(data){
                //保存完成后直接刷新 列标识
                editRow = undefined;
                editRowValue = undefined;
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }else {
                    parent.$.messager.alert("异常提示",data.message);
                }
            }
        });
    }else {
        parent.$.messager.alert("提示信息",'无变化数据！');
    }
};

function btnDeleteTsDict() {

};

function btnAddDictValue() {
    if (editRow != undefined){
        $(dg_dict).datagrid("endEdit",editRow);
    }
    var dict = $(dg_dict).datagrid("getSelected");
    if (dict == null){
        parent.$.messager.alert("提示信息",'请选择索引数据！',"error");
    }else {

        //添加列表的操作按钮添加，修改，删除等
        //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
        if (editRowValue != undefined) {
            $(dg_dict_value).datagrid("endEdit", editRowValue);
        }
        //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
        if (editRowValue == undefined) {
            $(dg_dict_value).datagrid("insertRow", {
                index: 0, // index start with 0
                row: {
                    tdParentCode:dict.tdCode,
                    tdParentName:dict.tdName,
                    tdKey:dict.tdKey,
                    tdResCode:dict.tsResCode,
                    tsResName:dict.tsResName,
                    //字典类型为索引
                    tdType:2,
                }
            });
            //将新插入的那一行开户编辑状态
            $(dg_dict_value).datagrid("beginEdit", 0);
            //给当前编辑的行赋值
            editRowValue = 0;
        }

    }
};

function btnDeleteDictValue() {

};



function btnBindFun() {
	$(btn_add_dict).bind('click', function(){
		btnAddTsDict();
	});
	$(btn_save_dict).bind('click', function(){
		btnSaveTsDict();
	});
	$(btn_delete_dict).bind('click', function(){
		btnDeleteTsDict();
	});

	$(btn_add_dict_value).bind('click', function(){
        btnAddDictValue();
    });
    $(btn_delete_dict_value).bind('click', function(){
        btnDeleteDictValue();
    });
}

$(function(){
	//初始化控件
	initComponent();
	//按钮绑定功能
	btnBindFun();

});