/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

/**
 * Default implementation of {@link UserService}.
 *
 * @author lb185112
 */
//@Transactional(rollbackFor = BusinessException.class)
public class DefaultUserService implements UserService {

    /**
     * Initializes an instance of <code>DefaultUserService</code> with the provided data.
     *
     */
    public DefaultUserService() {
    }

    @Override
    public void create(UserIdData request) {
        //create a user
    }

	@Override
	public UserIdData get(UserIdData userId) {
		return userId;
	}
}
