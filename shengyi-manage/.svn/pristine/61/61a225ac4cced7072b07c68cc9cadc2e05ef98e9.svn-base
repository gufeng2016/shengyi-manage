/**
 * Copyright  2014-03-06 14:57:01 第七大道-技术支持部-网站开发组
 * 自主运营平台WEB 上午11:49:20
 * 版本号： v1.0
*/
package com.sz.pojo.manage.log;

import java.util.Date;

import com.sz.common.annotation.PrimaryKey;

/***
 *类描述: OperateLog
 *
 */ 
public class OperateLog{

 	/** 主键ID */
    @PrimaryKey
    private Integer operateId;  
 	/** 操作用户 */
    private String userName;
 	/** ip地址(兼容ipv6) */
    private String ip;  
 	/** 操作URL */
    private String operateUrl;
	/** URL对应的名称 */
	private String operateName;
 	/** 操作时间 */
    private Date operateTime;
	/** 请求方法 */
	private String requestMethod;
    /** 请求参数 */
    private String requestParameters;
    /** 返回结果 */
    private String responseResult;

    private Integer deleteState;//是否可用，默认0可用，-1表示已经删除

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    public String getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(String requestParameters) {
        this.requestParameters = requestParameters;
    }

    public String getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(String responseResult) {
        this.responseResult = responseResult;
    }

    public Integer getOperateId() {
		return operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperateUrl() {
		return operateUrl;
	}

	public void setOperateUrl(String operateUrl) {
		this.operateUrl = operateUrl;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}


    @Override
    public String toString() {
        return "OperateLog{" +
                "operateId=" + operateId +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", operateUrl='" + operateUrl + '\'' +
                ", operateName='" + operateName + '\'' +
                ", operateTime=" + operateTime +
                ", requestMethod='" + requestMethod + '\'' +
                '}';
    }
}