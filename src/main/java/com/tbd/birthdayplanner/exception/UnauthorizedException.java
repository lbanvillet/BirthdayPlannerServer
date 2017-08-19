package com.tbd.birthdayplanner.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A generic exception used for reporting UNAUTHORIZED errors.
 *
 * @author lb185112
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BusinessException {

    /**
     * The Serialisation id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthorizedException.class);

    /**
     * Construct the exception with a message.
     *
     * @param message the message to include in the exception.
     */
    public UnauthorizedException(final String message) {
        super(message);
        LOGGER.warn(getMessage());
    }
}
