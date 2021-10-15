package com.espark.adarsh.api.server.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.espark.adarsh.api.server.utils.MessageUtils;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Data
public class LoginBean implements RequestBean {

    @NotNull(message = MessageUtils.NULL_ARGS)
    @NotEmpty(message = MessageUtils.EMPTY_ARGS)
    String userName;

    @NotNull(message = MessageUtils.NULL_ARGS)
    @NotEmpty(message = MessageUtils.EMPTY_ARGS)
    String userPwd;

    @JsonIgnore
    @Override
    public String getBeanType() {
        return LOGIN_BEAN;
    }
}
