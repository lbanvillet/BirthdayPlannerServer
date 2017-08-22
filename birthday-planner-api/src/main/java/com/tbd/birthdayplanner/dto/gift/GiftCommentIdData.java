/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.dto.gift;

import javax.validation.constraints.NotNull;

import com.tbd.birthdayplanner.common.ResourceData;
import com.tbd.birthdayplanner.common.ResourceIdentityProperties;

/**
 * ID data that represents minimum set of fields that uniquely identify a gift comment.
 *
 * @author lb185112
 */
@ResourceIdentityProperties(resource = "gift comment", key = "id")
public class GiftCommentIdData implements ResourceData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The gift comment identifier.
     */
    @NotNull
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
