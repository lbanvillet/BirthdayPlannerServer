/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Used to provide a bean validation constraint for a key name.
 *
 * @author lb185112
 */
@Constraint(validatedBy = {})
@Documented
@Pattern(regexp = "\\p{Alpha}[\\w\\.-]*")
@Retention(RetentionPolicy.RUNTIME)
@Size(min = ValidKey.MIN_KEY_LENGTH, max = ValidKey.MAX_KEY_LENGTH)
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER
})
public @interface ValidKey {

    /**
     * Upper bound for key length.
     */
    int MAX_KEY_LENGTH = 255;

    /**
     * Lower bound for key length.
     */
    int MIN_KEY_LENGTH = 1;

    /**
     * List of groups supported.
     */
    Class<?>[] groups() default {

    };

    /**
     * Message to report violation.
     */
    String message() default "{invalid.key}";

    /**
     * Payload types.
     */
    Class<? extends Payload>[] payload() default {

    };

}
