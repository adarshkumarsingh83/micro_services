package com.espark.adarsh.api.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Data
@Component
@ConfigurationProperties("espark.swagger")
public class SwaggerLocations {

    public List<Directories> directories;

    @Data
    public static class Directories{
        String fileName;
        String directory;
        List<String> locations;
    }
}