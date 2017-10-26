package com.plenumsoft.vuzee.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.plenumsoft.vuzee.entities.Candidate;

@Repository("candidateRepository")
public class CandidateRepositoryImpl implements CandidateRepositoryCustom{
	@PersistenceContext private EntityManager entityManager;
	
	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public List<Candidate> getCandidatesSortedByName() {
		// TODO Auto-generated method stub
		String jql ="SELECT c FROM Candidate as c order by c.name";
		Query query = entityManager.createQuery (jql);
		List<Candidate> resultList = null;
		if(query!=null)
			resultList = query.getResultList();
		return resultList;
	}
	
}