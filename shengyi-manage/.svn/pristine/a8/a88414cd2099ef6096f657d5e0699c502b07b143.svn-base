<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<jsp:useBean id="currentPageNumber" type="java.lang.Integer" scope="request"/>


<br>
    <span class="tabletitle">Result&nbsp;Page:&nbsp;</span>
    <span class="content">
     <pg:index export="total=itemCount">
         <pg:page export="first,last">
             <%= first %> - <%= last %> of <%= total %> records.&nbsp;&nbsp;
         </pg:page>
         <pg:first export="firstPageUrl=pageUrl" unless="current">
             <a href="<%= firstPageUrl %>" class="gotoPage" >第一页</a>
         </pg:first>
         <pg:prev export="prevPageUrl=pageUrl">
             <a href="<%= prevPageUrl %>" class="gotoPage">前一页</a>
         </pg:prev>
         <pg:pages><%
             if (pageNumber == currentPageNumber) {
         %> <b><%= pageNumber %></b> <%
         } else {
         %> <a href="<%= pageUrl %>" class="gotoPage"><%= pageNumber %></a> <%
             }
         %></pg:pages>
         <pg:next export="nextPageUrl=pageUrl">
             <a href="<%= nextPageUrl %>" class="gotoPage">后一页</a>
         </pg:next>
         <pg:last export="lastPageUrl=pageUrl" unless="current">
             <a href="<%= lastPageUrl %>" class="gotoPage">末页</a>
         </pg:last>
     </pg:index>
    </span>
<br>


<script>
$(function(){
    $(".gotoPage").on("click", function(event){
        event.preventDefault();
        var param = $("form").serialize();
        var href = $(this).attr("href");
        if (href != null) {
            var index = href.indexOf("?");
            if(index != -1){
                href += "&" + param;
            } else {
                href += "?" + param;
            }
            location.href =  href;
        }
    })
});
</script>