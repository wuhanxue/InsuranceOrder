var isSearch=false;
var tolalRecord;//总记录数
var totalPage;//总页数
 var fName;//方法名称
 var data;//查询从查询页面给的对象

function getData(data1) {
    data=data1;
}
 /*传给方法名*/
function initFName(functionName) {
    fName=functionName;
}

/*传递查询信息*/
function initisSearch(isSearch1){
    isSearch=isSearch1;
}

    (function($) {
        $.fn.paginate = function(options) {
            var opts = $.extend({}, $.fn.paginate.defaults, options);
            return this.each(function() {
                $this = $(this);
                var o = $.meta ? $.extend({}, opts, $this.data()) : opts;
                var selectedpage = o.start;
                $.fn.draw(o,$this,selectedpage);
            });
        };
        var outsidewidth_tmp = 0;
        var insidewidth 	 = 0;
        var bName = navigator.appName;
        var bVer = navigator.appVersion;
        if(bVer.indexOf('MSIE 7.0') > 0)
            var ver = "ie7";
        $.fn.paginate.defaults = {
            count 		: 5,
            start 		: 12,
            display  	: 5,
            border					: true,
            border_color			: '#fff',
            text_color  			: '#8cc59d',
            background_color    	: 'black',
            border_hover_color		: '#fff',
            text_hover_color  		: '#fff',
            background_hover_color	: '#fff',
            rotate      			: true,
            images					: true,
            mouse					: 'slide',
            onChange				: function(){return false;}
        };
        $.fn.draw = function(o,obj,selectedpage){
            if(o.display > o.count)
                o.display = o.count;
            $this.empty();
            if(o.images){
                var spreviousclass 	= 'jPag-sprevious-img';
                var previousclass 	= 'jPag-previous-img';
                var snextclass 		= 'jPag-snext-img';
                var nextclass 		= 'jPag-next-img';
            }
            else{
                var spreviousclass 	= 'jPag-sprevious';
                var previousclass 	= 'jPag-previous';
                var snextclass 		= 'jPag-snext';
                var nextclass 		= 'jPag-next';
            }
            var _first		= $(document.createElement('a')).addClass('jPag-first').html('First');

            if(o.rotate){
                if(o.images) var _rotleft	= $(document.createElement('span')).addClass(spreviousclass);
                else var _rotleft	= $(document.createElement('span')).addClass(spreviousclass).html('&laquo;');
            }

            var _divwrapleft	= $(document.createElement('div')).addClass('jPag-control-back');
            _divwrapleft.append(_first).append(_rotleft);

            var _ulwrapdiv	= $(document.createElement('div')).css('overflow','hidden');
            var _ul			= $(document.createElement('ul')).addClass('jPag-pages')
            var c = (o.display - 1) / 2;
            var first = selectedpage - c;
            var selobj;
            for(var i = 0; i < o.count; i++){
                var val = i+1;
                if(val == selectedpage){
                    var _obj = $(document.createElement('li')).html('<span  id="clicked"   class="jPag-current">'+val+'</span>');
                    selobj = _obj;
                    _ul.append(_obj);
                }
                else{
                    var _obj = $(document.createElement('li')).html('<a onclick="findPageumber(this,fName)">'+ val +'</a>');/*onclick="findPageumber(this,fName)"*/
                    _ul.append(_obj);
                }
            }

            _ulwrapdiv.append(_ul);

            if(o.rotate){
                if(o.images) var _rotright	= $(document.createElement('span')).addClass(snextclass);
                else var _rotright	= $(document.createElement('span')).addClass(snextclass).html('&raquo;');
            }

            var _last		= $(document.createElement('a')).addClass('jPag-last').html('Last');
            var _divwrapright	= $(document.createElement('div')).addClass('jPag-control-front');
            _divwrapright.append(_rotright).append(_last);


            /*添加跳转按钮*/
            var _jump		= $(document.createElement('button')).html('跳转');
            // var _divwrapright	= $(document.createElement('div')).addClass('jPag-control-front');
            // _divwrapright.append(_rotright).append(_jump);

            var _inputJump="<input style='width: 30px'>&nbsp;&nbsp;&nbsp;";
            // var _divwrapright	= $(document.createElement('div')).addClass('jPag-control-front');
            // _divwrapright.append(_rotright).append(_inputJump);

            // var currentPage="<span style='color:black;'>当前第</span>"+"<span id='currentPage' style='color:black;' >1</span>" +"<span style='color:black;'>页</span>"
            // _divwrapright.append(_rotright).append(currentPage);
            //append all:
            $this.addClass('jPaginate').append(_divwrapleft).append(_ulwrapdiv).append(_divwrapright);

            if(!o.border){
                if(o.background_color == 'none') var a_css 				= {'color':o.text_color};
                else var a_css 											= {'color':o.text_color,'background-color':o.background_color};
                if(o.background_hover_color == 'none')	var hover_css 	= {'color':o.text_hover_color};
                else var hover_css 										= {'color':o.text_hover_color,'background-color':o.background_hover_color};
            }
            else{
                if(o.background_color == 'none') var a_css 				= {'color':o.text_color,'border':'1px solid '+o.border_color};
                else var a_css 											= {'color':o.text_color,'background-color':o.background_color,'border':'1px solid '+o.border_color};
                if(o.background_hover_color == 'none')	var hover_css 	= {'color':o.text_hover_color,'border':'1px solid '+o.border_hover_color};
                else var hover_css 										= {'color':o.text_hover_color,'background-color':o.background_hover_color,'border':'1px solid '+o.border_hover_color};
            }

            $.fn.applystyle(o,$this,a_css,hover_css,_first,_ul,_ulwrapdiv,_divwrapright);
            //calculate width of the ones displayed:
            var outsidewidth = outsidewidth_tmp - _first.parent().width() -3;
            if(ver == 'ie7'){
                _ulwrapdiv.css('width',outsidewidth+72+'px');
                _divwrapright.css('left',outsidewidth_tmp+6+72+'px');
            }
            else{
                _ulwrapdiv.css('width',outsidewidth+'px');
                _divwrapright.css('left',outsidewidth_tmp+6+'px');
            }

            if(o.rotate){
                _rotright.hover(
                    function() {
                        thumbs_scroll_interval = setInterval(
                            function() {
                                var left = _ulwrapdiv.scrollLeft() + 1;
                                _ulwrapdiv.scrollLeft(left);
                            },
                            20
                        );
                    },
                    function() {
                        clearInterval(thumbs_scroll_interval);
                    }
                );
                _rotleft.hover(
                    function() {
                        thumbs_scroll_interval = setInterval(
                            function() {
                                var left = _ulwrapdiv.scrollLeft() - 1;
                                _ulwrapdiv.scrollLeft(left);
                            },
                            20
                        );
                    },
                    function() {
                        clearInterval(thumbs_scroll_interval);
                    }
                );
                if(o.mouse == 'press'){
                    _rotright.mousedown(
                        function() {
                            thumbs_mouse_interval = setInterval(
                                function() {
                                    var left = _ulwrapdiv.scrollLeft() + 5;
                                    _ulwrapdiv.scrollLeft(left);
                                },
                                20
                            );
                        }
                    ).mouseup(
                        function() {
                            clearInterval(thumbs_mouse_interval);
                        }
                    );
                    _rotleft.mousedown(
                        function() {
                            thumbs_mouse_interval = setInterval(
                                function() {
                                    var left = _ulwrapdiv.scrollLeft() - 5;
                                    _ulwrapdiv.scrollLeft(left);
                                },
                                20
                            );
                        }
                    ).mouseup(
                        function() {
                            clearInterval(thumbs_mouse_interval);
                        }
                    );
                }
                else{
                    _rotleft.click(function(e){
                        var width = outsidewidth - 10;
                        var left = _ulwrapdiv.scrollLeft() - width;
                        _ulwrapdiv.animate({scrollLeft: left +'px'});
                    });

                    _rotright.click(function(e){
                        var width = outsidewidth - 10;
                        var left = _ulwrapdiv.scrollLeft() + width;
                        _ulwrapdiv.animate({scrollLeft: left +'px'});
                    });
                }
            }

            //first and last:
            _first.click(function(e){
                _ulwrapdiv.animate({scrollLeft: '0px'});
                _ulwrapdiv.find('li').eq(0).click();
            });
            _last.click(function(e){
                _ulwrapdiv.animate({scrollLeft: insidewidth +'px'});
                _ulwrapdiv.find('li').eq(o.count - 1).click();
            });

            //click a page
            _ulwrapdiv.find('li').click(function(e){
                selobj.html('<a>'+selobj.find('.jPag-current').html()+'</a>');
                var currval = $(this).find('a').html();
                $(this).html('<span class="jPag-current">'+currval+'</span>');
                selobj = $(this);
                $.fn.applystyle(o,$(this).parent().parent().parent(),a_css,hover_css,_first,_ul,_ulwrapdiv,_divwrapright);
                var left = (this.offsetLeft) / 2;
                var left2 = _ulwrapdiv.scrollLeft() + left;
                var tmp = left - (outsidewidth / 2);
                if(ver == 'ie7')
                    _ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 52 + 'px'});
                else
                    _ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 'px'});
                o.onChange(currval);
            });

            var last = _ulwrapdiv.find('li').eq(o.start-1);
            last.attr('id','tmp');
            var left = document.getElementById('tmp').offsetLeft / 2;
            last.removeAttr('id');
            var tmp = left - (outsidewidth / 2);
            if(ver == 'ie7') _ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 52 + 'px'});
            else _ulwrapdiv.animate({scrollLeft: left + tmp - _first.parent().width() + 'px'});
        }

        $.fn.applystyle = function(o,obj,a_css,hover_css,_first,_ul,_ulwrapdiv,_divwrapright){
            obj.find('a').css(a_css);

            obj.find('span.jPag-current').css(hover_css);

            obj.find('a').hover(
                function(){
                    $(this).css(hover_css);
                },
                function(){
                    $(this).css(a_css);
                }
            );


            obj.css('padding-left',_first.parent().width() + 5 +'px');
            insidewidth = 0;

            obj.find('li').each(function(i,n){
                if(i == (o.display-1)){
                    outsidewidth_tmp = this.offsetLeft + this.offsetWidth ;
                }
                insidewidth += this.offsetWidth;
            })
            _ul.css('width',insidewidth+'px');
        }



    })(jQuery);



/**
 * 计算每页显示数量
 * */
function countValue() {
                return   $("#count").find("option:selected").text();;
}


//计算总页数
function TotalPage(url,data) {
    TotalRecord(url,data);
    // console.log("总记录:"+tolalRecord);
    $('#totalRecord').text(tolalRecord);
    var count=countValue();
    // console.log("每页显示:"+count);
    if (tolalRecord == 0) {
        console.log("总记录数为0，请检查！");
        return 0;
    }
    else if (tolalRecord % count == 0)
         totalPage=tolalRecord / count;
    else
        totalPage= parseInt(tolalRecord / count) + 1;

    $("#demo3").paginate({

        count 		: totalPage,//总页数(动态)

        start 		: 1,//起始页码

        display     : 5,

        border					: true,

        border_color			: '#BEF8B8',

        text_color  			: '#68BA64',

        background_color    	: '#E3F2E1',

        border_hover_color		: '#68BA64',

        text_hover_color  		: 'black',

        background_hover_color	: '#CAE6C6',

        rotate      : false,

        images		: false,

        mouse		: 'press'

    });
}

//计算总记录数(可能需要各自处理)==>url 获取总记录数的请求路径
function TotalRecord(url,data) {
    //普通分页
    if(!isSearch){
        $.ajax({
            type:"POST",
            url:url,
            async: false,
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            success:function (reslut) {
                // console.log(reslut)
                tolalRecord= reslut;
            },
            error:function(reslut){
                console.log("总记录数查询失败！")
            }
        })
    }
    //查询
    if(isSearch){
        console.log("查询下的总记录数")
        $.ajax({
            type:"POST",
            url:url,
            data:JSON.stringify(data),
            async: false,
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            success:function (reslut) {
                tolalRecord= reslut;
            },
            error:function(reslut){
                console.log("总记录数查询失败！")
            }
        })
    }

}


/*点击页码*/
function findPageumber(item,fName) {

    console.log(isSearch);
    var num=$(item).text();
    $("#demo3").paginate({

        count 		: totalPage,//总页数(动态)

        start 		: 1,//起始页码

        display     : 5,

        border					: true,

        border_color			: '#BEF8B8',

        text_color  			: '#68BA64',

        background_color    	: '#E3F2E1',

        border_hover_color		: '#68BA64',

        text_hover_color  		: 'black',

        background_hover_color	: '#CAE6C6',

        rotate      : false,

        images		: false,

        mouse		: 'press'

    });
    $('#currentPage').text(num);
    var page={};
    page.start=parseInt(countValue())*(parseInt($(item).text())-1);
    page.count=countValue();
    //普通分页下
if(!isSearch){
    window[fName](page);//以方法名调用改方法
}
    if(isSearch) {
    data.page=page;
    window[fName](page,data);//以方法名调用改方法
}

}

/*切换标签页码
* url:计算总页数的路径
*
* */
function switchPageNumber(fName) {

    //变化的是每页显示，其他不变的
    var page={};
    page.start=0;
    page.count=countValue();
    console.log("每页显示:")
    console.log(page)
    if(!isSearch){
        window[fName(page)]
    }
       if(isSearch){
     data.page=page;
           window[fName(page,data)]
       }
    $("#demo3").paginate({

        count 		: totalPage,//总页数(动态)

        start 		: 1,//起始页码

        display     : 5,

        border					: true,

        border_color			: '#BEF8B8',

        text_color  			: '#68BA64',

        background_color    	: '#E3F2E1',

        border_hover_color		: '#68BA64',

        text_hover_color  		: 'black',

        background_hover_color	: '#CAE6C6',

        rotate      : false,

        images		: false,

        mouse		: 'press'

    });


}

/*初始化页面信息*/
function getInitPage() {

    var page={};

    page.start=0;

    page.count=15;

    return page;
}

/*页面跳转*/
function jump() {
    var pagePage=$('#jumpPage').val();

    if(pagePage>totalPage){
        alert('跳转页数大于总页数，请重新输入页数!')
    }
    else {
        var page={};

        $('#currentPage').text(pagePage);
        page.start=parseInt(countValue())*(parseInt(pagePage)-1);
        page.count=countValue();
        console.log(tolalRecord)
        console.log(page)
        if(!isSearch){
            window[fName](page);//以方法名调用改方法
        }
            else {
            data.page=page;
            window[fName](page,data);//以方法名调用改方法
        }

    }

}



