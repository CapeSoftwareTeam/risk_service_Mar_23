package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.CustomerDetailsException;
import com.capeelectric.model.CustomerDetails;

public interface CustomerDetailsService {
	
	public CustomerDetails saveCustomerDetails(CustomerDetails customerDetails) throws CustomerDetailsException;

	public List<CustomerDetails> retrieveCustomerDetails(String userName, Integer riskId) throws CustomerDetailsException;

	public void updateCustomerDetails(CustomerDetails customerDetails) throws CustomerDetailsException;
	
	public void updateRiskAssessmentCustomerDetailsStatus(CustomerDetails customerDetails) throws CustomerDetailsException;
	
	public List<CustomerDetails> retriveAllCustomerDetails() throws CustomerDetailsException;

	public CustomerDetails findingEmailAndProject(String userName, String projectName) throws CustomerDetailsException;

}
