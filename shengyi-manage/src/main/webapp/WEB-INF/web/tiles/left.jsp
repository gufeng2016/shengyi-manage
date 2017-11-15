<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="config.jsp"%>

<div id="sidebar-wrapper"  >
    <ul id="main-nav">
        <c:forEach items="${sessionScope.authList }" var="menu">
           <c:if test="${menu.parentAuthId == 1}"  >
               <c:if test="${menu.menuDisplay == 1}">
                   <li>
                       <a class="${menu.authId } nav-top-item">${menu.authName }</a>

                       <ul class="nav-top-item-ul" style="display: none">
                           <c:forEach items="${menu.childAuth }" var="item">

                               <c:if test="${item.menuDisplay == 1}" >
                                   <li>
                                       <a class="nav-top-item-ul-a" href="${ctx}${item.url}" itemUlr="${item.url}" authId = "${item.authId}">${item.authName}</a>
                                   </li>
                               </c:if>
                           </c:forEach>
                       </ul>
                   </li>
               </c:if>
           </c:if>
        </c:forEach>
                   
    </ul>


    <!-- End #main-nav -->
</div>

<script type="text/javascript">
    var cookieName = "currentItemUrl";
    $(function(){

        showUrl();

        $(".nav-top-item").on("click", function(){
            var t = $(this);
            t.parent().siblings().children(".nav-top-item-ul").slideUp();
            t.next().slideDown();
            leftRightHeight && leftRightHeight();
        });

        $(".nav-top-item-ul-a").on("click", function(){
            var t = $(this);
            var url = t.attr("authId");
            Cookie.set(cookieName, url, 30);
        });
    });

    function showUrl(){
        $(".nav-top-item-ul-a").each(function(){
            var t = $(this);
            var url = t.attr("authId");
            var cookieUrl = Cookie.get(cookieName);
            if (typeof cookieUrl != 'undefined') {
                if (cookieUrl != '') {
                    if (url == cookieUrl) {
                        t.parent().parent().show();
                        t.css({"background": "#d5eefa", "border-color":"#0088cc"});
                        return;
                    }
                }
            }
        });
    }

</script>