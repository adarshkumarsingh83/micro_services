package com.espark.adarsh.api.server.services.integration;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.espark.adarsh.api.server.bean.LoginBean;
import com.espark.adarsh.api.server.config.serviceurl.AuthServiceUrlConfigurations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@Service("authIntegrationService")
public class AuthAuthIntegrationServiceImpl implements AuthIntegrationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuthServiceUrlConfigurations authServiceUrlConfigurations;


    @HystrixCommand(
            fallbackMethod = "loginProcessCallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public Object loginProcess(LoginBean loginBean) {
        log.info("label=AuthAuthIntegrationServiceImpl loginProcess()");
        String serviceUrl = this.authServiceUrlConfigurations.getHost()
                + this.authServiceUrlConfigurations.getContext()
                + this.authServiceUrlConfigurations.getUrl(loginBean.getBeanType());
        HashMap response = restTemplate
                .exchange(serviceUrl
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<HashMap>() {
                        }).getBody();
        return response;
    }

    @SuppressWarnings("unused")
    public Object loginProcessCallback(LoginBean loginBean, Throwable exception) {
        log.info("label=AuthAuthIntegrationServiceImpl loginProcessCallback()");
        //TODO IMPLEMENT LOGIC FOR FALLBACK OPERATIONS
        return new HashMap<String, String>() {
            {
                put("auth", "successful");
            }
        };
    }

}
