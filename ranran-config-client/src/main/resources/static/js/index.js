/**
 * 创建iframe
 * @param url
 * @returns {string}
 */
function createFrame(url) {
    var s = '<iframe scrolling="no" frameborder="0"  src=".'+url+'" style="width:100%;height:100%;"></iframe>';
    return s;
}

/**
 * 构建树形数据
 * @param rows
 * @returns {Array}
 */
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


function openContent(node){
    var tabsNode = $('#indexTab');
    var title = node.text;
    var url = node.resUrl;
    if (!tabsNode.tabs('exists', title)) {
        tabsNode.tabs('add', {
            title: title,
            border:false,
            bodyCls:'noflow',
            fit:true,
            content: createFrame(url),
            closable: true,
            tools:[{
                iconCls:'icon-mini-refresh',
                handler:function(){
                    //刷新
                    var tab = tabsNode.tabs('getSelected');
                    tabsNode.tabs('update', {
                        tab: tab,
                        options: {
                            title: title
                        }
                    });

                }
            }]
        });
        var tabs = tabsNode.tabs().tabs('tabs');
        for(var i=0; i<tabs.length; i++){
            tabs[i].panel('options').tab.unbind().bind('mouseenter',{index:i},function(e){
                tabsNode.tabs('select', e.data.index);
            });
        }
    }
    tabsNode.tabs('select', title);

};

function tabBindMenu() {
    var indexTab = $("#indexTab");
    var tabMenu = $('#tabMenu');
    indexTab.tabs({
        onContextMenu:function(e, title,index){
            e.preventDefault();
            if(index>0){
                tabMenu.menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data("tabTitle", title);
            }
        }
    });
    var tabs = indexTab.tabs().tabs('tabs');
    for(var i=0; i<tabs.length; i++){
        tabs[i].panel('options').tab.unbind().bind('mouseenter',{index:i},function(e){
            indexTab.tabs('select', e.data.index);
        });
    }
};

//删除Tabs
function closeTab(menu, type) {
    var tabsNode = $("#indexTab");
    var allTabs = $("#indexTab").tabs('tabs');
    var allTabtitle = [];
    $.each(allTabs, function (i, n) {
        var opt = $(n).panel('options');
        if (opt.closable)
            allTabtitle.push(opt.title);
    });
    var curTabTitle = $(menu).data("tabTitle");
    var curTabIndex = tabsNode.tabs("getTabIndex", tabsNode.tabs("getTab", curTabTitle));
    if (type == 1){
        //关闭当前
        tabsNode.tabs("close", curTabIndex);
        return false;
    } else if (type == 2){
        //全部关闭
        for (var i = 0; i < allTabtitle.length; i++) {
            tabsNode.tabs('close', allTabtitle[i]);
        }
    } else if (type == 3){
        //除此之外全部关闭
        for (var i = 0; i < allTabtitle.length; i++) {
            if (curTabTitle != allTabtitle[i])
                tabsNode.tabs('close', allTabtitle[i]);
        }
        tabsNode.tabs('select', curTabTitle);
    } else if (type == 4){
        //当前侧面右边
        for (var i = curTabIndex; i < allTabtitle.length; i++) {
            tabsNode.tabs('close', allTabtitle[i]);
        }
        tabsNode.tabs('select', curTabTitle);
    } else if (type == 5){
        //当前侧面左边
        for (var i = 0; i < curTabIndex - 1; i++) {
            tabsNode.tabs('close', allTabtitle[i]);
        }
        tabsNode.tabs('select', curTabTitle);
    } else if (type == 6){
        //刷新
        tabsNode.tabs("getTab", curTabTitle).panel("refresh");
    }
}

function tabMenuBindClose() {
    var tabRightCloseMenu = $("#tabMenu");
    //右键菜单click
    $("#tabMenu").menu({
        onClick : function (item) {
            closeTab(this, item.name);
        }
    });
}


function fillTreeMenuData() {
    /**
     * 获取菜单树数据
     */
    $.get("./menu.html", null, function(data) {
        data = convert(data.data);
        if(data) {
            var selected ;
            for (var i = 0,length = data.length;i < length;i++){
                var item = data[i];
                if (i == 0) {
                    selected = true;
                }
                // Accordion 折叠面板
                $('#navMenu').accordion('add', {
                    title: item.text,
                    content: "<ul id='menu_accordion_tree_" + item.resId + "'></ul>",
                    selected: selected
                });
                // 树形菜单
                $('#menu_accordion_tree_' + item.resId).tree({
                    data: item.children,
                    onClick: function(node) {
                        if(node.isLeaf){
                            openContent(node);
                        }
                    }
                });
                selected = false;
            }
        }
    }, 'json');
};

updateCurrentTab = function(index){
    var mainTabs = $("#indexTab");
    var currenTab = mainTabs.tabs("getTab", index);
    var opts = mainTabs.tabs("getTab", index).panel("options");
    currenTab.panel('refresh');
    window.location.hash = opts.href ? opts.href : "";
};

/**
 * 初始化Tabs
 */
function initTabs() {
    /**
     * tab绑定菜单
     */
    tabBindMenu();
    /**
     * tab右键关闭
     * */
    tabMenuBindClose();
};
/**
 * 初始化树形菜单
 */
function initTreeMenu() {
    /**
     * 填充菜单树数据
     * */
    fillTreeMenuData();
};
/**
 * 绑定按钮方法
 */
function bindBtnFun(){
    //退出按钮
    var logout = $('#logout');
    logout.bind('click',function () {
        location.href ='./logout.html';
    });
};
$(function(){
    /**
     * 初始化Tabs
     */
    initTabs();
    /**
     * 初始化菜单树
     */
    initTreeMenu();
    /**
     * 绑定按钮方法
     */
    bindBtnFun();
});