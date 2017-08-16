/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tbd.birthdayplanner.user.DefaultUserService;
import com.tbd.birthdayplanner.user.UserService;

/**
 * Configuration for the inventory services provided by the inventory application.
 *
 * @author lb185112
 */
@Configuration
@EnableTransactionManagement
public class ServicesConfiguration {

    @Bean
    public UserService userService() {
        return new DefaultUserService();
    }
}