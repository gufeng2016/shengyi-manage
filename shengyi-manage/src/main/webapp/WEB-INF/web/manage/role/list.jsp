<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<html>
<body>


<!-- Start Content Box -->
<div class="content-box-header">
    <h3 style="cursor: s-resize;">角色查询</h3>
    <div class="clear"></div>
</div>
<div class="content-box-content">
    <div style="margin-bottom: 5px; margin-top: 30px;">
        <button type="button" class="btn button" onclick="addItem();">增加角色</button>
        <button type="button" class="btn button" onclick="deleteChecked();">删除选中</button>
    </div>
    <div>
        <pg:pager url="${ctx}/role/query" items="${requestScope.paginationResult.total}"
                  maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                  export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
            <pg:item>
                <display:table class="its" name="requestScope.paginationResult.resultList" id="role">
                    <display:column title="<input type='checkbox'  onclick='checkAll()'>">
                        <input name="checklist" type="checkbox" id="<c:out value="${role.roleId}" />">
                    </display:column>
                    <display:column property="roleId" title="ID"/>
                    <display:column property="roleName" title="角色名"/>
                    <display:column property="roleDesc" title="角色描述"/>
                    <display:column property="createDate" title="创建时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                    <display:column title="查看子角色">
                        <a href="${ctx}/role/query?id=<c:out value="${role.roleId}" />">查看角色</a>
                    </display:column>
                    <display:column title="操作">
                        <a href="${ctx}/role/auth/allocation?id=<c:out value="${role.roleId}" />" accesskey="">分配权限</a>&nbsp;&nbsp;
                        <a href="${ctx}/role/update?id=<c:out value="${role.roleId}" />" accesskey="">编辑</a>&nbsp;&nbsp;
                        <a href="#" onClick="deleteItem('<c:out value="${role.roleId}"/>','<c:out value="${role.roleName}"/>')">
                            删除
                        </a>
                    </display:column>
                    <display:setProperty name="basic.empty.showtable" value="true"/>
                </display:table>
            </pg:item>
            <jsp:include page="../../common/page.jsp" flush="true" />
        </pg:pager>
    </div>
</div>

</body>
<script style="text/javascript">
    highlightTableRows(role);

<!-- 删除单个元素的函数 -->
function deleteItem(id,name){
    if(window.confirm("确认要删除角色名为" + name + "的角色吗？")){
        $.post('${ctx}/role/delete', {ids:id}, function (data) {
            if(data.code == 100){
                alert("角色【" + name + "】删除成功");
                location.href="${ctx}/role/query";
            } else{
                alert(data.message);
            }
        }) ;
    } else {
        return;
    }
}

    function addItem(){
        location.href="${ctx}/role/add";
    }

<!-- 选中全部的函数 -->
function checkAll(){
    var checkBoxs = document.getElementsByTagName("input");
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

    if(window.confirm("确定要删除选中的所有角色吗？")) {
        $.post('${ctx}/role/delete', {ids:ids},function (data) {
            if(data.code == 100){
                alert("选中的所有角色删除成功");
                location.href="${ctx}/role/query";
            } else{
                alert(data.message);
            }
        });
    } else {
        return;
    }

}
</script>
</html>

