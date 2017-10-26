package com.plenumsoft.vuzee.viewmodels;

public class TasksResumeViewModel {

	private Long candidateId;
	private String candidateName;
	private int numberOfPending;
	private int numberOfInProgress;
	private int numberOfDone;
	
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public int getNumberOfPending() {
		return numberOfPending;
	}
	public void setNumberOfPending(int numberOfPending) {
		this.numberOfPending = numberOfPending;
	}
	public int getNumberOfInProgress() {
		return numberOfInProgress;
	}
	public void setNumberOfInProgress(int numberOfInProgress) {
		this.numberOfInProgress = numberOfInProgress;
	}
	public int getNumberOfDone() {
		return numberOfDone;
	}
	public void setNumberOfDone(int numberOfDone) {
		this.numberOfDone = numberOfDone;
	}
}
