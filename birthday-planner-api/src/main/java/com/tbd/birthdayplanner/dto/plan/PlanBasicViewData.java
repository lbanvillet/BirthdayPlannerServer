/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.plan;

import java.io.Serializable;

import com.tbd.birthdayplanner.dto.user.UserBasicData;

/**
 * Defines the basic view data for a plan.
 *
 * @author lb185112
 */
public class PlanBasicViewData implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The guy we celebrate the birthday of.
     */
    private UserBasicData birthdayGuy;

    /**
     * Whether the gift list has been validated or not.
     */
    private boolean giftListValidated;

    /**
     * Whether the event has been completed or not.
     */
    private boolean eventCompleted;

    /**
     * Retrieves the value for {@link #birthdayGuy}.
     *
     * @return the current value
     */
    public UserBasicData getBirthdayGuy() {
        return birthdayGuy;
    }

    /**
     * Retrieves the value for {@link #eventCompleted}.
     *
     * @return the current value
     */
    public boolean isEventCompleted() {
        return eventCompleted;
    }

    /**
     * Retrieves the value for {@link #giftListValidated}.
     *
     * @return the current value
     */
    public boolean isGiftListValidated() {
        return giftListValidated;
    }

    /**
     * Provides a value for {@link #birthdayGuy}.
     *
     * @param birthdayGuy the new value to set
     */
    public void setBirthdayGuy(UserBasicData birthdayGuy) {
        this.birthdayGuy = birthdayGuy;
    }

    /**
     * Provides a value for {@link #eventCompleted}.
     *
     * @param isEventCompleted the new value to set
     */
    public void setEventCompleted(boolean isEventCompleted) {
        eventCompleted = isEventCompleted;
    }

    /**
     * Provides a value for {@link #giftListValidated}.
     *
     * @param isGiftListValidated the new value to set
     */
    public void setGiftListValidated(boolean isGiftListValidated) {
        giftListValidated = isGiftListValidated;
    }
}
