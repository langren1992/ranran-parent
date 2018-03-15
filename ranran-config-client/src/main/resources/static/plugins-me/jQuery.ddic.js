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
        var dv;
        var city;
        $(comb_deptProvince).combogrid({
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
        $(comb_deptCity).combogrid({
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

        $(comb_deptDistrict).combogrid({
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
        $(btn_buttonId).linkbutton({
            onClick:function () {
                $(comb_deptProvince).combobox({queryParams:{type:'province'}});
                $(comb_deptCity).combobox({queryParams:{type:'city'}});
                $(comb_deptDistrict).combobox({queryParams:{type:'area'}});
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
