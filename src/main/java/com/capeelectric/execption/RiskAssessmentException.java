package com.capeelectric.execption;

public class RiskAssessmentException extends Throwable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public RiskAssessmentException() {

	}

	public RiskAssessmentException(String message) {
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
