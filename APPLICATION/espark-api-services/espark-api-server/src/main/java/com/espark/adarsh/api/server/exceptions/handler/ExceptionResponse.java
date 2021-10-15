package com.espark.adarsh.api.server.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private String errorMessage;
    private String requestedURI;
    private String exceptionCode;
    private Map<String, List<String>> validationFailed;

}
