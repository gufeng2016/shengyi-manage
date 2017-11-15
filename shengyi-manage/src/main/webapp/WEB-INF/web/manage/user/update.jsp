<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp"%>
<html>
<body>
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">编辑用户</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <sp:form cssClass="form-horizontal" action="${ctx}/user/update"  method="post" commandName="user" >
            <sp:hidden path="userId" />
            <div class="control-group">
                <label class="control-label" for="inputEmail"> 用户名： </label>
                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="userName" readonly="true"  />
                    <span><sp:errors cssClass="input-notification error png_bg" path="userName" /></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="inputEmail"> 姓名： </label>
                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="userNameCn" />
                    <span><sp:errors cssClass="input-notification error png_bg" path="userNameCn" /></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="inputEmail"> emial： </label>
                <div class="controls">
                    <sp:input cssClass="text-input small-input" path="email" />
                    <span><sp:errors cssClass="input-notification error png_bg" path="email" /></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="inputEmail">角色： </label>
                <div class="controls">
                    <c:forEach items="${requestScope.roleList}" var="role">
                        <dl data-toggle="dd">
                            <dt class="checkbox inline"><sp:checkbox path="roleIds" cssClass="top_auth checkSelect"  label="${role.roleName }" value="${role.roleId}" /></dt>
                            <c:forEach items="${role.childRoleList }" var="item">
                                <dt class="checkbox inline"> <sp:checkbox path="roleIds" cssClass="checkSelect" label="${item.roleName }" value="${item.roleId }" /> </dt>
                            </c:forEach>
                        </dl>
                    </c:forEach>
                    <span><sp:errors cssClass="input-notification error png_bg" path="roleIds" /></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="inputEmail">是否启用： </label>
                <div class="controls">
                    <sp:radiobutton path="available" value="1"    />
                    启用&nbsp;&nbsp;&nbsp;&nbsp;
                    <sp:radiobutton path="available" value="-1" />
                    不启用

                    <span><sp:errors cssClass="input-notification error png_bg" path="available" /></span>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <c:if test="${!empty error}">
                        <span class="input-notification error png_bg">${error}</span>
                    </c:if>
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
<script>

    $(function() {
        $(".checkSelect").on("click", function (event) {
            var t = $(this);

            if(t.attr("checked") == "checked") {

                t.parent().parent().find("input").removeAttr("checked");
                t.attr("checked", "checked");

            }
        });
    });

</script>
</html>
