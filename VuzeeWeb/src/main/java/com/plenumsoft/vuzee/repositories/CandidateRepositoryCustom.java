package com.plenumsoft.vuzee.repositories;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.plenumsoft.vuzee.entities.Candidate;


public interface CandidateRepositoryCustom  {

	List<Candidate> getCandidatesSortedByName();
}
