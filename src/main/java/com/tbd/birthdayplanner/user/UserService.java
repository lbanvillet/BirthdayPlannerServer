/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tbd.birthdayplanner.exception.ResourceAlreadyExistsException;
import com.tbd.birthdayplanner.user.dto.CreateUserRequest;
import com.tbd.birthdayplanner.user.dto.UserIdData;
import com.tbd.birthdayplanner.user.dto.UserViewData;

/**
 * Service that allows users to manage products.
 *
 * @author lb185112
 */
@RestController
@RequestMapping("/users")
public class UserService {

    /**
     * Used for mapping between DTO and DAO objects.
     */
    @Resource
    private Mapper mapper;

    /**
     * Registry used to interact with {@link User} objects in the underlying data store.
     */
    @Resource
    private UserDomainRegistry userDomainRegistry;

    /**
     * Initializes an instance of <code>UserService</code> with the provided data.
     *
     * @param mapper - {@link #mapper}
     * @param userDomainRegistry - {@link #userDomainRegistry}
     */
    public UserService(Mapper mapper, UserDomainRegistry userDomainRegistry) {
        this.mapper = mapper;
        this.userDomainRegistry = userDomainRegistry;
    }

    /**
     * Creates a user.
     *
     * @param request the user to create
     */
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody CreateUserRequest request) {
        User existingUser = userDomainRegistry.find(request.getUserId());
        if (null != existingUser) {

            // Check if a virtual user becomes real
            if (existingUser.isVirtual() && !request.isVirtual()) {
                existingUser.setVirtual(false);
                userDomainRegistry.save(existingUser);
            } else {
                throw new ResourceAlreadyExistsException(UserIdData.class, request.getUserId().getPhone());
            }
        } else {
            userDomainRegistry.save(mapper.map(request, User.class));
        }
    }

    /**
     * Retrieves a user.
     *
     * @param userId the user to get
     * @return the user
     */
    @RequestMapping(value = "/{phone}", method = RequestMethod.GET)
    public UserViewData get(UserIdData userId) {
        User user = userDomainRegistry.get(userId);
        return mapper.map(user, UserViewData.class);
    }
}
