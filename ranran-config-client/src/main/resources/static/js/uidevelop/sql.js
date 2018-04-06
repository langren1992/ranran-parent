
function initDroplist() {
    $('#form_roleNo').droplist({
        panelWidth:'430',
        idField : 'dist_code',
        textField : 'dist_name',
        mainTable:{tableName:'ts_district'},
        conditions:[
            {field:'dist_level',value:'city',logicOpt:'and',compareOpt:'='}
        ],
        columns : [[
            {field:'dist_code',title: '部门编码' ,align:'center',width: 150},
            {field:'dist_name',title: '部门名称',align:'center',width: 150},
            {field:'dist_level',title: '部门名称',align:'center',width: 150}
        ]]
    });
}

/**
 * 初始化页面及控件
 */
$(function () {
    //初始化下拉列表组件
    initDroplist();
});