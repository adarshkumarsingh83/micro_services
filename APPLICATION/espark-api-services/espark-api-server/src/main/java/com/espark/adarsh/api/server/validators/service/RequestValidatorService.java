package com.espark.adarsh.api.server.validators.service;

import com.espark.adarsh.api.server.bean.RequestBean;
import com.espark.adarsh.api.server.exceptions.ValidationException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
public interface RequestValidatorService {

    /**
     * validate() take input of any type of child bean of request bean validate it and
     * aggregate it and then return list of the exception for a particular field in bean.
     *
     * @param requestBean
     * @return
     * @throws ValidationException
     */
    Map<String, List<String>> validate(RequestBean requestBean) throws ValidationException;

    default boolean checkNull(Object value) {
        return value == null;
    }

    default boolean checkEmpty(String value) {
        return StringUtils.isEmpty(value);
    }

}
