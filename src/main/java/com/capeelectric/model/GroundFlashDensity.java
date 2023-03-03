package com.capeelectric.model;

import java.io.Serializable;

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
@Table(name = "ground_flash_density_table")

public class GroundFlashDensity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GROUND_DENSITY_ID")
	private Integer groundDensityId;

	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "GFD_VALUE")
	private String gfdValue;

	public Integer getGroundDensityId() {
		return groundDensityId;
	}

	public void setGroundDensityId(Integer groundDensityId) {
		this.groundDensityId = groundDensityId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGfdValue() {
		return gfdValue;
	}

	public void setGfdValue(String gfdValue) {
		this.gfdValue = gfdValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
