/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user.dto;

import com.tbd.birthdayplanner.planner.dto.PlannerIdData;

/**
 * Summarized view data of a user's planner to display in a planner list view.
 *
 * @author lb185112
 */
public class UserPlannerSummaryViewData extends PlannerIdData {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The guy we celebrate the birthday of.
     */
    private UserBasicData birthdayGuy;

    /**
     * Retrieves the value for {@link #birthdayGuy}.
     *
     * @return the current value
     */
    public UserBasicData getBirthdayGuy() {
        return birthdayGuy;
    }

    /**
     * Provides a value for {@link #birthdayGuy}.
     *
     * @param birthdayGuy the new value to set
     */
    public void setBirthdayGuy(UserBasicData birthdayGuy) {
        this.birthdayGuy = birthdayGuy;
    }
}
