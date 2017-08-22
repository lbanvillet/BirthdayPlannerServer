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
 * Data access object providing metadata about a gift comment.
 *
 * @author lb185112
 */
@Entity
@Table(name = "gift_comment")
public class GiftComment implements Serializable {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sequence generator for the gift comment table.
     */
    private static final String GIFT_COMMENT_SEQUENCE = "seq_gift_comment";

    /**
     * The gift comment id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = GIFT_COMMENT_SEQUENCE)
    @SequenceGenerator(name = GIFT_COMMENT_SEQUENCE, sequenceName = GIFT_COMMENT_SEQUENCE, allocationSize = 1)
    private long id;

    /**
     * The comment.
     */
    @Column(name = "comment")
    private String comment;

    /**
     * The author.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * Initializes an instance of <code>GiftComment</code> with the default data.
     */
    public GiftComment() {
        super();
    }

    /**
     * Initializes an instance of <code>GiftComment</code> with the provided data.
     *
     * @param comment the comment
     * @param author the author
     */
    public GiftComment(String comment, User author) {
        this.comment = comment;
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
     * Retrieves the value for {@link #comment}.
     *
     * @return the current value
     */
    public String getComment() {
        return comment;
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
     * Provides a value for {@link #comment}.
     *
     * @param comment the new value to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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
