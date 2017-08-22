/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.plan;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.tbd.birthdayplanner.dto.gift.GiftIdData;
import com.tbd.birthdayplanner.dto.user.UserIdData;

/**
 * Contains details to remove a gift from a plan.
 *
 * @author lb185112
 */
public class RemoveGiftRequest extends GiftIdData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The user removing the gift.
     */
    @NotNull
    @Valid
    private UserIdData userRemoving;

    /**
     * Retrieves the value for {@link #userRemoving}.
     *
     * @return the current value
     */
    public UserIdData getUserRemoving() {
        return userRemoving;
    }

    /**
     * Provides a value for {@link #userRemoving}.
     *
     * @param userRemoving the new value to set
     */
    public void setUserRemoving(UserIdData userRemoving) {
        this.userRemoving = userRemoving;
    }
}
