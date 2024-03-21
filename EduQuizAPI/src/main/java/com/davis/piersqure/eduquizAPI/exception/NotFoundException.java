package com.davis.piersqure.eduquizAPI.exception;


public class NotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String string) {
		super(string);
	}

}
