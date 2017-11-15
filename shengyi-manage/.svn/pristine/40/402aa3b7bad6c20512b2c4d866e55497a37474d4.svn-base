jQuery.extend({
    ImgUpload: function (obj) {
        var fn = {
            init: function () {
                // init default properties
                obj.contextPath = obj.contextPath || '';
                obj.imageSiteUrl = obj.imageSiteUrl || '';
                
                var t = this;
                $("[data-imgtoggle='true']").live("click", function () {
                    t.btn = obj.btn = $(this);
                    t.addModel();                   
                    t.myModal.modal('show');
                 });
            },          
            addModel: function () {
                if (!this.myModal) {                                    
                    var str = '',
                        modal = document.createElement("div");
                        
                    this.myModal = $(modal);
                    this.myModal.addClass("modal fade");
                    
                    obj.name = obj.name || 'file_upload';
                    
                    str +=      '<div class="modal-header">';
                    str +=          '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
                    str +=          '<h3>&nbsp;</h3>';
                    str +=      '</div>';
                    str +=       '<div class="modal-body">';
                    str +=            '<ul class="nav nav-tabs myTab">';
                    str +=                '<li><a href="javascript:;" data-sign="upimg">上传图片</a></li>';
                    str +=                '<li><a href="javascript:;" data-sign="imglist">图片列表</a></li>';
                    str +=                '<li><a href="javascript:;" data-sign="insertimg">插入图片</a></li>';
                    str +=            '</ul>';
                    str +=            '<div class="tab-content form-horizontal">';
                    str +=                '<div class="tab-box">';
                    str +=                     '<form>';
                    str +=                            '<div id="queue"></div>';
                    str +=                            '<input id="file_upload" name="' + obj.name + '" type="file" multiple="true">';
                    str +=                     '</form>';
                    str +=                '</div>';
                    str +=                '<div class="tab-box imgList">';
                    str +=                      '<ul class="inline unstyled"></ul>';
                    str +=                '</div>';
                    str +=                '<div class="tab-box imgAddress">';
                    str +=                      '<div class="control-group"><label class="control-label">图片地址:</label><div class="controls"><input class="input-large imgAddressUrl" type="text" /></div></div>';
                    str +=                '</div>';
                    str +=             '</div>';
                    str +=       '</div>';
                    str +=       '<div class="modal-footer">';
                    str +=             '<a href="javascript:;" class="btn btn-primary imgSure" data-dismiss="modal" >确定</a>';
                    str +=             '<a href="javascript:;" data-dismiss="modal" class="btn">关闭</a>';
                    str +=        '</div>';
                    
               
                    $("body").append(this.myModal.html(str));
                    this.otherFn();
                }
            },
            otherFn: function () {
                var t = this,
                    ul = this.myModal.find(".imgList>ul"),
                    tabbox = this.myModal.find(".tab-box"),
                    tabIndex = (obj.tabIndex == 0 ? "0" : obj.tabIndex) || 1,
                    imgSure = this.myModal.find(".imgSure"),
                    tabIndexLink = this.myModal.find('.myTab a').eq(tabIndex);
                
                tabIndexLink.parent().addClass("active");
                tabbox.eq(tabIndex).addClass("active");
                t.sign = tabIndexLink.attr("data-sign");

                this.myModal.find('.myTab a').off("click");
                this.myModal.find('.myTab a').click(function (e) {
                    var $this = $(this),
                        index = $this.parent().index();
                    
                    e.preventDefault();
                    $this.parent().addClass('active').siblings().removeClass("active");                 
                    tabbox.eq(index).addClass('active').siblings().removeClass("active");
                    t.sign = $this.attr("data-sign");
                    if (index != 0) {
                        imgSure.removeClass("hide");
                    } else {
                        imgSure.addClass("hide");
                    }
                });
                
                
                $('#file_upload').uploadify({
                    'fileObjName'   : obj.name,
                    'swf'           : obj.swf,
                    'uploader'      : obj.uploader,
                    'fileTypeExts'  : obj.fileTypeExts || '*.gif; *.jpg; *.png',
                    'fileSizeLimit' : obj.fileSizeLimit || '10MB',
                    'onUploadSuccess' : function (file, src, response) {
                        if(src && src != '' && src != 'error'){                            
                            ul.prepend(addLi(src));
                        }
                    }
                });
                
                function addLi (src) {
                    return '<li class="img-polaroid control-group"><a href="javascript:;"><img src="' + (obj.imageSiteUrl == '' ? obj.contextPath : obj.imageSiteUrl) + src + '" width="110" height="110" title="' + src + '"></a></li>&nbsp;';
                }
                
                if (obj.imgListUrl && obj.imgListUrl != "") {
                    
                    $.get(obj.imgListUrl, function (json) {
                        var str = "";
                        for(var i = 0, len = json.length; i < len; i++) {
                            str += addLi(json[i].src);
                        }
                        ul.html(str);
                    });
                }
                
                ul.on("click", "li", function () {
                    var $this = $(this);
                    $this.addClass("imgSelected");
                    $this.siblings().removeClass("imgSelected");
                }).on("dblclick", "img", function () {
                    var $this = $(this);
                    window.open($this.attr("src"));
                });
                
                if (typeof obj.submitBtn == "function") {
                    imgSure.on("click", function () {
                        var src = "";
                        switch (t.sign) {
                            case "imglist":
                                //src = $(".imgSelected").find("img").attr("src");
                                src = $(".imgSelected").find("img").attr("title");
                                break;
                            case "insertimg":
                                var imgAddressUrl = t.myModal.find(".imgAddressUrl");
                                src = imgAddressUrl.val();
                                imgAddressUrl.val("");
                                break;
                        }
                        obj.submitBtn(src, t.btn);
                    });
                }
            }
        };
        fn.init();
    }
});