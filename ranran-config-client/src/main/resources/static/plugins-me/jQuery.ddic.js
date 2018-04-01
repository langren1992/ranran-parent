/**
 * Created by zengrui on 2018/3/11.
 */
(function($){

    $.parser.plugins.push("ddicPCD");

    function init(target){
        var opt = $(target).ddicPCD('options');
        var comb_deptProvince = '#'+opt.province;
        var comb_deptCity = '#'+opt.city;
        var comb_deptDistrict = '#'+opt.district;
        var btn_buttonId = '#'+opt.buttonId;

        function nav(_16,dir){
            var _17=$.data(_16,"combogrid");
            var _18=_17.options;
            var _19=_17.grid;
            var _1a=_19.datagrid("getRows").length;
            if(!_1a){
                return;
            }
            var tr=_18.finder.getTr(_19[0],null,"highlight");
            if(!tr.length){
                tr=_18.finder.getTr(_19[0],null,"selected");
            }
            var _1b;
            if(!tr.length){
                _1b=(dir=="next"?0:_1a-1);
            }else{
                var _1b=parseInt(tr.attr("datagrid-row-index"));
                _1b+=(dir=="next"?1:-1);
                if(_1b<0){
                    _1b=_1a-1;
                }
                if(_1b>=_1a){
                    _1b=0;
                }
            }
            _19.datagrid("highlightRow",_1b);
            if(_18.selectOnNavigation){
                _17.remainText=false;
                _19.datagrid("selectRow",_1b);
            }
        };
        var flag = true;
        $(comb_deptProvince).combogrid({
            panelWidth:480,
            idField: 'distCode',
            textField: 'distName',
            queryParams:{
                distLevel:'province'
            },
            pagination:true,
            columns:[[
                {field:'distCode',title:'省编码',width:120},
                {field:'distName',title:'省名称',width:120}
            ]],
            onChange:function (newValue, oldValue) {
                $(comb_deptCity).combogrid({queryParams:{distParentCode:newValue,distLevel:'city'}});
                if(flag){
                    $(comb_deptDistrict).combogrid({queryParams:{distLevel:'district'}});
                }

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
            keyHandler:{
                up:function(e){
                    nav(this,"prev");
                    e.preventDefault();
                },
                down:function(e){
                    nav(this,"next");
                    e.preventDefault();
                },
                enter: function(e){
                    $(this).combogrid("hidePanel");
                },
                query: function(q,e){
                    var combogrid = $.data(this,"combogrid");
                    var gridOpt = combogrid.grid;
                    gridOpt.datagrid("highlightRow",0);
                    gridOpt.datagrid({
                        queryParams:{
                            distLevel:'province',
                            distName:q
                        },
                        onLoadSuccess:function (data){
                            var opt = $(this).datagrid('options');
                            var text = opt.queryParams[opt.textField];
                            if(text != '' && text != undefined){
                                $(this).datagrid('highlightRow',0);
                                if(data.rows.length!=0){
                                    $(this).datagrid('selectRecord',data.rows[0][opt.idField]);
                                }
                            }
                        }
                    });
                }
            }
        });

        $(comb_deptCity).combogrid({
            panelWidth:480,
            idField: 'distCode',
            textField: 'distName',
            queryParams:{
                distLevel:'city'
            },
            pagination:true,
            columns:[[
                {field:'distCode',title:'市编码',width:120},
                {field:'distName',title:'市名称',width:120},
                {field:'distParentCode',title:'省编码',width:120},
                {field:'distParentName',title:'省名称',width:120}
            ]],
            onChange:function (newValue, oldValue) {
                //根据newValue 找出 parentCode
                var dates = $(this).combogrid('grid').datagrid('getData');
                var idField = $(this).combogrid('grid').datagrid('options').idField;
                var record = queryParentCode(dates.rows,idField,newValue);
                flag = false;
                $(comb_deptProvince).combogrid('setValue',record.distParentCode);
                flag = true;
                var dist = $(comb_deptDistrict).combogrid('getValue');
                $(comb_deptDistrict).combogrid('grid').datagrid({queryParams:{distParentCode:newValue,distLevel:'district'}});
                $(this).combogrid('setValue',newValue);
                //通过数据查询选中的记录
                function queryParentCode(array,idField,newValue){
                    var result, len=array.length, pos=0;
                    while(pos<len){
                        if(array[pos][idField] == newValue){//未找到就退出循环完成搜索
                            result = array[pos];//找到就存储索引
                            return result;
                        }
                        pos+=1;//并从下个位置开始搜索
                    }
                    return result;
                }

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
            },
            keyHandler:{
                up:function(e){
                    nav(this,"prev");
                    e.preventDefault();
                },
                down:function(e){
                    nav(this,"next");
                    e.preventDefault();
                },
                enter: function(e){
                    $(this).combogrid("hidePanel");
                },
                query: function(q,e){
                    var combogrid = $.data(this,"combogrid");
                    var grid = combogrid.grid;
                    var gridOpt = grid.datagrid('options');
                    gridOpt.queryParams['distLevel'] = 'city';
                    gridOpt.queryParams['distName'] = q;
                    var province = $(comb_deptProvince).combogrid('getValue');
                    if(province != ''){
                        gridOpt.queryParams['distParentCode'] = province;
                    }
                    grid.datagrid({
                        onLoadSuccess:function (data){
                            var opt = $(this).datagrid('options');
                            var text = opt.queryParams[opt.textField];
                            if(text != '' && text != undefined){
                                $(this).datagrid('highlightRow',0);
                                if(data.rows.length!=0){
                                    $(this).datagrid('selectRecord',data.rows[0][opt.idField]);
                                }
                            }
                        }
                    });
                }
            }
        });

        $(comb_deptDistrict).combogrid({
            panelWidth:480,
            idField: 'distCode',
            textField: 'distName',
            queryParams:{
                distLevel:'district'
            },
            pagination:true,
            columns:[[
                {field:'distCode',title:'区县编码',width:120},
                {field:'distName',title:'区县名称',width:120},
                {field:'distParentCode',title:'市编码',width:120},
                {field:'distParentName',title:'市名称',width:120}
            ]],
            onChange:function (newValue, oldValue) {
                $(this).combogrid("grid").datagrid('selectRecord',newValue);
                var row = $(this).combogrid("grid").datagrid('getSelected');
                if(row != null){
                    $(comb_deptCity).combogrid('setValue',row.distParentCode);
                }else {
                    $(comb_deptProvince).combogrid({queryParams:{distLevel:'province'}});
                    $(comb_deptCity).combogrid({queryParams:{distLevel:'city'}});
                    $(this).combogrid({queryParams:{distLevel:'district'}});
                }
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
            },
            keyHandler:{
                up:function(e){
                    nav(this,"prev");
                    e.preventDefault();
                },
                down:function(e){
                    nav(this,"next");
                    e.preventDefault();
                },
                enter: function(e){
                    $(this).combogrid("hidePanel");
                },
                query: function(q,e){
                    var combogrid = $.data(this,"combogrid");
                    var grid = combogrid.grid;
                    var gridOpt = grid.datagrid('options');
                    gridOpt.queryParams['distLevel'] = 'district';
                    gridOpt.queryParams['distName'] = q;
                    var city = $(comb_deptCity).combogrid('getValue');
                    if(city != ''){
                        gridOpt.queryParams['distParentCode'] = city;
                    }
                    grid.datagrid({
                        queryParams:{
                            distLevel:'district',
                            distName:q
                        },
                        onLoadSuccess:function (data){
                            var opt = $(this).datagrid('options');
                            var text = opt.queryParams[opt.textField];
                            if(text != '' && text != undefined){
                                $(this).datagrid('highlightRow',0);
                                if(data.rows.length!=0){
                                    $(this).datagrid('selectRecord',data.rows[0][opt.idField]);
                                }
                            }
                        }
                    });
                    $(this).combogrid("hidePanel");
                }
            }
        });
        $(btn_buttonId).linkbutton({
            onClick:function () {
                $(comb_deptProvince).combogrid({queryParams:{type:'province'}});
                $(comb_deptCity).combogrid({queryParams:{type:'city'}});
                $(comb_deptDistrict).combogrid({queryParams:{type:'district'}});
            }
        });
    }

    $.fn.ddicPCD = function(options, param){
        if (typeof options == 'string'){
            var method = $.fn.ddicPCD.methods[options];
            if (method){
                return method(this, param);
            } else {
                return this.combobox(options, param);
            }
        }

        options = options || {};
        return this.each(function(){
            var state = $.data(this, 'ddicPCD');
            if (state){
                $.extend(state.options, options);
            } else {
                state = $.data(this, 'ddicPCD', {
                    options: $.extend({}, $.fn.ddicPCD.defaults, $.fn.ddicPCD.parseOptions(this), options)
                });
                init(this);
            }
        });
    };

    $.fn.ddicPCD.methods = {
        options: function(jq){
            return $.data(jq[0], 'ddicPCD').options;
        }
    };

    $.fn.ddicPCD.parseOptions = function(target){
        return $.extend({}, $.fn.combobox.parseOptions(target) ,$.parser.parseOptions(target, [

        ]));
    };

    $.fn.ddicPCD.defaults = $.extend({},$.fn.combobox.defaults,{
        province:'province',
        city:'city',
        district:'district',
        buttonId:'buttonId'
    });
})(jQuery);
