package com.espark.adarsh.api.server.validators;

import com.espark.adarsh.api.server.bean.RequestBean;
import com.espark.adarsh.api.server.validators.service.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@Aspect
@Configuration
public class ValidationProcessor {

    @Autowired
    ValidatorService validatorService;

    /**
     * executeValidateProcess() will trigger the validate process for the request bean which are annotated with
     * ValidateRequestBean annotation before it get executed in the domain services.
     *
     * @param joinPoint aop based joint point to trap the args of the domain services
     */
    @Before("@annotation(com.espark.adarsh.api.server.utils.annotations.ValidateRequestBean) && args(..)")
    public void executeValidateProcess(JoinPoint joinPoint) {
        log.info("label=ValidationProcessor executeValidateProcess()");
        Object[] arguments = joinPoint.getArgs();
        for (Object methodArgs : arguments) {
            if (methodArgs != null && methodArgs instanceof RequestBean) {
                RequestBean requestBean = (RequestBean) methodArgs;
                log.debug("label=ValidationProcessor executeValidateProcess() type={} for validation ",
                        requestBean.getBeanType());
                this.validatorService.validateData(requestBean.getBeanType(), requestBean);
                break;
            }
        }
    }
}
