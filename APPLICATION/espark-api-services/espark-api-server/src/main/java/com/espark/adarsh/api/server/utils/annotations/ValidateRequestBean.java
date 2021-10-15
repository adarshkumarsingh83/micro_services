package com.espark.adarsh.api.server.utils.annotations;


import java.lang.annotation.*;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ValidateRequestBean {

    String value() default "";
}
