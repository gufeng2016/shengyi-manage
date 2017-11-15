<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<body>
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">增加权限</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <sp:form cssClass="form-horizontal" action="${ctx}/auth/add" method="post" commandName="auth">
            <div class="control-group">
                <label class="control-label" > 权限名： </label>

                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="authName" maxlength="20"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="authName"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" > 所属权限： </label>

                <div class="controls">
                    　 <sp:select cssClass="small-input text-input" items="${requestScope.authList}" itemValue="authId" itemLabel="authName" path="parentAuthId"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="parentAuthId"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" > 权限描述： </label>

                <div class="controls">
                    <sp:textarea path="authDesc"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="authDesc"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" > 权限URL： </label>

                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="url" size="20"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="url"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" >是否启用： </label>

                <div class="controls">
                    <sp:radiobutton path="available" value="1"/>
                    启用&nbsp;&nbsp;&nbsp;&nbsp;
                    <sp:radiobutton path="available" value="-1"/>
                    不启用

                    <span><sp:errors cssClass="input-notification error png_bg" path="available"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" >菜单显示： </label>

                <div class="controls">
                    <sp:radiobutton path="menuDisplay" value="1"/>
                    显示&nbsp;&nbsp;&nbsp;&nbsp;
                    <sp:radiobutton path="menuDisplay" value="-1"/>
                    不显示

                    <span><sp:errors cssClass="input-notification error png_bg" path="menuDisplay"/></span>
                </div>
            </div>
            
         <div class="control-group">
                <label class="control-label" >手机端菜单显示： </label>

                <div class="controls">
                    <sp:radiobutton path="mobileMenuDisplay" value="1"/>
                    显示&nbsp;&nbsp;&nbsp;&nbsp;
                    <sp:radiobutton path="mobileMenuDisplay" value="-1"/>
                    不显示
                    <span><sp:errors cssClass="input-notification error png_bg" path="mobileMenuDisplay"/></span>
                </div>
            </div>
            
            

            <div class="control-group">
                <label class="control-label" > 排序ID： </label>

                <div class="controls">
                    <sp:input  cssClass="small-input text-input" path="orderId" size="20"
                               type="number"  placeholder="11位以内的数字" maxlength="11"/>
                    <span><sp:errors cssClass="input-notification error png_bg" path="orderId"/></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" >  </label>
                <div class="controls">
                    <button type="submit" class="btn button">增&nbsp;加&nbsp;权&nbsp;限</button>
                </div>
            </div>
        </sp:form>
    </div>
</body>


</html>
