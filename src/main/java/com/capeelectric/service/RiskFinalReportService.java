package com.capeelectric.service;

import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.RiskFinalReportException;
import com.capeelectric.model.CustomerDetails;
import com.capeelectric.model.RiskFinalReport;


public interface RiskFinalReportService {
	
	List<CustomerDetails> retrieveListOfCustomerDetails(String userName) throws RiskFinalReportException;

	Optional<RiskFinalReport> retrieveRiskReports(String userName, Integer emcId) throws RiskFinalReportException;
	
	List<CustomerDetails> retrieveAllCustomerDetails() throws RiskFinalReportException;

}
