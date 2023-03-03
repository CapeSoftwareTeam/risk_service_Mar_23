package com.capeelectric.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.RiskFinalReportException;
import com.capeelectric.model.CustomerDetails;
import com.capeelectric.model.RiskFinalReport;
import com.capeelectric.service.RiskFinalReportService;

@RestController
@RequestMapping("/api/v1")
public class RiskFinalReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(RiskFinalReportController.class);

	@Autowired
	RiskFinalReportService finalReportService;
	
	@GetMapping("/risk/retrieveRiskReport/{userName}/{riskId}")
	public ResponseEntity<Optional<RiskFinalReport>> retrieveriskReports(@PathVariable String userName,
			@PathVariable Integer riskId) throws RiskFinalReportException {
		logger.info("FinalReportAPI_started retrieveFinalRiskReport function userName: {},riskId : {}", userName, riskId);

		return new ResponseEntity<Optional<RiskFinalReport>>(
				finalReportService.retrieveRiskReports(userName, riskId), HttpStatus.OK);

	}

	@GetMapping("/risk/retrieveListOfCustomerDetails/{userName}")
	public ResponseEntity<List<CustomerDetails>> retrieveListOfCustomerDetails(@PathVariable String userName)
			throws RiskFinalReportException {
		logger.info("FinalReportAPI_started retrieveListOfCustomerDetails function userName: {}", userName);

		return new ResponseEntity<List<CustomerDetails>>(finalReportService.retrieveListOfCustomerDetails(userName),
				HttpStatus.OK);

	}
	
	@GetMapping("/risk/retrieveAllCustomers")
	public ResponseEntity<List<CustomerDetails>> retrieveAllCustomerDetails() throws RiskFinalReportException{
		logger.info("FinalReportAPI_started retrieveAllCustomerDetails");
			
		return new ResponseEntity<List<CustomerDetails>>(finalReportService.retrieveAllCustomerDetails(),
						HttpStatus.OK);
	}


}
