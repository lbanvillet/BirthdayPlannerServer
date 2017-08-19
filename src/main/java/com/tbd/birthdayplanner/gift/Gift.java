/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift;

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
 * Data access object providing metadata about a gift.
 *
 * @author lb185112
 */
@Entity
@Table(name = "gift")
public class Gift implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sequence generator for the gift table.
     */
    private static final String GIFT_SEQUENCE = "seq_gift";

    /**
     * The gift id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = GIFT_SEQUENCE)
    @SequenceGenerator(name = GIFT_SEQUENCE, sequenceName = GIFT_SEQUENCE, allocationSize = 1)
    private long id;

    /**
     * The name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The detail.
     */
    @Column(name = "detail")
    private String detail;

    /**
     * The buyer.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    /**
     * The author.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * Whether the gift has been collected or not.
     */
    @Column(name = "collected")
    private boolean collected = false;

    /**
     * Initializes an instance of <code>Gift</code> with the default data.
     */
    public Gift() {
        super();
    }

    /**
     * Initializes an instance of <code>Gift</code> with the provided data.
     *
     * @param name the name
     * @param detail the detail
     * @param author the author
     */
    public Gift(String name, String detail, User author) {
        this.name = name;
        this.detail = detail;
        this.author = author;
    }

    /**
     * Retrieves the value for {@link #author}.
     *
     * @return the current value
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Retrieves the value for {@link #buyer}.
     *
     * @return the current value
     */
    public User getBuyer() {
        return buyer;
    }

    /**
     * Retrieves the value for {@link #detail}.
     *
     * @return the current value
     */
    public String getDetail() {
        return detail;
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
     * Retrieves the value for {@link #collected}.
     *
     * @return the current value
     */
    public boolean isCollected() {
        return collected;
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
     * Provides a value for {@link #buyer}.
     *
     * @param buyer the new value to set
     */
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    /**
     * Provides a value for {@link #collected}.
     *
     * @param collected the new value to set
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    /**
     * Provides a value for {@link #detail}.
     *
     * @param detail the new value to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Provides a value for {@link #name}.
     *
     * @param name the new value to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
