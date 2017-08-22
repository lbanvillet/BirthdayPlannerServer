/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.user;

import java.io.Serializable;
import java.util.List;

/**
 * Contains the user's participations.
 *
 * @author lb185112
 */
public class GetUserParticipationsResponse implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The user's participations.
     */
    private List<UserParticipationViewData> participations;

    /**
     * Retrieves the value for {@link #participations}.
     *
     * @return the current value
     */
    public List<UserParticipationViewData> getParticipations() {
        return participations;
    }

    /**
     * Provides a value for {@link #participations}.
     *
     * @param participations the new value to set
     */
    public void setParticipations(List<UserParticipationViewData> participations) {
        this.participations = participations;
    }
}
