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
        <h3 style="cursor: s-resize;">游戏查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">

        <div style="margin-bottom: 5px; margin-top: 30px;">
            <button type="button" class="btn button" onclick="addItem();">增加游戏</button>
        </div>
            <div>
        <table class="table table-hover table-bordered" style="margin-top: 5px">
            <thead>
            <tr>
                <th>ID</th>
                <th>游戏名称</th>
                <th>游戏别名</th>
                <th>游戏类型</th>
                <th>游戏状态</th>
                <th>版本号</th>
                <th>创建时间</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${gameList}" var="item">
                 <tr>
                 	<td>${item.id}</td>
                     <td>${item.gameName}</td>
                     <td>${item.aliasName}</td>
                     <td>${item.gameType}</td>
                     <td>${item.status == 1 ? '<font color="green">开启</font>' : '<font color="red">关闭</font>'}</td>
                     <td>${item.version}</td>
                     <td>${item.createTime}</td>
                     <td>
                         <a href="update?gameId=${item.id}" title="编辑">编辑</a>
                         <a href="javascript:void(0)" title="删除" onclick="deleteItem('<c:out value="${item.id}"/>','<c:out value="${item.gameName}"/>')">删除</a>
                     </td>
                 </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </div>
</body>
<script style="text/javascript">

    function addItem(){
        location.href="${ctx}/game/add";
    }

    <!-- 删除单个元素的函数 -->
    function deleteItem(id,name){
        if(window.confirm("确认要删除游戏" + name + "吗？")){
            $.get('${ctx}/game/delete?gameId=' + id,function (data) {
                if(data.code == 100){
                    alert("游戏【" + name + "】删除成功");
                    location.href="${ctx}/game/query";
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
