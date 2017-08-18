/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner.dto;

import java.io.Serializable;

import com.tbd.birthdayplanner.user.dto.UserIdData;

/**
 * Defines the basic data for a planner.
 *
 * @author lb185112
 */
public class PlannerBasicData implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The guy we celebrate the birthday of.
     */
    private UserIdData birthdayGuy;

    /**
     * The author of the event.
     */
    private UserIdData author;

    /**
     * Retrieves the value for {@link #author}.
     *
     * @return the current value
     */
    public UserIdData getAuthor() {
        return author;
    }

    /**
     * Retrieves the value for {@link #birthdayGuy}.
     *
     * @return the current value
     */
    public UserIdData getBirthdayGuy() {
        return birthdayGuy;
    }

    /**
     * Provides a value for {@link #author}.
     *
     * @param author the new value to set
     */
    public void setAuthor(UserIdData author) {
        this.author = author;
    }

    /**
     * Provides a value for {@link #birthdayGuy}.
     *
     * @param birthdayGuy the new value to set
     */
    public void setBirthdayGuy(UserIdData birthdayGuy) {
        this.birthdayGuy = birthdayGuy;
    }
}
