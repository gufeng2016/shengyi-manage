/*------------------------------------
    后台管理系统 首页
------------------------------------*/

$(document).ready(function(){

    //菜单动画
    var _obU = $("ul[data-toggle]"),
        tagS = _obU.attr('data-toggle');

    $("ul[data-toggle] li").find("dt").click(function () {
        $(this).parents("li").siblings().removeClass("show-tag");
        $(this).parents("li").addClass("show-tag");
        $(this).parents("li").siblings().find("dd").slideUp();
        $(this).nextAll("dd").stop().slideToggle("fast");
    });

    $("ul[data-toggle] li").find("dd").click(function () {
        $(this).parents("li").siblings().find(".a-tag").removeClass("a-tag");
        $(this).siblings().removeClass("a-tag");
        $(this).addClass("a-tag");
    });

    //根据窗口大小调整窗口
    function _resHe() {
        var _heg = $(window).height();
        if ($(window).width() > 767) {
            $(".silde-nav").height(_heg - 100);
        } else {
            $(".silde-nav").height("auto");
        }
    }

    function setIframeHeight(){
        var headerHeight = $("header").height();
        var footerHeight = $("footer").height();

        var windowHeight = $(window).height();
        var h = windowHeight - headerHeight - footerHeight - 60;
        $("#frame_content").attr('height', h + 'px');
    }

    $(window).load(function(){
        setIframeHeight();
        var resizeTime = null;

        $(window).resize(function(){
            clearTimeout(resizeTime);
            resizeTime = setTimeout(function(){
                //_resHe();
                setIframeHeight();
            },200);
        });
    });

})


    
    
    


