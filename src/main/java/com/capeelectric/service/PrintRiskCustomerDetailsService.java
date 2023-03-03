package com.capeelectric.service;

import com.capeelectric.exception.RiskAssessmentException;

public interface PrintRiskCustomerDetailsService {

	public String printRiskCustomerDetails(String userName, Integer riskId) throws RiskAssessmentException;

}
