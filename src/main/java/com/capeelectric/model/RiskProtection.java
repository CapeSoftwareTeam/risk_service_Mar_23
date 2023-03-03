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
@Table(name = "riskprotection_table")
public class RiskProtection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RISK_PROTECTION_ID")
	private Integer riskProtectionId;

	@Column(name = "R_PRO_RC2")
	private String riskProtectionRC2;

	@Column(name = "R_PRO_RM2")
	private String riskProtectionRM2;

	@Column(name = "R_PRO_RV2")
	private String riskProtectionRV2;

	@Column(name = "R_PRO_RW2")
	private String riskProtectionRW2;

	@Column(name = "R_PRO_RZ2")
	private String riskProtectionRZ2;

	@Column(name = "ECO_VALUE_RA")
	private String econamicValueRA;  //RA4

	@Column(name = "ECO_VALUE_RB")
	private String econamicValueRB;  //RB4

	@Column(name = "ECO_VALUE_RC")
	private String econamicValueRC;  //RC4

	@Column(name = "ECO_VALUE_RM")
	private String econamicValueRM;  //RM4

	@Column(name = "ECO_VALUE_RU")
	private String econamicValueRU;   //RU4

	@Column(name = "ECO_VALUE_RV")
	private String econamicValueRV;   //RV4

	@Column(name = "ECO_VALUE_RW")
	private String econamicValueRW;   //RW4

	@Column(name = "ECO_VALUE_RZ")
	private String econamicValueRZ;   //RZ4

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "STRUCTURE_CHARACTERISTIC_ID")
	private StructureCharacteristics structureCharacteristics;

	public Integer getRiskProtectionId() {
		return riskProtectionId;
	}

	public void setRiskProtectionId(Integer riskProtectionId) {
		this.riskProtectionId = riskProtectionId;
	}

	public String getRiskProtectionRC2() {
		return riskProtectionRC2;
	}

	public void setRiskProtectionRC2(String riskProtectionRC2) {
		this.riskProtectionRC2 = riskProtectionRC2;
	}

	public String getRiskProtectionRM2() {
		return riskProtectionRM2;
	}

	public void setRiskProtectionRM2(String riskProtectionRM2) {
		this.riskProtectionRM2 = riskProtectionRM2;
	}

	public String getRiskProtectionRV2() {
		return riskProtectionRV2;
	}

	public void setRiskProtectionRV2(String riskProtectionRV2) {
		this.riskProtectionRV2 = riskProtectionRV2;
	}

	public String getRiskProtectionRW2() {
		return riskProtectionRW2;
	}

	public void setRiskProtectionRW2(String riskProtectionRW2) {
		this.riskProtectionRW2 = riskProtectionRW2;
	}

	public String getRiskProtectionRZ2() {
		return riskProtectionRZ2;
	}

	public void setRiskProtectionRZ2(String riskProtectionRZ2) {
		this.riskProtectionRZ2 = riskProtectionRZ2;
	}

	public String getEconamicValueRA() {
		return econamicValueRA;
	}

	public void setEconamicValueRA(String econamicValueRA) {
		this.econamicValueRA = econamicValueRA;
	}

	public String getEconamicValueRB() {
		return econamicValueRB;
	}

	public void setEconamicValueRB(String econamicValueRB) {
		this.econamicValueRB = econamicValueRB;
	}

	public String getEconamicValueRC() {
		return econamicValueRC;
	}

	public void setEconamicValueRC(String econamicValueRC) {
		this.econamicValueRC = econamicValueRC;
	}

	public String getEconamicValueRM() {
		return econamicValueRM;
	}

	public void setEconamicValueRM(String econamicValueRM) {
		this.econamicValueRM = econamicValueRM;
	}

	public String getEconamicValueRU() {
		return econamicValueRU;
	}

	public void setEconamicValueRU(String econamicValueRU) {
		this.econamicValueRU = econamicValueRU;
	}

	public String getEconamicValueRV() {
		return econamicValueRV;
	}

	public void setEconamicValueRV(String econamicValueRV) {
		this.econamicValueRV = econamicValueRV;
	}

	public String getEconamicValueRW() {
		return econamicValueRW;
	}

	public void setEconamicValueRW(String econamicValueRW) {
		this.econamicValueRW = econamicValueRW;
	}

	public String getEconamicValueRZ() {
		return econamicValueRZ;
	}

	public void setEconamicValueRZ(String econamicValueRZ) {
		this.econamicValueRZ = econamicValueRZ;
	}

	public StructureCharacteristics getStructureCharacteristics() {
		return structureCharacteristics;
	}

	public void setStructureCharacteristics(StructureCharacteristics structureCharacteristics) {
		this.structureCharacteristics = structureCharacteristics;
	}

	
}
