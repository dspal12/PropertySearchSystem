package com.dhruv.PropertySearchLogin_webapp.helper_exception;

public class UserNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
        super("User with this username not found in database !!");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
