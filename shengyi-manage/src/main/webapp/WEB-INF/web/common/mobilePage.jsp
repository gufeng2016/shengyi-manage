<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>

<span class="content">
     <pg:index export="total=itemCount">

         <pg:prev export="prevPageUrl=pageUrl">
             <a href="<%= prevPageUrl %>" class="gotoPage">前一页</a>
         </pg:prev>

         <pg:next export="nextPageUrl=pageUrl">
             <a href="<%= nextPageUrl %>" class="gotoPage">后一页</a>
         </pg:next>

     </pg:index>
     </span>
