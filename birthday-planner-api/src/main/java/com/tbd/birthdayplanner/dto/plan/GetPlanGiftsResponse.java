/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.plan;

import java.io.Serializable;
import java.util.List;

import com.tbd.birthdayplanner.dto.gift.GiftBasicViewData;

/**
 * Contains the plan's gifts.
 *
 * @author lb185112
 */
public class GetPlanGiftsResponse implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The plan's gifts.
     */
    private List<GiftBasicViewData> gifts;

    /**
     * Retrieves the value for {@link #gifts}.
     *
     * @return the current value
     */
    public List<GiftBasicViewData> getGifts() {
        return gifts;
    }

    /**
     * Provides a value for {@link #gifts}.
     *
     * @param gifts the new value to set
     */
    public void setGifts(List<GiftBasicViewData> gifts) {
        this.gifts = gifts;
    }
}
