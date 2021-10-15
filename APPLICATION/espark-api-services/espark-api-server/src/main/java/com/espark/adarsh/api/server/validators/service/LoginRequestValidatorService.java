package com.espark.adarsh.api.server.validators.service;

import com.espark.adarsh.api.server.bean.LoginBean;
import com.espark.adarsh.api.server.bean.RequestBean;
import com.espark.adarsh.api.server.utils.MessageUtils;
import com.espark.adarsh.api.server.utils.annotations.RequestValidator;
import com.espark.adarsh.api.server.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@RequestValidator(name = RequestBean.LOGIN_BEAN)
public class LoginRequestValidatorService implements RequestValidatorService {

    private static final String USER_NAME = "username";
    private static final String USER_PWD = "password";

    @Autowired
    MessageUtils messageUtils;

    @Override
    public Map<String, List<String>> validate(RequestBean requestBean) throws ValidationException {
        //TODO implement logic and return kay as field name and list of values as the validation failed message.

        log.info("label=LoginRequestValidatorService validate()");
        LoginBean loginBean = (LoginBean) requestBean;
        Map<String, List<String>> validationResponse = new HashMap<>();
        List<String> userNameErrors = new LinkedList<>();
        if (checkEmpty(loginBean.getUserName())) {
            userNameErrors.add(this.messageUtils.get(MessageUtils.EMPTY_ARGS, new Object[]{USER_NAME}));
        }
        //TODO OTHER TYPE OF APPLICABLE VALIDATIONS FOR USER NAME.
        if (!userNameErrors.isEmpty()) {
            validationResponse.put(USER_NAME, userNameErrors);
        }

        List<String> userPwdErrors = new LinkedList<>();
        if (checkEmpty(loginBean.getUserPwd())) {
            userPwdErrors.add(this.messageUtils.get(MessageUtils.EMPTY_ARGS, new Object[]{USER_PWD}));
        }
        //TODO OTHER TYPE OF APPLICABLE VALIDATIONS FOR USER PWD
        if (!userPwdErrors.isEmpty()) {
            validationResponse.put(USER_PWD, userPwdErrors);
        }


        return validationResponse;
    }
}
