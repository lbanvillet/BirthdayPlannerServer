/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tbd.birthdayplanner.gift.Gift;
import com.tbd.birthdayplanner.user.User;

/**
 * Data access object providing metadata about plans.
 *
 * @author lb185112
 */
@Entity
@Table(name = "bp_plan")
public class Plan implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sequence generator for the plan table.
     */
    private static final String PLAN_SEQUENCE = "seq_plan";

    /**
     * The plan id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = PLAN_SEQUENCE)
    @SequenceGenerator(name = PLAN_SEQUENCE, sequenceName = PLAN_SEQUENCE, allocationSize = 1)
    private long id;

    /**
     * The guy we celebrate the birthday of.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "birthday_guy_id")
    private User birthdayGuy;

    /**
     * The list of participants.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    private List<Participation> participants = new ArrayList<>();

    /**
     * The list of gifts.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    private List<Gift> gifts = new ArrayList<>();

    /**
     * Whether the plan's gift list has been validated or not.
     */
    @Column(name = "gift_list_validated")
    private boolean giftListValidated = false;

    /**
     * Whether the plan is completed or not.
     */
    @Column(name = "event_completed")
    private boolean eventCompleted = false;

    /**
     * Initializes an instance of <code>Plan</code> with the default data.
     */
    public Plan() {
        super();
    }

    /**
     * Initializes an instance of <code>Plan</code> with the provided data.
     *
     * @param birthdayGuy the guy we celebrate the birthday of
     */
    public Plan(User birthdayGuy) {
        super();
        this.birthdayGuy = birthdayGuy;
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
     * Retrieves the value for {@link #gifts}.
     *
     * @return the current value
     */
    public List<Gift> getGifts() {
        return gifts;
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
     * Retrieves the value for {@link #participants}.
     *
     * @return the current value
     */
    public List<Participation> getParticipants() {
        return participants;
    }

    /**
     * Retrieves the value for {@link #eventCompleted}.
     *
     * @return the current value
     */
    public boolean isEventCompleted() {
        return eventCompleted;
    }

    /**
     * Retrieves the value for {@link #giftListValidated}.
     *
     * @return the current value
     */
    public boolean isGiftListValidated() {
        return giftListValidated;
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
     * Provides a value for {@link #eventCompleted}.
     *
     * @param eventCompleted the new value to set
     */
    public void setEventCompleted(boolean eventCompleted) {
        this.eventCompleted = eventCompleted;
    }

    /**
     * Provides a value for {@link #giftListValidated}.
     *
     * @param giftListValidated the new value to set
     */
    public void setGiftListValidated(boolean giftListValidated) {
        this.giftListValidated = giftListValidated;
    }

    /**
     * Provides a value for {@link #gifts}.
     *
     * @param gifts the new value to set
     */
    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
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
     * Provides a value for {@link #participants}.
     *
     * @param participants the new value to set
     */
    public void setParticipants(List<Participation> participants) {
        this.participants = participants;
    }
}
