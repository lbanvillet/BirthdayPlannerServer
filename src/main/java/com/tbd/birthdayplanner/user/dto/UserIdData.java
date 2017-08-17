/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user.dto;

import com.tbd.birthdayplanner.common.ResourceData;
import com.tbd.birthdayplanner.common.ResourceIdentityProperties;
import com.tbd.birthdayplanner.validation.ValidKey;

/**
 * ID data that represents minimum set of fields that uniquely identify a user.
 *
 * @author lb185112
 */
@ResourceIdentityProperties(resource = "user", key = "phone")
public class UserIdData implements ResourceData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The user phone.
     */
    @ValidKey
    private String phone;

    /**
     * Retrieves the value for {@link #phone}.
     *
     * @return the current value
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Provides a value for {@link #phone}.
     *
     * @param phone the new value to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
