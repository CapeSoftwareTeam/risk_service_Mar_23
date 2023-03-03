package com.capeelectric.execption;

public class RiskFinalReportException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public RiskFinalReportException() {
	}

	public RiskFinalReportException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
