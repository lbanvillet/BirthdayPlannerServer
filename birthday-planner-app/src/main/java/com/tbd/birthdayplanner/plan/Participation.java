/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan;

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

import com.tbd.birthdayplanner.user.User;

/**
 * Data access object providing metadata about a participation.
 *
 * @author lb185112
 */
@Entity
@Table(name = "bp_participation")
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
     * The plan.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    /**
     * The average price proposed by the participant.
     */
    @Column(name = "average_price_proposed")
    private double averagePriceProposed = -1;

    /**
     * Whether the participant is the author of the plan or not.
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
     * @param plan the plan
     * @param isAuthor whether the participant is the author of the plan or not
     * @param approved whether the participant has approved or not
     */
    public Participation(User participant, Plan plan, boolean isAuthor, boolean approved) {
        super();
        this.participant = participant;
        this.plan = plan;
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
     * Retrieves the value for {@link #plan}.
     *
     * @return the current value
     */
    public Plan getPlan() {
        return plan;
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
     * Provides a value for {@link #plan}.
     *
     * @param plan the new value to set
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
