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
        <h3 style="cursor: s-resize;">充值返利规则查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <div>
            <form class="form-horizontal" action="${ctx}/rechargeReward/query" method="get">
                <div class="control-group">
                    <label class="control-label">充值定额： </label>
                    <div class="controls">
                        <input type="text" class="small-input text-input" name="chargeMoney" onblur="onlyNum(this)" onkeyup="onlyNum(this)" value="${params.chargeMoney}" maxlength="10"  style="margin-right:10px;"/>
                    </div>
                    <div class="controls">
                        <select name="state" style="margin-right:10px;"  >
                        	<option value="">---请选择是否有效---</option>
                        	<option value="0" <c:if test="${params.state == 0}">selected="selected"</c:if> >有效</option>
                        	<option value="1"<c:if test="${params.state == 1}">selected="selected"</c:if> >无效</option>
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
            <button type="button" class="btn button" onclick="addItem();">增加返利规则</button>
            <button type="button" class="btn button" onclick="deleteChecked();">删除选中</button>
        </div>
        <div>
            <pg:pager url="${ctx}/rechargeReward/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="reward">
                        <display:column title="<input type='checkbox'  onclick='checkAll()'>">
                            <input name="checklist" type="checkbox" id="<c:out value="${reward.id}" />">
                            <input type="hidden" value="${reward.rewardMoney }" id="rewardMoney_${reward.id}"/>
		                        <input type="hidden" value="${reward.rewardTime }" id="rewardTime_${reward.id }"/>
		                        <input type="hidden" value="${reward.chargeMoney }" id="chargeMoney_${reward.id }"/>
                        </display:column>
                        <display:column property="id" title="ID"/>
                        <display:column property="chargeMoney" title="充值定额"/>
                        <display:column property="rewardMoney" title="奖励现金(元)"/>
                        <display:column property="rewardTime" title="奖励骑行时间(分)"/>
                        <display:column title="是否有效">
                        	<c:if test="${ reward.state == 1}"><font style="color: red;">无效</font></c:if>
                        	<c:if test="${ reward.state == 0}">有效</c:if>
                        </display:column>
                        <display:column property="createTime" title="创建时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display:column property="updateTime" title="最后修改时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display-el:column title="操作">
                        	<c:if test="${ reward.state == 0}">
                        		<a href="javascript:setItemState('${reward.id}',1)">置为无效</a>
													</c:if>
                        	<c:if test="${ reward.state == 1}">
                        		<a href="javascript:setItemState('${reward.id}',0)">置为有效</a>
                        	</c:if>
                        		<a href="javascript:editItem('${reward.id}')">修改</a>
                        		<a href="javascript:deleteItem('${reward.id}')">删除</a>
                        </display-el:column>
                    </display:table>
                </pg:item>
 								<jsp:include page="../common/page.jsp" flush="true" />
            </pg:pager>
        </div>
    </div>
    <div id="fugaiceng" ></div>
<!--     添加充值返利                                                                                                     									-->
    <div id="editOrderView" class="editOrderViewOff">
  	<input type="hidden" value="" id="rruId"/>
    <div style="margin: 10px 20px 10px 20px;">
    	<h4 id="addTitle" style="text-align:center;font-size: 18px;font-weight: bold;"></h4>
    	<p class="listHang">
    		<label class="shuoming"  title="单位：元">充值定额：</label> 
    		<input type="text" id="chargeMoney" onblur="onlyNum(this)" onkeyup="onlyNum(this)" /> 元
    	</p>
    	<p  class="listHang" >
    		<label class="shuoming"  title="单位：元">奖励金额：</label>
    		<input type="text" id="rewardMoney" onblur="onlyNum(this)" onkeyup="onlyNum(this)" /> 元
    	</p>
    	<p class="listHang">
    		<label class="shuoming" title="单位：分钟">奖励骑行时间：</label> 
    		<input type="text" id=rewardTime onblur="onlyNum(this)" onkeyup="onlyNum(this)" /> 分钟
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
	var chargeMoney = $('#chargeMoney').val();
	var rewardMoney = $('#rewardMoney').val();
	var rewardTime = $('#rewardTime').val();

	if(checkIsEmpty(chargeMoney)){
		alert("充值定额不能为空");
		return;
	}
	if(checkIsEmpty(rewardTime) && checkIsEmpty(rewardMoney)){
		alert("奖励金额和奖励骑行不能同时为空！");
		return;
	}
	var url = "${ctx}/rechargeReward/add";
	var rruId = $("#rruId").val();
	if(!checkIsEmpty(rruId)){
		url = "${ctx}/rechargeReward/update";
	}
	$.ajax({
		url:url,
		data:{
			"rruId":rruId,
			"rewardTime":rewardTime,
			"chargeMoney":chargeMoney,
			"rewardMoney":rewardMoney
		},
		dataType:'html',
		type:'post',
		cache:false,
		success:function(data){
			var result = eval("("+data+")");
			alert(result.msg);
			if(result.code == "0000"){
				location.href="${ctx}/rechargeReward/query";
			}
		},failure:function(){
			alert("操作失败！");
		}
	});
}
	function addItem(){
			openBow();
			$('#addTitle').html("添加充值返利规则");
       // location.href="${ctx}/rechargeReward/add";
    }
    
	function editItem(rruId){
        openBow();
        $('#addTitle').html("修改充值返利规则");
        $("#rruId").val(rruId);
        $('#chargeMoney').val($('#chargeMoney_'+rruId).val());
				$('#rewardMoney').val($('#rewardMoney_'+rruId).val());
				$('#rewardTime').val($('#rewardTime_'+rruId).val());
    }
    
 function openBow(){
	var $div = $("#fugaiceng");
	$div.attr("class","fugaicengOn");
	$("#editOrderView").attr("class","editOrderViewOn");
	
}
function setItemState(rruId,state){
		$.ajax({
		url:"${ctx}/rechargeReward/setRuleIsval",
		data:{
			"rruId":rruId,
			"state":state
		},
		dataType:'html',
		type:'post',
		cache:false,
		success:function(data){
			var result = eval("("+data+")");
			alert(result.msg);
			if(result.code == "0000"){
				location.href="${ctx}/rechargeReward/query";
			}
		},failure:function(){
			alert("操作失败！");
		}
	});
}
function cancelBow(){
		$div = $("#fugaiceng");
		$div.attr("class","fugaicengOff");
		$("#editOrderView").attr("class","editOrderViewOff");
		$("#rruId").val('');
}
    <!-- 删除单个元素的函数 -->
    function deleteItem(id){
        if(window.confirm("确认要删除充值返利ID为" + id + "的充值返利规则吗？")){
            $.post('${ctx}/rechargeReward/delete', {"rruId":id}, function (data) {
                if(data.code =="0000"){
                    alert("充值返利规则【" + id + "】删除成功");
                    location.href="${ctx}/rechargeReward/query";
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

        if(window.confirm("确定要删除选中的所有充值返利规则吗？")) {
            $.get('${ctx}/rechargeReward/delete?rruIds=' + ids,function (data) {
                if(data.code == "0000"){
                    alert("选中的所有充值返利规则删除成功");
                    location.href="${ctx}/rechargeReward/query";
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
