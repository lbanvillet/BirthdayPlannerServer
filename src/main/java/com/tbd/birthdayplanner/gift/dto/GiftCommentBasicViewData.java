/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift.dto;

/**
 * Defines the basic view data for a gift comment.
 *
 * @author lb185112
 */
public class GiftCommentBasicViewData extends GiftCommentBasicData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The identifier.
     */
    private GiftCommentIdData commentId;

    /**
     * Retrieves the value for {@link #commentId}.
     *
     * @return the current value
     */
    public GiftCommentIdData getCommentId() {
        return commentId;
    }

    /**
     * Provides a value for {@link #commentId}.
     *
     * @param commentId the new value to set
     */
    public void setCommentId(GiftCommentIdData commentId) {
        this.commentId = commentId;
    }
}
