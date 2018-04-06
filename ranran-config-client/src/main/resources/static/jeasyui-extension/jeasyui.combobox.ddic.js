/**
 * @description ddic组件
 * Created by zengrui on 2018/3/11.
 */
(function($){
    //注册组件
    $.parser.plugins.push("ddic");

    //初始化组件
    $.fn.ddic = function (options, param) {
        if (typeof options == 'string'){
            var method = $.fn.ddic.methods[options];
            if (method){
                return method(this, param);
            } else {
                return this.combobox(options, param);
            }
        }
        //初始化combobox样式
        this.combobox(options, param);
    };

    //继承combobox的options属性
    $.fn.ddic.methods = {
        options: function(jq){
            return jq.combobox('options');
        }
    };

    //创建并继承默认属性
    $.fn.ddic.defaults = $.extend($.fn.combobox.defaults,{
        panelHeight:'auto',
        textField : 'tdName',
        valueField :'tdCode',
        loader:function (param,success,error) {
            $.ajax({
                type: 'post',
                url: ddicUrl,
                data: JSON.stringify(param),
                dataType: 'json',
                contentType : 'application/json;charset=utf-8', // 设置请求头信息
                success: function(data){
                    if(data.data!=0){
                        success(data.data);
                    }
                },
                error: function(){
                    error.apply(this, arguments);
                }
            });
        }
    });
})(jQuery);
