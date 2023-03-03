package com.capeelectric.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author capeelectricsoftware
 *
 */

@Entity
@Table(name = "customer_details_table")
public class CustomerDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RISK_ID")
	private Integer riskId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ORGANISATION_NAME")
	private String organisationName;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "PROJECT_DESC")
	private String projectDescription;

	@Column(name = "CONTACT_PERSON_NAME")
	private String contactPersonName;

	@Column(name = "CONTACT_NO")
	private String contactNumber;

	@Column(name = "PREPARED_BY")
	private String preparedBy;

	@Column(name = "VERIFIED_BY")
	private String verifiedBy;
	
	@Column(name = "ALL_STEPS_COMPLETED")
	private String allStepsCompleted;
	
	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

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

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPreparedBy() {
		return preparedBy;
	}

	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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

	public String getAllStepsCompleted() {
		return allStepsCompleted;
	}

	public void setAllStepsCompleted(String allStepsCompleted) {
		this.allStepsCompleted = allStepsCompleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
