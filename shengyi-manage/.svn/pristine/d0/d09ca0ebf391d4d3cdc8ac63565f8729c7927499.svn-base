<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp"%>
<html>
<body>

    <!-- Start Content Box -->
    <div class="content-box-header">
        <h3 style="cursor: s-resize;">增加角色</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">
        <sp:form cssClass="form-horizontal" action="${ctx}/role/add"   method="post" commandName="role" >
            <div class="control-group">
                <label class="control-label"> 角色名： </label>
                <div class="controls">
                    <sp:input cssClass="small-input text-input" path="roleName" size="50" maxlength="20" />
                    <span><sp:errors cssClass="input-notification error png_bg" path="roleName" /></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"> 所属角色： </label>
                <div class="controls">
                　  <sp:select cssClass="small-input"  path="parentId" id="parentRole" >
                        <sp:option value="" label="--请选择所属角色--"/>
                        <sp:options items="${roleList}" itemValue="roleId"  itemLabel="roleName" />
                    </sp:select>

                    <span><sp:errors cssClass="input-notification error png_bg" path="parentId" /></span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"> 角色描述： </label>
                <div class="controls">
                    <sp:textarea cols="100" rows="10"  path="roleDesc" />
                    <span><sp:errors cssClass="input-notification error png_bg" path="roleDesc" /></span>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn button">提交</button>
                </div>
            </div>
        </sp:form>
    </div>


</body>

<script type="application/javascript" >
$(function(){

    var parentRole = $("#parentRole");
    if(parentRole.val() != 1 && parentRole.val() != 0 ){
        $("#site").hide();
    }

    parentRole.on("change", function(){
        var val = parentRole.val();
        if( val != 1 & val != 0){
            $("#site").hide();
        }else{
            $("#site").show();
        }
    })
});

</script>

</html>
