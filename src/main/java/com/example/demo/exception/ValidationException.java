/**
 * 
 */
package com.example.demo.exception;

import java.util.Collections;
import java.util.List;

import com.example.demo.common.ValidationErrorMessage;

/**
 * @author virens
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	List<ValidationErrorMessage> errorList;

	public ValidationException(List<ValidationErrorMessage> exceptions) {
		super();
		this.errorList = exceptions;
	}

	public ValidationException(ValidationErrorMessage exception) {
		super();
		this.errorList = Collections.singletonList(exception);
	}

	public List<ValidationErrorMessage> getViolations() {
		return errorList;
	}

}
