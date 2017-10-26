package com.plenumsoft.vuzee.services;

public class TaskServiceException  extends RuntimeException {
	
	private static final long serialVersionUID = -45039187260255305L;

	public TaskServiceException() {
		super();
	}
	
	public TaskServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)	
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public TaskServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskServiceException(String message) {
		super(message);
	}

	public TaskServiceException(Throwable cause) {
		super(cause);
	}
}
