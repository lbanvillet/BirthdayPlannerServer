/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner;

import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tbd.birthdayplanner.user.UserDomainRegistry;
import com.tbd.birthdayplanner.user.UserRepository;
import com.tbd.birthdayplanner.user.UserService;

/**
 * Configuration for the services provided by the application.
 *
 * @author lb185112
 */
@Configuration
@EnableTransactionManagement
public class ServicesConfiguration {

    /**
     * Creates the user domain registry.
     *
     * @param userRepository the user repository
     * @return the user registry
     */
    @Bean
    public UserDomainRegistry userDomainRegistry(UserRepository userRepository) {
        return new UserDomainRegistry(userRepository);
    }

    /**
     * Creates the user service.
     *
     * @param mapper used for mapping between DTO and DAO objects
     * @param userDomainRegistry the product domain registry
     * @return the user service
     */
    @Bean
    public UserService userService(Mapper mapper, UserDomainRegistry userDomainRegistry) {
        return new UserService(mapper, userDomainRegistry);
    }
}