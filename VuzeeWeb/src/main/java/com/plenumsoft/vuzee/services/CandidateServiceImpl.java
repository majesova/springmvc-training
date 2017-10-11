package com.plenumsoft.vuzee.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.repositories.CandidateRepository;

@Service
public class CandidateServiceImpl implements CandidateService {
	CandidateRepository candidateRepository;
	
		
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
	
}

