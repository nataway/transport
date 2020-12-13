package com.ziczac.transport.validator.annotations;

import javax.validation.Constraint;

import com.ziczac.transport.validator.AdultValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, PARAMETER})
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {
	String message() default "{adult}";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
