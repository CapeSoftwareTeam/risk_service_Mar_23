package com.capeelectric.model;

public class RiskFinalReport {
	
	private String userName;

	private Integer riskId;

	private CustomerDetails customerDetails;

	private StructureCharacteristics structureCharacteristics;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRiskId() {
		return riskId;
	}

	public void setRiskId(Integer riskId) {
		this.riskId = riskId;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public StructureCharacteristics getStructureCharacteristics() {
		return structureCharacteristics;
	}

	public void setStructureCharacteristics(StructureCharacteristics structureCharacteristics) {
		this.structureCharacteristics = structureCharacteristics;
	}

	

}
