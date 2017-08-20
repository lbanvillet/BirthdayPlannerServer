/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Common exception class.
 *
 * @author lb185112
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    /**
     * The Serialisation id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

    /**
     * Construct the exception with a message.
     *
     * @param message the message to include in the exception.
     */
    public BusinessException(final String message) {
        super(message);
        LOGGER.warn(getMessage());
    }
}
