package com.capeelectric.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.model.GroundFlashDensity;
import com.capeelectric.repository.GroundFlashDensityRepositary;
import com.capeelectric.service.GroundFlashDensityLocationService;

@Service
public class GroundFlashDensityLocationServiceImpl implements GroundFlashDensityLocationService {

	private static final Logger logger = LoggerFactory.getLogger(GroundFlashDensityLocationServiceImpl.class);

	@Autowired
	private GroundFlashDensityRepositary groundFlashDensityRepositary;

	public List<GroundFlashDensity> fetchLocations() {
		logger.debug("Retrieve All Location");
		List<GroundFlashDensity> locations = (List<GroundFlashDensity>) groundFlashDensityRepositary.findAll();
		locations.sort((o1, o2) -> o1.getLocation().compareTo(o2.getLocation()));
		return locations;
	}
}
