package com.espark.adarsh.api.server.web.domain;

import com.espark.adarsh.api.server.services.domain.AuthenticationService;
import com.espark.adarsh.api.server.bean.LoginBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public Object loginProcess(@Valid @RequestBody LoginBean loginBean) {
        log.info("label=AuthenticationController loginProcess()");
        return this.authenticationService.loginProcess(loginBean);
    }

}
