/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner.dto;

import java.io.Serializable;
import java.util.List;

import com.tbd.birthdayplanner.gift.dto.GiftBasicData;

/**
 * Contains the planner's gifts.
 *
 * @author lb185112
 */
public class GetPlannerGiftsResponse implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The planner's gifts.
     */
    private List<GiftBasicData> gifts;

    /**
     * Retrieves the value for {@link #gifts}.
     *
     * @return the current value
     */
    public List<GiftBasicData> getGifts() {
        return gifts;
    }

    /**
     * Provides a value for {@link #gifts}.
     *
     * @param gifts the new value to set
     */
    public void setGifts(List<GiftBasicData> gifts) {
        this.gifts = gifts;
    }
}
