/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Defines the basic data for a user.
 *
 * @author lb185112
 */
public class UserBasicData implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The user birth date.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    /**
     * The user name.
     */
    private String name;

    /**
     * Retrieves the value for {@link #birthDate}.
     *
     * @return the current value
     */
    public Date getBirthDate() {
        return birthDate;
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
     * Provides a value for {@link #birthDate}.
     *
     * @param birthDate the new value to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
