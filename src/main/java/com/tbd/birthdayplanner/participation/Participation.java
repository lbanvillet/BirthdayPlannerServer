/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.participation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tbd.birthdayplanner.planner.Planner;
import com.tbd.birthdayplanner.user.User;

/**
 * Data access object providing metadata about a participation.
 *
 * @author lb185112
 */
@Entity
@Table(name = "participation")
public class Participation implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sequence generator for the participation table.
     */
    private static final String PARTICIPATION_SEQUENCE = "seq_participation";

    /**
     * The participation id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = PARTICIPATION_SEQUENCE)
    @SequenceGenerator(name = PARTICIPATION_SEQUENCE, sequenceName = PARTICIPATION_SEQUENCE, allocationSize = 1)
    private long id;

    /**
     * The participant.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private User participant;

    /**
     * The planner.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planner_id")
    private Planner planner;

    /**
     * The average price proposed by the participant.
     */
    @Column(name = "average_price_proposed")
    private double averagePriceProposed = -1;

    /**
     * Whether the participant is the author of the planner or not.
     */
    @Column(name = "is_author")
    private boolean isAuthor = false;

    /**
     * Whether the participant has approved or not.
     */
    @Column(name = "approved")
    private boolean approved = false;

    /**
     * Initializes an instance of <code>Participation</code> with the default data.
     */
    public Participation() {
        super();
    }

    /**
     * Initializes an instance of <code>Participation</code> with the provided data.
     *
     * @param participant the participant
     * @param planner the planner
     * @param isAuthor whether the participant is the author of the planner or not
     * @param approved whether the participant has approved or not
     */
    public Participation(User participant, Planner planner, boolean isAuthor, boolean approved) {
        super();
        this.participant = participant;
        this.planner = planner;
        this.isAuthor = isAuthor;
        this.approved = approved;
    }

    /**
     * Retrieves the value for {@link #averagePriceProposed}.
     *
     * @return the current value
     */
    public double getAveragePriceProposed() {
        return averagePriceProposed;
    }

    /**
     * Retrieves the value for {@link #participant}.
     *
     * @return the current value
     */
    public User getParticipant() {
        return participant;
    }

    /**
     * Retrieves the value for {@link #planner}.
     *
     * @return the current value
     */
    public Planner getPlanner() {
        return planner;
    }

    /**
     * Retrieves the value for {@link #approved}.
     *
     * @return the current value
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Retrieves the value for {@link #isAuthor}.
     *
     * @return the current value
     */
    public boolean isAuthor() {
        return isAuthor;
    }

    /**
     * Provides a value for {@link #approved}.
     *
     * @param approved the new value to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Provides a value for {@link #isAuthor}.
     *
     * @param isAuthor the new value to set
     */
    public void setAuthor(boolean isAuthor) {
        this.isAuthor = isAuthor;
    }

    /**
     * Provides a value for {@link #averagePriceProposed}.
     *
     * @param averagePriceProposed the new value to set
     */
    public void setAveragePriceProposed(double averagePriceProposed) {
        this.averagePriceProposed = averagePriceProposed;
    }

    /**
     * Provides a value for {@link #participant}.
     *
     * @param participant the new value to set
     */
    public void setParticipant(User participant) {
        this.participant = participant;
    }

    /**
     * Provides a value for {@link #planner}.
     *
     * @param planner the new value to set
     */
    public void setPlanner(Planner planner) {
        this.planner = planner;
    }
}
