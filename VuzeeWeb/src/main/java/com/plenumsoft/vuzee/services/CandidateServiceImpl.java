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
	public Long addCandidate(Candidate prospectus) {
		if(prospectus==null)
			throw new CandidateServiceException("No se pudo agregar la entidad" + prospectus);
		
		if(prospectus.getName().length()==0)
			throw new CandidateServiceException("No se pudo agregar la entidad" + prospectus);
		
		if(prospectus.getPositionApplied().length()==0)
			throw new CandidateServiceException("No se pudo agregar la entidad" + prospectus);
		
		Candidate insertedCandidate = this.candidateRepository.save(prospectus);
		if(insertedCandidate!=null)
			return insertedCandidate.getId();
		
		return null;
	}
	
}

