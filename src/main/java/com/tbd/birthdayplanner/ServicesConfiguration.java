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

import com.tbd.birthdayplanner.gift.GiftController;
import com.tbd.birthdayplanner.gift.GiftDomainRegistry;
import com.tbd.birthdayplanner.gift.GiftRepository;
import com.tbd.birthdayplanner.planner.PlannerController;
import com.tbd.birthdayplanner.planner.PlannerDomainRegistry;
import com.tbd.birthdayplanner.planner.PlannerRepository;
import com.tbd.birthdayplanner.user.UserController;
import com.tbd.birthdayplanner.user.UserDomainRegistry;
import com.tbd.birthdayplanner.user.UserRepository;

/**
 * Configuration for the services provided by the application.
 *
 * @author lb185112
 */
@Configuration
@EnableTransactionManagement
public class ServicesConfiguration {

    /**
     * Creates the gift controller.
     *
     * @param mapper used for mapping between DTO and DAO objects
     * @param userDomainRegistry the user domain registry
     * @param giftDomainRegistry the gift domain registry
     * @return the gift controller
     */
    @Bean
    public GiftController giftController(Mapper mapper, UserDomainRegistry userDomainRegistry, GiftDomainRegistry giftDomainRegistry) {
        return new GiftController(mapper, userDomainRegistry, giftDomainRegistry);
    }

    /**
     * Creates the gift domain registry.
     *
     * @param giftRepository the gift repository
     * @return the gift registry
     */
    @Bean
    public GiftDomainRegistry giftDomainRegistry(GiftRepository giftRepository) {
        return new GiftDomainRegistry(giftRepository);
    }

    /**
     * Creates the planner controller.
     *
     * @param mapper used for mapping between DTO and DAO objects
     * @param userDomainRegistry the user domain registry
     * @param plannerDomainRegistry the planner domain registry
     * @param giftDomainRegistry the gift domain registry
     * @return the planner controller
     */
    @Bean
    public PlannerController plannerController(Mapper mapper, UserDomainRegistry userDomainRegistry,
            PlannerDomainRegistry plannerDomainRegistry, GiftDomainRegistry giftDomainRegistry) {
        return new PlannerController(mapper, userDomainRegistry, plannerDomainRegistry, giftDomainRegistry);
    }

    /**
     * Creates the planner domain registry.
     *
     * @param plannerRepository the planner repository
     * @return the planner registry
     */
    @Bean
    public PlannerDomainRegistry plannerDomainRegistry(PlannerRepository plannerRepository) {
        return new PlannerDomainRegistry(plannerRepository);
    }

    /**
     * Creates the user controller.
     *
     * @param mapper used for mapping between DTO and DAO objects
     * @param userDomainRegistry the user domain registry
     * @return the user controller
     */
    @Bean
    public UserController userController(Mapper mapper, UserDomainRegistry userDomainRegistry) {
        return new UserController(mapper, userDomainRegistry);
    }

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
}