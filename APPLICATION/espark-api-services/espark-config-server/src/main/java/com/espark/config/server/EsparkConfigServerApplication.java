package com.espark.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@EnableConfigServer
@SpringBootApplication
public class EsparkConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsparkConfigServerApplication.class, args);
	}

}
