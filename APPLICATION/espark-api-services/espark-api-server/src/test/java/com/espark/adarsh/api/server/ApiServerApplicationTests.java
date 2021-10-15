package com.espark.adarsh.api.server;

import com.espark.adarsh.api.server.services.domain.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date::15/6/20 10:12 AM#$
 */
@Slf4j
@SpringBootTest
@ContextConfiguration(classes = ApiServerApplication.class)
public abstract class ApiServerApplicationTests {

    @Autowired
    protected AuthenticationService authenticationService;

    @BeforeAll
    public static void initForAllTest() {
        log.info("label=ApiServerApplicationTests initForAllTest() ");
        //TODO INIT ACTIVITY FOR ALL THE TEST CASES
    }

    @Test
    void contextLoads() {
        log.info("label=ApiServerApplicationTests contextLoads() ");
    }


    @AfterAll
    public static void cleanUpForAllTest() {
        log.info("label=ApiServerApplicationTests cleanUpForAllTest() ");
        //TODO CLEANUP ACTIVITY FOR ALL THE TEST CASES
    }

}
