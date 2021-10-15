package com.espark.adarsh.api.server.exceptions;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
public class ApplicationException extends DefaultException {

    public ApplicationException(String exceptionCode) {
        super(exceptionCode);
    }

    public ApplicationException(String message, String exceptionCode) {
        super(message, exceptionCode);
    }

    public ApplicationException(String message, Throwable cause, String exceptionCode) {
        super(message, cause, exceptionCode);
    }

    public ApplicationException(Throwable cause, String exceptionCode) {
        super(cause, exceptionCode);
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace, exceptionCode);
    }
}
