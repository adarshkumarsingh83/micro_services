package com.espark.adarsh.api.server.bean;


/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
public interface RequestBean {

    // TYPE OF THE BEAN FOR VALIDATION AND FOR SERVICE URL
    String LOGIN_BEAN="login";

    /**
     * getBeanType() will return the type of bean.
     * @return
     */
    String getBeanType();

}
