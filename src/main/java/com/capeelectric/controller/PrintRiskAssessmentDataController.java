package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.RiskAssessmentException;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintRiskAssessmentDataDetailsService;
import com.capeelectric.service.PrintRiskCustomerDetailsService;


@RestController
@RequestMapping("/api/v1")
public class PrintRiskAssessmentDataController {

	private static final Logger logger = LoggerFactory.getLogger(PrintRiskAssessmentDataController.class);

	@Autowired
	private PrintRiskCustomerDetailsService printRiskCustomerDetailsService;
	
	@Autowired
	private PrintRiskAssessmentDataDetailsService printRiskAssessmentDataDetailsService;
	
	@Autowired
	private PrintFinalPDFService printFinalPDFService;

	@GetMapping("/risk/printRiskCustomerDetails/{userName}/{riskId}")
	public ResponseEntity<String> printRiskCustomerDetails(@PathVariable String userName, @PathVariable Integer riskId)
			throws RiskAssessmentException {
		logger.info("called printRiskCustomerDetails function userName: {},riskId : {}", userName, riskId);
		printRiskCustomerDetailsService.printRiskCustomerDetails(userName, riskId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/risk/printRiskAssessmentDataDetails/{userName}/{riskId}")
	public ResponseEntity<String> printRiskAssessmentDataDetails(@PathVariable String userName, @PathVariable Integer riskId)
			throws RiskAssessmentException {
		logger.info("called printRiskAssessmentDataDetails function userName: {},riskId : {}", userName, riskId);
		printRiskAssessmentDataDetailsService.printRiskAssessmentDataDetails(userName, riskId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	@GetMapping("/risk/printRiskAssessmentFinalPDF/{userName}/{riskId}/{projectName}")
	public ResponseEntity<String> printFinalPDF(@PathVariable String userName, @PathVariable Integer riskId, @PathVariable String projectName)
			throws RiskAssessmentException, Exception {
		logger.info("called printRiskAssessmentDataDetails function userName: {},riskId : {},projectName : {}", userName, riskId, projectName);
		printRiskCustomerDetailsService.printRiskCustomerDetails(userName, riskId);
		printRiskAssessmentDataDetailsService.printRiskAssessmentDataDetails(userName, riskId);
		printFinalPDFService.printFinalPDF(userName, riskId, projectName);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
