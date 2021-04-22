package com.sjp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{
	private final static long serialversionuid=1L;

	public ResourceNotFound(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	

}
