package com.espark.adarsh.api.server.config.serviceurl;


import com.espark.adarsh.api.server.exceptions.ApplicationException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Data
@Component
@ConfigurationProperties("auth")
public class AuthServiceUrlConfigurations implements ServiceUrlConfiguration {

    String host;
    String context;
    Map<String, String> urls;

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public String getUrl(String type) {
        if (urls != null && !urls.isEmpty()) {
            if (urls.containsKey(type)) {
                return urls.get(type);
            } else {
                throw new ApplicationException("invalid url type for auth services ");
            }
        } else {
            throw new ApplicationException("Urls not read for auth services ");
        }
    }
}
