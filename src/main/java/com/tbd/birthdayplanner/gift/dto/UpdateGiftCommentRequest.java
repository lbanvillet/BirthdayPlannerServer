/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift.dto;

import java.io.Serializable;

/**
 * Defines the request data for updating a gift comment.
 *
 * @author lb185112
 */
public class UpdateGiftCommentRequest implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The identifier.
     */
    private GiftCommentIdData commentId;

    /**
     * The comment.
     */
    private String comment;

    /**
     * Retrieves the value for {@link #comment}.
     *
     * @return the current value
     */
    public String getComment() {
        return comment;
    }

    /**
     * Retrieves the value for {@link #commentId}.
     *
     * @return the current value
     */
    public GiftCommentIdData getCommentId() {
        return commentId;
    }

    /**
     * Provides a value for {@link #comment}.
     *
     * @param comment the new value to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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
