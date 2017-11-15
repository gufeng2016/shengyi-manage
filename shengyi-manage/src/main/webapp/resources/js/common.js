/*------------------------------------
后台管理系统公共脚本
------------------------------------*/

//得到iframe frame_content 的高度
function getIframeHeight(){
    return $("#frame_content").height();
}

//点击 “合并”
function showDialog(dialog,formId,title){
    var windowWidth = $(window).width();
    var maxDialog = (windowWidth - 500) > 1200 ? 1200 : (windowWidth - 500);
    $( "#"+dialog ).dialog({
        title:title,
        width : maxDialog,
        height: 450,
        modal: true,
        close: function( event, ui ) {
            $("#"+formId).get(0).reset();
        }
    });
}

function dialogEditClick(rowId,tableId,editUrl){
    var postData = {};
    if(rowId == null || rowId == ''){
        alert('不正确的提交数据！');
        return;
    }
    //var rowData = $(tableId).jqGrid('getRowData', rowId);
    postData.id = rowId;
    var ajaxOptions = {
        dataType: 'text',
        data:postData,
        success:function(responseHtml){
            $('#dialog').html(responseHtml);
            var windowWidth = $(window).width();
            var maxDialog = (windowWidth - 500) > 1200 ? 1200 : (windowWidth - 500);
            $( "#dialog" ).dialog({
                width : maxDialog,
                height: 450,
                modal: true,
                close:function(){
                    sysDialogCallback();
                }
            });
            editCallback();
         },
        url: editUrl
    };
    $.sz7roadAjax(ajaxOptions);
}
//删除事件
function dialogDeleteClick(rowId,tableId,deleteUrl){
    deleteCallBack(rowId,tableId,deleteUrl);
}
//用户自定义事件
function customizeEditClick(rowId,tableId) {
    customizeEditCallBack(rowId,tableId);
}

function callBackEditClick(rowId,tableId) {
    editCallBack4Type(rowId,tableId);
}

//后台管理的全局变量
var Road = window.Road || {};

//遮罩层管理
Road.CoverBg = {
    id : 0,
    
    //创建遮罩层
    create : function(para){
        var id = this.id;
        if( id !== 0 ) return;
        
        id = "_Road_CoverBg_" + id;
        this.id = id;
        
        $("<div></div>").css({
            "zIndex" : 8888,
            "width" : "100%",
            "height" : $(document).height(),
            "position" : "absolute",
            "left" : 0,
            "top" : 0,
            "background-color" : "#fff",
            "opacity" : 0.7
        }).attr("id", id).appendTo('body');
    },
    
    //移除遮罩层
    remove : function(){
        var id = this.id;
        if( id === 0 ) return;
        
        $("#"+id).remove();
        this.id = 0;
    }
};

//弹出提示层
Road.tip = {
    close : function($elem){
        Road.CoverBg.remove();
        $elem.remove();
    },
    
    //提示
    alert : function(msg, closeCallback){
        var s = '<div class="alert alert-success alert-road">'+
                  '<button type="button" class="close">&times;</button>'+
                  '<span class="label label-success">提示</span>'+
                  '<p>'+msg+'</p>'+
                '</div>';
        
        if( $.trim(msg) === '' ) return;
            
        var $alertElem = $("<div></div>");
        $alertElem.appendTo("body").html(s);
        Road.CoverBg.create();
        
        $alertElem.find(".close").click(function(){
            Road.tip.close($alertElem);
            closeCallback && closeCallback();
        });

    },
    
    //confirm 提示
    confirm : function(msg, okCallback, closeCallback){
        var s = '<div class="alert alert-block alert-road alert-confirm-road">'+
                  '<button type="button" class="close">&times;</button>'+
                  '<span class="label label-important">重要</span>'+
                  '<p>'+ msg +'</p>'+
                  '<div class="btn-wrap">'+
                    '<a href="javascript:;" class="btn btn-success">确认</a>'+
                    '<a href="javascript:;" class="btn btn-cancel">取消</a>'+
                  '</div>'+
                '</div>;'
        
        if( $.trim(msg) === '' ) return;
            
        var $confirmElem = $("<div></div>");
        $confirmElem.appendTo("body").html(s);
        Road.CoverBg.create();
        
        function closeFn(){
            Road.tip.close($confirmElem);
            closeCallback && closeCallback();
        }
        
        function okFn(){
            okCallback && okCallback();
            Road.tip.close($confirmElem);
        }
        
        $confirmElem.find(".close").click(function(){
            closeFn();
        });
        $confirmElem.find(".btn-cancel").click(function(){
            closeFn();
        });
        $confirmElem.find(".btn-success").click(function(){
            okFn();
        });

    }
    
};

$(document).ready(function(){

    //根据窗口，调整iframe的大小
    function setJqGridWidth(){
        var w = $(window).width() - 20;
        if( $(".road-jqGrid").length != 0 ){
            $(".road-jqGrid").setGridWidth(w);
        }
    }

    $(window).load(function(){
        setJqGridWidth();
        var resizeTime = null;

        $(window).resize(function(){
            clearTimeout(resizeTime);
            resizeTime = setTimeout(function(){
                setJqGridWidth();
            },200);
        });
    });

    //隐藏显示左边导航
    $("#JS_nav_door").toggle(function(){
        $(this).html("显示左侧导航 &gt;");

        $("#pg-cont nav.span2").hide();
        $("#pg-cont").css({
            "margin-left" : 15 + "px"
        });
        /*$("#pg-cont div.span10").css({
            "width":"100%",
            "margin-left" : 0
        });*/
    },function(){
        $(this).html("&lt; 隐藏左侧导航");

        $("#pg-cont nav.span2").show();
        $("#pg-cont").css({
            "margin-left" : 200 + "px"
        });
        /*$("#pg-cont div.span10").css({
            "width":"auto",
            "margin-left" : "0"
        });*/
    });

    var Globle_jqgrid_height = 0;
    //绑定：隐藏和显示 操作区域
    $(".toggleZoneBtn").toggle(function(){
        //触发隐藏事件
        $(".opZone-7road").hide();
        $(this).html("显示操作区域");

        var $jqgrid = $(".road-jqGrid");

        if( $jqgrid.length == 1 ){
            Globle_jqgrid_height = $(".ui-jqgrid-bdiv").height();
            //alert(Globle_jqgrid_height)

            //console.log( top.getIframeHeight() );

            //判断最新的高度：
            var iframeHeight = top.getIframeHeight();

            var h = iframeHeight - 180;
            $jqgrid.jqGrid('setGridHeight', h);
        }
    },
    function(){
        $(".opZone-7road").show();
        $(this).html("隐藏操作区域");

        //高度还原
        var $jqgrid = $(".road-jqGrid");

        if( $jqgrid.length == 1 ){
            Globle_jqgrid_height = $jqgrid.jqGrid('setGridHeight', Globle_jqgrid_height);
        }
    });


});//end ready

/* 左右等高 */
function leftRightHeight() {
    var sidebarHeight = $("#sidebar").height(),
        wrapperHeight = $("#body-wrapper").height();

    if(sidebarHeight >= wrapperHeight) {

        $("#body-wrapper").height(sidebarHeight + 20);

    } else {

        $("#sidebar").height(wrapperHeight - 20);
    }

}


$(window).on("load", function () {
    leftRightHeight();
});

 /**首页管理，删除Item*/
    function delHomePageItem(gridId,actionUrl) {
    	var ids = $('#'+gridId).jqGrid('getGridParam', 'selarrrow');
    	if(ids==null || ids == ""){
    		Road.tip.alert("请先选择要删除的记录")
    		return ;
    	}
      Road.tip.confirm("您确定要删除选中记录吗？",function(){
            var idsStr = JSON.stringify(ids);
            var ajaxOptions = {
                data : {
                    "ids" : idsStr
                },
                success : function() {
                	Road.tip.alert("删除成功");
                    location.reload();
                },
                url : actionUrl
            };
            $.sz7roadAjax(ajaxOptions);
        });
    }

    function showAddModel(divID) {
        var windowWidth = $(window).width();
        var maxDialog = (windowWidth - 300) > 1200 ? 1200 : (windowWidth - 300);
        $("#"+divID).dialog({
            width : maxDialog,
            height : 400,
            modal : true
        });
    }

/** cookie操作 **/
var Cookie = {
    getDomain: function () {
        var domain = document.domain;
        var index = domain.lastIndexOf('.');
        index = domain.substring(0, index).lastIndexOf('.');
        if (index == -1) {
            return domain;
        }
        return domain.substring(index + 1);
    },
    set: function (key, value, expires) {
        var ep = '';
        if (expires) {
            var date = new Date();
            date.setTime(date.getTime() + (expires < 0 ? -1 : expires * 24 * 3600 * 1000));
            ep = ";expires=" + date.toGMTString();
        }
        var domain = arguments[3] || this.getDomain();
        document.cookie = key + "=" + value + ep + ";path=/";
    },
    get: function (key) {
        var strcookie = document.cookie;
        if (strcookie != "") {
            var arrcookie = strcookie.split("; ");
            for ( var i = 0; i < arrcookie.length; i++) {
                var arr = arrcookie[i].split("=");
                if (arr[0] == key) {
                    return arr[1] || ""
                }
            }
        }
        return "";
    }
};

$.extend({
    //ajax
    "sz7roadAjax": function(ajaxOptions) {
        var defaultOptions = {
            type: "POST",
            error: function() {
                alert("网络异常，请联系管理员！");
            },
            timeout: 200000
        };
        $.extend(ajaxOptions, defaultOptions);
        $.ajax(ajaxOptions);
    },

    //不能包含 ~!@#$%^&*()=+|\[]{};':\",?/<>等特殊字符
    "isContainSpecialCharacter":function(input){
        var reg=/[\`\~\!\@\#\$\%\^\&\*\(\)\=\+\|\\\[\]\{\}\;\'\:\"\,\?\/\<\>]/g;
        if(reg.test(input)){
            return true;
        }
        return false;
    },
    "isContainOracleSpecialCharacter":function(input){
        var reg=/[\%\^\&\*\|\'\`\"]/g;
        if(reg.test(input)){
            return true;
        }
        return false;
    },
    "jqGridAjaxQuery":function(tableId,formId,toPage){
        var $table = $('#'+tableId);

        if(typeof formId == 'undefined' || formId == null) {
            var e = $table.data('events');
            if (typeof(e) !== 'undefined' && typeof(e.reloadGrid) !== 'undefined') {
                $('#'+tableId).trigger('reloadGrid');
            } else {
                alert('Network error, please try again!');
            }
        } else {

            var dt = $('#'+formId).serializeArray();
            var gridParam = $table.jqGrid('getGridParam');
            var postData = {};
            if(typeof toPage != 'undefined' || toPage != null) {
                gridParam['page']=toPage;
            }
            for (var param in dt) {
                var formValue = dt[param].value;
                if(formValue != null && formValue.length >0){
                    postData[dt[param].name] = dt[param].value;
                }
            }
            gridParam.postData=postData;
            $table.jqGrid('setGridParam', gridParam).trigger('reloadGrid');
        }
    },
    "sz7roadFormAjax":function(ajaxOptions,formId){
        var dt = $('#'+formId).serializeArray();
        var postData = {};
        for (var param in dt) {
            if(dt[param].value!='' && dt[param].value!=null) {
                postData[dt[param].name] = dt[param].value;
            }
        }
        ajaxOptions.data = postData;
        $.sz7roadAjax(ajaxOptions);
    }
})

/**
* @param serverUrl 服务器的地址：http://web.7road.com/web
* @param path 上传文件存放地址
* @param size 现在上传文件大小
* @param imageUrl 图片域名url
* @param showImageNameAndId 用于展示图片和存放图片地址的name和ID
*/
function uploadImage(serverUrl, path, size, imageUrl, showImageNameAndId) {
   var noImgUrl = serverUrl + "/resources/images/nopic.png";
   $.ImgUpload({
       name       : "file",
       swf        : serverUrl + "/resources/upImgDialog/uploadify.swf",  /* 上传图片flash地址 */
       uploader   : serverUrl + "/upload/image?path=" + path,     /* 上传图片地址 */
       contextPath: serverUrl,                                      /* 网站程序路径*/
       imgListUrl : serverUrl + "/upload/list?path=" + path,      /* 图片列表请求地址 */
       fileSizeLimit : size,                                      /* 图片限制大小*/
       imageSiteUrl  : imageUrl,                                 /* 图片服务器地址， 本地调试请设置为空字符串*/
       submitBtn  : function (src) {  /* 确定按钮自定义函数 */
           $(":input[name=" + showImageNameAndId + "]").val(src);
           if(src != '') {
               if(imageUrl == '') {
                   src = serverUrl + src;
               } else {
                   src = imageUrl + src;
               }
               $('#' + showImageNameAndId).prev().attr('src', src);
           } else {
               $('#' + showImageNameAndId).prev().attr('src', noImgUrl);
           }
       }
   });
}


/**
* @param serverUrl 服务器的地址：http://web.7road.com/web
* @param path 上传文件存放地址
* @param size 现在上传文件大小
* @param imageUrl 图片域名url
* @param showImageNameAndId 用于展示图片和存放图片地址的name和ID
*/
function uploadImage2(serverUrl, path, size, imageUrl) {
   var noImgUrl = serverUrl + "/resources/images/nopic.png";
   $.ImgUpload({
       name       : "file",
       swf        : serverUrl + "/resources/upImgDialog/uploadify.swf",  /* 上传图片flash地址 */
       uploader   : serverUrl + "/upload/image?path=" + path,     /* 上传图片地址 */
       contextPath: serverUrl,                                      /* 网站程序路径*/
       imgListUrl : serverUrl + "/upload/list?path=" + path,      /* 图片列表请求地址 */
       fileSizeLimit : size,                                      /* 图片限制大小*/
       imageSiteUrl  : imageUrl,                                 /* 图片服务器地址， 本地调试请设置为空字符串*/
       submitBtn  : function (src) {  /* 确定按钮自定义函数 */
           this.btn.prev().val(src);
           if(src != '') {
               if(imageUrl == '') {
                   src = serverUrl + src;
               } else {
                   src = imageUrl + src;
               }
               this.btn.prev().prev().attr('src', src);
           } else {
        	   this.btn.prev().prev().attr('src', noImgUrl);
           }
       }
   });
}






    


