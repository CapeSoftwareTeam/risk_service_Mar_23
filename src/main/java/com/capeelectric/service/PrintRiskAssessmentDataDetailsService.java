package com.capeelectric.service;

import com.capeelectric.exception.RiskAssessmentException;

public interface PrintRiskAssessmentDataDetailsService {
	
	public void printRiskAssessmentDataDetails(String userName, Integer riskId) throws RiskAssessmentException;

}
