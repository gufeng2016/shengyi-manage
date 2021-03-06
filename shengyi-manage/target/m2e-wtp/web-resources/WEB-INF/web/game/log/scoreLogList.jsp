<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>

<html>
<body>

    <div class="content-box-header">
        <h3 style="cursor: s-resize;">上下分日志</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">

        <div>
            <sp:form cssClass="form-horizontal" action="${ctx}/gameLog/queryScoreLog" commandName="params">
                <div class="control-group  row">
                    <label class="control-label"> 时间： </label>
                    <div class="controls">
                        <sp:input class="col-sm-2 text-input" path="beginTime" placeholder="开始时间" onclick="WdatePicker({e1:'operateTime',dateFmt:'yyyy-MM-dd HH:mm'})" maxlength="20"/>
                        &nbsp;&nbsp;-&nbsp;&nbsp;
                        <sp:input class="col-sm-2 text-input" path="endTime" placeholder="结束时间" onclick="WdatePicker({e1:'operateTime',dateFmt:'yyyy-MM-dd HH:mm'})" maxlength="20"/>
                    </div>
                </div>
                <div class="control-group row">
                    <label class="control-label">  </label>
                    <div class="controls">
                        <button type="submit" class="btn button">查&nbsp;&nbsp;&nbsp;&nbsp;询</button>
                    </div>
                </div>
            </sp:form>
        </div>

        <div>
            <pg:pager url="${ctx}/gameLog/queryScoreLog" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request" >
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList">
                    	<display:column property="createTime" title="时间"/>
                    	<display:column property="wechatNick" title="玩家"/>
                        <display:column property="detailTypeDesc" title="类型"/>
                        <display:column property="detailMoneyDesc" title="变动金额"/>
                        <display:column property="beforeChange" title="变动前金额"/>
                        <display:column property="afterChage" title="变动后金额"/>
                        <display:setProperty name="basic.empty.showtable" value="true"/>
                    </display:table>
                </pg:item>
                <jsp:include page="../../common/page.jsp" flush="true" />
            </pg:pager>
        </div>
    </div>
</body>
</html>
