/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 NCR Corporation
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import java.io.Serializable;

/**
 * ID data that represents minimum set of fields that uniquely identify a user.
 *
 * @author lb185112
 */
public class UserIdData implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The product name.
     */
    private String name;

    /**
     * Initializes an instance of <code>UserIdData</code> with the default data.
     */
    public UserIdData() {
    }

    /**
     * Initializes an instance of <code>UserIdData</code> with the provide data.
     *
     * @param name - {@link #name}
     */
    public UserIdData(String name) {
        super();
        this.name = name;
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
     * Provides a value for {@link #name}.
     *
     * @param name the new value to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
