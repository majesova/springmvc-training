package com.plenumsoft.vuzee.controllers;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plenumsoft.vuzee.VuzeeConstants;
import com.plenumsoft.vuzee.entities.Candidate;
import com.plenumsoft.vuzee.entities.Task;
import com.plenumsoft.vuzee.entities.TaskState;
import com.plenumsoft.vuzee.services.CandidateService;
import com.plenumsoft.vuzee.services.TaskService;
import com.plenumsoft.vuzee.viewmodels.TaskCreateViewModel;

@Controller
@RequestMapping(value= {"/tasks"})
public class TasksController {
	String prefix = VuzeeConstants.TasksControllerPrefix;
	
	@Autowired
	public TasksController(TaskService taskService, CandidateService candidateService) {
		super();
		this.taskService = taskService;
		this.candidateService = candidateService;
	}
	
	private TaskService taskService;
	
	private CandidateService candidateService;
	
	
	@RequestMapping(value = { "/create"}, method= RequestMethod.GET)
	public String prepareCreate(TaskCreateViewModel taskCreateViewModel) {
		return prefix + "create";
	}
	
	
	@ModelAttribute("elegibleCandidates")
	public List<Candidate> getCandidates(){
		List<Candidate> candidates = candidateService.getAllCandidatesSortedByName();
		return candidates;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String postCreateTask(@Valid TaskCreateViewModel taskCreateViewModel, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			return prefix + "create";
		}
		try {
			
		if(taskService.findByTitle(taskCreateViewModel.getTitle())!=null) {
			model.addAttribute("message_error","El título expecificado ya existe en la base de datos ");
			return prefix + "create";
		}
		Task task = new Task();
		task.setTitle(taskCreateViewModel.getTitle());
		task.setInstructions(taskCreateViewModel.getInstructions());
		Candidate candidate = candidateService.findById(taskCreateViewModel.getSelectedCandidateId());
		task.setCandidate(candidate);
		task.setTaskState(TaskState.PENDING);
		task.setHasRating(taskCreateViewModel.getHasRating());
		task.setTaskDate(taskCreateViewModel.getTaskDate());
		task.setCreatedAt(new Date());
		task.setCreatedBy("msoberanis");
		taskService.addTask(task);
	}catch(Exception ex) {
		//model.addAttribute("message_error",ex.getLocalizedMessage());
		model.addAttribute("message_error",ex.getLocalizedMessage());
		return prefix + "create";
	}
		redirectAttributes.addFlashAttribute("message_success","Registro creado con éxito");
		return "redirect:/" +  prefix +"/";
	}
	
	
	
}
