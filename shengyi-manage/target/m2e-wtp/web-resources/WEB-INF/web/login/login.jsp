<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../tiles/config.jsp"%>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<meta charset="utf-8" />
<title>高级板材管理</title>
<link href="<%=path%>/resources/css/bootstrap.css" rel="stylesheet" />
<%-- <link href="<%=path%>/resources/css/bootstrap-responsive.css" rel="stylesheet" /> --%>
<%-- <link href="<%=path%>/resources/css/page.css" rel="stylesheet" /> --%>
<script type="text/javascript" src="${ctx}/resources/js/jquery-1.7.1.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/js/bootstrap.js"></script> --%>
<style type="text/css">
	*{
		margin:0px;
		padding:0px;
	}
	body{
		background: url("<%=path%>/resources/images/login/pic1.jpg");
	}
	input{
		height:30px;
		border-radius: 5px;
	}
	.big_box{
		width:350px;
		height:260px;
		border:1px solid #67C754;
		margin:300px auto;
		background:#97E080;
		border-radius:10px;
		box-shadow:15px 15px 15px 8px ;
	}
	.big_box .fullInput{
		width:210px;
		margin:18px auto;
	}
	.captchaSpan{
		width:115px;
		height:30px;
		border-radius: 5px;
	}
	.captchaImg{
		width:75px;
		height:30px;
		border-radius: 5px; 
		vertical-align:top;
	}
	.footer{
		width:410px;
		margin:auto;
		color:#fff;
	}
</style>
</head>

<body >
	
	<div class="big_box">
		<form class="form-inline" action="${ctx}/login" method="post">
			<p class="fullInput" style="text-align:center"><font style="color:#173AF1;font-size:26px;font-weight: bold;font-family: 楷体;letter-spacing:12px;">登陆</font></p>
			<p class="fullInput"><input class="form-control" required pattern=".{1,20}" name="name" id="username" type="text" placeholder="用户名" ></p>
			<p class="fullInput"><input class=" input" required pattern=".{1,20}" name="password" id="password" type="text" placeholder="密 码" ></p>
			<p class="fullInput">
				<input class="captchaSpan" type="text" name="captcha" placeholder="验证码">
	            <img class="captchaImg" src="${ctx }/captcha" alt="验证码" id="kaptchaImage"/>
	        </p>
	        <p  class="fullInput" style="text-align:center;">
		        <button type="submit"  class="btn btn-info" style="withd:100px;letter-spacing:8px;font-family: 楷体;">登 陆 </button>
		    </p>
	    </form>
		<p class="fullInput" style="margin-top:20px;padding-top:20px;text-align:center;">
			<font color="red">${error }</font>
		</p>
	</div>
	<!--底部-->

	<div class="footer">Copyright © 2017-2018  Powered by
		大漠孤风  版权所有 翻版必究</div>
	<script>
		$(function() {
			$('#kaptchaImage').click(
					function() {//生成验证码  
						$(this).hide().attr(
								'src',
								'${ctx}/captcha?'
										+ Math.floor(Math.random() * 100))
								.fadeIn();
					})
		});

		function _resHe() {
			var _heg = $(window).height();
			$(".manager-login").height(_heg - 100);
		}
		window.onload = window.onresize = _resHe;
	</script>
</body>
</html>
