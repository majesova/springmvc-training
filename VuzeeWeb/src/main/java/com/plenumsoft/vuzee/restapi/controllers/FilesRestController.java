package com.plenumsoft.vuzee.restapi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.restapi.models.Greeting;
import com.plenumsoft.vuzee.services.CandidateService;

@RestController
@RequestMapping("/api/files")
public class FilesRestController {
	
	private CandidateService candidateService;
	@Autowired
	public FilesRestController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}

	@RequestMapping(value="/cv/{id}", method=RequestMethod.GET)
	public HttpEntity<?> getCVFile(@PathVariable("id") Long id){
		try {
			Candidate candidate = candidateService.findById(id);
			if(candidate.getCvFile()!=null) {
				byte[] file = candidate.getCvFile();
				String fileName = candidate.getName().replaceAll(" ", "_");
				 HttpHeaders header = new HttpHeaders();
				    header.set(HttpHeaders.CONTENT_TYPE,candidate.getCvMimeType());
				    header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv_"+ fileName.toLowerCase() +"."+ candidate.getCvExtension());
				    header.setContentLength(file.length);
				    header.set(HttpHeaders.PRAGMA, "no-cache");
				    header.set(HttpHeaders.CACHE_CONTROL, "no-cache");
				    return new HttpEntity<byte[]>(file, header);
			}
			
		}catch(Exception ex) {
			return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
	}
}
