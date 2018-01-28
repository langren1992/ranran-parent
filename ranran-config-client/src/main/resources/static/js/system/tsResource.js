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
}


/**
 * Created by Administrator on 2016/10/17 0017.
 */
function initComponent(){
    var formResStatus = $("#form_resStatus");
    formResStatus.combobox({
        panelHeight:"auto",
        valueField: 'tdCode',
        textField: 'tdName',
        queryParams:{
            tdKey:'RESOURCE_STATUS',
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

    $("#res_btn").datagrid({
        fit:true,
        method: "get",
        idField: 'resNo',
        enableRowContextMenu: true,         //此属性开启表头列名称右键点击菜单
        pagingMenu: { submenu: false},//开启行右键菜单的翻页功能，此属性可丰富配置，详情见 API 文档
        exportMenu:{submenu: false},
        toolbar:"#tb",
        columns: [[
            { field: 'resNo', title: '编码', width: 120, sortable: true,editor:{type:'textbox',options:{required:true}} },
            { field: 'resName', title: '名称', width: 120, sortable: true,editor:'textbox'},
            { field: 'resPermission', title: '权限项', width: 120, sortable: true,editor:'textbox' },
            { field: 'resDescribe', title: '描述', width: 160, sortable: true,editor:'textbox' },
            { field: 'resId', title: '资源ID',hidden:true},
            { field: 'resParentNo', title: '父编码', width: 120, sortable: true,hidden:true},
            { field: 'resParentName', title: '父名称', width: 120, sortable: true,hidden:true},
            { field: 'resType', title: '类型', width: 120,hidden:true}
        ]],
        /*
         * datagrid中的loadFilter属性会影响loadData使用效果，每次远程赋值和本地赋值都会走loadFilter，需要从服务端进行修改
         * */
        // loadFilter:function (data) {
        //     data.total = data.data.length;
        //     data.rows =  data.data;
        //     return data;
        // },
        onClickCell: onClickCell
    });


    $("#res_menu").tree({
        method: "get",
        url: "./res/resList.html",
        queryParams:{
            resType: 1
        },
        onClick:function(node){
            $("#form_resNo").textbox("readonly",true);
            $("#form_menu").form("load",node);
            $("#res_btn").datagrid({
                url:"./res/resBtnList.html",
                queryParams:{
                    resType:2,
                    resParentNo:node.resNo
                }
            });

        },
        loadFilter:function(rows){
            return convert(rows.data);
        }
    });


    $("#form_createTime").datebox({
        formatter:function (date) {
            return  date.pattern("yyyy-MM-dd");
        },
        parser:function (s) {
            if (!isNaN(s) && s!=""){
                return new Date(s);
            } else {
                return new Date();
            }
        }
    })
};

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#res_btn').datagrid('validateRow', editIndex)){
        $('#res_btn').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
};

function onClickCell(index, field){
    if (editIndex != index){
        if (endEditing()){
            $('#res_btn').datagrid('selectRow', index).datagrid('beginEdit', index);
            var ed = $('#res_btn').datagrid('getEditor', {index:index,field:field});
            if (ed){
                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
            }
            editIndex = index;
        } else {
            setTimeout(function(){
                $('#res_btn').datagrid('selectRow', editIndex);
            },0);
        }
    }
};


function btnAddResOpt(){
    if (endEditing()){
        $('#res_btn').datagrid('appendRow',{resParentNo:$("#res_menu").tree("getSelected").resNo,resParentName:$("#res_menu").tree("getSelected").resName,resType:2});
        editIndex = $('#res_btn').datagrid('getRows').length-1;
        $('#res_btn').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
    }
};

function btnRemoveOpt(){
    if (editIndex == undefined){return}
    $('#res_btn').datagrid('cancelEdit', editIndex)
        .datagrid('deleteRow', editIndex);
    editIndex = undefined;
};

function btnAddOpt() {
    $("#form_menu").form("reset");
    $("#form_resNo").textbox({"required":true,"readonly":false});
    $("#form_resName").textbox({"required":true,"readonly":false});
    $("#form_menu").form("load",{
        resParentNo:$("#res_menu").tree("getSelected").resNo,
        resParentName:$("#res_menu").tree("getSelected").resName
    });

};

function btnSaveOpt() {
    if($("#form_resNo").val() != ""){
        /**
         * 没有编辑列直接保存的情况
         */
        if (editIndex != undefined){
            $('#res_btn').datagrid("endEdit",editIndex);
        }
        var json = '{"tsResource":{'+$("#form_menu input").map(function(){
            if($(this).attr("name")!= undefined)
                return '"'+$(this).attr("name")+'":"'+$(this).val()+'"';
        }).get().join(", ")+'},"resBtnList":'+JSON.stringify($('#res_btn').datagrid("getChanges","inserted").concat($('#res_btn').datagrid("getChanges","updated")))+'}';
        $.ajax({
            type: "POST",
            url: "./res/updateRes.html",
            data: json,
            contentType:"application/json;charset=utf-8",
            dataType: "json",
            success: function(data){
                //保存完成后直接刷新 列标识
                editIndex = 0;
                $("#form_menu").form("reset");
                $("#res_menu").tree("reload");
                $("#res_btn").datagrid('loadData',{total:0,rows:[]});
                if(data.success){
                    parent.$.messager.alert("提示信息",data.message);
                }else {
                    parent.$.messager.alert("异常提示",data.message);
                }
            },
            error:function (message) {
                console.log(message);
            }
        });
    }

};
function btnDeleteOpt() {
    $("#res_btn").datagrid('loadData',{dataList:{total:0,rows:[]}});
};

function btnBind() {
    $("#btn_add").bind('click', function(){
        btnAddOpt();
    });
    // $("#btn_edite").bind('click', function(){
    //     btnEditeOpt();
    // });
    $("#btn_save").bind('click', function(){
        btnSaveOpt();
    });
    $("#btn_delete").bind('click', function(){
        btnDeleteOpt();
    });
    //行新增按钮
    $("#btn_addBtn").bind("click",function () {
        btnAddResOpt();
    });
    $("#btn_removeBtn").bind("click",function () {
        btnRemoveOpt();
    });
};
(function ($) {

    $(function(){
        initComponent();
        btnBind();
    });
})(jQuery);