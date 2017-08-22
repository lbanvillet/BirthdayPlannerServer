/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.user;

/**
 * Contains like/dislike request details.
 *
 * @author lb185112
 */
public class UserFollowRequest extends UserBasicData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifier of the user provided by the client. Can be null in this situation.
     */
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
