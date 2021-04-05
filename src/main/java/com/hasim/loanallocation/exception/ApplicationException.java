package com.hasim.loanallocation.exception;

public class ApplicationException extends RuntimeException{
	private static final long serialVersionUID = -6522864005919472631L;

	public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

}
