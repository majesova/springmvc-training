package com.plenumsoft.vuzee.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.entities.Candidate;

@Service
public interface CandidateService {
	List<Candidate> getAll();
	Candidate findById(Long id);
	Long addCandidate(Candidate candidate);
	void updateCandidate(Candidate candidate);
}
