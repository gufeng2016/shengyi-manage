var global={
  "registerData":{},
    "wanServerHost":"http://sq.wan.com",    
    "serverHost":"http://sq.7road.com",    
    "loginUrl":"http://sq.7road.com/game7road/loginSubmit.action",
  "logoutUrl":"http://sq.7road.com/game7road/indexLogout.action",
  "registerUrl":"http://sq.7road.com/game7road/registrationSubmit.action",
  "checkUserNameUrl":"http://sq.7road.com/game7road/checkUserName.action",
  "checkLogin":"http://sq.7road.com/game7road/checkLogin.action",//检测是否已经登陆
  "checkLoginGame":"http://sq.7road.com/game7road/onlineGame/checkLoginGame.action",//检测服务器是否能够进入
  "getLatestServerUrl":"http://sq.7road.com/game7road/onlineGame/acquireId.action",//获取最新能够登陆的区服信息
    "gameUrl":"http://account.7road.com/PlayGame2?g=1&game=S&subGame=0&z=",
    "adStatisticsUrl":"http://sq.7road.com/game7road/enterFromAd.action",
  "actualGameUrl":"http://sq.7road.com/gameStart/gameStart.html?g=1&serverId="
};


//获取某一区所有排行榜

	function getRankData() {
		var serverId=$(".irankServer>span").attr("data-value");

		$.ajax({
			url: global.wanServerHost+"/player/"+serverId+".html?t=" + (+new Date()),
			success: function(data){        
				$("#rankList").html(data);
				$(".irankCondList li").eq($(".irankCond>span").attr("data-index")).click();
			},
			error: function () {
				$("#rankList").html("");
			}
		});
	}
	
	Play.getServerList("sq", {}, function(data){
        if(data.result==0){
			var list = data.serverList,_len=list.length;
			var str ="", str2 = "";
			
			//左侧快速选服
			var $leftQuickBtn = $("#leftQuickBtn");
			var $leftQuickInput = $("#leftQuickInput");			
			var allServer_7road = [];
			
			/*新服推荐*/
			var liCs = "";
			for(var i = 0; i < 4; i++) {
				if (list[i].plat == 1004) {
					if (i == 0 || i == 1) {
						liCs = "class='liCs" + i + "'";
					} else {
						liCs = "";
					}
					str2 += '<li ' + liCs + ' ><span></span><a target="_blank" href="http://sq.wan.com/webgame.shtml?server=' + list[i].index + '"><em>火爆开启</em>' + list[i].name + '</a></li>';
				}
			}
			
			allServer_7road.push(list[0]);
			
			/* 从1开始，神曲每天开一区，新区无法获取数据 */
			for(var i=1; i<_len; i++){
				if (list[i].plat == 1004) {/* 只显示七道区服列表 */					
					str += '<li><a href="javascript:;" data-value="' + list[i].index + '" title="' + list[i].name + '">' + list[i].name + '</a></li>';
					allServer_7road.push(list[i]);
				}
			}
			$(".irankServerList").html(str);
			$(".server ul").html(str2); /* 新服推荐 */
			allServer_7road.reverse();
			var irLia = $(".irankServerList li").find("a");
			$(".irankServer>span").attr("data-value", irLia.attr("data-value")).html(irLia.html());
			getRankData();
			
			/* 快速选服 */
			$leftQuickBtn.click(function(){
			  var leftQuickInputVal = $.trim($leftQuickInput.val()) - 1;
			  
			  if(allServer_7road[leftQuickInputVal]){
				window.open("http://sq.wan.com/webgame.shtml?server="+allServer_7road[leftQuickInputVal].index);
			  }else{
				alert("服务器不存在，请重新输入！");
			  }
			});
			
			$(".iinputBox").on("click", function () {
				var $this = $(this);
				$this.find("label").hide();
				$this.find("input").focus();
			});
			$(".iinputBox input").on("blur", function () {
				var $this = $(this);
				if ($.trim($this.val()) == "") {
					$this.prev().show();
				}
			}).on("focus", function () {
				$(this).prev().hide();
			});
		}
		
		/*选择服务器*/
		var tims;
		$(".irankSelect").on("click", function () {
			var next = $(this).next();
			next.show();			
		}).on("mouseleave", function () {
			var $this = $(this);
			tims = setTimeout(function () {
				$this.next().hide();
			}, 300);
		});
		
		$(".irankSelectList").on("mouseleave", function () {
			$(this).hide();
		}).on("mouseenter", function () {
			clearTimeout(tims);
		});	
		
		$(".irankServerList").on("click", "li", function () {
			var $this = $(this).find("a");

			$(".irankServer>span").text($this.html()).attr("data-value", $this.attr("data-value"));
			$this.parent().parent().hide();
			getRankData();
		});
		
		$(".irankCondList").on("click", "li", function () {
			var $this = $(this),
				index = $this.index();
			
			$(".irankCond>span").html($this.find("a").html()).attr("data-index", index);
			$("#rankList>div").eq(index).siblings().hide();
			$("#rankList>div").eq(index).show();
			$this.parent().hide();
		});
		
    });
	
	/*登陆开始*/
//左边登陆框登陆按钮

function leftSideLogin(){
  
  if(!isLeftLoginOk())
  {
    return false;
  }

  loginFunction($("#uid").val(),$("#pwd").val(),false);
  
}

$(function () {
	$(".login-btn").bind("click",leftSideLogin);

	$("#pwd").bind("keydown",function(event){
	   if (event.keyCode == '13') {
		 event.preventDefault();
		 leftSideLogin();
	   }
	});

});

//刷新页面后检测是否登陆以及获取最新的区服

$(function(){
  
  SQ_COM.userLoginOperate.checkLogin(function(data){
    loginSuccess(data);
  });
  

});






//登陆
function loginFunction(uid,pwd,isModal)
{
    
    SQ_COM.userLoginOperate.login( uid, pwd, function(data){
      loginSuccess(data);

    }, function(data){

    });
    
}
//左侧登陆框条件判断
function isLeftLoginOk()
{
    var flag=true;
    var userNameVal=$("#uid").val(),pwdVal=$("#pwd").val();
 
    if(!userNameVal||!pwdVal)
    {
        alert("帐号或密码不能为空");
        return false;
    }

    return flag;
}

//成功之后
function loginSuccess(data)
{
  
  SQ_COM.loginBox.logged(data);
  
}

function starGame () {
	window.open("http://sq.wan.com/server.shtml");
}


/*注销*/
$(".quit,.indexLogout").live("click",function(){
  
  SQ_COM.userLoginOperate.loginOut(function () {	
	location.reload();
  });
  
});