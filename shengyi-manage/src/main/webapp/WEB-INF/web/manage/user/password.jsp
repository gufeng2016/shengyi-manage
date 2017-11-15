<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp"%>
<html>
<body>
<div class="content-box-header">
    <h3 style="cursor: s-resize;">增加用户</h3>
    <div class="clear"></div>
</div>
<div class="content-box-content">
    <form cssClass="form-horizontal" method="post" >
        <div class="control-group">
            <label class="control-label"> 原始密码： </label>
            <div class="controls">
                <input type="password" cssClass="small-input text-input" name="oldPassord" />
                <span id="oldPassordError"></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"> 新密码： </label>
            <div class="controls">
                <input type="password" cssClass="small-input text-input" name="newPassword" />
                <span id="newPasswordError"></span>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label"> 重复新密码： </label>
            <div class="controls">
                <input type="password" cssClass="small-input text-input" name="newPasswordRep" />
                <span id="newPasswordRepError"></span>
            </div>
        </div>


        <div class="control-group">
            <div class="controls">
                <button type="button" onclick="passwordBtn()" class="btn">提交</button>
            </div>
        </div>
    </form>
</div>
</body>
<script type="application/x-javascript">
    function passwordBtn(){
        var oldPassord = $("input[name = 'oldPassord']").val();
        var newPassword = $("input[name = 'newPassword']").val();
        var newPasswordRep = $("input[name = 'newPasswordRep']").val();
        var errorClass = " input-notification error png_bg";

        if (oldPassord == "") {
            $("#oldPassordError").attr("class", errorClass).html("请填写原始密码");
            return;
        }else{
            $("#oldPassordError").removeAttr("class").empty();
        }

        if (newPassword == "") {
            $("#newPasswordError").attr("class", errorClass).html("请填写新密码");
            return;
        }else{
            $("#newPasswordError").empty().removeAttr("class");
        }

        if (newPasswordRep == "") {
            $("#newPasswordRepError").attr("class", errorClass).html("请填写重复新密码");
            return;
        }else{
            $("#newPasswordRepError").empty().removeAttr("class");
        }

        if (newPassword != newPasswordRep) {
            $("#newPasswordRepError").attr("class", errorClass).html("两次密码填写不一样");
            return;
        }else{
            $("#newPasswordRepError").empty().removeAttr("class");
        }

        $.ajax({
            url:"${ctx}/user/password",
            type:"post",
            data:{"oldPassord":oldPassord, "newPassword":newPassword, "newPasswordRep":newPasswordRep},
            success:function(data){
                if(data.code == 115){
                    alert("修改密码成功,正在退出系统");
                    location.href="${ctx}/logout";
                }else{
                    alert(data.message);
                }
            }
        })
    };


</script>



</html>
