/**
 * Created by zengrui on 2018/3/11.
 */
(function($){



    $.fn.droplist = function(options, param){
        if(typeof options=="string"){
            var method=$.fn.droplist.methods[options];
            if(method){
                return method(this,options);
            }else{
                return this.combo(options,param);
            }
        }
        //初始化combogrid样式
        this.combogrid(options, param);
    };

    $.fn.droplist.methods = {
        options: function(jq){
            return jq.combogrid("options");
        },
        grid:function(jq){
            return $.data(jq[0],"combogrid").grid;
        }
    };

    $.fn.droplist.parseOptions = function (target) {
        return $.extend($.fn.combogrid.parseOptions(target), $.parser.parseOptions(target, []));
    };

    $.fn.droplist.defaults = $.extend($.fn.combogrid.defaults,$.fn.datagrid.defaults,{
        mTable:{name:'',mtAlias:''},
        sTable:[],
        msRelation:[],
        loader:function (param,success,error) {
            var datagrid = $(this).datagrid('options');
            // datagrid.queryParams['mTable'] = datagrid.mTable;
            // if(datagrid.sTable.length != 0 && datagrid.msRelation.length ==0){
            //     $.messager.alert('警告','请填写关联关系！','error');
            //     return false;
            // }
            // datagrid.queryParams['sTable'] = datagrid.sTable;
            // datagrid.queryParams['sTable'] = datagrid.msRelation;
            // datagrid.queryParams['columns'] = datagrid.columns;
            // datagrid.queryParams[datagrid.idField] = param.idField;
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


    $.parser.plugins.push("droplist");
})(jQuery);
