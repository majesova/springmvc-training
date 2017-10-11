package com.plenumsoft.vuzee.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.entities.Candidate;

@Service
public interface CandidateService {
	List<Candidate> getAll();
	Long addCandidate(Candidate candidate);
}
