package com.ucoltis.karen.exceptions;

public class UException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UException() {
		super("Usuario ya tiene cuenta");
	}

}
