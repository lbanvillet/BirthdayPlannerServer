/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user.dto;

import java.util.List;

/**
 * Defines the data for a user.
 *
 * @author lb185112
 */
public class UserData extends UserBasicData {

    /**
     * Default serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The user likes.
     */
    private List<String> likes;

    /**
     * The user dislikes.
     */
    private List<String> dislikes;

    /**
     * The identifiers of the followed users.
     */
    private List<UserIdData> followedUsers;

    /**
     * Retrieves the value for {@link #dislikes}.
     *
     * @return the current value
     */
    public List<String> getDislikes() {
        return dislikes;
    }

    /**
     * Retrieves the value for {@link #followedUsers}.
     *
     * @return the current value
     */
    public List<UserIdData> getFollowedUsers() {
        return followedUsers;
    }

    /**
     * Retrieves the value for {@link #likes}.
     *
     * @return the current value
     */
    public List<String> getLikes() {
        return likes;
    }

    /**
     * Provides a value for {@link #dislikes}.
     *
     * @param dislikes the new value to set
     */
    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }

    /**
     * Provides a value for {@link #followedUsers}.
     *
     * @param followedUsers the new value to set
     */
    public void setFollowedUsers(List<UserIdData> followedUsers) {
        this.followedUsers = followedUsers;
    }

    /**
     * Provides a value for {@link #likes}.
     *
     * @param likes the new value to set
     */
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
}
