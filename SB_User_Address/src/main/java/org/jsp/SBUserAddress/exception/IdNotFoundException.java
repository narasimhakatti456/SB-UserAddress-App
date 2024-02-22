package org.jsp.SBUserAddress.exception;

public class IdNotFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Id";
	}
//
//	public IdNotFoundException(String message) {
//		super(message);
//	}
//
//	public IdNotFoundException() {
//	}
}
