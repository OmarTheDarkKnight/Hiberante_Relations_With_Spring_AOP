package com.bat.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Email(message="Please provide a valid email address")
@Pattern(regexp="^[\\w!#$%&*/=?^_+-{|}~]+(?:\\.[\\w!#$%&*/=?^_+-{|}~]+)*@(?:[^-.][\\w]+(-[\\w]+)*\\.)+[a-zA-Z]{2,10}$", message="Please provide a valid email address")
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface CustomEmail {
    public String message()  default "Please provide a valid email address";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}