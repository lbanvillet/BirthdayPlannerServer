/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tbd.birthdayplanner.user.User;

/**
 * Data access object providing metadata about planners.
 *
 * @author lb185112
 */
@Entity
@Table(name = "planner")
public class Planner implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sequence generator for the planner table.
     */
    private static final String PLANNER_SEQUENCE = "seq_planner";

    /**
     * The planner id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = PLANNER_SEQUENCE)
    @SequenceGenerator(name = PLANNER_SEQUENCE, sequenceName = PLANNER_SEQUENCE, allocationSize = 1)
    private long id;

    /**
     * The guy we celebrate the birthday of.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "birthday_guy_id")
    private User birthdayGuy;

    /**
     * The author of the event.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "author_id")
    private User author;

    /**
     * Retrieves the value for {@link #author}.
     *
     * @return the current value
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Retrieves the value for {@link #birthdayGuy}.
     *
     * @return the current value
     */
    public User getBirthdayGuy() {
        return birthdayGuy;
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
     * Provides a value for {@link #author}.
     *
     * @param author the new value to set
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Provides a value for {@link #birthdayGuy}.
     *
     * @param birthdayGuy the new value to set
     */
    public void setBirthdayGuy(User birthdayGuy) {
        this.birthdayGuy = birthdayGuy;
    }

    /**
     * Provides a value for {@link #id}.
     *
     * @param id the new value to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
