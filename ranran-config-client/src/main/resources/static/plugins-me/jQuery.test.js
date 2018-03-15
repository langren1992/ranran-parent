/**
 * Created by zengrui on 2018/3/11.
 */
(function($){

    $.parser.plugins.push("test");

    function init(target){
        var opts = $.data(target, 'test').options;
        $(target).css({"background-color":"yellow","width": "100px","height": "100px"});
    }

    $.fn.test = function(options, param){
        if (typeof options == 'string'){
            return $.fn.test.methods[options](this, param);
        }

        options = options || {};
        return this.each(function(){
            var state = $.data(this, 'test');
            if (state){
                $.extend(state.options, options);
            } else {
                state = $.data(this, 'test', {
                    options: $.extend({}, $.fn.test.defaults, $.fn.test.parseOptions(this), options)
                });
                init(this);
            }
        });
    };

    $.fn.test.methods = {
        options: function(jq){
            return $.data(jq[0], 'test').options;
        }
    };

    $.fn.test.parseOptions = function(target){
        return $.extend({}, $.parser.parseOptions(target, [

        ]));
    };

    $.fn.test.defaults = {
        name:'test'
    };
})(jQuery);
