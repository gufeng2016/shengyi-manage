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
        <h3 style="cursor: s-resize;">日志查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">

        <div>
            <sp:form cssClass="form-horizontal" action="${ctx}/operateLog/query" commandName="log"  >
                <div class="control-group">

                </div>
                <div class="control-group">
                    <label class="control-label"> 请求URL路径： </label>
                    <div class="controls">
                        <sp:input cssClass="small-input text-input" path="operateUrl"  maxlength="50"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"> 模块名称： </label>
                    <div class="controls">
                        <sp:input cssClass="small-input text-input" path="operateName"  maxlength="50"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"> 操作时间： </label>
                    <div class="controls">
                        <sp:input cssClass="small-input text-input" path="operateTime" onclick="WdatePicker({e1:'operateTime',dateFmt:'yyyy-MM-dd'})" maxlength="50"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">  </label>
                    <div class="controls">
                        <button type="submit" class="btn button">查&nbsp;&nbsp;&nbsp;&nbsp;询</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn button" onclick="clearForm();">清空查询条件</button>
                    </div>
                </div>
            </sp:form>
        </div>

        <div>
            <pg:pager url="${ctx}/operateLog/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request" >
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="article"   >
                        <display:column property="userName" title="操作用户"/>
                        <display:column property="operateTime" title="操作时间"/>
                        <display:column property="operateName" title="模块名称"/>
                        <display:column property="operateUrl" title="请求URL路径"/>
                        <display:column maxLength="30" property="requestParameters" title="请求参数"/>
                        <display:column maxLength="30" property="responseResult" title="返回结果"/>
                        <display:column property="ip" title="ip地址"/>
                        <display:setProperty name="basic.empty.showtable" value="true"/>
                    </display:table>
                </pg:item>
                <jsp:include page="../../common/page.jsp" flush="true" />
            </pg:pager>
        </div>
    </div>
</body>
<script style="text/javascript">

    function clearForm(){
        $("form").find("input, select").val("");
    }


</script>
</html>
