package com.plenumsoft.vuzee.viewmodels;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public class TaskCreateViewModel {

	@NotNull	
	Boolean hasRating;
	@NotNull
	@Size(min=2, max=100)
	@Length(max=100)
	String title;
	@NotNull
	@Size(min=2, max=250)
	@Length(max=250)
	String instructions;
	
	@NotNull
	Long selectedCandidateId;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date taskDate;
	
	public Boolean getHasRating() {
		return hasRating;
	}

	public void setHasRating(Boolean hasRating) {
		this.hasRating = hasRating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Long getSelectedCandidateId() {
		return selectedCandidateId;
	}

	public void setSelectedCandidateId(Long selectedCandidateId) {
		this.selectedCandidateId = selectedCandidateId;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
}
