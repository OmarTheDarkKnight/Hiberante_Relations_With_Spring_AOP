package com.bat.annotations;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {
    // define default table name
    public String table();

    // define default column name
    public String column();

    // define default error message
    public String message() default "Value does not exist.";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};
}
