<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp"%>
<style>
    .clearfix{ zoom:1}
    .clearfix:after{
        visibility: hidden;
        clear: both;
        font-size: 0;
        height: 0;
        content: "";
        display: block;
    }
    #main-content ul li{
        float: left;
        clear: both;
        width: 100%;
    }
    #main-content ul li label{ display: inline-block;}
    #main-content ul li ul{ clear:both;margin-right: 10px}
    #main-content ul li ul li{
        float:left;
        clear: none;

        width: auto;

    }

    #main-content ul li ul li *{ float: left; margin-right: 5px;}

</style>



<sp:form action="${ctx}/role/auth/allocation" method="post" commandName="role">
    <sp:hidden path="roleId" />

    <div class="control-group">
        <div class="controls">
            <ul data-toggle="dd">
                <c:forEach items="${requestScope.authList}" var="auth">
                    <li>
                       <sp:checkbox path="authIds" cssClass="top_auth" label="${auth.authName }" value="${auth.authId }" />
                       <ul class="clearfix">
                            <c:forEach items="${auth.childAuth }" var="item">
                               <li> <sp:checkbox path="authIds" label="${item.authName }" value="${item.authId }" /></li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="clear"></div>
    <div class="control-group">
        <div class="controls" style="text-align: center">
            <button type="submit" class="btn button">提交</button>
        </div>
    </div>
</sp:form>


<script type="text/javascript">
    $(".top_auth").on("click", function (){
        if ($(this).attr("checked")) {

            $(this).siblings("ul").children().children().each(function(){
                if($(this).attr("type") == "checkbox"){
                    $(this).attr("checked", "checked");
                }
            });
        } else{
           // $(this).next().children(":checkbox").removeAttr("checked");
            $(this).siblings("ul").children().children().each(function(){
                if($(this).attr("type") == "checkbox"){
                    $(this).attr("checked", false);
                }
            });
        }
    });
</script>
