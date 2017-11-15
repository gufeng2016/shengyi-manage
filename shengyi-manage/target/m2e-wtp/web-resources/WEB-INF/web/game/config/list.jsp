<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>

<div class="content-box-header">
    <h3 style="cursor: s-resize;">配置查询</h3>
    <div class="clear"></div>
</div>

<div class="content-box-content">

    <div>
        <form class="form-horizontal" action="${ctx}/config/query" method="post">

            <div class="control-group">
                <label class="control-label"> key： </label>

                <div class="controls">
                    <input type="text" class="small-input text-input" name="key" value="${key}" maxlength="20"/>
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
         <a href="add" class="btn">添加配置</a>
    </div>
    <div>
       <pg:pager url="${ctx}/config/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="systemConfig">
                        <display:column property="id" title="ID"/>
                        <display:column property="key" title="键"/>
                        <display:column maxLength="30" property="value" title="值" style="align:left;"/>
                        <display:column property="comment" title="描述"/>
                        <display:column title="#">
                            <a href="${ctx}/config/update?id=<c:out value="${systemConfig.id}"/>" accesskey="">
                                编辑
                            </a>&nbsp;&nbsp;
                            <a href="#" onClick="deleteItem('<c:out value="${systemConfig.id}"/>','<c:out value="${systemConfig.key}"/>')">
                                删除
                            </a>
                        </display:column>
                        <display:setProperty name="basic.empty.showtable" value="true"/>
                    </display:table>
                </pg:item>
                <jsp:include page="../../common/page.jsp" flush="true" />

            </pg:pager>
    </div>
    
    <script style="text/javascript">
    
    <!-- 删除单个元素的函数 -->
    function deleteItem(id,name){
        if(window.confirm("确认要删除配置" + name + "吗？")){
            $.post('${ctx}/config/delete', {id:id}, function (data) {
                if(data.code == 100){
                    alert("配置【" + name + "】删除成功");
                    location.href="${ctx}/config/query";
                } else{
                    alert(data.message);
                }
            }) ;
        } else {
            return;
        }
    }
    
    </script>

</div>

