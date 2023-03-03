package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Integer> {

	List<CustomerDetails> findByUserName(String userName);
	
	List<CustomerDetails> findByOrganisationNameAndProjectName(String organization, String projectName);
	
	Optional<CustomerDetails> findByRiskId(Integer riskId);

	List<CustomerDetails> findByUserNameAndRiskId(String userName, Integer riskId);

	public Optional<CustomerDetails> findByEmailAndStatusAndProjectName(String userName, String string, String projectName);

}
