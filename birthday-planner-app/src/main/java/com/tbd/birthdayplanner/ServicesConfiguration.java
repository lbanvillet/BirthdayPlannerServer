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
import com.tbd.birthdayplanner.plan.PlanController;
import com.tbd.birthdayplanner.plan.PlanDomainRegistry;
import com.tbd.birthdayplanner.plan.PlanRepository;
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
     * Creates the plan controller.
     *
     * @param mapper used for mapping between DTO and DAO objects
     * @param userDomainRegistry the user domain registry
     * @param planDomainRegistry the plan domain registry
     * @param giftDomainRegistry the gift domain registry
     * @return the plan controller
     */
    @Bean
    public PlanController planController(Mapper mapper, UserDomainRegistry userDomainRegistry, PlanDomainRegistry planDomainRegistry,
            GiftDomainRegistry giftDomainRegistry) {
        return new PlanController(mapper, userDomainRegistry, planDomainRegistry, giftDomainRegistry);
    }

    /**
     * Creates the plan domain registry.
     *
     * @param planRepository the plan repository
     * @return the plan registry
     */
    @Bean
    public PlanDomainRegistry planDomainRegistry(PlanRepository planRepository) {
        return new PlanDomainRegistry(planRepository);
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