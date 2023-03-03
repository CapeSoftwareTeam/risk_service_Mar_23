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
@Table(name = "structure_attributes_table")
public class StructureAttributes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STRUCTURE_ATTRIBUTES_ID")
	private Integer structureAttributesId;

//	Structure's Attributes
	@Column(name = "ST_TYPE_FLOOR_SURFACE")
	private String stTypeOfFloorSurface;

	@Column(name = "ST_ADDITIONAL_PROTECTION")
	private String stAdditionalProtection;

	@Column(name = "ST_RISK_OF_FIRE")
	private String stRiskOfFire;

	@Column(name = "ST_FIRE_PROTECTION_MEASURES")
	private String stFireProtectionMeasure;

	@Column(name = "ST_TYPE_INTERNAL_WIRING")
	private String stTypeOfInternalWiring;

//  Lines
	@Column(name = "TOTAL_NO_OF_LINES")
	private String totalNoOfLines;

	@Column(name = "NUMBER_POWER_LINES")
	private String noOfPowerLines;

	@Column(name = "TYPE_POWER_LINES")
	private String typeOfPowerLines;

	@Column(name = "LENGTH_POWER_LINES")
	private String lengthOfPowerLines;

	@Column(name = "SHIELD_GROUND_ISOLA")
	private String shieldingGroundingIsolation;

	@Column(name = "COLL_AREA_POWER_LINES")
	private String collAreaOfPowerLines;

	@Column(name = "COLL_AREA_NEAR_LINES")
	private String collAreaOfNearLines;

	@Column(name = "EVENT_NEAR_POWER_LINES")
	private String eventNearThePowerLines;

	@Column(name = "EVENT_ON_POWER_LINES")
	private String eventOnThePowerLines;

	@Column(name = "NO_OF_TELECOM_LINES")
	private String noOfTelecomLines;

	@Column(name = "TYPE_OF_TELECOM_LINES")
	private String typeOfTelecomLines;

	@Column(name = "LENGTH_TELECOM_LINES")
	private String lengthOfTelecomLines;

	@Column(name = "SHIELD_GROUND_ISOLA_L1")
	private String shieldingGroundingIsolationL1;

	@Column(name = "COLL_AREA_TELECOM_LINES")
	private String collAreaOfTelecomLines;

	@Column(name = "COLL_NEAR_TELECOM_LINES")
	private String collNearOfTelecomLines;

	@Column(name = "EVENT_NEAR_TELECOM_LINES")
	private String eventNearTheTelecomeLines;

	@Column(name = "EVENT_ON_TELECOM_LINES")
	private String eventOnTheTelecomLines;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "STRUCTURE_CHARACTERISTIC_ID")
	private StructureCharacteristics structureCharacteristics;

	public Integer getStructureAttributesId() {
		return structureAttributesId;
	}

	public void setStructureAttributesId(Integer structureAttributesId) {
		this.structureAttributesId = structureAttributesId;
	}

	public String getStTypeOfFloorSurface() {
		return stTypeOfFloorSurface;
	}

	public void setStTypeOfFloorSurface(String stTypeOfFloorSurface) {
		this.stTypeOfFloorSurface = stTypeOfFloorSurface;
	}

	public String getStAdditionalProtection() {
		return stAdditionalProtection;
	}

	public void setStAdditionalProtection(String stAdditionalProtection) {
		this.stAdditionalProtection = stAdditionalProtection;
	}

	public String getStRiskOfFire() {
		return stRiskOfFire;
	}

	public void setStRiskOfFire(String stRiskOfFire) {
		this.stRiskOfFire = stRiskOfFire;
	}

	public String getStFireProtectionMeasure() {
		return stFireProtectionMeasure;
	}

	public void setStFireProtectionMeasure(String stFireProtectionMeasure) {
		this.stFireProtectionMeasure = stFireProtectionMeasure;
	}

	public String getStTypeOfInternalWiring() {
		return stTypeOfInternalWiring;
	}

	public void setStTypeOfInternalWiring(String stTypeOfInternalWiring) {
		this.stTypeOfInternalWiring = stTypeOfInternalWiring;
	}

	public String getTotalNoOfLines() {
		return totalNoOfLines;
	}

	public void setTotalNoOfLines(String totalNoOfLines) {
		this.totalNoOfLines = totalNoOfLines;
	}

	public String getNoOfPowerLines() {
		return noOfPowerLines;
	}

	public void setNoOfPowerLines(String noOfPowerLines) {
		this.noOfPowerLines = noOfPowerLines;
	}

	public String getTypeOfPowerLines() {
		return typeOfPowerLines;
	}

	public void setTypeOfPowerLines(String typeOfPowerLines) {
		this.typeOfPowerLines = typeOfPowerLines;
	}

	public String getLengthOfPowerLines() {
		return lengthOfPowerLines;
	}

	public void setLengthOfPowerLines(String lengthOfPowerLines) {
		this.lengthOfPowerLines = lengthOfPowerLines;
	}

	public String getShieldingGroundingIsolation() {
		return shieldingGroundingIsolation;
	}

	public void setShieldingGroundingIsolation(String shieldingGroundingIsolation) {
		this.shieldingGroundingIsolation = shieldingGroundingIsolation;
	}

	public String getCollAreaOfPowerLines() {
		return collAreaOfPowerLines;
	}

	public void setCollAreaOfPowerLines(String collAreaOfPowerLines) {
		this.collAreaOfPowerLines = collAreaOfPowerLines;
	}

	public String getCollAreaOfNearLines() {
		return collAreaOfNearLines;
	}

	public void setCollAreaOfNearLines(String collAreaOfNearLines) {
		this.collAreaOfNearLines = collAreaOfNearLines;
	}

	public String getEventNearThePowerLines() {
		return eventNearThePowerLines;
	}

	public void setEventNearThePowerLines(String eventNearThePowerLines) {
		this.eventNearThePowerLines = eventNearThePowerLines;
	}

	public String getEventOnThePowerLines() {
		return eventOnThePowerLines;
	}

	public void setEventOnThePowerLines(String eventOnThePowerLines) {
		this.eventOnThePowerLines = eventOnThePowerLines;
	}

	public String getNoOfTelecomLines() {
		return noOfTelecomLines;
	}

	public void setNoOfTelecomLines(String noOfTelecomLines) {
		this.noOfTelecomLines = noOfTelecomLines;
	}

	public String getTypeOfTelecomLines() {
		return typeOfTelecomLines;
	}

	public void setTypeOfTelecomLines(String typeOfTelecomLines) {
		this.typeOfTelecomLines = typeOfTelecomLines;
	}

	public String getLengthOfTelecomLines() {
		return lengthOfTelecomLines;
	}

	public void setLengthOfTelecomLines(String lengthOfTelecomLines) {
		this.lengthOfTelecomLines = lengthOfTelecomLines;
	}

	public String getShieldingGroundingIsolationL1() {
		return shieldingGroundingIsolationL1;
	}

	public void setShieldingGroundingIsolationL1(String shieldingGroundingIsolationL1) {
		this.shieldingGroundingIsolationL1 = shieldingGroundingIsolationL1;
	}

	public String getCollAreaOfTelecomLines() {
		return collAreaOfTelecomLines;
	}

	public void setCollAreaOfTelecomLines(String collAreaOfTelecomLines) {
		this.collAreaOfTelecomLines = collAreaOfTelecomLines;
	}

	public String getCollNearOfTelecomLines() {
		return collNearOfTelecomLines;
	}

	public void setCollNearOfTelecomLines(String collNearOfTelecomLines) {
		this.collNearOfTelecomLines = collNearOfTelecomLines;
	}

	public String getEventNearTheTelecomeLines() {
		return eventNearTheTelecomeLines;
	}

	public void setEventNearTheTelecomeLines(String eventNearTheTelecomeLines) {
		this.eventNearTheTelecomeLines = eventNearTheTelecomeLines;
	}

	public String getEventOnTheTelecomLines() {
		return eventOnTheTelecomLines;
	}

	public void setEventOnTheTelecomLines(String eventOnTheTelecomLines) {
		this.eventOnTheTelecomLines = eventOnTheTelecomLines;
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


}
