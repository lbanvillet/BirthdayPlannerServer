/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift.dto;

import com.tbd.birthdayplanner.common.ResourceData;
import com.tbd.birthdayplanner.common.ResourceIdentityProperties;
import com.tbd.birthdayplanner.validation.ValidKey;

/**
 * ID data that represents minimum set of fields that uniquely identify a gift.
 *
 * @author lb185112
 */
@ResourceIdentityProperties(resource = "gift", key = "id")
public class GiftIdData implements ResourceData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The planner identifier.
     */
    @ValidKey
    private Long id;

    /**
     * Retrieves the value for {@link #id}.
     *
     * @return the current value
     */
    public Long getId() {
        return id;
    }

    /**
     * Provides a value for {@link #id}.
     *
     * @param id the new value to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
