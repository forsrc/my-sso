package org.forsrc.sso.aop;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionRestControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> exception(Exception e, WebRequest request) {
		Map<String, Object> message = new HashMap<>();
		message.put("class", e.getClass());
		message.put("message", e.getMessage());
		Throwable throwable = e.getCause();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		sb.append(i++).append(": ").append(throwable.getMessage()).append("\n");
		while ((throwable = throwable.getCause()) != null) {
			sb.append(i++).append(": ").append(throwable.getMessage()).append("\n");
		}
		message.put("cause", sb.toString());

		return message;
	}

	@ExceptionHandler({ EntityNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> notFound(Exception e, WebRequest request) {
		Map<String, Object> message = new HashMap<>();
		message.put("class", e.getClass());
		message.put("message", e.getMessage());
		Throwable throwable = e.getCause();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		sb.append(i++).append(": ").append(throwable.getMessage()).append("\n");
		while ((throwable = throwable.getCause()) != null) {
			sb.append(i++).append(": ").append(throwable.getMessage()).append("\n");
		}
		message.put("cause", sb.toString());

		return message;
	}

}