/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.tbd.birthdayplanner.validation.ValidKey;

/**
 * Data access object providing metadata about users.
 *
 * @author lb185112
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sequence generator for the user table.
     */
    private static final String USER_SEQUENCE = "seq_user";

    /**
     * The user id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = USER_SEQUENCE)
    @SequenceGenerator(name = USER_SEQUENCE, sequenceName = USER_SEQUENCE, allocationSize = 1)
    private long id;

    /**
     * The user name.
     */
    @NaturalId
    @Column(name = "name", length = ValidKey.MAX_KEY_LENGTH)
    private String name;

    /**
     * The user birth date.
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * The user phone.
     */
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    /**
     * Whether the user is real or not.
     */
    @Column(name = "virtual")
    private boolean virtual;

    /**
     * Retrieves the value for {@link #birthDate}.
     *
     * @return the current value
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Retrieves the value for {@link #id}.
     *
     * @return the current value
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the value for {@link #name}.
     *
     * @return the current value
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the value for {@link #phone}.
     *
     * @return the current value
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Retrieves the value for {@link #virtual}.
     *
     * @return the current value
     */
    public boolean isVirtual() {
        return virtual;
    }

    /**
     * Provides a value for {@link #birthDate}.
     *
     * @param birthDate the new value to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Provides a value for {@link #id}.
     *
     * @param id the new value to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Provides a value for {@link #name}.
     *
     * @param name the new value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides a value for {@link #phone}.
     *
     * @param phone the new value to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Provides a value for {@link #virtual}.
     *
     * @param virtual the new value to set
     */
    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }
}
