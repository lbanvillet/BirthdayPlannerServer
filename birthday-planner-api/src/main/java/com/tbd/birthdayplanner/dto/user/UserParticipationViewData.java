/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.user;

import java.io.Serializable;

import com.tbd.birthdayplanner.dto.plan.PlanBasicViewData;

/**
 * The details of a user's participation.
 *
 * @author lb185112
 */
public class UserParticipationViewData implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The plan (contains the birthday guy).
     */
    private PlanBasicViewData plan;

    /**
     * Whether the participant is the author of the plan or not.
     */
    private boolean isAuthor;

    /**
     * Whether the participant has approved or not.
     */
    private boolean approved;

    /**
     * Retrieves the value for {@link #plan}.
     *
     * @return the current value
     */
    public PlanBasicViewData getPlan() {
        return plan;
    }

    /**
     * Retrieves the value for {@link #approved}.
     *
     * @return the current value
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Retrieves the value for {@link #isAuthor}.
     *
     * @return the current value
     */
    public boolean isAuthor() {
        return isAuthor;
    }

    /**
     * Provides a value for {@link #approved}.
     *
     * @param approved the new value to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Provides a value for {@link #isAuthor}.
     *
     * @param isAuthor the new value to set
     */
    public void setAuthor(boolean isAuthor) {
        this.isAuthor = isAuthor;
    }

    /**
     * Provides a value for {@link #plan}.
     *
     * @param plan the new value to set
     */
    public void setPlan(PlanBasicViewData plan) {
        this.plan = plan;
    }
}
