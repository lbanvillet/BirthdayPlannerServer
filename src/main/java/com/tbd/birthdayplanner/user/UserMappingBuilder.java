/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import com.tbd.birthdayplanner.user.dto.CreateUserRequest;
import com.tbd.birthdayplanner.user.dto.UserViewData;

/**
 * Mapping builder used for users.
 *
 * @author lb185112
 */
public class UserMappingBuilder extends BeanMappingBuilder {

    /**
     * Field <code>userId.phone</code>
     */
    private static final String USER_ID_PHONE_FIELD = "userId.phone";

    /**
     * Field <code>phone</code>
     */
    private static final String PHONE_FIELD = "phone";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        mapping(CreateUserRequest.class, User.class, TypeMappingOptions.mapNull(false), TypeMappingOptions.oneWay())
                .fields(USER_ID_PHONE_FIELD, PHONE_FIELD);
        mapping(User.class, UserViewData.class, TypeMappingOptions.mapNull(false)).fields(PHONE_FIELD, USER_ID_PHONE_FIELD);
    }
}