/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user.dto;

import java.io.Serializable;

/**
 * Contains like/dislike request details.
 *
 * @author lb185112
 */
public class UserLikeRequest implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The user like or dislike.
     */
    private String object;

    /**
     * Retrieves the value for {@link #object}.
     *
     * @return the current value
     */
    public String getObject() {
        return object;
    }

    /**
     * Provides a value for {@link #object}.
     *
     * @param object the new value to set
     */
    public void setObject(String object) {
        this.object = object;
    }
}
