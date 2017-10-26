package com.plenumsoft.vuzee.restapi.models;

import org.springframework.hateoas.ResourceSupport;

import com.plenumsoft.vuzee.restapi.controllers.CandidatesRestController;

public class CandidateApi {
	
	private Long id;
	private String name;
	private String positionApplied;
	private String cvUrl;
	private String self;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPositionApplied() {
		return positionApplied;
	}
	public void setPositionApplied(String positionApplied) {
		this.positionApplied = positionApplied;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long candidateId) {
		this.id = candidateId;
	}
	public String getCvUrl() {
		return cvUrl;
	}
	public void setCvUrl(String cvUrl) {
		this.cvUrl = cvUrl;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	
}
