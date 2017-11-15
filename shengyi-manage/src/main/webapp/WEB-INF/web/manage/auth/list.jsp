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
        <h3 style="cursor: s-resize;">权限查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">

        <div>
            <form class="form-horizontal" action="${ctx}/auth/query" method="get">

                <div class="control-group">
                    <label class="control-label"> 权限ID： </label>

                    <div class="controls">
                        <input type="text" class="small-input text-input" name="authId" value="${authId}" maxlength="20"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"> 权限名： </label>

                    <div class="controls">
                        <input type="text" class="small-input text-input" name="authName" value="${authName}" maxlength="20"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label"> 权限URL： </label>

                    <div class="controls">
                        <input type="text" class="small-input text-input" name="url" value="${url}" maxlength="20"/>
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
            <button type="button" class="btn button" onclick="addItem();">增加权限</button>
            <button type="button" class="btn button" onclick="deleteChecked();">删除选中</button>
        </div>
        <div>
            <pg:pager url="${ctx}/auth/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:param name="id" value='<%=(String)request.getAttribute("id")%>' />

                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" id="auth">
                        <display:column title="<input type='checkbox'  onclick='checkAll()'>">
                            <input name="checklist" type="checkbox" id="<c:out value="${auth.authId}" />">
                        </display:column>
                        <display:column property="authId" title="ID"/>
                        <display:column property="authName" title="权限名"/>
                        <display:column property="url" title="权限URL"/>
                        <display:column property="available" title="是否启用"/>
                        <display:column property="menuDisplay" title="是否菜单显示"/>
                        <display:column property="mobileMenuDisplay" title="是否手机端菜单显示"/>
                        <display:column property="orderId" title="排序ID"/>
                        <display:column property="createDate" title="创建时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
                        <display:column title="查看子权限">
                            <a href="${ctx}/auth/query?id=<c:out value="${auth.authId}" />">查看子权限</a>
                        </display:column>
                        <display:column title="操作">
                            <a href="${ctx}/auth/update?id=<c:out value="${auth.authId}" />" accesskey="">
                                编辑
                            </a>&nbsp;&nbsp;
                            <a href="#" onClick="deleteItem('<c:out value="${auth.authId}"/>','<c:out value="${auth.authName}"/>')">
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

    function addItem(){
        location.href="${ctx}/auth/add";
    }

    <!-- 删除单个元素的函数 -->
    function deleteItem(id,name){
        if(window.confirm("确认要删除权限名为" + name + "的权限吗？")){
            $.post('${ctx}/auth/delete', {id:id}, function (data) {
                if(data.code == 100){
                    alert("权限【" + name + "】删除成功");
                    location.href="${ctx}/auth/query";
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

        if(window.confirm("确定要删除选中的所有权限吗？")) {
            $.get('${ctx}/auth/delete?id=' + ids,function (data) {
                if(data.code == 100){
                    alert("选中的所有权限删除成功");
                    location.href="${ctx}/auth/query";
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
