package com.plenumsoft.vuzee.repositories;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import com.plenumsoft.vuzee.entities.Candidate;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface CandidateRepository extends CrudRepository<Candidate, Long>, CandidateRepositoryCustom {
	List<Candidate> findByName(String name);
}
