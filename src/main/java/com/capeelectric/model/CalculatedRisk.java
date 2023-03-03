package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "calculated_risk_table")
public class CalculatedRisk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CALCULATED_RISK_ID")
	private Integer calculatedRiskId;

	@Column(name = "LOSS_HUMAN_LIFE")
	private String lossOfHumanLifeRT1;

	@Column(name = "LOSS_PUBLIC_SERVICE")
	private String lossOfPublicSerivceRT2;

	@Column(name = "LOSS_CULTURAL_HERITAGE")
	private String lossOfCulturalHeritageRT3;

	@Column(name = "ECONOMIC_LOSS")
	private String economicLossRT4;

	@Column(name = "R_PRO_R1")
	private String riskProtectionR1;

	@Column(name = "R_PRO_R2")
	private String riskProtectionR2;

	@Column(name = "R_PRO_R3")
	private String riskProtectionR3;

	@Column(name = "R_PRO_R4")
	private String riskProtectionR4;

	@Column(name = "R_PRO_RD1")
	private String riskProtectionRD1;

	@Column(name = "R_PRO_RD2")
	private String riskProtectionRD2;

	@Column(name = "R_PRO_RD3")
	private String riskProtectionRD3;

	@Column(name = "R_PRO_RD4")
	private String riskProtectionRD4;

	@Column(name = "R_PRO_RI1")
	private String riskProtectionRI1;

	@Column(name = "R_PRO_RI2")
	private String riskProtectionRI2;

	@Column(name = "R_PRO_RI3")
	private String riskProtectionRI3;

	@Column(name = "R_PRO_RI4")
	private String riskProtectionRI4;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "STRUCTURE_CHARACTERISTIC_ID")
	private StructureCharacteristics structureCharacteristics;

	public Integer getCalculatedRiskId() {
		return calculatedRiskId;
	}

	public void setCalculatedRiskId(Integer calculatedRiskId) {
		this.calculatedRiskId = calculatedRiskId;
	}

	public String getLossOfHumanLifeRT1() {
		return lossOfHumanLifeRT1;
	}

	public void setLossOfHumanLifeRT1(String lossOfHumanLifeRT1) {
		this.lossOfHumanLifeRT1 = lossOfHumanLifeRT1;
	}

	public String getLossOfPublicSerivceRT2() {
		return lossOfPublicSerivceRT2;
	}

	public void setLossOfPublicSerivceRT2(String lossOfPublicSerivceRT2) {
		this.lossOfPublicSerivceRT2 = lossOfPublicSerivceRT2;
	}

	public String getLossOfCulturalHeritageRT3() {
		return lossOfCulturalHeritageRT3;
	}

	public void setLossOfCulturalHeritageRT3(String lossOfCulturalHeritageRT3) {
		this.lossOfCulturalHeritageRT3 = lossOfCulturalHeritageRT3;
	}

	public String getEconomicLossRT4() {
		return economicLossRT4;
	}

	public void setEconomicLossRT4(String economicLossRT4) {
		this.economicLossRT4 = economicLossRT4;
	}

	public StructureCharacteristics getStructureCharacteristics() {
		return structureCharacteristics;
	}

	public void setStructureCharacteristics(StructureCharacteristics structureCharacteristics) {
		this.structureCharacteristics = structureCharacteristics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRiskProtectionR1() {
		return riskProtectionR1;
	}

	public void setRiskProtectionR1(String riskProtectionR1) {
		this.riskProtectionR1 = riskProtectionR1;
	}

	public String getRiskProtectionR2() {
		return riskProtectionR2;
	}

	public void setRiskProtectionR2(String riskProtectionR2) {
		this.riskProtectionR2 = riskProtectionR2;
	}

	public String getRiskProtectionR3() {
		return riskProtectionR3;
	}

	public void setRiskProtectionR3(String riskProtectionR3) {
		this.riskProtectionR3 = riskProtectionR3;
	}

	public String getRiskProtectionR4() {
		return riskProtectionR4;
	}

	public void setRiskProtectionR4(String riskProtectionR4) {
		this.riskProtectionR4 = riskProtectionR4;
	}

	public String getRiskProtectionRD1() {
		return riskProtectionRD1;
	}

	public void setRiskProtectionRD1(String riskProtectionRD1) {
		this.riskProtectionRD1 = riskProtectionRD1;
	}

	public String getRiskProtectionRD2() {
		return riskProtectionRD2;
	}

	public void setRiskProtectionRD2(String riskProtectionRD2) {
		this.riskProtectionRD2 = riskProtectionRD2;
	}

	public String getRiskProtectionRD3() {
		return riskProtectionRD3;
	}

	public void setRiskProtectionRD3(String riskProtectionRD3) {
		this.riskProtectionRD3 = riskProtectionRD3;
	}

	public String getRiskProtectionRD4() {
		return riskProtectionRD4;
	}

	public void setRiskProtectionRD4(String riskProtectionRD4) {
		this.riskProtectionRD4 = riskProtectionRD4;
	}

	public String getRiskProtectionRI1() {
		return riskProtectionRI1;
	}

	public void setRiskProtectionRI1(String riskProtectionRI1) {
		this.riskProtectionRI1 = riskProtectionRI1;
	}

	public String getRiskProtectionRI2() {
		return riskProtectionRI2;
	}

	public void setRiskProtectionRI2(String riskProtectionRI2) {
		this.riskProtectionRI2 = riskProtectionRI2;
	}

	public String getRiskProtectionRI3() {
		return riskProtectionRI3;
	}

	public void setRiskProtectionRI3(String riskProtectionRI3) {
		this.riskProtectionRI3 = riskProtectionRI3;
	}

	public String getRiskProtectionRI4() {
		return riskProtectionRI4;
	}

	public void setRiskProtectionRI4(String riskProtectionRI4) {
		this.riskProtectionRI4 = riskProtectionRI4;
	}

	
}
