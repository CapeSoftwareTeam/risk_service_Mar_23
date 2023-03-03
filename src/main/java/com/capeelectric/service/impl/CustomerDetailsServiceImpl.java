package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CustomerDetailsException;
import com.capeelectric.model.CustomerDetails;
import com.capeelectric.model.License;
import com.capeelectric.repository.CustomerDetailsRepository;
import com.capeelectric.repository.LicenseRepository;
import com.capeelectric.service.CustomerDetailsService;
import com.capeelectric.util.UserFullName;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsServiceImpl.class);

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private LicenseRepository licenseRepository;

	private CustomerDetails customerData;

	@Override
	public CustomerDetails saveCustomerDetails(CustomerDetails customerDetails) throws CustomerDetailsException {

		if (customerDetails != null && customerDetails.getUserName() != null) {

			List<CustomerDetails> customerDetailsRepo = customerDetailsRepository
					.findByOrganisationNameAndProjectName(customerDetails.getOrganisationName(), customerDetails.getProjectName());
			logger.debug("Customer Details Repo data available");

			if (customerDetailsRepo.isEmpty()) {
				customerDetails.setStatus("Active");
				customerDetails.setCreatedDate(LocalDateTime.now());
				customerDetails.setUpdatedDate(LocalDateTime.now());
				customerDetails.setCreatedBy(userFullName.findByUserName(customerDetails.getUserName()));
				customerDetails.setUpdatedBy(userFullName.findByUserName(customerDetails.getUserName()));
				customerDetails.setAllStepsCompleted("step-1 completed");
				logger.info("Ended addCustomersDetails function");
				decreaseLicense(customerDetails.getUserName());
				return customerDetailsRepository.save(customerDetails);
				
			} else {
				logger.error("Organization:  " + customerDetails.getOrganisationName()
						+ "& Project Name: "
						+ customerDetails.getProjectName()+
						" is already created for this User Name: " + customerDetails.getUserName());
				throw new CustomerDetailsException("Organization:  " + customerDetails.getOrganisationName()
				+ "& Project Name: "
				+ customerDetails.getProjectName()+
				" is already created for this User Name: " + customerDetails.getUserName());
			}

		} else {
			logger.error("Invalid Inputs");
			throw new CustomerDetailsException("Invalid Inputs");
		}

	}
	
	private void decreaseLicense(String userName) throws CustomerDetailsException {
		Optional<License> riskLicenseRepo = licenseRepository.findByUserName(userName);
		if(riskLicenseRepo.isPresent()) {
			riskLicenseRepo.get().setRiskNoOfLicence(Integer.toString((Integer.parseInt(riskLicenseRepo.get().getRiskNoOfLicence()) - 1)));
			licenseRepository.save(riskLicenseRepo.get());
			logger.debug("Risk Assessment license decresed");
		}
	}

	@Override
	public List<CustomerDetails> retrieveCustomerDetails(String userName, Integer riskId)
			throws CustomerDetailsException {
		if (userName != null && !userName.isEmpty() && riskId != null) {
			List<CustomerDetails> customerDetailsRepo = customerDetailsRepository.findByUserNameAndRiskId(userName,
					riskId);
			if (customerDetailsRepo != null && !customerDetailsRepo.isEmpty()) {
				return customerDetailsRepo;
			} else {
				logger.error("Given RiskAssessmentId doesn't exist in CustomerDetails");
				throw new CustomerDetailsException("Given RiskAssessmentId doesn't exist in CustomerDetails");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new CustomerDetailsException("Invalid Inputs");
		}
	}

	@Override
	public void updateCustomerDetails(CustomerDetails customerDetails) throws CustomerDetailsException {
		if (customerDetails != null && customerDetails.getUserName() != null && customerDetails.getRiskId() != null) {
			Optional<CustomerDetails> customerDetailsRepo = customerDetailsRepository
					.findByRiskId(customerDetails.getRiskId());
			if (customerDetailsRepo.isPresent()
					&& customerDetailsRepo.get().getRiskId().equals(customerDetails.getRiskId())) {
				customerDetails.setUpdatedDate(LocalDateTime.now());
				customerDetails.setUpdatedBy(userFullName.findByUserName(customerDetails.getUserName()));
				customerDetailsRepository.save(customerDetails);
			} else {
				logger.error("Given RiskAssessment Id is Invalid");
				throw new CustomerDetailsException("Given RiskAssessment Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new CustomerDetailsException("Invalid Inputs");
		}
	}

	@Transactional
	@Override
	public void updateRiskAssessmentCustomerDetailsStatus(CustomerDetails customerDetails)
			throws CustomerDetailsException {
		logger.info("Called updateRiskAssessmentCustomerDetailsStatus function");

		if (customerDetails != null && customerDetails.getRiskId() != null && customerDetails.getRiskId() != 0) {
			Optional<CustomerDetails> customerDetailsRepo = customerDetailsRepository
					.findByRiskId(customerDetails.getRiskId());
			if (customerDetailsRepo.isPresent()
					&& customerDetailsRepo.get().getRiskId().equals(customerDetails.getRiskId())) {
				customerData = customerDetailsRepo.get();
				customerData.setStatus("InActive");
				customerData.setUpdatedDate(LocalDateTime.now());
				customerData.setUpdatedBy(userFullName.findByUserName(customerDetails.getUserName()));
				customerDetailsRepository.save(customerData);
				logger.debug("Risk_Assessment Details successfully Updated in DB with InActive Status");

			} else {
				logger.error("Given Risk_Assessment Id is Invalid");
				throw new CustomerDetailsException("Given BRisk_Assessment Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new CustomerDetailsException("Invalid inputs");
		}
		logger.info("Ended updateRiskAssessmentCustomerDetailsStatus function");

	}

	@Override
	public List<CustomerDetails> retriveAllCustomerDetails() throws CustomerDetailsException {
		logger.debug("Retrieve All Customer Details");
		return (List<CustomerDetails>) customerDetailsRepository.findAll();
	}
	
	//	Email & Project Name Validation
	@Override
	public CustomerDetails findingEmailAndProject(String userName, String projectName) throws CustomerDetailsException {
		logger.info("Called findingEmailAndProject function");

		Optional<CustomerDetails> customerListRepo = customerDetailsRepository
				.findByEmailAndStatusAndProjectName(userName, "Active", projectName);

		if (customerListRepo.isPresent()) {

			List<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
			customerList.add(customerListRepo.get());

			return customerListRepo.get();
		}
		return null;

	}
}
