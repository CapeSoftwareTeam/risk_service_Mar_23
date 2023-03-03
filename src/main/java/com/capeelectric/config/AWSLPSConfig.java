package com.capeelectric.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Component
public class AWSLPSConfig {

	@Value("${aws.email.send.pdf}")
	private String sendEmailWithPDF;
	
	/**
	 * @return the sendEmailWithPDF
	 */
	public String getSendEmailWithPDF() {
		return sendEmailWithPDF;
	}

	/**
	 * @param sendEmailWithPDF the sendEmailWithPDF to set
	 */
	public void setSendEmailWithPDF(String sendEmailWithPDF) {
		this.sendEmailWithPDF = sendEmailWithPDF;
	}

}
