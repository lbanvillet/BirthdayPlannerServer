/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for performing CRUD operations on the plan table.
 *
 * @author lb185112
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // Empty
}
