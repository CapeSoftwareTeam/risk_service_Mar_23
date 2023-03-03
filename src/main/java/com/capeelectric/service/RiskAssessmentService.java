package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.RiskAssessmentException;
import com.capeelectric.model.StructureCharacteristics;

public interface RiskAssessmentService {

	public void addRiskAssessmentDetails(StructureCharacteristics structureCharacteristics)
			throws RiskAssessmentException, Exception;

	public List<StructureCharacteristics> retrieveRiskAssessmentDetails(String userName, Integer riskId)
			throws RiskAssessmentException;

	public String updateRiskAssessmentDetails(StructureCharacteristics structureCharacteristics, String buttonName)
			throws RiskAssessmentException, Exception;

}
