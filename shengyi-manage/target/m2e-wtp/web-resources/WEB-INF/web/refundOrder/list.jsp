<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<style type="text/css">
.fugaicengOn{
		width:100%;
		height:200%;
		position:absolute;
		top:0;
		left:0;
		opacity:0.3;
		z-index:88;
		background:#333;
}
.editOrderViewOn{
		width:400px;
		height:300px;
		position:absolute;
		top:35%;
		left:50%;
		z-index:90;
		margin-left:-200px;
		margin-top:-150px;
		background:#eee;
		padding:20px auto;
		display:block;
}
.fugaicengOff{
		width:0;
		height:0;
		position:absolute;
		top:0;
		left:0;
		opacity:0.3;
		z-index:88;
		background:red;
}
.editOrderViewOff{
	display:none;
}

</style>
<body>
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">用户退款管理</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <div>
            <form class="form-horizontal" action="${ctx}/refundOrder/query" method="get">
                <div class="control-group">
                    <label class="control-label">手机号： </label>

                    <div class="controls">
                        <input type="text" class="small-input text-input" name="mobile" value="${mobile}" maxlength="20"/>
                    </div>
                </div>

                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn button">查询</button>
                    </div>
                </div>
            </form>
        </div>
        <div>
            <pg:pager url="${ctx}/refundOrder/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="order">
                        <display:column property="id" title="ID"/>
                        <display:column property="orderId" title="充值订单ID"/>
                        <display:column maxLength="28" property="channelOrderId" title="第三方充值订单号"/>
                        <display:column property="userId" title="用户ID"/>
                        <display:column maxLength="11" property="mobile" title="手机号"/>
                        <display:column property="totalAmount" title="订单金额(元)"/>
                        <display:column property="refundAmount" title="退款金额(元)"/>
                        <display:column title="是否押金">
                        	<c:if test="${ order.isDeposit == 1}">是</c:if>
                        	<c:if test="${ order.isDeposit == 0}">否</c:if>
                        </display:column>
                        <display:column property="payTime" title="充值时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display:column maxLength="19" property="createTime" title="退款发起时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                         <display:column title="订单状态">
                         <div style="width: 70px;">
                        	<c:if test="${ order.status == 0}"><font color="red">待审核</font></c:if>
                        	<c:if test="${ order.status == 1}">审核通过，发起第三方退款</c:if>
                        	<c:if test="${ order.status == 2}">审核不通过</c:if>
                        	<c:if test="${ order.status == 3}">退款成功</c:if>
                        	<c:if test="${ order.status == 4}"><font color="red">退款失败</font></c:if>
                        	</div>
                        </display:column>
                        <display:column property="remark" title="备注"/>
                        <display:column property="sureTime" title="退款确定时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display-el:column   title="操作">
                        	<div style="width: 30px;">
	                        	<c:if test="${order.status == 0 }"><a href="javascript:examineOrder('${order.id}')">审核</a></c:if>
                        	</div>
                        </display-el:column>
                    </display:table>
                </pg:item>
 								<jsp:include page="../common/page.jsp" flush="true" />
            </pg:pager>
        </div>
    </div>
    <div id="fugaiceng" >
    </div>
    <div id="editOrderView" class="editOrderViewOff">
    <input type="hidden" value="" id="setOrderId"/>
    <div style="margin: 10px 20px 10px 20px;">
    	<h4 style="text-align:center;font-size: 20px;font-weight: bold;">审核情况</h4>
    	<p style="margin-top: 10px;margin-bottom: 10px;">
    		<label style="margin-right: 20px;font-size: 14px;">是否同意退款：</label> 
    		<input type="radio" checked="checked" name="isAgree" value="1" /> <label style="margin-right: 10px;">同意退款</label> 
    		<input type="radio" name="isAgree" value="0"/><label>不同意退款</label>
    	</p>
    	<p style="text-align: left;font-size: 14px;">添加备注：</p>
    	<p><textarea id="orderRemark"  cols="50" rows="6" style="width: 300px;"></textarea></p>
    </div>
    <p style="text-align: center;">
    <button onclick="okExamineOrder()" >确定</button>
    <button onclick="cancelExamineOrder()" >关闭</button>
    </p>
    </div>
</body>
<script type="text/javascript">
function okExamineOrder(){
	var orderId = $("#setOrderId").val();
	var remark = $("#orderRemark").val();
	var isAgree=$('input:radio[name="isAgree"]:checked').val();
	var cont = true;
	$.ajax({
		url:"${ctx}/refundOrder/examine",
		data:{
			"refundId":orderId,
			"remark":remark,
			"isAgree":isAgree
		},
		dataType:'html',
		type:'post',
		cache:false,
		success:function(data){
		cont = false;
			var result = eval("("+data+")");
			alert(result.msg);
			location.href="${ctx}/refundOrder/query";
		},failure:function(){
			alert("操作失败！");
		}
	}); 
	setTimeout(function(){
		if(cont){
			alert("操作失败！");
		}else{
			cancelExamineOrder();
		}
	},1500);
	 
}
function examineOrder(orderId){
	var $div = $("#fugaiceng");
	$div.attr("class","fugaicengOn");
	$("#editOrderView").attr("class","editOrderViewOn");
	$("#setOrderId").val(orderId);
}
function cancelExamineOrder(){
		var $div = $("#fugaiceng");
		$div.attr("class","fugaicengOff");
		$("#editOrderView").attr("class","editOrderViewOff");
		$("#setOrderId").val('');
}
</script>
</html>
