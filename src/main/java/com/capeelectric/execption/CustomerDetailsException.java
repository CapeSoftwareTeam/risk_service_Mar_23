package com.capeelectric.execption;

public class CustomerDetailsException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public CustomerDetailsException() {

	}

	public CustomerDetailsException(String message) {
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
