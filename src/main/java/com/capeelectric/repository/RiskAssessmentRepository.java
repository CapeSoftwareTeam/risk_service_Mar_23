package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.StructureCharacteristics;

@Repository
public interface RiskAssessmentRepository extends CrudRepository<StructureCharacteristics, Integer> {

	Optional<StructureCharacteristics> findByRiskId(Integer riskId);
	List<StructureCharacteristics> findByUserNameAndRiskId(String userName, Integer riskId);

	
}
