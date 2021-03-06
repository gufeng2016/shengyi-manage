<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<head>
<style type="text/css">
	.ratio-label{margin: 0 5px}
	#error_content{
		text-align:center;
		color:red;
		font-size:20px;
		margin:50px auto;
	}
</style>
</head>
<body>

    <div class="content-box-content">
        <div id="error_content">${empty errorMsg ? "系统错误请联系管理员" : errorMsg}
        </div>
    </div>
</body>
</html>
