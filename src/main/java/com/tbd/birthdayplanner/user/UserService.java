/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 NCR Corporation
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service that allows users to manage products.
 *
 * @author lb185112
 */
@RestController
@RequestMapping("/users")
public interface UserService {

    /**
     * Creates a user.
     *
     * @param request the user to create
     */
	@RequestMapping(method = RequestMethod.POST)
    void create(UserIdData request);

    /**
     * Gets a user.
     *
     * @param userId the user to get
     */
	@RequestMapping(method = RequestMethod.GET)
    UserIdData get(UserIdData userId);
}
