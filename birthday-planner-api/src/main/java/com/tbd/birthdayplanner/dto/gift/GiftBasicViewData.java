/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.gift;

/**
 * Defines the basic view data for a gift.
 *
 * @author lb185112
 */
public class GiftBasicViewData extends GiftBasicData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The identifier.
     */
    private GiftIdData giftId;

    /**
     * Retrieves the value for {@link #giftId}.
     *
     * @return the current value
     */
    public GiftIdData getGiftId() {
        return giftId;
    }

    /**
     * Provides a value for {@link #giftId}.
     *
     * @param giftId the new value to set
     */
    public void setGiftId(GiftIdData giftId) {
        this.giftId = giftId;
    }
}
