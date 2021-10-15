package com.espark.adarsh.api.server.exceptions;

import java.util.List;
import java.util.Map;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
public class ValidationException extends RuntimeException {

    private String exceptionCode;
    private Map<String, List<String>> validationFailed;

    public ValidationException(String exceptionCode,
                               Map<String, List<String>> validationFailed) {
        this.exceptionCode = exceptionCode;
        this.validationFailed = validationFailed;
    }

    public ValidationException(String message, String exceptionCode,
                               Map<String, List<String>> validationFailed) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.validationFailed = validationFailed;
    }

    public ValidationException(String message, Throwable cause, String exceptionCode,
                               Map<String, List<String>> validationFailed) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
        this.validationFailed = validationFailed;
    }

    public ValidationException(Throwable cause, String exceptionCode,
                               Map<String, List<String>> validationFailed) {
        super(cause);
        this.exceptionCode = exceptionCode;
        this.validationFailed = validationFailed;
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace, String exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public Map<String, List<String>> getValidationFailed() {
        return validationFailed;
    }

    public void setValidationFailed(Map<String, List<String>> validationFailed) {
        this.validationFailed = validationFailed;
    }
}
