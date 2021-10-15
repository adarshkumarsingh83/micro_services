package com.espark.adarsh.api.server.config.serviceurl;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
public interface ServiceUrlConfiguration {

    String getHost();
    String getContext();
    String getUrl(String type);
}
