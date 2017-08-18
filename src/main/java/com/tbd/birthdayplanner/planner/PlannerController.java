/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbd.birthdayplanner.planner.dto.CreatePlannerRequest;
import com.tbd.birthdayplanner.planner.dto.PlannerIdData;
import com.tbd.birthdayplanner.planner.dto.PlannerViewData;
import com.tbd.birthdayplanner.user.User;
import com.tbd.birthdayplanner.user.UserDomainRegistry;

/**
 * Service that allows consumers to manage planners.
 *
 * @author lb185112
 */
@RestController
@RequestMapping("/planners")
public class PlannerController {

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
     * Registry used to interact with {@link Planner} objects in the underlying data store.
     */
    @Resource
    private PlannerDomainRegistry plannerDomainRegistry;

    /**
     * Initializes an instance of <code>PlannerController</code> with the provided data.
     *
     * @param mapper - {@link #mapper}
     * @param userDomainRegistry - {@link #userDomainRegistry}
     * @param plannerDomainRegistry - {@link #plannerDomainRegistry}
     */
    public PlannerController(Mapper mapper, UserDomainRegistry userDomainRegistry, PlannerDomainRegistry plannerDomainRegistry) {
        this.mapper = mapper;
        this.userDomainRegistry = userDomainRegistry;
        this.plannerDomainRegistry = plannerDomainRegistry;
    }

    /**
     * Creates a planner.
     *
     * @param request the planner to create
     */
    @PostMapping
    public void create(@RequestBody CreatePlannerRequest request) {
        Planner planner = new Planner();
        planner.setBirthdayGuy(userDomainRegistry.get(request.getBirthdayGuy()));
        planner.setAuthor(userDomainRegistry.get(request.getAuthor()));
        plannerDomainRegistry.save(planner);
    }

    /**
     * Retrieves a planner.
     *
     * @param plannerId the planner to get
     * @return the planner
     */
    @GetMapping(value = "/{id}")
    public PlannerViewData get(PlannerIdData plannerId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        return mapper.map(planner, PlannerViewData.class);
    }
}
