/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 NCR Corporation
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.util;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 * Class to generate a random String.
 *
 * @author lb185112
 */
public class RandomString {

    /**
     * Handle to the {@link Random}.
     */
    private final Random random;

    /**
     * Symbol to use in the random string.
     */
    private final char[] symbols;

    /**
     * The array of char for the buffer.
     */
    private final char[] buffer;

    /**
     * Initializes an instance of <code>RandomString</code> with the default data.
     *
     * @param length the random string length
     */
    public RandomString(int length) {
        random = Objects.requireNonNull(new SecureRandom());
        symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        buffer = new char[length];
    }

    /**
     * Generate a random string.
     *
     * @return the next random string
     */
    public String nextString() {
        for (int idx = 0; idx < buffer.length; ++idx) {
            buffer[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buffer);
    }
}
