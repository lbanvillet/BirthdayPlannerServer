/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan.dto;

import java.io.Serializable;

import com.tbd.birthdayplanner.user.dto.UserBasicViewData;

/**
 * The details of a user's participation.
 *
 * @author lb185112
 */
public class PlanParticipantsViewData implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The participant.
     */
    private UserBasicViewData participant;

    /**
     * The average price proposed by the participant.
     */
    private double averagePriceProposed;

    /**
     * Whether the participant is the author of the plan or not.
     */
    private boolean isAuthor;

    /**
     * Whether the participant has approved or not.
     */
    private boolean approved;

    /**
     * Retrieves the value for {@link #averagePriceProposed}.
     *
     * @return the current value
     */
    public double getAveragePriceProposed() {
        return averagePriceProposed;
    }

    /**
     * Retrieves the value for {@link #participant}.
     *
     * @return the current value
     */
    public UserBasicViewData getParticipant() {
        return participant;
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
     * Provides a value for {@link #averagePriceProposed}.
     *
     * @param averagePriceProposed the new value to set
     */
    public void setAveragePriceProposed(double averagePriceProposed) {
        this.averagePriceProposed = averagePriceProposed;
    }

    /**
     * Provides a value for {@link #participant}.
     *
     * @param participant the new value to set
     */
    public void setParticipant(UserBasicViewData participant) {
        this.participant = participant;
    }
}
