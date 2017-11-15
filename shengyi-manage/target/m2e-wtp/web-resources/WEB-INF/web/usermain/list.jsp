<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<style>
#userMoneyDiv{
	width:210px;
}
	.userMoney{
		width:50px;
		height:20px;
		display:inline-block;
		text-align:left;
		line-height:20px;
	}
	.userMoneyBtn{
		width:160px;
		height:25px;
		display:inline-block;
/* 		text-align:right; */
		line-height:20px;
	}
	.userMoneyBtn button{
		width:50px;
		height:25px;
	}
	.userMoneyBtn input{
		width:45px;
		height:15px;
		vertical-align:top;
	}
</style>
<body>
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">用户查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">

        <div>
            <form class="form-horizontal" action="${ctx}/usermain/query" method="get">

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
            <pg:pager url="${ctx}/usermain/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="user">
                        <display:column  property="userId" title="ID"/>
                        <display:column maxLength="13" property="mobile" title="手机号"/>
                        <display:column title="余额(元)">
                        	<div id="userMoneyDiv">
                        		<span class="userMoney">${user.money}</span><span id="editBtn${user.userId}" class="userMoneyBtn"><button type="button" onclick="editMoney(${user.userId})">修改</button></span>
                        	</div>
                        </display:column>
                        <display:column maxLength="7" property="depositAmount" title="我的押金(元)"/>
                        <display:column maxLength="20" property="identifyNumber" title="身份证号码"/>
                        <display:column   title="是否认证">
                        	<c:if test="${ user.authentication == 0}"><font color="red">否</font></c:if>
                        	<c:if test="${ user.authentication == 1}">是</c:if>
                        </display:column>
                        <display:column maxLength="10" property="invitationCode" title="我的邀请码"/>
                        <display:column maxLength="10" property="usedInvitationCode" title="我输入的邀请码"/>
                        <display:column property="registTime" title="创建时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display:column   title="是否封号">
                        	<c:if test="${ user.isEffect == 0}"><font color="red">是</font></c:if>
                        	<c:if test="${ user.isEffect == 1}">否</c:if>
                        </display:column>
                        <display:column title="操作">
                        	<c:if test="${ user.isEffect == 1}">
                        	<a href="javascript:forbidUser(<c:out value="${user.userId}" />,0);">封号</a>
													</c:if>
                        	<c:if test="${ user.isEffect == 0}">
                        	<a href="javascript:forbidUser(<c:out value="${user.userId}" />,1);">解封号</a>
													</c:if>
                        </display:column>
                    </display:table>
                </pg:item>
					<jsp:include page="../common/page.jsp" flush="true" />
            </pg:pager>
        </div>
    </div>
</body>
<script style="text/javascript">
	function forbidUser(userId,forbid){
	var str = "您确定要给ID为“"+userId+"”的用户封号吗？";
	if(forbid == 1){
		str = "您确定要给ID为“"+userId+"”的用户解除封号吗？";
	}
		if(confirm(str)){
			$.ajax({
				url:"${ctx}/usermain/forbid",
				data:{
					"userId":userId,
					"forbid":forbid
				},
				type:"post",
				dataType:"html",
				cache:false,
				success:function(data){
					var result = eval("("+data+")");
					alert(result.msg);
					location.href = "${ctx}/usermain/query?mobile=${mobile}";
				},
				failure:function(){
					alert("操作失败！");
				}
			});
			
		}
		}
function editMoney(userId){
	var $span = $("#editBtn"+userId);
	var ht = "<input type=\"text\" id=\"moneyId"+userId+"\" onblur=\"onlyNum(this); \" onkeyup=\"onlyNum(this);\"/>";
		ht += "<button onclick=\"sureEditMoney('"+userId+"')\">确定</button>";
	 	ht +="<button onclick=\"cancelEditMoney('"+userId+"')\">取消</button>";
	$span.html(ht);
}
function cancelEditMoney(userId){
	var $span = $("#editBtn"+userId);
	var ht = "<button onclick=\"editMoney('"+userId+"')\">修改</button>";
	$span.html(ht);
}
function sureEditMoney(userId){
		var $txt = $("#moneyId"+userId);
		 var va = $txt.val();
		 if(va == ""){
		 	alert("不能填写空");
		 	return ;
		 }
		 if(va.indexOf(".")> -1){
			 var b = va.split(".");
				var x=b[0];
				var y=b[1];
				if(y.length >2 ){
					alert("小数不能超过2位");
					return ;
				}
		 }
			if(confirm("您确定要修改这条金额？")){
			$.ajax({
				url:"${ctx}/usermain/updateMoney",
				data:{
					"userId":userId,
					"money":va
				},
				type:"post",
				dataType:"html",
				cache:false,
				success:function(data){
					var result = eval("("+data+")");
					alert(result.msg);
					location.href = "${ctx}/usermain/query?mobile=${mobile}";
				},
				failure:function(){
					alert("操作失败！");
				}
			});
			
		}
}
function onlyNum(obj){
	//obj.value = obj.value.replace(/\D/g,'');
 obj.value = obj.value.replace(/[^\d.]/g, "");//清除“数字”和“.”以外的字符
 obj.value = obj.value.replace(/^\./g, "");//验证第一个字符是数字而不是.
 obj.value = obj.value.replace(/\.{2,}/g, ".");//只保留第一个. 清除多余的.
 obj.value = obj.value.replace(".", "$#$").replace(/\./g,"").replace("$#$", ".");
}
</script>
</html>
