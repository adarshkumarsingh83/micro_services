package com.espark.adarsh.api.server.exceptions.handler;

import com.espark.adarsh.api.server.exceptions.ApplicationException;
import com.espark.adarsh.api.server.exceptions.ValidationException;
import com.espark.adarsh.api.server.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @Autowired
    MessageUtils messageUtils;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception,
                                                            final HttpServletRequest request) {
        log.info("label=ApplicationExceptionHandler handleMethodArgumentNotValidException()");
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        Map<String, List<String>> errorMap = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(errorObject -> {
            String fieldName = ((FieldError) errorObject).getField();
            String errorCode = errorObject.getDefaultMessage();
            errorMap.put(fieldName,
                    Arrays.asList(this.messageUtils.get(errorCode, new Object[]{fieldName})));
        });
        error.setValidationFailed(errorMap);
        return error;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse handleConstraintViolationException(final ConstraintViolationException exception,
                                                         final HttpServletRequest request) {
        log.info("label=ApplicationExceptionHandler handleConstraintViolationException()");
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        Map<String, List<String>> errorMap = new HashMap<>();
        exception.getConstraintViolations().forEach(violation -> {
            String message = violation.getMessage();
            String fieldName = violation.getPropertyPath().toString();
            errorMap.put(fieldName, Arrays.asList(message));
        });
        error.setValidationFailed(errorMap);
        return error;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse handleValidationException(final ValidationException exception,
                                                final HttpServletRequest request) {
        log.info("label=ApplicationExceptionHandler handleValidationException()");
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        error.setExceptionCode(exception.getExceptionCode());
        error.setValidationFailed(exception.getValidationFailed());
        return error;
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleApplicationException(final ApplicationException exception,
                                                 final HttpServletRequest request) {
        log.info("label=ApplicationExceptionHandler handleApplicationException()");
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        error.setExceptionCode(exception.getExceptionCode());
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleException(final Exception exception,
                                      final HttpServletRequest request) {
        log.info("label=ApplicationExceptionHandler handleException()");
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        return error;
    }

}
