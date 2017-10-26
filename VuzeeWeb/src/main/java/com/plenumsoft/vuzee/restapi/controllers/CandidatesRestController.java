package com.plenumsoft.vuzee.restapi.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plenumsoft.vuzee.controllers.CandidatesController;
import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.restapi.models.CandidateApi;
import com.plenumsoft.vuzee.restapi.models.Greeting;
import com.plenumsoft.vuzee.services.CandidateService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesRestController {
	
	private CandidateService candidateService;
	
	@Autowired
	public CandidatesRestController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
	
	@RequestMapping(value= {"/"},method = RequestMethod.GET)
	public ResponseEntity<?> get(){
		try 
		{
			List<Candidate> candidates = candidateService.getAllCandidatesSortedByName();
			List<CandidateApi> models = new ArrayList<CandidateApi>();
			if(candidates!=null && candidates.size()>0){
					models = candidates.stream()
							.map(candidate->Mapper.convertToApiModel(candidate))
							.collect(Collectors.toList());
				}
			return new ResponseEntity<>(models, HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable Long id){
		try 
		{
			Candidate candidate = candidateService.findById(id);
			CandidateApi model = Mapper.convertToApiModel(candidate);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/",method = RequestMethod.POST)
	public ResponseEntity<?> post(CandidateApi model){
		try 
		{
			Candidate candidate = new Candidate();
			candidate.setName(model.getName());
			candidate.setCreatedAt(new Date());
			candidate.setPositionApplied(model.getPositionApplied());
			candidateService.addCandidate(candidate);
			return new ResponseEntity<>(Mapper.convertToApiModel(candidate), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> put(CandidateApi model){
		try 
		{
			Candidate candidate = new Candidate();
			candidate.setName(model.getName());
			candidate.setCreatedAt(new Date());
			candidate.setPositionApplied(model.getPositionApplied());
			candidateService.updateCandidate(candidate);
			return new ResponseEntity<>(Mapper.convertToApiModel(candidate), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id,CandidateApi model){
		try 
		{
			Candidate candidate = new Candidate();
			candidate.setName(model.getName());
			candidate.setCreatedAt(new Date());
			candidate.setPositionApplied(model.getPositionApplied());
			candidateService.deleteCandidate(candidate);
			return new ResponseEntity<>(Mapper.convertToApiModel(candidate), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
