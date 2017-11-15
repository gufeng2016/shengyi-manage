<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<link rel="stylesheet" href="${ctx}/resources/css/bowWindow.css" type="text/css" media="screen" />
<style type="text/css">
.shuoming{
	font-size:14px;
	width:115px;
	display:inline-block;
}
.listHang{
	margin-top: 5px;
	margin-bottom: 5px;
	line-height:40px;
}
</style>
<body>
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">优惠券查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <div>
            <form class="form-horizontal" action="${ctx}/couponManage/query" method="get">
                <div class="control-group">
                    <label class="control-label">用户ID： </label>
                    <div class="controls">
                        <input type="text" class="small-input text-input" name="userId" value="${params.userId}" maxlength="10"  style="margin-right:10px;"/>
                    </div>
                    <div class="controls">
                        <select name="used" style="margin-right:10px;"  >
                        	<option value="">---请选择是否已使用---</option>
                        	<option value="0" <c:if test="${params.used == 0}">selected="selected"</c:if> >未使用</option>
                        	<option value="1"<c:if test="${params.used == 1}">selected="selected"</c:if> >已使用</option>
                        </select>
                        <select name="overdu">
                        	<option value="">---请选择是否已过期---</option>
                        	<option value="0"<c:if test="${params.overdu == 0}">selected="selected"</c:if> >未过期</option>
                        	<option value="1"<c:if test="${params.overdu == 1}">selected="selected"</c:if> >已过期</option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn button">查询</button>
                    </div>
                </div>
            </form>
        </div>
        <div style="margin-bottom: 5px; margin-top: 30px;">
            <button type="button" class="btn button" onclick="addItem();">增加优惠券</button>
            <button type="button" class="btn button" onclick="deleteChecked();">删除选中</button>
        </div>
        <div>
            <pg:pager url="${ctx}/couponManage/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="coupon">
                        <display:column title="<input type='checkbox'  onclick='checkAll()'>">
                            <input name="checklist" type="checkbox" id="<c:out value="${coupon.id}" />">
                            <input type="hidden" value="${coupon.userId }" id="userId_${coupon.id}"/>
		                        <input type="hidden" value="${coupon.amount }" id="amount_${coupon.id }"/>
		                        <input type="hidden" value="${coupon.isRide }" id="isRide_${coupon.id }"/>
		                        <input type="hidden" value="${coupon.overduTime }" id="overduTime_${coupon.id }"/>
                        </display:column>
                        <display:column property="id" title="ID"/>
                        <display:column property="userId" title="用户ID"/>
                        <display:column title="优惠券面额">
                        <c:if test="${ coupon.isRide == 1}">${coupon.amount }(分钟)</c:if>
                        	<c:if test="${ coupon.isRide == 0}">${coupon.amount }(元)</c:if>
                        </display:column>
                        <display:column title="是否骑行券">
                        	<c:if test="${ coupon.isRide == 1}">骑行券</c:if>
                        	<c:if test="${ coupon.isRide == 0}">现金券</c:if>
                        </display:column>
                        <display:column property="reveiceTime" title="发放时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display:column property="overduTime" title="过期时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                         <display:column title="优惠券状态">
                        	<c:if test="${ coupon.used == 0}">未使用</c:if>
                        	<c:if test="${ coupon.used == 1}"><font color="red">已使用</font></c:if>
                        </display:column>
                        <display:column property="useTime" title="使用时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display-el:column title="操作">
                        	<c:if test="${ coupon.used == 0}">
                        		<a href="javascript:editItem('${coupon.id}')">修改</a>
                        	</c:if>
                        		<a href="javascript:deleteItem('${coupon.id}')">删除</a>
                        </display-el:column>
                    </display:table>
                </pg:item>
 								<jsp:include page="../common/page.jsp" flush="true" />
            </pg:pager>
        </div>
    </div>
    <div id="fugaiceng" ></div>
<!--     添加优惠券                                                                                                     									-->
    <div id="editOrderView" class="editOrderViewOff">
  	<input type="hidden" value="" id="couponId"/>
    <div style="margin: 10px 20px 10px 20px;">
    	<h4 style="text-align:center;font-size: 18px;font-weight: bold;">添加优惠券</h4>
    	<p class="listHang">
    		<label class="shuoming" >要发放的用户ID：</label> <input type="text" id="userId" onblur="onlyNumber(this)" onkeyup="onlyNumber(this)" />
    	</p>
    	<p  class="listHang" >
    		<label class="shuoming">优惠券类型：</label>
    		<select id="isRide">
    			<option value="1" >骑行券</option>
    			<option value="0" selected="selected">现金券</option>
    		</select>
    	</p>
    	<p class="listHang">
    		<label class="shuoming" title="骑行券单位：分钟，现金券单位：元">优惠券面额：</label> <input type="text" id="miane" onblur="onlyNum(this)" onkeyup="onlyNum(this)" />
    	</p>
    	<p class="listHang">
    	<label class="shuoming">过期时间：</label>
	    	<input type="text" placeholder="过期时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" id="overduTime" maxlength="20"/>
    	</p>
    	
    </div>
    <p  class="listHang" style="text-align: center;">
    <button onclick="sureAddItem()" >确定</button>
    <button onclick="cancelBow()" >关闭</button>
    </p>
    </div>
</body>
<script type="text/javascript">
function sureAddItem(){
	var isRide = $('#isRide').val();
	var miane = $('#miane').val();
	var userId = $('#userId').val();
	var overduTime = $('#overduTime').val();
	if(checkIsEmpty(userId)){
		alert("要发放的用户ID不能为空");
		return;
	}
	if(checkIsEmpty(miane)){
		alert("优惠券面额不能为空！");
		return;
	}
	var url = "${ctx}/couponManage/add";
	var couponId = $("#couponId").val();
	if(!checkIsEmpty(couponId)){
		url = "${ctx}/couponManage/update";
	}
	$.ajax({
		url:url,
		data:{
			"couponId":couponId,
			"userId":userId,
			"isRide":isRide,
			"amount":miane,
			"overduDate":overduTime
		},
		dataType:'html',
		type:'post',
		cache:false,
		success:function(data){
			var result = eval("("+data+")");
			alert(result.msg);
			if(result.code == "0000"){
				location.href="${ctx}/couponManage/query";
			}
		},failure:function(){
			alert("操作失败！");
		}
	});
}
	function addItem(){
			openBow();
       // location.href="${ctx}/couponManage/add";
    }
    
	function editItem(couponId){
        openBow();
        $("#couponId").val(couponId);
        $('#isRide').val($('#isRide_'+couponId).val());
				$('#miane').val($('#amount_'+couponId).val());
				$('#userId').val($('#userId_'+couponId).val());
				$('#overduTime').val($('#overduTime_'+couponId).val());
    }
    
 function openBow(){
	var $div = $("#fugaiceng");
	$div.attr("class","fugaicengOn");
	$("#editOrderView").attr("class","editOrderViewOn");
	
}
function cancelBow(){
		$div = $("#fugaiceng");
		$div.attr("class","fugaicengOff");
		$("#editOrderView").attr("class","editOrderViewOff");
		$("#couponId").val('');
}
    <!-- 删除单个元素的函数 -->
    function deleteItem(id){
        if(window.confirm("确认要删除优惠券ID为" + id + "的优惠券吗？")){
            $.post('${ctx}/couponManage/delCoupon', {"couponId":id}, function (data) {
                if(data.code =="0000"){
                    alert("优惠券【" + id + "】删除成功");
                    location.href="${ctx}/couponManage/query";
                } else{
                    alert(data.message);
                }
            }) ;
        } else {
            return;
        }
    }
<!-- 选中全部的函数 -->
    function checkAll(){
        var checkBoxs = document.getElementsByName("checklist");
        var j = 0;
        for(var i = 0; i < checkBoxs.length; i++){
            if(checkBoxs[i].checked == true)
                j++;
        }
        if(j == checkBoxs.length - 1){
            for(var k = 0 ; k < checkBoxs.length; k++){
                checkBoxs[k].checked = false;
            }
        }else{
            for(var i = 0; i < checkBoxs.length; i++){
                checkBoxs[i].checked = true;
            }
        }
    }
     <!-- 删除选中的函数 -->
    function deleteChecked(){
        var checkBoxs = document.getElementsByName("checklist");
        var ids = "";
        for(var i = 0; i < checkBoxs.length; i++){
            if(checkBoxs[i].checked == true){
                ids = ids+checkBoxs[i].id+",";
            }
        }
        if(ids == "")
            return;

        if(window.confirm("确定要删除选中的所有优惠券吗？")) {
            $.get('${ctx}/couponManage/delCoupon?couponIds=' + ids,function (data) {
                if(data.code == "0000"){
                    alert("选中的所有优惠券删除成功");
                    location.href="${ctx}/couponManage/query";
                } else{
                    alert(data.message);
                }
            }) ;
        } else {
            return;
        }

    }
    function onlyNum(obj){
	//obj.value = obj.value.replace(/\D/g,'');
 obj.value = obj.value.replace(/[^\d.]/g, "");//清除“数字”和“.”以外的字符
 obj.value = obj.value.replace(/^\./g, "");//验证第一个字符是数字而不是.
 obj.value = obj.value.replace(/\.{2,}/g, ".");//只保留第一个. 清除多余的.
 obj.value = obj.value.replace(".", "$#$").replace(/\./g,"").replace("$#$", ".");
}
function onlyNumber(obj){
	obj.value = obj.value.replace(/\D/g,'');
}
function checkIsEmpty(str){
	if (str !== null && str !== undefined && str !== '') { 
		return false;
	}
	return true;
	}
	 //字符串转日期格式，strDate要转为日期格式的字符串
        function getDate(strDate) {
        if(checkIsEmpty(strDate)){
        	return null;
        }
            var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
             function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
            return date;
        }
</script>
</html>
