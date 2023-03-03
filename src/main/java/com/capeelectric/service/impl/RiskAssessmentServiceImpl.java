package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.RiskAssessmentException;
import com.capeelectric.model.CustomerDetails;
import com.capeelectric.model.StructureCharacteristics;
import com.capeelectric.repository.CustomerDetailsRepository;
import com.capeelectric.repository.RiskAssessmentRepository;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintRiskAssessmentDataDetailsService;
import com.capeelectric.service.PrintRiskCustomerDetailsService;
import com.capeelectric.service.RiskAssessmentService;
import com.capeelectric.util.UserFullName;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssessmentServiceImpl.class);

	@Autowired
	private RiskAssessmentRepository riskAssessmentRepository;

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;

	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private PrintRiskCustomerDetailsService printRiskCustomerDetailsService;
	
	@Autowired
	private PrintRiskAssessmentDataDetailsService printRiskAssessmentDataDetailsService;
	
	@Autowired
	private PrintFinalPDFService printFinalPDFService;
	
	@Override
	public void addRiskAssessmentDetails(StructureCharacteristics structureCharacteristics)
			throws RiskAssessmentException, RiskAssessmentException, Exception {
		if (structureCharacteristics != null && structureCharacteristics.getUserName() != null) {
			Optional<StructureCharacteristics> riskAssessmentDataRepo = riskAssessmentRepository
					.findByRiskId(structureCharacteristics.getRiskId());

			Optional<CustomerDetails> customerDetailsRepo = customerDetailsRepository
					.findByRiskId(structureCharacteristics.getRiskId());
			if (customerDetailsRepo.isPresent()
					&& customerDetailsRepo.get().getRiskId().equals(structureCharacteristics.getRiskId())) {
				if (!riskAssessmentDataRepo.isPresent()) {
					structureCharacteristics.setCreatedDate(LocalDateTime.now());
					structureCharacteristics.setCreatedBy(structureCharacteristics.getUserName());
					riskAssessmentRepository.save(structureCharacteristics);
					logger.debug("Risk Assessment Details Saved Successfully");
					userFullName.addUpdatedByandDate(customerDetailsRepo.get().getRiskId(), customerDetailsRepo.get().getUpdatedBy(),"step-2 completed");
					logger.debug("Customer Details UpdatedBy and UpdatedDate by Risk Assessment");

				} else {
					logger.error("Given RiskAssessment Details Already Exists");
					throw new RiskAssessmentException("Given RiskAssessment Details Already Exists");
				}

			} else {
				logger.error("Given Risk Id is Not Registered in Customer Details");
				throw new RiskAssessmentException("Given Risk Id is Not Registered in Customer Details");
			}
		} else {
			logger.error("Invalid Inputs");
			throw new RiskAssessmentException("Invalid Inputs");
		}

	}

	@Override
	public List<StructureCharacteristics> retrieveRiskAssessmentDetails(String userName, Integer riskId)
			throws RiskAssessmentException {
		if (userName != null && !userName.isEmpty() && riskId != null) {
			List<StructureCharacteristics> riskAssessmentDetailsRepo = riskAssessmentRepository
					.findByUserNameAndRiskId(userName, riskId);
			if (riskAssessmentDetailsRepo != null && !riskAssessmentDetailsRepo.isEmpty()) {
				return riskAssessmentDetailsRepo;
			} else {
				logger.error("Given UserName & Risk Id doesn't exist in Riak Assessment Details");
				throw new RiskAssessmentException("Given UserName & Risk Id doesn't exist in Riak Assessment Details");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new RiskAssessmentException("Invalid Inputs");
		}
	}

	@Override
	public String updateRiskAssessmentDetails(StructureCharacteristics structureCharacteristics, String buttonName)
			throws RiskAssessmentException, Exception {
		String msg = "";
		if (structureCharacteristics != null && structureCharacteristics.getRiskId() != null
				&& structureCharacteristics.getUserName() != null) {
			Optional<StructureCharacteristics> riskAssessmentDetailsRepo = riskAssessmentRepository
					.findByRiskId(structureCharacteristics.getRiskId());
			
			if (riskAssessmentDetailsRepo.isPresent()
					&& riskAssessmentDetailsRepo.get().getRiskId().equals(structureCharacteristics.getRiskId())) {
				structureCharacteristics.setUpdatedDate(LocalDateTime.now());
				structureCharacteristics.setUpdatedBy(structureCharacteristics.getUserName());
				riskAssessmentRepository.save(structureCharacteristics);


				Optional<CustomerDetails> customerDetailsRepo = customerDetailsRepository
						.findByRiskId(structureCharacteristics.getRiskId());
				logger.debug("Risk Assessment Details Updated Successfully");
				msg = "Risk Assessment Details Updated Successfully";
				if (buttonName.equalsIgnoreCase("submit")) {
				    String projectName = printRiskCustomerDetailsService.printRiskCustomerDetails(structureCharacteristics.getUserName(), structureCharacteristics.getRiskId());
			        printRiskAssessmentDataDetailsService.printRiskAssessmentDataDetails(structureCharacteristics.getUserName(), structureCharacteristics.getRiskId());
			        printFinalPDFService.printRiskFinalPDF(structureCharacteristics.getUserName(), structureCharacteristics.getRiskId(), projectName);
					userFullName.addUpdatedByandDateRisk(customerDetailsRepo.get().getRiskId(), customerDetailsRepo.get().getUpdatedBy(),"AllStepCompleted");
					msg = "Risk Assessment Details Submitted Successfully";
				}
				else {
					userFullName.addUpdatedByandDateRisk(customerDetailsRepo.get().getRiskId(), customerDetailsRepo.get().getUpdatedBy(),"");
				}
				logger.debug("Customer Details UpdatedBy and UpdatedDate by Risk Assessment");

			} else {
				logger.error("Given Risk Id is Invalid");
				throw new RiskAssessmentException("Given Risk Id is Invalid");
			}

		} else {
			logger.error("Invalid Inputs");
			throw new RiskAssessmentException("Invalid inputs");
		}
		return msg;
	}
}
