package com.plenumsoft.vuzee.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.plenumsoft.vuzee.entities.Candidate;


@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
	List<Candidate> findByName(String name);
}
