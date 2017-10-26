package com.plenumsoft.vuzee.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.repositories.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {
	CandidateRepository candidateRepository;
	
	@Autowired
	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		super();
		this.candidateRepository = candidateRepository;
	}
	
	@Override
	public List<Candidate> getAll() {
		return (List<Candidate>) this.candidateRepository.findAll();
	}
	
	@Override
	public Long addCandidate(Candidate candidate) {
		if(candidate==null)
			throw new CandidateServiceException("No se pudo agregar la entidad" + candidate);
		
		if(candidate.getName().length()==0)
			throw new CandidateServiceException("No se pudo agregar la entidad" + candidate);
		
		if(candidate.getPositionApplied().length()==0)
			throw new CandidateServiceException("No se pudo agregar la entidad" + candidate);
		
		Candidate insertedCandidate = this.candidateRepository.save(candidate);
		if(insertedCandidate!=null)
			return insertedCandidate.getId();
		
		return null;
	}

	@Override
	public Candidate findById(Long id) {
		// TODO Auto-generated method stub
		return candidateRepository.findOne(id);
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		candidateRepository.save(candidate);
		
	}

	@Override
	public void deleteCandidate(Candidate candidate) {
		candidateRepository.delete(candidate);
	}

	@Override
	public List<Candidate> getAllCandidatesSortedByName() {
		// TODO Auto-generated method stub
		return candidateRepository.getCandidatesSortedByName();
		
	}
	
	
}

