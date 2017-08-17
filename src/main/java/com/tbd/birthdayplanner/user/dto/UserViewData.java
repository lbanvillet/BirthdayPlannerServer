/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * View data of the product.
 *
 * @author lb185112
 */
public class UserViewData extends UserBasicData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifier of the user provided by the client.
     */
    @NotNull
    @Valid
    private UserIdData userId;

    /**
     * Retrieves the value for {@link #userId}.
     *
     * @return the current value
     */
    public UserIdData getUserId() {
        return userId;
    }

    /**
     * Provides a value for {@link #userId}.
     *
     * @param userId the new value to set
     */
    public void setUserId(UserIdData userId) {
        this.userId = userId;
    }
}
