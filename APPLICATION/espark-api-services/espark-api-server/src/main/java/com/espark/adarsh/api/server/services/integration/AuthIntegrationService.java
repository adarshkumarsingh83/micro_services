package com.espark.adarsh.api.server.services.integration;

import com.espark.adarsh.api.server.bean.LoginBean;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
public interface AuthIntegrationService {

     Object loginProcess(LoginBean loginBean);

}
