package com.tbd.birthdayplanner.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tbd.birthdayplanner.common.ResourceData;
import com.tbd.birthdayplanner.common.ResourceIdentityProperties;

/**
 * A generic exception used for reporting CONFLICT errors.
 *
 * @author lb185112
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends BusinessException {

    /**
     * The Serialisation id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Error message template for non-localized error message.
     */
    private static final String ERROR_MESSAGE = "The %1$s with the %2$s '%3$s' already exists.";

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceAlreadyExistsException.class);

    /**
     * Construct the exception with a {@link ResourceData}.
     *
     * @param resourceClass the {@link ResourceData} class to include in the exception
     * @param value the resource value that were not found
     */
    public ResourceAlreadyExistsException(Class<? extends ResourceData> resourceClass, String value) {
        super(String.format(ERROR_MESSAGE, resourceClass.getAnnotation(ResourceIdentityProperties.class).resource(),
                resourceClass.getAnnotation(ResourceIdentityProperties.class).key(), value));
        LOGGER.warn(getMessage());
    }

    /**
     * Construct the exception with a message.
     *
     * @param message the message to include in the exception.
     */
    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }
}
