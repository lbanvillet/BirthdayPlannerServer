/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift.dto;

import java.io.Serializable;

import com.tbd.birthdayplanner.user.dto.UserIdData;

/**
 * Defines the basic data for a comment on a gift.
 *
 * @author lb185112
 */
public class GiftCommentBasicData implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The comment.
     */
    private String comment;

    /**
     * The author.
     */
    private UserIdData author;

    /**
     * Retrieves the value for {@link #author}.
     *
     * @return the current value
     */
    public UserIdData getAuthor() {
        return author;
    }

    /**
     * Retrieves the value for {@link #comment}.
     *
     * @return the current value
     */
    public String getComment() {
        return comment;
    }

    /**
     * Provides a value for {@link #author}.
     *
     * @param author the new value to set
     */
    public void setAuthor(UserIdData author) {
        this.author = author;
    }

    /**
     * Provides a value for {@link #comment}.
     *
     * @param comment the new value to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
