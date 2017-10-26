package com.plenumsoft.vuzee.restapi.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.restapi.models.CandidateApi;


public class Mapper {

	public static CandidateApi convertToApiModel(Candidate candidate) {
		ModelMapper modelMapper = new ModelMapper();
		CandidateApi apiModel = modelMapper.map(candidate, CandidateApi.class);
		Link linkCv = linkTo(methodOn(FilesRestController.class).getCVFile(candidate.getId())).withRel("cv");
		Link self  = linkTo(methodOn(CandidatesRestController.class).get(candidate.getId())).withSelfRel();
		if(candidate.getCvFile()!=null && candidate.getCvFile().length>0)
			apiModel.setCvUrl(linkCv.getHref());
		apiModel.setSelf(self.getHref());
		return apiModel;
	}
	
	
}
