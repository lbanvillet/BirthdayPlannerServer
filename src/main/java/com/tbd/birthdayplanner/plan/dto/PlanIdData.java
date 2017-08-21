/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan.dto;

import com.tbd.birthdayplanner.common.ResourceData;
import com.tbd.birthdayplanner.common.ResourceIdentityProperties;
import com.tbd.birthdayplanner.validation.ValidKey;

/**
 * ID data that represents minimum set of fields that uniquely identify a plan.
 *
 * @author lb185112
 */
@ResourceIdentityProperties(resource = "plan", key = "id")
public class PlanIdData implements ResourceData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The plan identifier.
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
