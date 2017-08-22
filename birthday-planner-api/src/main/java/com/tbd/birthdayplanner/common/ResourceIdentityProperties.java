/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the names of the resource's properties that uniquely identify the resource.
 *
 * @author lb185112
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE
})
public @interface ResourceIdentityProperties {

    /**
     * The key string.
     */
    String key() default "key";

    /**
     * The resource string.
     */
    String resource() default "resource";
}