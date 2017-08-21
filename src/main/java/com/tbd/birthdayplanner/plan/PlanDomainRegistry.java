/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan;

import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.plan.dto.PlanIdData;

/**
 * Domain registry for manipulating {@link Plan} objects in the underlying data store.
 *
 * @author lb185112
 */
public class PlanDomainRegistry {

    /**
     * Repository used to interact with the data store.
     */
    private PlanRepository planRepository;

    /**
     * Initializes an instance of <code>PlanDomainRegistry</code> with the provided data.
     *
     * @param planRepository a handle to the {@link PlanRepository}
     */
    public PlanDomainRegistry(PlanRepository planRepository) {
        super();
        this.planRepository = planRepository;
    }

    /**
     * Deletes a plan from the data store.
     *
     * @param planId the identifier of the plan to delete
     */
    public void delete(PlanIdData planId) {
        Plan planToDelete = get(planId);
        planRepository.delete(planToDelete);
    }

    /**
     * Retrieves the plan from the data store.
     *
     * @param planId the plan to retrieve
     * @return the found {@link Plan}, or <code>null</code> if no matching result is found
     */
    public Plan find(PlanIdData planId) {
        return planRepository.findOne(planId.getId());
    }

    /**
     * Retrieves the plan from the data store. Throws a {@link ResourceDoesNotExistException} if not found.
     *
     * @param planId the plan to retrieve
     * @return the {@link Plan} with given identifier, if it exists
     */
    public Plan get(PlanIdData planId) {
        Plan plan = find(planId);
        if (null == plan) {
            throw new ResourceDoesNotExistException(PlanIdData.class, planId.getId().toString());
        }
        return plan;
    }

    /**
     * Saves the given plan.
     *
     * @param planToSave the plan to save
     * @return {@link Plan}
     */
    public Plan save(Plan planToSave) {
        return planRepository.save(planToSave);
    }
}
