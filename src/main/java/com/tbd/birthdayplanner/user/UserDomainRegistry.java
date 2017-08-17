/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.user.dto.UserIdData;

/**
 * Domain registry for manipulating {@link User} objects in the underlying data store.
 *
 * @author lb185112
 */
public class UserDomainRegistry {

    /**
     * Repository used to interact with the data store.
     */
    private UserRepository userRepository;

    /**
     * Initializes an instance of <code>UserDomainRegistry</code> with the provided data.
     *
     * @param userRepository a handle to the {@link UserRepository}
     */
    public UserDomainRegistry(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    /**
     * Deletes a user from the data store.
     *
     * @param userId the identifier of the user to delete
     */
    public void delete(UserIdData userId) {
        User userToDelete = get(userId);
        userRepository.delete(userToDelete);
    }

    /**
     * Retrieves the user from the data store.
     *
     * @param userIdData the user to retrieve
     * @return the found {@link User}, or <code>null</code> if no matching result is found
     */
    public User find(UserIdData userIdData) {
        return userRepository.findByPhone(userIdData.getPhone());
    }

    /**
     * Retrieves the user from the data store.
     *
     * @param userIdData the user to retrieve
     * @return the {@link User} with given identifier, if it exists
     */
    public User get(UserIdData userIdData) {
        User user = find(userIdData);
        if (null == user) {
            throw new ResourceDoesNotExistException(UserIdData.class, userIdData.getPhone());
        }
        return user;
    }

    /**
     * Saves the given product.
     *
     * @param userToSave the user to save
     * @return {@link User}
     */
    public User save(User userToSave) {
        return userRepository.save(userToSave);
    }
}
