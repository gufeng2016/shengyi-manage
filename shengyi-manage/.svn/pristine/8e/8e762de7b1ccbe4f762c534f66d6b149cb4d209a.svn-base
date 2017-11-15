<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sp"%>

<div class="content-box-header">
    <h3 style="cursor: s-resize;">编辑配置</h3>
    <div class="clear"></div>
</div>

<div class="content-box-content">
    <form class="form-horizontal" action="update" method="post">
        <input type="hidden" name="id" value="${entity.id}"/>
        <div class="control-group">
            <label class="control-label">键</label>
            <div class="controls">
                <input type="text" id="key" name="key" placeholder="关键值" required pattern=".{1,}" value="${entity.key}" maxlength="40">
                <span class="help-inline">*必填, 不能重复</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">值</label>
            <div class="controls">
                <textarea class="input-xxlarge" name="value" rows="5" required="required" maxlength="355">${entity.value}</textarea>
                <span class="help-inline">输入信息</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">备注</label>
            <div class="controls">
 
                <input type="text" id="comment" name="comment" class="input-xxlarge" placeholder="备注" value="${entity.comment}" maxlength="255">
                <span class="help-inline">备注信息</span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <a href="javascript:fnSubmit();" class="btn">确定</a>
                <a href="javascript:window.history.back()" class="btn">返回</a>
            </div>
        </div>
    </form>
</div>

<script style="text/javascript">
    function fnSubmit(){
        var valid = true;
        if($('#key').val().length == 0){
            alert('关键码不能为空');
            valid = false;
        }
        if (valid){
            $.ajax({
                type:"POST",
                dataType:"JSON",
                url:"update",
                data: $("form").serialize(),
                beforeSend: function(){},
                success: function(msg){
                    if(msg && msg.code == 1){
                        window.location.href = 'query';
                    } else {
                        alert(msg.message);
                    }
                },
                error: function(msg){}
            });
        }
    }
</script>