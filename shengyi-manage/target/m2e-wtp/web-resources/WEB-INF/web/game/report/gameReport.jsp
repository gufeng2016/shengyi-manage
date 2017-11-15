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
        <h3 style="cursor: s-resize;">游戏报表查询</h3>
        <div class="clear"></div>
    </div>
    <div class="content-box-content">

        <div>
            <sp:form class="form-horizontal" action="${ctx}/report/game/query" method="post" commandName="reportRequestParams">

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
			 <pg:pager url="${ctx}/report/game/query" items="${requestScope.paginationResult.total}"
                      maxPageItems="${requestScope.paginationResult.pageSize}" isOffset="true"
                      export="pageOffset, currentPageNumber=pageNumber" index="center" scope="request">
                <pg:item>
                    <display:table class="its" name="requestScope.paginationResult.resultList"  style="width:3000px">
                    	<display:column property="id" title="ID"/>
                        <display:column property="gameId" title="游戏Id"/>
                        <display:column property="gameName" title="游戏名称"/>
                        <display:column property="serialNumber" title="游戏轮次"/>
                        <display:column property="groupId" title="微信群Id"/>
                        <display:column property="groupName" title="微信群名称"/>
                        <display:column property="playerNum" title="玩家数量"/>
                        <display:column property="zhuangWechatId" title="庄家微信号"/>
                        <display:column property="zhuangWechatNick" title="庄家微信昵称"/>
                        <display:column property="zhuangScore" title="庄家积分"/>
                        <display:column property="operateWechatId" title="运营微信号"/>
                        <display:column property="playTime" title="开始时间"/>
                        <display:column property="playStatusDesc" title="状态"/>
                        <display:column property="effectiveNum" title="有效下注数量"/>
                        <display:column property="invalidNum" title="无效下注数量"/>
                        <display:column property="stakeNum" title="总下注人数"/>
                        <display:column property="sendId" title="红包id"/>
                        <display:column property="sendMoney" title="实发红包金额"/>
                        <display:column property="redMoney" title="应发红包金额"/>
                        <display:column property="redNum" title="应发红包数量"/>
                        <display:column property="stakeScore" title="总下注积分"/>
                        <display:column property="suohaScore" title="梭哈总注"/>
                        <display:column property="headTime" title="头包时间"/>
                        <display:column property="footTime" title="尾包时间"/>
                        <display:column property="joinPool" title="本期入池"/>
                        <display:column property="outBonus" title="本期发出"/>
                        <display:column property="surplusBonus" title="奖池剩余"/>
                        <display:column property="gameTips" title="手气最佳微信id"/>
                        <display:column property="eatNum" title="庄吃闲数量"/>
                        <display:column property="loseNum" title="庄输闲数量"/>
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
