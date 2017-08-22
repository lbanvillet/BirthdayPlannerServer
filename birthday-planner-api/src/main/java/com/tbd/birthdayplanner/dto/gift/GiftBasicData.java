/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.gift;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.tbd.birthdayplanner.dto.user.UserIdData;

/**
 * Defines the basic data for a gift.
 *
 * @author lb185112
 */
public class GiftBasicData implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name.
     */
    @NotNull
    private String name;

    /**
     * The detail.
     */
    private String detail;

    /**
     * The buyer.
     */
    private UserIdData buyer;

    /**
     * The author.
     */
    private UserIdData author;

    /**
     * Whether the gift has been collected or not.
     */
    private boolean collected;

    /**
     * Retrieves the value for {@link #author}.
     *
     * @return the current value
     */
    public UserIdData getAuthor() {
        return author;
    }

    /**
     * Retrieves the value for {@link #buyer}.
     *
     * @return the current value
     */
    public UserIdData getBuyer() {
        return buyer;
    }

    /**
     * Retrieves the value for {@link #detail}.
     *
     * @return the current value
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Retrieves the value for {@link #name}.
     *
     * @return the current value
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the value for {@link #collected}.
     *
     * @return the current value
     */
    public boolean isCollected() {
        return collected;
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
     * Provides a value for {@link #buyer}.
     *
     * @param buyer the new value to set
     */
    public void setBuyer(UserIdData buyer) {
        this.buyer = buyer;
    }

    /**
     * Provides a value for {@link #collected}.
     *
     * @param collected the new value to set
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    /**
     * Provides a value for {@link #detail}.
     *
     * @param detail the new value to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Provides a value for {@link #name}.
     *
     * @param name the new value to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
