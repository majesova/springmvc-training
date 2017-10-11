package com.plenumsoft.vuzee.services;

public class CandidateServiceException  extends RuntimeException {
	
	private static final long serialVersionUID = -45039187260255302L;

	public CandidateServiceException() {
		super();
	}
	
	public CandidateServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)	
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public CandidateServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CandidateServiceException(String message) {
		super(message);
	}

	public CandidateServiceException(Throwable cause) {
		super(cause);
	}
}
