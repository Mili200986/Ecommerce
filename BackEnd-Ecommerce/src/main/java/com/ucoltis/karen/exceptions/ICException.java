package com.ucoltis.karen.exceptions;

public class ICException extends RuntimeException{

private static final long serialVersionUID = 1L;
	
	public ICException() {
		super("Credenciales invalidas");
}
}
