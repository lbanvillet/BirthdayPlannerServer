/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for performing CRUD operations on the gift table.
 *
 * @author lb185112
 */
@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
    // Empty
}
