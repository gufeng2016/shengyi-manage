<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/displaytag-el.tld" prefix="display-el" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld" %>
<%@ taglib prefix="spt" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp" %>
<html>
<body>

    <div class="content-box-header">
        <h3 style="cursor: s-resize;">玩家游戏报表查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">

        <div>
            <sp:form class="form-horizontal" action="${ctx}/report/player/query" method="post" commandName="reportRequestParams">

            <div class="control-group row">
                <label class="control-label" > 游戏： </label>
                <div class="controls">
                    <sp:select id="gameSelect" cssClass="small-input"  path="gameId" multiple="fale">
                    	<sp:options items="${requestScope.gameList}" itemValue="id" itemLabel="gameName"  />
                    </sp:select>
                </div>
            </div>

            <div class="control-group row">
                <label class="control-label"> 游戏轮次：  </label>

                <div class="controls">
                   	 <sp:input class="col-sm-2 text-input" path="beginGameTerm" maxlength="20" pattern="^\d+$"/>轮
                   	 &nbsp;&nbsp;-&nbsp;&nbsp;
                   	 <sp:input class="col-sm-2 text-input" path="endGameTerm" maxlength="20" pattern="^\d+$"/>轮
                </div>
            </div>
                
         
             <div class="control-group row">
                 <div class="controls">
                     <button type="submit" class="btn button">查询</button>
                 </div>
             </div>
            </sp:form>
           </div>

        <div style="overflow:scroll;">
			 <pg:pager url="${ctx}/report/player/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList" style="width:2600px">
                    	<display:column property="id" title="ID"/>
                        <display:column property="wechatId" title="微信id"/>
                        <display:column property="wechatNick" title="微信昵称"/>
                        <display:column property="gameId" title="游戏id"/>
                        <display:column property="groupId" title="微信群Id"/>
                        <display:column property="operateId" title="运营人微信id"/>
                        <display:column property="serialNumber" title="游戏轮次"/>
                        <display:column property="isZhuangDesc" title="是否庄家"/>
                        <display:column property="zhuangScore" title="上庄积分"/>
                        <display:column property="stakeTime" title="下注时间"/>
                        <display:column property="isStakeDesc" title="是否下注"/>
                        <display:column property="stakeSocre" title="下注积分"/>
                        <display:column property="sendId" title="红包id"/>
                        <display:column property="sendMoney" title="红包金额"/>
                        <display:column property="grabSendTime" title="抢包时间"/>
                        <display:column property="balanceMoney" title="余额"/>
                        <display:column property="preBalance" title="上一局余额"/>
                        <display:column property="moneyChange" title="余额变动"/>
                        <display:column property="bonus" title="奖励"/>
                        <display:column property="winOrLose" title="庄家输赢"/>
                        <display:column property="cardShow" title="牌型"/>
                        <display:column property="deduction" title="系统抽水"/>
                        <display:column property="totalScore" title="庄总积分"/>
                        <display:column property="rate" title="倍率"/>
                        <display:setProperty name="basic.empty.showtable" value="true"/>
                    </display:table>
                </pg:item>
                <jsp:include page="../../common/page.jsp" flush="true" />

            </pg:pager>
		</div>
    </div>
     <script style="text/javascript">
		//初始化select2插件
		$('#gameSelect').select2();
	</script>
</body>
</html>
