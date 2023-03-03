package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.RiskAssessmentException;
import com.capeelectric.model.StructureCharacteristics;
import com.capeelectric.service.RiskAssessmentService;

@RestController
@RequestMapping("/api/v1")
public class RiskAssessmentController {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssessmentController.class);

	@Autowired
	private RiskAssessmentService riskAssessmentService;

	@PostMapping("/risk/saveRiskAssessmentDetails")
	public ResponseEntity<String> saveRiskAssessmentDetails(
			@RequestBody StructureCharacteristics structureCharacteristics) throws RiskAssessmentException, Exception {
		logger.debug("started addRiskAssessmentDetails function userName: {},riskId : {}",
				structureCharacteristics.getUserName(), structureCharacteristics.getRiskId());
		riskAssessmentService.addRiskAssessmentDetails(structureCharacteristics);
		logger.debug("ended saveRiskAssessmentDetails function");
		return new ResponseEntity<String>("RiskAssessmentDetails  Details Saved Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/risk/retrieveRiskAssessmentDetails/{userName}/{riskId}")
	public ResponseEntity<List<StructureCharacteristics>> retrieveRiskAssessmentDetails(@PathVariable String userName,
			@PathVariable Integer riskId) throws RiskAssessmentException {
		logger.info("called retrieveRiskAssessmentDetails function UserName: {}, riskId : {}", userName, riskId);
		return new ResponseEntity<List<StructureCharacteristics>>(
				riskAssessmentService.retrieveRiskAssessmentDetails(userName, riskId), HttpStatus.OK);
	}

	@PutMapping("/risk/updateRiskAssessmentDetails/{buttonName}")
	public ResponseEntity<String> updateRiskAssessmentDetails(
			@RequestBody StructureCharacteristics structureCharacteristics,@PathVariable String buttonName) throws RiskAssessmentException, Exception {
		logger.info("called updateRiskAssessmentDetails function UserName : {},getRiskId : {}",
				structureCharacteristics.getUserName());
		return new ResponseEntity<String>(riskAssessmentService.updateRiskAssessmentDetails(structureCharacteristics,buttonName), HttpStatus.OK);
	}

}