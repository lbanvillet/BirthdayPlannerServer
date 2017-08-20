/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner;

import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.planner.dto.PlannerIdData;

/**
 * Domain registry for manipulating {@link Planner} objects in the underlying data store.
 *
 * @author lb185112
 */
public class PlannerDomainRegistry {

    /**
     * Repository used to interact with the data store.
     */
    private PlannerRepository plannerRepository;

    /**
     * Initializes an instance of <code>PlannerDomainRegistry</code> with the provided data.
     *
     * @param plannerRepository a handle to the {@link PlannerRepository}
     */
    public PlannerDomainRegistry(PlannerRepository plannerRepository) {
        super();
        this.plannerRepository = plannerRepository;
    }

    /**
     * Deletes a planner from the data store.
     *
     * @param plannerId the identifier of the planner to delete
     */
    public void delete(PlannerIdData plannerId) {
        Planner plannerToDelete = get(plannerId);
        plannerRepository.delete(plannerToDelete);
    }

    /**
     * Retrieves the planner from the data store.
     *
     * @param plannerId the planner to retrieve
     * @return the found {@link Planner}, or <code>null</code> if no matching result is found
     */
    public Planner find(PlannerIdData plannerId) {
        return plannerRepository.findOne(plannerId.getId());
    }

    /**
     * Retrieves the planner from the data store. Throws a {@link ResourceDoesNotExistException} if not found.
     *
     * @param plannerId the planner to retrieve
     * @return the {@link Planner} with given identifier, if it exists
     */
    public Planner get(PlannerIdData plannerId) {
        Planner planner = find(plannerId);
        if (null == planner) {
            throw new ResourceDoesNotExistException(PlannerIdData.class, plannerId.getId().toString());
        }
        return planner;
    }

    /**
     * Saves the given planner.
     *
     * @param plannerToSave the planner to save
     * @return {@link Planner}
     */
    public Planner save(Planner plannerToSave) {
        return plannerRepository.save(plannerToSave);
    }
}
