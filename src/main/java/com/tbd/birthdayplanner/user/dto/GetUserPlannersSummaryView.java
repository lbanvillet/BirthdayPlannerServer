/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Contains a summarized view of the user's planners.
 *
 * @author lb185112
 */
public class GetUserPlannersSummaryView implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The guy we celebrate the birthday of.
     */
    private List<UserPlannerSummaryViewData> planners;

    /**
     * Retrieves the value for {@link #planners}.
     *
     * @return the current value
     */
    public List<UserPlannerSummaryViewData> getPlanners() {
        return planners;
    }

    /**
     * Provides a value for {@link #planners}.
     *
     * @param planners the new value to set
     */
    public void setPlanners(List<UserPlannerSummaryViewData> planners) {
        this.planners = planners;
    }
}
