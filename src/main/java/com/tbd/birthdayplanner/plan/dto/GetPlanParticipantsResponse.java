/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Contains the plan's participants.
 *
 * @author lb185112
 */
public class GetPlanParticipantsResponse implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The plan's participants.
     */
    private List<PlanParticipantsViewData> participants;

    /**
     * Retrieves the value for {@link #participants}.
     *
     * @return the current value
     */
    public List<PlanParticipantsViewData> getParticipants() {
        return participants;
    }

    /**
     * Provides a value for {@link #participants}.
     *
     * @param participants the new value to set
     */
    public void setParticipants(List<PlanParticipantsViewData> participants) {
        this.participants = participants;
    }
}
