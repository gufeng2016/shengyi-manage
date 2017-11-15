
package com.sz.common.exception;

/**
 * @author vic
 * @version 1.0
 * @date 2013-5-27
 * 自定义异常对象
 */
public class AppException extends Exception {

	// serial version id
	private static final long serialVersionUID = 9049160567189835471L;

	/**
	 * 错误码，定义在ErrorConstant中
	 */
	private String errorCode;

	/**
	 * 异常描述信息
	 */
	private String description;

	/**
	 * 异常参数
	 */
	private Object[] errorArgs;

	/**
	 * 导致异常的根本因素
	 */
	private Throwable cause;

	/**
	 *
	 * @param errCode
	 * @param description
	 */
	public AppException(String errCode, String description) {
		super(description);
		this.errorCode = errCode;
		this.description = description;
	}

	public AppException(String errCode) {
		super(errCode);
		this.errorCode = errCode;
		this.description = errCode;

	}


	/**
	 */

	public AppException() {
		super();
	}

	/**
	 *
	 * @param errCode
	 * @param description
	 * @param cause
	 */
	public AppException(String errCode, String description, Throwable cause) {
		super(description, cause);
		this.errorCode = errCode;
		this.description = description;
		this.cause = cause;
	}

	/**
	 *
	 * @param errCode
	 * @param errorArgs
	 * @param description
	 */
	public AppException(String errCode, Object[] errorArgs, String description) {
		super(description);
		this.errorCode = errCode;
		this.description = description;
		this.errorArgs = errorArgs;
	}

	/**
	 *
	 * @param errCode
	 * @param errorArgs
	 * @param description
	 * @param cause
	 */
	public AppException(String errCode, Object[] errorArgs, String description, Throwable cause) {
		super(description, cause);
		this.errorCode = errCode;
		this.description = description;
		this.cause = cause;
		this.errorArgs = errorArgs;
	}

	/**
	 * 返回错误码
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置错误码
	 *
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 返回描述信息
	 *
	 * @return the error message description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述信息
	 *
	 * @param description
	 *            the error message description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回root cause
	 *
	 * @return the root cause of exception.
	 */
	public Throwable getCause() {
		return cause;
	}

	/**
	 * 设置root cause
	 *
	 * @param cause
	 *            the root cause of exception to set.
	 */
	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	/**
	 *
	 * {@inheritDoc} overridden:
	 *
	 * @see Throwable#printStackTrace()
	 *
	 */
	public void printStackTrace() {
		super.printStackTrace();
		if (this.cause != null) {
			System.err.println("<---- Caused by:");
			this.cause.printStackTrace();
			System.err.println("---->");
		}
	}

	/**
	 *
	 * {@inheritDoc} overridden:
	 *
	 * @see Throwable#printStackTrace(java.io.PrintStream)
	 *
	 */
	public void printStackTrace(java.io.PrintStream ps) {
		super.printStackTrace(ps);
		if (this.cause != null) {
			ps.println("<---- Caused by:");
			this.cause.printStackTrace(ps);
			ps.println("---->");
		}
	}

	/**
	 *
	 * {@inheritDoc} overridden:
	 *
	 * @see Throwable#printStackTrace(java.io.PrintWriter)
	 *
	 */
	public void printStackTrace(java.io.PrintWriter pw) {
		super.printStackTrace(pw);
		if (this.cause != null) {
			pw.println("<---- Caused by:");
			this.cause.printStackTrace(pw);
			pw.println("---->");
		}
	}

	/**
	 *
	 * {@inheritDoc} overridden:
	 *
	 * @see Throwable#toString()
	 *
	 */
	public String toString() {
		if (this.cause == null) {
			return super.toString();
		} else {
			return super.toString() + "<---- Caused by: " + cause.toString() + " ---->";
		}
	}

	/**
	 * 返回异常参数
	 * @return
	 */
	public Object[] getErrorArgs() {
		return errorArgs;
	}

	/**
	 * 设置异常参数
	 * @param errorArgs
	 */
	public void setErrorArgs(Object[] errorArgs) {
		this.errorArgs = errorArgs;
	}
}
