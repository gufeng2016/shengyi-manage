<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<body>
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">编辑代理</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <sp:form cssClass="form-horizontal" action="${ctx}/game/update" method="post" commandName="game">
            <sp:hidden path="id" />
            <div class="control-group">
                <label class="control-label" > 游戏名字： </label>
                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="gameName" maxlength="20" required="required" placeholder="不超过20个字符"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="gameName"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" > 游戏别名： </label>
                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="aliasName" maxlength="20" required="required" placeholder="不超过20个字符"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="aliasName"/></span>
                </div>
            </div>
            
            <div class="control-group">
                <label class="control-label" > 状态： </label>
                <div class="controls">
                    　 			<sp:select id="parentAgentSelect" cssClass="small-input text-input" path="status">
                        <sp:option value="1">开启</sp:option>
              			<sp:option value="0">关闭</sp:option>
                    </sp:select>
                    <span><sp:errors cssClass="input-notification error png_bg" path="status"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" > 游戏版本： </label>
                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="version" maxlength="20" placeholder="不超过20个字符"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="version"/></span>
                </div>
            </div>
            
            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn">提交</button>
                </div>
            </div>
        </sp:form>
    </div>
</body>


</html>
