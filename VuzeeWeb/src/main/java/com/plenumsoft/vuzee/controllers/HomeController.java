package com.plenumsoft.vuzee.controllers;



import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plenumsoft.vuzee.entities.TaskStatistics;
import com.plenumsoft.vuzee.services.TaskService;
import com.plenumsoft.vuzee.viewmodels.TasksResumeViewModel;
import com.sun.mail.util.MailSSLSocketFactory;

@Controller
@RequestMapping(value= {"/"})
public class HomeController {
	String prefix = "home/";
	
	private JavaMailSender javaMailSender;
	
	private TaskService taskService;
	
	
	@Autowired
	public HomeController(JavaMailSender javaMailSender, TaskService taskService) {
		super();
		this.javaMailSender = javaMailSender;
		this.taskService = taskService;
	}
	@RequestMapping(value="/")
	public String index(Model model) {
		
		List<TaskStatistics> stats = taskService.findStatistics(); 
		List<TasksResumeViewModel> resumes = new ArrayList<>();
		for(TaskStatistics stat : stats) {
			TasksResumeViewModel resume = new TasksResumeViewModel();
			resume.setCandidateId(stat.getCandidateId());
			resume.setCandidateName(stat.getCandidateName());
			resume.setNumberOfPending(stat.getNumberOfPending());
			resume.setNumberOfInProgress(stat.getNumberOfInProgress());
			resume.setNumberOfDone(stat.getNumberOfDone());
			resumes.add(resume);
		}
		model.addAttribute("stats", resumes);
		return "index";
	}
	@RequestMapping(value= {"/sendmail"},method=RequestMethod.GET)	
	public ModelAndView sendEmail(final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("sendmail");

		try {
		Properties props = new Properties(); 
		MailSSLSocketFactory socketFactory = new MailSSLSocketFactory(); socketFactory.setTrustedHosts(new String[] { "my-server"});
		socketFactory.setTrustAllHosts(true); 
		props.put("mail.smtps.socketFactory", socketFactory);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("demoapps15@gmail.com");
		mail.setTo("msoberanis@plenumsoft.com.mx");
		mail.setSubject("Prueba Spring");
		mail.setText("Este es un correo enviado desde Spring");
		javaMailSender.send(mail);
		}
		catch(Exception ex) {
			modelAndView.addObject("message_error", ex.getLocalizedMessage());
			redirectAttributes.addAttribute("message_error",ex.getLocalizedMessage());
			return modelAndView;
		}
		modelAndView.addObject("message_success", "Awech, listo");
	return modelAndView;
	}
	
}
