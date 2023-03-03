package com.capeelectric.execption;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capeelectric.exception.RiskAssessmentException;
import com.capeelectric.util.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ CustomerDetailsException.class })
	public ResponseEntity<ErrorMessage> handleCustomerDetailsException(CustomerDetailsException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ RiskAssessmentException.class })
	public ResponseEntity<ErrorMessage> handleRiskAssessmentException(RiskAssessmentException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ RiskFinalReportException.class })
	public ResponseEntity<ErrorMessage> handleRiskFinalReportException(RiskFinalReportException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}