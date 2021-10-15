package com.espark.adarsh.api.server.services.domain;


import com.espark.adarsh.api.server.services.integration.AuthIntegrationService;
import com.espark.adarsh.api.server.bean.LoginBean;
import com.espark.adarsh.api.server.utils.MessageUtils;
import com.espark.adarsh.api.server.exceptions.ApplicationException;
import com.espark.adarsh.api.server.utils.annotations.ValidateRequestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    MessageUtils messageUtils;

    @Autowired
    AuthIntegrationService authIntegrationService;

    @Override
    @ValidateRequestBean
    public Object loginProcess(LoginBean login) {
        log.info("label=AuthenticationServiceImpl login()");
        try {
            return this.authIntegrationService.loginProcess(login);
        } catch (Exception e) {
            throw new ApplicationException(MessageUtils.PROCESSING_ERROR,
                    this.messageUtils.get(MessageUtils.PROCESSING_ERROR, new Object[]{login.getBeanType()}));
        }
    }
}
