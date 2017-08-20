/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift.dto;

import java.util.ArrayList;
import java.util.List;

import com.tbd.birthdayplanner.user.dto.UserIdData;

/**
 * Defines the basic view data for a gift.
 *
 * @author lb185112
 */
public class GiftViewData extends GiftBasicData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The identifier.
     */
    private GiftIdData giftId;

    /**
     * The likes.
     */
    private List<UserIdData> likes = new ArrayList<>();

    /**
     * The dislikes.
     */
    private List<UserIdData> dislikes = new ArrayList<>();

    /**
     * Retrieves the value for {@link #likes}.
     *
     * @return the current value
     */
    public List<UserIdData> getDislikes() {
        return dislikes;
    }

    /**
     * Retrieves the value for {@link #giftId}.
     *
     * @return the current value
     */
    public GiftIdData getGiftId() {
        return giftId;
    }

    /**
     * Retrieves the value for {@link #likes}.
     *
     * @return the current value
     */
    public List<UserIdData> getLikes() {
        return likes;
    }

    /**
     * Provides a value for {@link #dislikes}.
     *
     * @param dislikes the new value to set
     */
    public void setDislikes(List<UserIdData> dislikes) {
        this.dislikes = dislikes;
    }

    /**
     * Provides a value for {@link #giftId}.
     *
     * @param giftId the new value to set
     */
    public void setGiftId(GiftIdData giftId) {
        this.giftId = giftId;
    }

    /**
     * Provides a value for {@link #likes}.
     *
     * @param likes the new value to set
     */
    public void setLikes(List<UserIdData> likes) {
        this.likes = likes;
    }
}
