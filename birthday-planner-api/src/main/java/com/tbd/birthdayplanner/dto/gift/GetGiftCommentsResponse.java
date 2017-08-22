/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.gift;

import java.io.Serializable;
import java.util.List;

/**
 * Contains the gift's comments.
 *
 * @author lb185112
 */
public class GetGiftCommentsResponse implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The gift's comments.
     */
    private List<GiftCommentBasicViewData> comments;

    /**
     * Retrieves the value for {@link #comments}.
     *
     * @return the current value
     */
    public List<GiftCommentBasicViewData> getComments() {
        return comments;
    }

    /**
     * Provides a value for {@link #comments}.
     *
     * @param comments the new value to set
     */
    public void setComments(List<GiftCommentBasicViewData> comments) {
        this.comments = comments;
    }
}
