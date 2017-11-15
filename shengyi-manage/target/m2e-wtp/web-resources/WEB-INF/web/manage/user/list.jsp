<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<html>
<body>

    <div class="content-box-header">
        <h3 style="cursor: s-resize;">角色查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <div style="margin-bottom: 5px; margin-top: 30px;">
            <button type="button" class="btn button" onclick="addItem();">增加用户</button>
            <button type="button" class="btn button" onclick="deleteChecked();">删除选中</button>
        </div>
        <div>
            <pg:pager url="${ctx}/user/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="uesr">
                        <display:column title="<input type='checkbox'  onclick='checkAll()'>">
                            <input name="checklist" type="checkbox" id="<c:out value="${uesr.userId}" />">
                        </display:column>
                        <display:column property="userId" title="ID"/>
                        <display:column property="userName" title="用户名"/>
                        <display:column property="userNameCn" title="姓名"/>
                        <display:column title="是否启用">
                            <c:out value="${uesr.available}"/>
                        </display:column>
                        <display:column property="email" title="邮件"/>
                        <display:column property="effectiveTime" title="密码有效时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display:column title="操作">
                            <a href="${ctx}/user/update?id=<c:out value="${uesr.userId}" />" accesskey="">编辑</a>&nbsp;&nbsp;
                            <a href="#" onClick="resetPassword('<c:out value="${uesr.userId}"/>','<c:out value="${uesr.userName}"/>')">重置密码</a>&nbsp;&nbsp;
                            <a href="#" onClick="deleteItem('<c:out value="${uesr.userId}"/>','<c:out value="${uesr.userName}"/>')">删除</a>&nbsp;
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

function resetPassword(id, name){
    if(window.confirm("确认要重置用户名【" + name + "】的密码吗？")){
        $.get('${ctx}/user/reset?userId=' + id,function (data) {
            if(data.code == 100){
                alert("用户【" + name + "】密码重置成功");
                location.href="${ctx}/user/query";
            } else{
                alert(data.message);
            }
        }) ;
    } else {
        return;
    }
}

function addItem(){
    location.href="${ctx}/user/add";
}
<!-- 删除单个元素的函数 -->
function deleteItem(id,name){
    if(window.confirm("确认要删除用户名为【" + name + "】的用户吗？")){
        $.post('${ctx}/user/delete', {id:id}, function (data) {
            if(data.code == 107){
                alert("用户【" + name + "】删除成功");
                location.href="${ctx}/user/query";
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

    if(window.confirm("确定要删除选中的所有用户吗？")) {
        $.post('${ctx}/user/delete',{id:ids},function (data) {
            if(data.code == 107){
                alert("选中的用户删除成功");
                location.href="${ctx}/user/query";
            } else{
                alert(data.message);
            }
        }) ;
    } else {
        return;
    }

}
</script>
</html>

