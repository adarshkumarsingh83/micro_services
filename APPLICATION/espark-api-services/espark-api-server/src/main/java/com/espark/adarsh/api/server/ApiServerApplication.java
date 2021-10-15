package com.espark.adarsh.api.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
@EnableHystrix
@EnableAspectJAutoProxy
@SpringBootApplication
public class ApiServerApplication {

	public static void main(String[] args) {
		log.info("label=ApiServerApplication main()");
		SpringApplication.run(ApiServerApplication.class, args);
	}

}
