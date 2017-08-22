/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * View data of the user.
 *
 * @author lb185112
 */
public class UserViewData extends UserData {

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
     * Whether the user is real or not.
     */
    private boolean virtual;

    /**
     * Retrieves the value for {@link #userId}.
     *
     * @return the current value
     */
    public UserIdData getUserId() {
        return userId;
    }

    /**
     * Retrieves the value for {@link #virtual}.
     *
     * @return the current value
     */
    public boolean isVirtual() {
        return virtual;
    }

    /**
     * Provides a value for {@link #userId}.
     *
     * @param userId the new value to set
     */
    public void setUserId(UserIdData userId) {
        this.userId = userId;
    }

    /**
     * Provides a value for {@link #virtual}.
     *
     * @param virtual the new value to set
     */
    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }
}
