/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for performing CRUD operations on the user table.
 *
 * @author lb185112
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Reusable phone parameter.
     */
    String PHONE_PARAM = "phone";

    /**
     * Retrieves a {@link User} by its unique phone.
     *
     * @param phone the phone of the user to retrieve
     * @return the user which matches the phone or <code>null</code> if it does not exist
     */
    @Query("SELECT u FROM User u where u.phone = :phone")
    User findByPhone(@Param(PHONE_PARAM) String phone);
}
