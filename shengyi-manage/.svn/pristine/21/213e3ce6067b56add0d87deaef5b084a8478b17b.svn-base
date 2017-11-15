//递归数组，构造treeNode格式数据
//调用本js getData()必须在传递如下参数
//initCheckKey 初始化时选择的node
//requestURL 访问url获取的数据
//requestParam 请求的参数
//propertyName 对象中需要存储的属性的名称
//zTreeType zTree的类型1为单选下拉。2为复选下拉
var selectTypes = "";
var selectTypeIds = "";
var arrayData = [];
var zNodes = [];

var initCheckKey = [];
var requestURL = "";
var requestParam = {};
var propertyName = "";
var zTreeType = 1;

function recursionArray(arrayData, initCheckKey){
    for (var i =0, size = arrayData.length; i < size ;i++) {
        var data = arrayData[i];
        if ( typeof(data) != 'undefined') {
            var obj = {};
            obj.id = data.typeId;
            obj.pId = data.parentId;
            obj.name =  data.typeName;
            if (initCheckKey instanceof Array && initCheckKey.constructor == Array && initCheckKey.length != 0) {
                for (var initCheckKeyIndex = 0, length = initCheckKey.length; initCheckKeyIndex < length; initCheckKeyIndex ++ ) {
                    if (obj.id == initCheckKey[initCheckKeyIndex]) {
                        if (initCheckKeyIndex == 0) {
                            selectTypes += obj.name
                        } else {
                            selectTypes +=  "," + obj.name ;
                        }

                        selectTypeIds += '<input type="hidden" name="'+ propertyName + '" value="' + obj.id  + '" /> ';
                        setNodeCheckState(obj);

                        break;
                    }
                }
            }


            var list = arrayData[i].childTypeList;
            zNodes.push(obj);
            if (list instanceof Array && list.constructor == Array && list.length != 0) {
                obj.open = true;
                obj.nocheck = true;
                recursionArray(list, initCheckKey);
            }
        }
    }
}

function setNodeCheckState(obj){
    if (zTreeType == 1) {
        obj.checked = true;
    } else if (zTreeType == 2) {
        obj.checked = true;
    }
}

/** checkbox的下拉菜单配置 */
var settingChkbox = {
    check: {
        enable: true,
        chkboxType: {"Y":"", "N":""}
    },
    view: {
        dblClickExpand: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick,
        onCheck: onCheck
    }
};

function beforeClick(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
}

function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true),
        v = "", k = "";

    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
        k += '<input type="hidden" name="'+ propertyName +'" value="' + nodes[i].id  + '" /> ';
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    $("#selectValue").attr("value", v);
    $("#selectKey").html(k);
}
function clearMenu(){
    $("#selectValue").attr("value", "");
    $("#selectKey").html("");
}

function showMenu() {
//    recursionArray(arrayData, initCheckKey);
//    initZtree(zTreeType);
//    $("#selectValue").attr("value", selectTypes);
//    $("#selectKey").html(selectTypeIds);
    var cityObj = $("#selectValue");
    var cityOffset = $("#selectValue").offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top - 61 + cityObj.outerHeight() + "px"}).slideDown("fast");
    $("body").bind("mousedown", onBodyDown);
}


function getData(_initCheckKey, _requestURL, _requestParam, _propertyName, _zTreeType){
    initCheckKey = _initCheckKey;
    requestURL = _requestURL;
    requestParam = _requestParam
    propertyName = _propertyName
    zTreeType = _zTreeType
    $.ajax({
        type:"post",
        url:requestURL,
        data:requestParam,
        dataType:"json",
        success:function (data) {
            if (data.code == 100) {
                arrayData = data.obj;
                recursionArray(arrayData, initCheckKey);
                initZtree();
                $("#selectValue").attr("value", selectTypes);
                $("#selectKey").html(selectTypeIds);
            }
        }
    });
}


function initZtree() {
    if (zTreeType == 1) {
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    } else if (zTreeType == 2) {
        $.fn.zTree.init($("#treeDemo"), settingChkbox, zNodes);
    }

}

function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "typeId" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}

/** 单选的下拉菜单 */
var setting = {
    view: {
        dblClickExpand: false
    },
    data: {
        simpleData: {
            enable: true,
            idkey:'id'
        }
    },
    callback: {
        //beforeClick: beforeClick,
        onClick: onClick
    }
};


function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getSelectedNodes(),
        v = "";
    var k = "";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v = nodes[i].name;
        k = nodes[i].id;
    }
    $("#selectValue").attr("value", v);
    $("#selectKey").html('<input type="hidden" name="'+ propertyName +'" value="' + k  + '" /> ');
    $("#menuContent").css({display:'none', position: 'absolute'});
}