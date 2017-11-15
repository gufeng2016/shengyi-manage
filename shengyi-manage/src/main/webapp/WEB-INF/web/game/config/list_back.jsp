<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>

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
        <table class="table table-hover table-bordered" style="margin-top: 5px">
            <thead>
            <tr>
                <th>键</th>
                <th>值</th>
                <th>备注</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="item">
                 <tr>
                     <td>${item.key}</td>
                     <td>${item.value}</td>
                     <td>${item.remark}</td>
                     <td>
                         <a href="update?id=${item.id}" title="编辑">编辑</a>
                         <a href="javascript:void(0)" title="删除" onclick="if(confirm('确定要删除 [${item.key}]?'))location.href='delete?id=${item.id}';">删除</a>
                     </td>
                 </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

