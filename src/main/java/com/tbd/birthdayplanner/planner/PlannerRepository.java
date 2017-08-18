/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for performing CRUD operations on the planner table.
 *
 * @author lb185112
 */
@Repository
public interface PlannerRepository extends JpaRepository<Planner, Long> {
    // Empty
}
