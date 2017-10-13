package com.plenumsoft.vuzee.controllers;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.services.CandidateService;
import com.plenumsoft.vuzee.viewmodels.CandidateCreateViewModel;
import com.plenumsoft.vuzee.viewmodels.CandidateEditViewModel;

@Controller
@RequestMapping(value= {"/candidates"})
public class CandidatesController {
	String prefix = "candidates/";
	
	private CandidateService candidateService;
	
	
	public CandidatesController() {
	}
	
	@Autowired
	public CandidatesController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}
	
	@RequestMapping(value = { "/", "" })
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView(prefix +"index");
		List<Candidate> candidates= candidateService.getAll();
		mv.addObject("candidates", candidates);
		return mv;
	}
	
	@RequestMapping(value = { "/create"})
	public String PrepareCreate(CandidateCreateViewModel candidateCreateViewModel) {
		return prefix+"create";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String PostCreateCandidate(@Valid CandidateCreateViewModel candidateCreateViewModel, BindingResult bindingResult, final RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) {
		if(bindingResult.hasErrors()) {
			return prefix+"create";
		}
		try 
		{
			Candidate candidate = new Candidate();
			if(file!=null) {
				String mimeType = file.getContentType();
				byte[] fileBytes = file.getBytes();
				String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
				candidate.setCvFile(fileBytes);
				candidate.setCvExtension(fileExtension);
				candidate.setCvMimeType(mimeType);
			}	
		candidate.setName(candidateCreateViewModel.getName());
		candidate.setPositionApplied(candidateCreateViewModel.getPositionApplied());
		candidate.setCreatedBy("msoberanis");//TODO: hard-code
		Date now = new Date();
		candidate.setCreatedAt(new Date());
		
		candidateService.addCandidate(candidate);
		}catch(Exception ex) {
			redirectAttributes.addFlashAttribute("message_error",ex.getLocalizedMessage());
		}
		redirectAttributes.addFlashAttribute("message_success","Registro creado con éxito");
		return "redirect:/"+ prefix +"/";
	}
	
	@RequestMapping(value = { "/edit/{id}"}, method= RequestMethod.GET)
	public String PrepareEdit(@PathVariable("id") Long id, Model model, final RedirectAttributes redirectAttributes) {	
		Candidate candidate = candidateService.findById(id);
		if(candidate!=null) {
		CandidateEditViewModel candidateEditViewModel = new CandidateEditViewModel();
		candidateEditViewModel.setCreatedAt(candidate.getCreatedAt());
		candidateEditViewModel.setCreatedBy(candidate.getCreatedBy());
		candidateEditViewModel.setId(candidate.getId());
		candidateEditViewModel.setName(candidate.getName());
		candidateEditViewModel.setPositionApplied(candidate.getPositionApplied());
		candidateEditViewModel.setHasFile(candidate.getCvFile()!=null);
		model.addAttribute("candidateEditViewModel", candidateEditViewModel);
		}else {
			redirectAttributes.addFlashAttribute("message_error","No se encontró el candidato");
		}
		return prefix+"edit";
	}
	
	@RequestMapping(value = { "/edit/{id}"}, method= RequestMethod.PUT)
	public String PutEdit(@Valid CandidateEditViewModel candidateEditViewModel, BindingResult bindingResult, Model model,  final RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) {
		try {
		if(bindingResult.hasErrors()) {
			model.addAttribute("candidateEditViewModel", candidateEditViewModel);
			List<ObjectError> errors = bindingResult.getAllErrors();
			return prefix + "edit";
		}
		Long id = candidateEditViewModel.getId();
		Candidate candidate = candidateService.findById(id);
		candidate.setName(candidateEditViewModel.getName());
		candidate.setPositionApplied(candidateEditViewModel.getPositionApplied());
		if(file!=null) {
			String mimeType = file.getContentType();
			byte[] fileBytes = file.getBytes();
			String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
			candidate.setCvFile(fileBytes);
			candidate.setCvExtension(fileExtension);
			candidate.setCvMimeType(mimeType);
		}	
		candidateService.updateCandidate(candidate);
		
		}catch(Exception ex) {
			redirectAttributes.addFlashAttribute("message_error", ex.getLocalizedMessage());	
		}
		//Solo se mantiene una petición
		redirectAttributes.addFlashAttribute("message_success","Registro actualizado con éxito");
		return "redirect:/"+ prefix +"/";
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Candidate deleteCandidate(@PathVariable Long id) {
		Candidate candidate = candidateService.findById(id);
		candidateService.deleteCandidate(candidate);
		return candidate;
	} 
	
	@RequestMapping(value="/cv/{id}", method=RequestMethod.GET)
	@ResponseBody
	public void getCVFile(@PathVariable("id") Long id, HttpServletResponse response,
           HttpServletRequest request){
		try {
			Candidate candidate = candidateService.findById(id);
			if(candidate.getCvFile()!=null) {
				byte[] file = candidate.getCvFile();
				String fileName = candidate.getName().replaceAll(" ", "_");
				response.setContentType(candidate.getCvMimeType());
			    response.setHeader("Content-Disposition", "attachment; filename=cv_"+ fileName.toLowerCase() +"."+ candidate.getCvExtension());
			    response.setHeader("Pragma", "no-cache");
			    response.setHeader("Cache-Control", "no-cache");
			    response.getOutputStream().write(file);
			}
			
		}catch(Exception ex) {
		}
		
	}
	
	
	
	
	
}
