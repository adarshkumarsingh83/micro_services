package com.espark.adarsh.api.server.validators.service;

import com.espark.adarsh.api.server.bean.RequestBean;
import com.espark.adarsh.api.server.utils.MessageUtils;
import com.espark.adarsh.api.server.utils.annotations.RequestValidator;
import com.espark.adarsh.api.server.exceptions.ApplicationException;
import com.espark.adarsh.api.server.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@Service
public class ValidatorService {

    @Autowired
    MessageUtils messageUtils;

    @Autowired
    ConfigurableApplicationContext context;

    private Map<String, RequestValidatorService> validatorHashMap = new HashMap<>();

    /**
     * loadValidators() loads the all type of request validators
     */
    @PostConstruct
    public void loadValidators() {
        log.info("label=ValidatorFactoryService loadValidators() ");
        final Map<String, Object> beans = context.getBeanFactory()
                .getBeansWithAnnotation(RequestValidator.class);
        for (final Map.Entry<String, Object> bean : beans.entrySet()) {
            validatorHashMap.put(bean.getValue().getClass().getAnnotation(RequestValidator.class).name(),
                    (RequestValidatorService) bean.getValue());
        }
    }

    /**
     * validateData() will select the request validator based on type and then invoke validate()
     * and wrap the response of the validate() in validation exception if their are any.
     *
     * @param type        of the request bean.
     * @param requestBean
     * @throws ValidationException
     */
    public void validateData(String type, RequestBean requestBean) throws ValidationException {
        log.info("label=ValidatorFactoryService validateData() ");
        if (StringUtils.isEmpty(type)) {
            log.error("label=ValidatorFactoryService validateData() emptyType");
            throw new ApplicationException(MessageUtils.INVALID_INPUT_TYPE,
                    this.messageUtils.get(MessageUtils.INVALID_INPUT_TYPE, new Object[]{type, "request-bean-type"}));
        }
        if (requestBean == null) {
            log.error("label=ValidatorFactoryService validateData() null requestBean");
            throw new ApplicationException(MessageUtils.INVALID_INPUT_TYPE,
                    this.messageUtils.get(MessageUtils.INVALID_INPUT_TYPE, new Object[]{null, "request-bean"}));
        }

        if (this.validatorHashMap.containsKey(type)) {
            log.debug("label=ValidatorFactoryService loadValidators() key found for validation");
            RequestValidatorService validatorService = this.validatorHashMap.get(type);
            Map<String, List<String>> validationResponse = validatorService.validate(requestBean);
            if (validationResponse != null && !validationResponse.isEmpty()) {
                log.error("label=ValidatorFactoryService validateData() validations failed for type={}", type);
                validationResponse
                        .entrySet()
                        .stream()
                        .forEach(entry -> log.error(entry.getKey(), entry.getValue()));
                throw new ValidationException(type, validationResponse);
            }
        } else {
            log.error("label=ValidatorFactoryService validateData() invalid type for bean validation");
            throw new ApplicationException(MessageUtils.INVALID_VALIDATOR_TYPE,
                    this.messageUtils.get(MessageUtils.INVALID_VALIDATOR_TYPE, new Object[]{type}));
        }
    }
}
