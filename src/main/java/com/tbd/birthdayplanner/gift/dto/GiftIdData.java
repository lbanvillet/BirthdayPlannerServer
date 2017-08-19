/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Contains the gift name.
 *
 * @author lb185112
 */
public class GiftIdData implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name.
     */
    @NotEmpty
    private String name;

    /**
     * Retrieves the value for {@link #name}.
     *
     * @return the current value
     */
    public String getName() {
        return name;
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
