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
@Table(name = "losses_table")
public class Losses implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOSSES_ID")
	private Integer lossesId;

	// Loss of Human Life
	@Column(name = "HL_HAZARD_CLASSIFICATION")
	private String hazardClassification;

	@Column(name = "HL_PHYSICAL_DAMAGE")
	private String humanLossOfphysicalDamage;

	@Column(name = "HL_FAILURE_INTERNAL_SYSTEM")
	private String humanLossOffailureOfInternalSystem;

	@Column(name = "HL_INJURY_ELECTRIC_SHOCK")
	private String humanLossOfInjuryOfElectricShock;

	@Column(name = "HL_PHYSICAL_DAMAGE_L1")
	private String humanLossOfPhysicalDamageL1;

	@Column(name = "HL_FAILURE_INTERNAL_SYSTEM_L1")
	private String humanLossOffailureOfInternalSystemL1;

	// Loss Of Service To Public
	@Column(name = "SP_PHYSICAL_DAMAGE")
	private String serToPubPhysicalDamage;

	@Column(name = "SP_FAILURE_INTERNAL_SYSTEM")
	private String serToPubfailureOfInternalSystem;

	@Column(name = "SP_PHYSICAL_DAMAGE_L1")
	private String serToPubPhysicalDamageL1;

	@Column(name = "SP_FAILURE_INTERNAL_SYSTEM_L1")
	private String serToPubfailureOfInternalSystemL1;

	// Loss Of Cultural Heritage
	@Column(name = "CH_PHYSICAL_DAMAGE")
	private String culHerOfPhysicalDamage;

	@Column(name = "CH_PHYSICAL_DAMAGE_L1")
	private String culHerOfPhysicalDamageL1;

	// Loss Of Economic Loss
	@Column(name = "EL_PHYSICAL_DAMAGE")
	private String ecoLossOfPhysicalDamage;

	@Column(name = "EL_FAILURE_INTERNAL_SYSTEM")
	private String ecoLossOfFailureOfInternalSystem;

	@Column(name = "EL_INJURY_ELECTRIC_SHOCK")
	private String ecoLossOfInjuryOfElectricShock;

	@Column(name = "EL_PHYSICAL_DAMAGE_L1")
	private String ecoLossOfPhysicalDamageL1;

	@Column(name = "EL_FAILURE_INTERNAL_SYSTEM_L1")
	private String ecoLossOfFailureOfInternalSystemL1;

	// Loss Of Protection
	@Column(name = "PROTEC_CLASS_LPS")
	private String classOfLPS;

	@Column(name = "PROTEC_CLASS_SPD")
	private String classOfSPD;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "STRUCTURE_CHARACTERISTIC_ID")
	private StructureCharacteristics structureCharacteristics;

	public Integer getLossesId() {
		return lossesId;
	}

	public void setLossesId(Integer lossesId) {
		this.lossesId = lossesId;
	}

	public String getHazardClassification() {
		return hazardClassification;
	}

	public void setHazardClassification(String hazardClassification) {
		this.hazardClassification = hazardClassification;
	}

	public String getHumanLossOfphysicalDamage() {
		return humanLossOfphysicalDamage;
	}

	public void setHumanLossOfphysicalDamage(String humanLossOfphysicalDamage) {
		this.humanLossOfphysicalDamage = humanLossOfphysicalDamage;
	}

	public String getHumanLossOffailureOfInternalSystem() {
		return humanLossOffailureOfInternalSystem;
	}

	public void setHumanLossOffailureOfInternalSystem(String humanLossOffailureOfInternalSystem) {
		this.humanLossOffailureOfInternalSystem = humanLossOffailureOfInternalSystem;
	}

	public String getHumanLossOfInjuryOfElectricShock() {
		return humanLossOfInjuryOfElectricShock;
	}

	public void setHumanLossOfInjuryOfElectricShock(String humanLossOfInjuryOfElectricShock) {
		this.humanLossOfInjuryOfElectricShock = humanLossOfInjuryOfElectricShock;
	}

	public String getHumanLossOfPhysicalDamageL1() {
		return humanLossOfPhysicalDamageL1;
	}

	public void setHumanLossOfPhysicalDamageL1(String humanLossOfPhysicalDamageL1) {
		this.humanLossOfPhysicalDamageL1 = humanLossOfPhysicalDamageL1;
	}

	public String getSerToPubfailureOfInternalSystem() {
		return serToPubfailureOfInternalSystem;
	}

	public void setSerToPubfailureOfInternalSystem(String serToPubfailureOfInternalSystem) {
		this.serToPubfailureOfInternalSystem = serToPubfailureOfInternalSystem;
	}

	public String getCulHerOfPhysicalDamage() {
		return culHerOfPhysicalDamage;
	}

	public void setCulHerOfPhysicalDamage(String culHerOfPhysicalDamage) {
		this.culHerOfPhysicalDamage = culHerOfPhysicalDamage;
	}

	public String getEcoLossOfPhysicalDamage() {
		return ecoLossOfPhysicalDamage;
	}

	public void setEcoLossOfPhysicalDamage(String ecoLossOfPhysicalDamage) {
		this.ecoLossOfPhysicalDamage = ecoLossOfPhysicalDamage;
	}

	public String getEcoLossOfFailureOfInternalSystem() {
		return ecoLossOfFailureOfInternalSystem;
	}

	public void setEcoLossOfFailureOfInternalSystem(String ecoLossOfFailureOfInternalSystem) {
		this.ecoLossOfFailureOfInternalSystem = ecoLossOfFailureOfInternalSystem;
	}

	public String getEcoLossOfInjuryOfElectricShock() {
		return ecoLossOfInjuryOfElectricShock;
	}

	public void setEcoLossOfInjuryOfElectricShock(String ecoLossOfInjuryOfElectricShock) {
		this.ecoLossOfInjuryOfElectricShock = ecoLossOfInjuryOfElectricShock;
	}

	public String getClassOfLPS() {
		return classOfLPS;
	}

	public void setClassOfLPS(String classOfLPS) {
		this.classOfLPS = classOfLPS;
	}

	public String getClassOfSPD() {
		return classOfSPD;
	}

	public void setClassOfSPD(String classOfSPD) {
		this.classOfSPD = classOfSPD;
	}

	public String getHumanLossOffailureOfInternalSystemL1() {
		return humanLossOffailureOfInternalSystemL1;
	}

	public void setHumanLossOffailureOfInternalSystemL1(String humanLossOffailureOfInternalSystemL1) {
		this.humanLossOffailureOfInternalSystemL1 = humanLossOffailureOfInternalSystemL1;
	}


	public String getSerToPubfailureOfInternalSystemL1() {
		return serToPubfailureOfInternalSystemL1;
	}

	public void setSerToPubfailureOfInternalSystemL1(String serToPubfailureOfInternalSystemL1) {
		this.serToPubfailureOfInternalSystemL1 = serToPubfailureOfInternalSystemL1;
	}

	public String getCulHerOfPhysicalDamageL1() {
		return culHerOfPhysicalDamageL1;
	}

	public void setCulHerOfPhysicalDamageL1(String culHerOfPhysicalDamageL1) {
		this.culHerOfPhysicalDamageL1 = culHerOfPhysicalDamageL1;
	}

	public String getEcoLossOfPhysicalDamageL1() {
		return ecoLossOfPhysicalDamageL1;
	}

	public void setEcoLossOfPhysicalDamageL1(String ecoLossOfPhysicalDamageL1) {
		this.ecoLossOfPhysicalDamageL1 = ecoLossOfPhysicalDamageL1;
	}

	public String getEcoLossOfFailureOfInternalSystemL1() {
		return ecoLossOfFailureOfInternalSystemL1;
	}

	public void setEcoLossOfFailureOfInternalSystemL1(String ecoLossOfFailureOfInternalSystemL1) {
		this.ecoLossOfFailureOfInternalSystemL1 = ecoLossOfFailureOfInternalSystemL1;
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

	public String getSerToPubPhysicalDamageL1() {
		return serToPubPhysicalDamageL1;
	}

	public void setSerToPubPhysicalDamageL1(String serToPubPhysicalDamageL1) {
		this.serToPubPhysicalDamageL1 = serToPubPhysicalDamageL1;
	}

	public String getSerToPubPhysicalDamage() {
		return serToPubPhysicalDamage;
	}

	public void setSerToPubPhysicalDamage(String serToPubPhysicalDamage) {
		this.serToPubPhysicalDamage = serToPubPhysicalDamage;
	}

}
