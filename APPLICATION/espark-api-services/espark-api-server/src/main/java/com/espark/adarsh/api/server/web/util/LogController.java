package com.espark.adarsh.api.server.web.util;

import com.espark.adarsh.api.server.services.util.DynamicLoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/log")
public class LogController {

    @Autowired
    DynamicLoggerService dynamicLoggerService;

    @GetMapping("/beans")
    public Map<String, String> getSpringBeanNames() {
        log.info("label=LogController getSpringBeanNames() ");
        return this.dynamicLoggerService.getSpringBeanNames();
    }

    @PutMapping(value = "/all/{logLevel}")
    public String changeLogLevelForAll(@PathVariable("logLevel") String logLevel) {
        log.info("label=LogController changeLogLevelForAll() LogLevel supplied for all level={}", logLevel);
        return this.dynamicLoggerService.changeLogLevelForAll(logLevel);
    }

    @PutMapping(value = "/{logLevel}")
    public Map<String, String> changeLogLevelForSelected(@RequestBody List<String> classNameList, @PathVariable("logLevel") String logLevel) {
        log.info("label=LogController  changeLogLevelForSelected() level={} supplied for classes={}", logLevel, classNameList);
        return this.dynamicLoggerService.changeLogLevelForSelected(classNameList, logLevel);
    }

}
