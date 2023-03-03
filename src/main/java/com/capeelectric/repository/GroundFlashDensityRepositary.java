package com.capeelectric.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.GroundFlashDensity;

@Repository
public interface GroundFlashDensityRepositary extends CrudRepository<GroundFlashDensity, Integer> {

	@Query(value = "select * from ground_flash_density_table where location = (select location from ground_flash_density_table where location=?)", nativeQuery = true)
	GroundFlashDensity findByGroundDensity(String location);
	
}
