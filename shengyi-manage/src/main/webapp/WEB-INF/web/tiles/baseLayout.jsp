<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>高级板材管理后台</title>
        <!--                       CSS                       -->


        <link href="<%=path%>/resources/css/display/displaytag.css" rel="stylesheet" />

        <%--<link rel="stylesheet" href="<%=path%>/resources/css/reset.css" type="text/css" media="screen" />--%>
        <link rel="stylesheet" href="<%=path%>/resources/css/bootstrap.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="<%=path%>/resources/css/style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="<%=path%>/resources/css/invalid.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="<%=path%>/resources/upImgDialog/uploadify.css" type="text/css" media="screen" />
        <link href="${cssPath}/select2.min.css" rel="stylesheet" />

        <!--                       Javascripts                       -->
        <!-- jQuery -->
        <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<%=path%>/resources/js/common.js"></script>
        <script type="text/javascript" src="<%=path%>/resources/js/sdmenu.js"></script>
        <script type="text/javascript" src="<%=path%>/resources/js/bootstrap.js"></script>
        <script type="text/javascript" src="<%=path%>/resources/upImgDialog/jquery.uploadify.js"></script>
        <script type="text/javascript" src="<%=path%>/resources/upImgDialog/upimg.js"></script>
        <script type="text/javascript" src="<%=path%>/resources/js/ztree_type.js"></script>
        <script type="text/javascript" src="${jsPath}/select2.full.min.js"></script>


        <!-- z-tree -->
        <link rel="stylesheet" href="${ctx}/resources/z-tree/css/zTreeStyle/zTreeStyle.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="${ctx}/resources/z-tree/css/demo.css" type="text/css" media="screen" />
        <script type="text/javascript" src="${ctx}/resources/z-tree/js/jquery.ztree.core-3.5.js"></script>
        <script type="text/javascript" src="${ctx}/resources/z-tree/js/jquery.ztree.excheck-3.5.js"></script>


        <!-- ueditor -->
        <script type="text/javascript">
            URL = window.UEDITOR_HOME_URL|| "<%=path%>/resources/ueditor/";
        </script>
        <script type="text/javascript" src="<%=path%>/resources/ueditor/editor_all.js"></script>
        <script type="text/javascript" src="<%=path%>/resources/ueditor/editor_config.js?version=2"></script>
        <%--日期选择--%>
        <script type="text/javascript" src="<%=path%>/resources/My97DatePicker/WdatePicker.js"></script>
        <%--设置JS上下文路径--%>
		<script type="text/javascript">
			var ctx = '${ctx}';
		</script>
	</head>
	<body>
        <div class="header">
            <tiles:insertAttribute name="header" ignore="true" />
        </div>
        <div id="body-wrapper" >
            <div id="sidebar">
                <tiles:insertAttribute name="left" ignore="true" />
            </div>

            <div id="main-content" >
                <div class="clear"></div>
                <div class="content-box">

                    <tiles:insertAttribute name="body" ignore="true" />
                </div>
                <div class="clear" style="height: 20px;"></div>

            </div>
        </div>

        <div class="clear"></div>
        <div id="footer">
            <tiles:insertAttribute name="footer" ignore="true" />
        </div>
	</body>
</html>