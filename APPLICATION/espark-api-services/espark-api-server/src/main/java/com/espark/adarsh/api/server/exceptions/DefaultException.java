package com.espark.adarsh.api.server.exceptions;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
public class DefaultException extends RuntimeException{

    private String exceptionCode;

    public DefaultException(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public DefaultException(String message, String exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public DefaultException(String message, Throwable cause, String exceptionCode) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public DefaultException(Throwable cause, String exceptionCode) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public DefaultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
