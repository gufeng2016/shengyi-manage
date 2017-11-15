<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header >
    <script type="text/javascript">

        function logout() {
            var confirm = window.confirm("确定要退出系统吗?");
            if (confirm) {
                location.href="${ctx}/logout";
            }
        }


        function mdPassword(){
            var confirm = window.confirm("确定要修改用户密码吗?");
            if (confirm) {
                location.href="${ctx}/user/password";
            }
        }
    </script>

    <div style="margin:auto;">
        <div style="float:left;margin:0 25px;">
            <img id="logo" src="${ctx}/resources/images/logo.png" alt="logo" style="width:195px;height:60px;border-radius:5px;">
            <span>欢迎您，<span style="color: red;font-size:18px">${sessionScope.user.userNameCn}&nbsp;</span>
            <a href="javascript:mdPassword();">修改密码</a>
            <a href="javascript:logout();">退出</a></span>
        </div>
    </div>
</header>

