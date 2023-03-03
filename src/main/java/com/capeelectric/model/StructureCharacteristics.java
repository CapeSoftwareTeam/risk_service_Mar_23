package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "structure_characterisics_table")

@NamedQueries(value = {
		@NamedQuery(name = "RiskAssessmentRepository.findByUserNameAndRiskId", query = "select r from StructureCharacteristics r where r.userName=:userName and r.riskId=:riskId"),
		@NamedQuery(name = "RiskAssessmentRepository.findByRiskId", query = "select r from StructureCharacteristics r where r.riskId=:riskId") })

public class StructureCharacteristics implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STRUCTURE_CHARACTERISTIC_ID")
	private Integer structureCharacteristicsId;

	@Column(name = "RISK_ID")
	private Integer riskId;

	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "OTHER_LOCATION")
	private String otherLocation;

	@Column(name = "GROUND_FLASH_DENSITY")
	private String groundFlashDensity;

	@Column(name = "TYPE_OF_BUILDING")
	private String typeOfBuilding;

	@Column(name = "ST_SCREENING_EFFECTIVENESS")
	private String structureScreeningEffectiveness;

	@Column(name = "IN_SCREENING_EFFECTIVENESS")
	private String internalScreeningEffectiveness;

	@Column(name = "PROTRUSION_LENGHT")
	private String protrusionLenght;

	@Column(name = "PROTRUSION_WIDTH")
	private String protrusionWidth;

	@Column(name = "PROTRUSION_HEIGHT")
	private String protrusionHeight;

	@Column(name = "HEIGHEST_ROOF_PROTRUSION")
	private String heighestRoofProtrusion;

	@Column(name = "COLL_AREA_STRUCTURE")
	private String collectionAreaOfStructure;

	@Column(name = "COLL_AREA_STRUC_PROTRUSION")
	private String collAreaOfStrucWithProtrusion;

	@Column(name = "COLL_AREA_NEAR_STRUC")
	private String collAreaOfNearStructure;

	@Column(name = "HEIGHT_NEARBY_STRUCTURE")
	private String heightNearByStructure;

	@Column(name = "TELEPHONE_SERVICE_LINE")
	private String telephoneServiceLine;

	@Column(name = "ENVIRONMENT")
	private String environment;

	@Column(name = "NO_OF_DANG_EVENT_ON_STRUCTURE")
	private String noOfDangerousEventOnStructure;

	@Column(name = "NO_OF_DANG_EVENT_NEAR_STRUCTURE")
	private String noOfDangerousEventNearStructure;

	@Column(name = "PROTECTION_PART_BUILDING")
	private String protectionPartOFBuilding;

	@Column(name = "PROTECTION_LENGHT")
	private String protectionLenght;

	@Column(name = "PROTECTION_WIDTH")
	private String protectionWidth;

	@Column(name = "PROTECTION_HEIGHT")
	private String protectionHeight;

	@Column(name = "PRO_COLL_AREA")
	private String protectionCollectionArea;

	@Column(name = "ADJACENT_BUILDING")
	private String adjacentBuilding;

	@Column(name = "ADJ_LENGTH")
	private String adjacentLength;

	@Column(name = "ADJ_WIDTH")
	private String adjacentWidth;

	@Column(name = "ADJ_HEIGHT")
	private String adjacentHeight;

	@Column(name = "COLL_AREA_ADJ_STRUC")
	private String collAreaOfAdjacentStruc;

	@Column(name = "NO_OF_DANG_EVENT_ON_ADJ_STRUC")
	private String noOfDangEventOnAdjacentStruc;

	@Column(name = "NO_OF_PEOPLE_BUILDING")
	private String noOfPeopleInBuilding;

	@Column(name = "NO_OF_PEOPLE_ZONE")
	private String noOfPeopleInZone;

	@Column(name = "DAY_PEOP_PRESENT__BUILDING")
	private String dayPeoplePresentBuilding;

	@Column(name = "YEAR_PEOP_PRESENT__BUILDING")
	private String yearPeoplePresentBuilding;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	@JsonManagedReference
	@OneToMany(mappedBy = "structureCharacteristics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StructureAttributes> structureAttributes;

	@JsonManagedReference
	@OneToMany(mappedBy = "structureCharacteristics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Losses> losses;

	@JsonManagedReference
	@OneToMany(mappedBy = "structureCharacteristics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Protection> protection;

	@JsonManagedReference
	@OneToMany(mappedBy = "structureCharacteristics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RiskProtection> riskProtection;

	@JsonManagedReference
	@OneToMany(mappedBy = "structureCharacteristics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CalculatedRisk> calculatedRisk;

	public Integer getStructureCharacteristicsId() {
		return structureCharacteristicsId;
	}

	public void setStructureCharacteristicsId(Integer structureCharacteristicsId) {
		this.structureCharacteristicsId = structureCharacteristicsId;
	}

	public Integer getRiskId() {
		return riskId;
	}

	public void setRiskId(Integer riskId) {
		this.riskId = riskId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOtherLocation() {
		return otherLocation;
	}

	public void setOtherLocation(String otherLocation) {
		this.otherLocation = otherLocation;
	}

	public String getGroundFlashDensity() {
		return groundFlashDensity;
	}

	public void setGroundFlashDensity(String groundFlashDensity) {
		this.groundFlashDensity = groundFlashDensity;
	}

	public String getTypeOfBuilding() {
		return typeOfBuilding;
	}

	public void setTypeOfBuilding(String typeOfBuilding) {
		this.typeOfBuilding = typeOfBuilding;
	}

	public String getStructureScreeningEffectiveness() {
		return structureScreeningEffectiveness;
	}

	public void setStructureScreeningEffectiveness(String structureScreeningEffectiveness) {
		this.structureScreeningEffectiveness = structureScreeningEffectiveness;
	}

	public String getInternalScreeningEffectiveness() {
		return internalScreeningEffectiveness;
	}

	public void setInternalScreeningEffectiveness(String internalScreeningEffectiveness) {
		this.internalScreeningEffectiveness = internalScreeningEffectiveness;
	}

	public String getProtrusionLenght() {
		return protrusionLenght;
	}

	public void setProtrusionLenght(String protrusionLenght) {
		this.protrusionLenght = protrusionLenght;
	}

	public String getProtrusionWidth() {
		return protrusionWidth;
	}

	public void setProtrusionWidth(String protrusionWidth) {
		this.protrusionWidth = protrusionWidth;
	}

	public String getProtrusionHeight() {
		return protrusionHeight;
	}

	public void setProtrusionHeight(String protrusionHeight) {
		this.protrusionHeight = protrusionHeight;
	}

	public String getHeighestRoofProtrusion() {
		return heighestRoofProtrusion;
	}

	public void setHeighestRoofProtrusion(String heighestRoofProtrusion) {
		this.heighestRoofProtrusion = heighestRoofProtrusion;
	}

	public String getCollectionAreaOfStructure() {
		return collectionAreaOfStructure;
	}

	public void setCollectionAreaOfStructure(String collectionAreaOfStructure) {
		this.collectionAreaOfStructure = collectionAreaOfStructure;
	}

	public String getCollAreaOfStrucWithProtrusion() {
		return collAreaOfStrucWithProtrusion;
	}

	public void setCollAreaOfStrucWithProtrusion(String collAreaOfStrucWithProtrusion) {
		this.collAreaOfStrucWithProtrusion = collAreaOfStrucWithProtrusion;
	}

	public String getCollAreaOfNearStructure() {
		return collAreaOfNearStructure;
	}

	public void setCollAreaOfNearStructure(String collAreaOfNearStructure) {
		this.collAreaOfNearStructure = collAreaOfNearStructure;
	}

	public String getHeightNearByStructure() {
		return heightNearByStructure;
	}

	public void setHeightNearByStructure(String heightNearByStructure) {
		this.heightNearByStructure = heightNearByStructure;
	}

	public String getTelephoneServiceLine() {
		return telephoneServiceLine;
	}

	public void setTelephoneServiceLine(String telephoneServiceLine) {
		this.telephoneServiceLine = telephoneServiceLine;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getNoOfDangerousEventOnStructure() {
		return noOfDangerousEventOnStructure;
	}

	public void setNoOfDangerousEventOnStructure(String noOfDangerousEventOnStructure) {
		this.noOfDangerousEventOnStructure = noOfDangerousEventOnStructure;
	}

	public String getProtectionPartOFBuilding() {
		return protectionPartOFBuilding;
	}

	public void setProtectionPartOFBuilding(String protectionPartOFBuilding) {
		this.protectionPartOFBuilding = protectionPartOFBuilding;
	}

	public String getProtectionLenght() {
		return protectionLenght;
	}

	public void setProtectionLenght(String protectionLenght) {
		this.protectionLenght = protectionLenght;
	}

	public String getProtectionWidth() {
		return protectionWidth;
	}

	public void setProtectionWidth(String protectionWidth) {
		this.protectionWidth = protectionWidth;
	}

	public String getProtectionHeight() {
		return protectionHeight;
	}

	public void setProtectionHeight(String protectionHeight) {
		this.protectionHeight = protectionHeight;
	}

	public String getProtectionCollectionArea() {
		return protectionCollectionArea;
	}

	public void setProtectionCollectionArea(String protectionCollectionArea) {
		this.protectionCollectionArea = protectionCollectionArea;
	}

	public String getAdjacentBuilding() {
		return adjacentBuilding;
	}

	public void setAdjacentBuilding(String adjacentBuilding) {
		this.adjacentBuilding = adjacentBuilding;
	}

	public String getAdjacentLength() {
		return adjacentLength;
	}

	public void setAdjacentLength(String adjacentLength) {
		this.adjacentLength = adjacentLength;
	}

	public String getAdjacentWidth() {
		return adjacentWidth;
	}

	public void setAdjacentWidth(String adjacentWidth) {
		this.adjacentWidth = adjacentWidth;
	}

	public String getAdjacentHeight() {
		return adjacentHeight;
	}

	public void setAdjacentHeight(String adjacentHeight) {
		this.adjacentHeight = adjacentHeight;
	}

	public String getCollAreaOfAdjacentStruc() {
		return collAreaOfAdjacentStruc;
	}

	public void setCollAreaOfAdjacentStruc(String collAreaOfAdjacentStruc) {
		this.collAreaOfAdjacentStruc = collAreaOfAdjacentStruc;
	}

	public String getNoOfDangEventOnAdjacentStruc() {
		return noOfDangEventOnAdjacentStruc;
	}

	public void setNoOfDangEventOnAdjacentStruc(String noOfDangEventOnAdjacentStruc) {
		this.noOfDangEventOnAdjacentStruc = noOfDangEventOnAdjacentStruc;
	}

	public String getNoOfPeopleInBuilding() {
		return noOfPeopleInBuilding;
	}

	public void setNoOfPeopleInBuilding(String noOfPeopleInBuilding) {
		this.noOfPeopleInBuilding = noOfPeopleInBuilding;
	}

	public String getNoOfPeopleInZone() {
		return noOfPeopleInZone;
	}

	public void setNoOfPeopleInZone(String noOfPeopleInZone) {
		this.noOfPeopleInZone = noOfPeopleInZone;
	}

	public String getDayPeoplePresentBuilding() {
		return dayPeoplePresentBuilding;
	}

	public void setDayPeoplePresentBuilding(String dayPeoplePresentBuilding) {
		this.dayPeoplePresentBuilding = dayPeoplePresentBuilding;
	}

	public String getYearPeoplePresentBuilding() {
		return yearPeoplePresentBuilding;
	}

	public void setYearPeoplePresentBuilding(String yearPeoplePresentBuilding) {
		this.yearPeoplePresentBuilding = yearPeoplePresentBuilding;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<StructureAttributes> getStructureAttributes() {
		return structureAttributes;
	}

	public void setStructureAttributes(List<StructureAttributes> structureAttributes) {
		this.structureAttributes = structureAttributes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Losses> getLosses() {
		return losses;
	}

	public void setLosses(List<Losses> losses) {
		this.losses = losses;
	}

	public String getNoOfDangerousEventNearStructure() {
		return noOfDangerousEventNearStructure;
	}

	public void setNoOfDangerousEventNearStructure(String noOfDangerousEventNearStructure) {
		this.noOfDangerousEventNearStructure = noOfDangerousEventNearStructure;
	}

	public List<Protection> getProtection() {
		return protection;
	}

	public void setProtection(List<Protection> protection) {
		this.protection = protection;
	}

	public List<RiskProtection> getRiskProtection() {
		return riskProtection;
	}

	public void setRiskProtection(List<RiskProtection> riskProtection) {
		this.riskProtection = riskProtection;
	}

	public List<CalculatedRisk> getCalculatedRisk() {
		return calculatedRisk;
	}

	public void setCalculatedRisk(List<CalculatedRisk> calculatedRisk) {
		this.calculatedRisk = calculatedRisk;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	

}
