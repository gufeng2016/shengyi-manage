<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<body>
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">用户充值查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <div>
            <form class="form-horizontal" action="${ctx}/rechargeOrder/query" method="get">
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
            <pg:pager url="${ctx}/rechargeOrder/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="order">
                        <display:column maxLength="23" property="id" title="ID"/>
                        <display:column maxLength="28" property="channelOrderId" title="第三方充值订单号"/>
                        <display:column property="userId" title="用户ID"/>
                        <display:column maxLength="11" property="mobile" title="手机号"/>
                        <display:column property="amount" title="充值金额(元)"/>
                        <display:column title="是否押金">
                        	<c:if test="${ order.isDeposit == 1}">是</c:if>
                        	<c:if test="${ order.isDeposit == 0}">否</c:if>
                        </display:column>
                        <display:column property="createTime" title="创建时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display:column  property="subject" title="充值主题"/>
                         <display:column title="订单状态">
                        	<c:if test="${ order.status == 0}">新建</c:if>
                        	<c:if test="${ order.status == 1}">第三方支付成功</c:if>
                        	<c:if test="${ order.status == 2}">成功</c:if>
                        	<c:if test="${ order.status == 3}">失败</c:if>
                        	<c:if test="${ order.status == 4}"><font color="red">需补单</font></c:if>
                        </display:column>
                        <display:column property="confirmTime" title="确认时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        
                        <display:column   title="是否已退款">
                        	<c:if test="${ order.isRefound == 1}"><font color="red">是</font></c:if>
                        	<c:if test="${ order.isRefound == 0}">否</c:if>
                        </display:column>
                        <display:column maxLength="13" property="clientIp" title="充值客户端IP"/>
                    </display:table>
                </pg:item>
 								<jsp:include page="../common/page.jsp" flush="true" />
            </pg:pager>
        </div>
    </div>
</body>
</html>
