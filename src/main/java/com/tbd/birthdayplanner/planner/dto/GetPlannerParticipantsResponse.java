/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Contains the planner's participants.
 *
 * @author lb185112
 */
public class GetPlannerParticipantsResponse implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The planner's participants.
     */
    private List<PlannerParticipantsViewData> participants;

    /**
     * Retrieves the value for {@link #participants}.
     *
     * @return the current value
     */
    public List<PlannerParticipantsViewData> getParticipants() {
        return participants;
    }

    /**
     * Provides a value for {@link #participants}.
     *
     * @param participants the new value to set
     */
    public void setParticipants(List<PlannerParticipantsViewData> participants) {
        this.participants = participants;
    }
}
