/**
 * 
 */
package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.common.ResponseUtil;
import com.example.demo.common.ValidationErrorMessage;
import com.example.demo.exception.ValidationException;

/**
 * @author virens
 *
 */
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		return new ResponseEntity<>(new ResponseUtil<>(null, ex.getLocalizedMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String errorMessage = "Validation Failed.";
		BindingResult bindingResult = ex.getBindingResult();
		List<ValidationErrorMessage> errorList = new ArrayList<>(bindingResult.getErrorCount());

		bindingResult.getFieldErrors().forEach(fieldError -> {
			ValidationErrorMessage errorDetailObj = new ValidationErrorMessage();
			errorDetailObj.setObjectName(fieldError.getObjectName());
			errorDetailObj.setField(fieldError.getField());
			errorDetailObj.setRejectedValue(fieldError.getRejectedValue());
			errorDetailObj.setErrorMessage(fieldError.getDefaultMessage());
			errorDetailObj.setErrorCode(fieldError.getCode());
			errorList.add(errorDetailObj);
		});

		return new ResponseEntity<>(new ResponseUtil<>(errorList, errorMessage), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ValidationException ex, WebRequest request) {
		ResponseUtil<List<ValidationErrorMessage>> responseUtil = new ResponseUtil<>();
		responseUtil.setData(ex.getViolations());
		responseUtil.setMessage("Validation Failed.");
		return new ResponseEntity<>(responseUtil, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(new ResponseUtil<>(null, ex.getLocalizedMessage()), HttpStatus.FORBIDDEN);
	}

}
