<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" scope="request" value="${pageContext.request.contextPath}" />
<c:set var="imagePath" scope="request" value="${ctx}/resources/images" />
<c:set var="cssPath" scope="request" value="${ctx}/resources/css" />
<c:set var="jsPath" scope="request" value="${ctx}/resources/js" />