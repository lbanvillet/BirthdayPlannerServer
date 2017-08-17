/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.user;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.hsqldb.lib.StringUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbd.birthdayplanner.exception.ResourceAlreadyExistsException;
import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.user.dto.CreateUserRequest;
import com.tbd.birthdayplanner.user.dto.UserBasicData;
import com.tbd.birthdayplanner.user.dto.UserFollowRequest;
import com.tbd.birthdayplanner.user.dto.UserIdData;
import com.tbd.birthdayplanner.user.dto.UserLikeRequest;
import com.tbd.birthdayplanner.user.dto.UserViewData;
import com.tbd.birthdayplanner.util.RandomString;

/**
 * Service that allows users to manage products.
 *
 * @author lb185112
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Used for mapping between DTO and DAO objects.
     */
    @Resource
    private Mapper mapper;

    /**
     * Registry used to interact with {@link User} objects in the underlying data store.
     */
    @Resource
    private UserDomainRegistry userDomainRegistry;

    /**
     * Initializes an instance of <code>UserController</code> with the provided data.
     *
     * @param mapper - {@link #mapper}
     * @param userDomainRegistry - {@link #userDomainRegistry}
     */
    public UserController(Mapper mapper, UserDomainRegistry userDomainRegistry) {
        this.mapper = mapper;
        this.userDomainRegistry = userDomainRegistry;
    }

    /**
     * Add a user dislike.
     *
     * @param userId the user to update
     * @param request the dislike to add
     */
    @PostMapping(value = "/{phone}/dislikes")
    public void addDislike(UserIdData userId, @RequestBody UserLikeRequest request) {
        User user = userDomainRegistry.get(userId);
        if (!user.getDislikes().contains(request.getObject())) {
            user.getDislikes().add(request.getObject());
        } else {
            throw new ResourceAlreadyExistsException(
                    "'" + request.getObject() + "' is already a dislike for the user with phone '" + userId.getPhone() + "'.");
        }
        userDomainRegistry.save(user);
    }

    /**
     * Add a user to follow. If this user's phone does not exist in the database, create a virtual {@link User}.
     *
     * @param userId the user to update
     * @param request the request containing the details of the user to follow
     */
    @PostMapping(value = "/{phone}/followed-users")
    public void addFollowedUser(UserIdData userId, @RequestBody UserFollowRequest request) {
        User user = userDomainRegistry.get(userId);
        User userToFollow = request.getUserId() == null || StringUtils.isEmpty(request.getUserId().getPhone()) ? null
                : userDomainRegistry.find(request.getUserId());
        if (null == userToFollow) {
            userToFollow = mapper.map(request, User.class);
            if (StringUtil.isEmpty(userToFollow.getPhone())) {
                userToFollow.setPhone("VIRTUAL-" + new RandomString(30).nextString());
            }
            userToFollow.setVirtual(true);
            userDomainRegistry.save(userToFollow);
        }
        user.getFollowedUsers().add(userToFollow);
        userDomainRegistry.save(user);
    }

    /**
     * Add a user like.
     *
     * @param userId the user to update
     * @param request the like to add
     */
    @PostMapping(value = "/{phone}/likes")
    public void addLike(UserIdData userId, @RequestBody UserLikeRequest request) {
        User user = userDomainRegistry.get(userId);
        if (!user.getLikes().contains(request.getObject())) {
            user.getLikes().add(request.getObject());
        } else {
            throw new ResourceAlreadyExistsException(
                    "'" + request.getObject() + "' is already a like for the user with phone '" + userId.getPhone() + "'.");
        }
        userDomainRegistry.save(user);
    }

    /**
     * Creates a user. Used at registration time.
     *
     * @param request the user to create
     */
    @PostMapping
    public void create(@RequestBody CreateUserRequest request) {
        User existingUser = userDomainRegistry.find(request.getUserId());
        if (null != existingUser) {
            if (existingUser.isVirtual()) {
                existingUser.setBirthDate(request.getBirthDate());
                existingUser.setName(request.getName());
                existingUser.setVirtual(false);
                userDomainRegistry.save(existingUser);
            } else {
                throw new ResourceAlreadyExistsException(UserIdData.class, request.getUserId().getPhone());
            }
        } else {
            userDomainRegistry.save(mapper.map(request, User.class));
        }
    }

    /**
     * Deletes a user.
     *
     * @param userId the user to delete
     */
    @DeleteMapping(value = "/{phone}")
    public void delete(UserIdData userId) {
        userDomainRegistry.delete(userId);
    }

    /**
     * Retrieves a user.
     *
     * @param userId the user to get
     * @return the user
     */
    @GetMapping(value = "/{phone}")
    public UserViewData get(UserIdData userId) {
        User user = userDomainRegistry.get(userId);
        return mapper.map(user, UserViewData.class);
    }

    /**
     * Remove a user dislike.
     *
     * @param userId the user to update
     * @param request the dislike to remove
     */
    @DeleteMapping(value = "/{phone}/dislikes")
    public void removeDislike(UserIdData userId, @RequestBody UserLikeRequest request) {
        User user = userDomainRegistry.get(userId);
        if (!user.getDislikes().remove(request.getObject())) {
            throw new ResourceDoesNotExistException(
                    "'" + request.getObject() + "' is not a dislike for the user with phone '" + userId.getPhone() + "'.");
        }
        userDomainRegistry.save(user);
    }

    /**
     * Remove a user like.
     *
     * @param userId the user to update
     * @param request the like to remove
     */
    @DeleteMapping(value = "/{phone}/likes")
    public void removeLike(UserIdData userId, @RequestBody UserLikeRequest request) {
        User user = userDomainRegistry.get(userId);
        if (!user.getLikes().remove(request.getObject())) {
            throw new ResourceDoesNotExistException(
                    "'" + request.getObject() + "' is not a like for the user with phone '" + userId.getPhone() + "'.");
        }
        userDomainRegistry.save(user);
    }

    /**
     * Remove a followed user.
     *
     * @param userId the user to update
     * @param userToUnfollowId the identifier of the user to stop following
     */
    @DeleteMapping(value = "/{phone}/followed-users")
    public void unfollowUser(UserIdData userId, @RequestBody UserIdData userToUnfollowId) {
        User user = userDomainRegistry.get(userId);
        User userToUnfollow = userDomainRegistry.get(userToUnfollowId);
        if (!user.getFollowedUsers().remove(userToUnfollow)) {
            throw new ResourceDoesNotExistException("The user with the phone '" + userToUnfollowId.getPhone()
                    + "' is not followed by the user with the phone '" + userId.getPhone() + "'.");
        }
        userDomainRegistry.save(user);
    }

    /**
     * Updates the user basic data.
     *
     * @param userId the user to update
     * @param request the data to update
     */
    @PutMapping(value = "/{phone}")
    public void update(UserIdData userId, @RequestBody UserBasicData request) {
        User user = userDomainRegistry.get(userId);
        user.setBirthDate(request.getBirthDate());
        user.setName(request.getName());
        userDomainRegistry.save(user);
    }
}
