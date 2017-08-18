/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner.dto;

/**
 * View data of the planner.
 *
 * @author lb185112
 */
public class PlannerViewData extends PlannerBasicData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Whether the gift list has been validated or not.
     */
    private boolean isGiftListValidated;

    /**
     * Whether the event has been completed or not.
     */
    private boolean isEventCompleted;

    /**
     * Retrieves the value for {@link #isEventCompleted}.
     *
     * @return the current value
     */
    public boolean isEventCompleted() {
        return isEventCompleted;
    }

    /**
     * Retrieves the value for {@link #isGiftListValidated}.
     *
     * @return the current value
     */
    public boolean isGiftListValidated() {
        return isGiftListValidated;
    }

    /**
     * Provides a value for {@link #isEventCompleted}.
     *
     * @param isEventCompleted the new value to set
     */
    public void setEventCompleted(boolean isEventCompleted) {
        this.isEventCompleted = isEventCompleted;
    }

    /**
     * Provides a value for {@link #isGiftListValidated}.
     *
     * @param isGiftListValidated the new value to set
     */
    public void setGiftListValidated(boolean isGiftListValidated) {
        this.isGiftListValidated = isGiftListValidated;
    }
}
