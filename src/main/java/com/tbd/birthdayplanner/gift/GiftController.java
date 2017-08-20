/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift;

import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbd.birthdayplanner.exception.BusinessException;
import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.exception.UnauthorizedException;
import com.tbd.birthdayplanner.gift.dto.GiftIdData;
import com.tbd.birthdayplanner.gift.dto.GiftViewData;
import com.tbd.birthdayplanner.user.User;
import com.tbd.birthdayplanner.user.UserDomainRegistry;
import com.tbd.birthdayplanner.user.dto.UserIdData;

/**
 * Service that allows consumers to manage gifts.
 *
 * @author lb185112
 */
@RestController
@RequestMapping("/gifts")
public class GiftController {

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
     * Registry used to interact with {@link Gift} objects in the underlying data store.
     */
    @Resource
    private GiftDomainRegistry giftDomainRegistry;

    /**
     * Initializes an instance of <code>GiftController</code> with the provided data.
     *
     * @param mapper - {@link #mapper}
     * @param userDomainRegistry - {@link #userDomainRegistry}
     * @param giftDomainRegistry - {@link #giftDomainRegistry}
     */
    public GiftController(Mapper mapper, UserDomainRegistry userDomainRegistry, GiftDomainRegistry giftDomainRegistry) {
        this.mapper = mapper;
        this.userDomainRegistry = userDomainRegistry;
        this.giftDomainRegistry = giftDomainRegistry;
    }

    /**
     * Add a dislike.
     *
     * @param giftId the gift to update
     * @param userId the user to add as someone that dislikes the gift (cannot be the author)
     */
    @PostMapping(value = "/{id}/dislikes/{phone}")
    public void addDislike(GiftIdData giftId, UserIdData userId) {
        addLikesOrDislikes(giftId, userId, false);
    }

    /**
     * Add a like.
     *
     * @param giftId the gift to update
     * @param userId the user to add as someone that likes the gift (cannot be the author)
     */
    @PostMapping(value = "/{id}/likes/{phone}")
    public void addLike(GiftIdData giftId, UserIdData userId) {
        addLikesOrDislikes(giftId, userId, true);
    }

    /**
     * Retrieves a gift.
     *
     * @param giftId the gift to get
     * @return the gift
     */
    @GetMapping(value = "/{id}")
    public GiftViewData get(GiftIdData giftId) {
        Gift gift = giftDomainRegistry.get(giftId);
        return mapper.map(gift, GiftViewData.class);
    }

    /**
     * Remove a dislike.
     *
     * @param giftId the gift to update
     * @param userId the user to remove
     */
    @DeleteMapping(value = "/{id}/dislikes/{phone}")
    public void removeDislike(GiftIdData giftId, UserIdData userId) {
        removeLikesOrDislikes(giftId, userId, false);
    }

    /**
     * Remove a like.
     *
     * @param giftId the gift to update
     * @param userId the user to remove
     */
    @DeleteMapping(value = "/{id}/likes/{phone}")
    public void removeLike(GiftIdData giftId, UserIdData userId) {
        removeLikesOrDislikes(giftId, userId, true);
    }

    /**
     * Add to the like or dislike list.
     *
     * @param giftId the gift to update
     * @param userId the user to add
     * @param addToLikes true if the like list has to be increased and the dislike list has to be reduced; false otherwise
     */
    private void addLikesOrDislikes(GiftIdData giftId, UserIdData userId, boolean addToLikes) {
        Gift gift = giftDomainRegistry.get(giftId);

        if (userId.getPhone().equals(gift.getAuthor().getPhone())) {
            throw new UnauthorizedException(
                    "The author with the phone '" + userId.getPhone() + "' cannot like his gift (id: '" + giftId.getId() + "').");
        }

        List<User> listToIncrease = addToLikes ? gift.getLikes() : gift.getDislikes();
        for (User existingUser : listToIncrease) {
            if (userId.getPhone().equals(existingUser.getPhone())) {
                throw new BusinessException("The user with the phone '" + userId.getPhone()
                        + "' is already part of the list for the gift with id '" + gift.getId() + "'.");
            }
        }

        User user = userDomainRegistry.get(userId);
        if (addToLikes) {
            gift.getLikes().add(user);
            gift.getDislikes().remove(user);
        } else {
            gift.getDislikes().add(user);
            gift.getLikes().remove(user);
        }

        giftDomainRegistry.save(gift);
    }

    /**
     * Remove from the like or dislike list.
     *
     * @param giftId the gift to update
     * @param userId the user to remove
     * @param removeFromLikes true if the like list has to be updated, false if the dislike list has to be updated
     */
    private void removeLikesOrDislikes(GiftIdData giftId, UserIdData userId, boolean removeFromLikes) {
        Gift gift = giftDomainRegistry.get(giftId);

        User userToRemove = null;
        List<User> listToUpdate = removeFromLikes ? gift.getLikes() : gift.getDislikes();
        for (User existingUser : listToUpdate) {
            if (userId.getPhone().equals(existingUser.getPhone())) {
                userToRemove = existingUser;
            }
        }

        if (null == userToRemove) {
            throw new ResourceDoesNotExistException("The user with the phone '" + userId.getPhone()
                    + "' is not in the (dis)like list of the gift with the id '" + giftId.getId() + "'.");
        }

        listToUpdate.remove(userToRemove);
        giftDomainRegistry.save(gift);
    }
}
