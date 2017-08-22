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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbd.birthdayplanner.dto.gift.GetGiftCommentsResponse;
import com.tbd.birthdayplanner.dto.gift.GiftBasicData;
import com.tbd.birthdayplanner.dto.gift.GiftCommentBasicData;
import com.tbd.birthdayplanner.dto.gift.GiftCommentIdData;
import com.tbd.birthdayplanner.dto.gift.GiftIdData;
import com.tbd.birthdayplanner.dto.gift.GiftViewData;
import com.tbd.birthdayplanner.dto.gift.UpdateGiftCommentRequest;
import com.tbd.birthdayplanner.dto.user.UserIdData;
import com.tbd.birthdayplanner.exception.BusinessException;
import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.exception.UnauthorizedException;
import com.tbd.birthdayplanner.user.User;
import com.tbd.birthdayplanner.user.UserDomainRegistry;

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
     * Add a comment.
     *
     * @param giftId the gift to update
     * @param comment the comment to add
     */
    @PostMapping(value = "/{id}/comments")
    public void addComment(GiftIdData giftId, @RequestBody GiftCommentBasicData comment) {
        Gift gift = giftDomainRegistry.get(giftId);
        User author = userDomainRegistry.get(comment.getAuthor());
        gift.getComments().add(new GiftComment(comment.getComment(), author));
        giftDomainRegistry.save(gift);
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
     * Retrieves the gift comments.
     *
     * @param giftId the gift to get comments of
     * @return the gift comments
     */
    @GetMapping(value = "/{id}/comments")
    public GetGiftCommentsResponse getComments(GiftIdData giftId) {
        Gift gift = giftDomainRegistry.get(giftId);
        return mapper.map(gift, GetGiftCommentsResponse.class);
    }

    /**
     * Remove a comment.
     *
     * @param giftId the gift to update
     * @param userId the user that removes
     * @param commentId the comment to remove
     */
    @DeleteMapping(value = "/{id}/comments/{phone}")
    public void removeComment(GiftIdData giftId, UserIdData userId, @RequestBody GiftCommentIdData commentId) {
        Gift gift = giftDomainRegistry.get(giftId);

        GiftComment commentToRemove = null;
        for (GiftComment comment : gift.getComments()) {
            if (commentId.getId().longValue() == comment.getId()) {
                commentToRemove = comment;
            }
        }

        if (null == commentToRemove) {
            throw new ResourceDoesNotExistException("The comment with the id '" + commentId.getId()
                    + "' is not in the comment list of the gift with the id '" + giftId.getId() + "'.");
        }

        if (!userId.getPhone().equals(commentToRemove.getAuthor().getPhone())) {
            throw new UnauthorizedException("The user with the phone '" + userId.getPhone()
                    + "' is not the author of this comment and, therefore, he cannot remove it.");
        }

        gift.getComments().remove(commentToRemove);
        giftDomainRegistry.save(gift);
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
     * Updates a gift.
     *
     * @param giftId the gift to update
     * @param gift the data to put
     */
    @PutMapping(value = "/{id}")
    public void update(GiftIdData giftId, @RequestBody GiftBasicData gift) {
        Gift giftToUpdate = giftDomainRegistry.get(giftId);
        giftToUpdate.setName(gift.getName());
        giftToUpdate.setDetail(gift.getDetail());
        if (null != gift.getBuyer()) {
            User buyer = userDomainRegistry.get(gift.getBuyer());
            giftToUpdate.setBuyer(buyer);
        }
        giftToUpdate.setCollected(gift.isCollected());
        giftDomainRegistry.save(giftToUpdate);
    }

    /**
     * Update a comment.
     *
     * @param giftId the gift to update
     * @param userId the user that updates
     * @param commentData the comment to update
     */
    @PutMapping(value = "/{id}/comments/{phone}")
    public void updateComment(GiftIdData giftId, UserIdData userId, @RequestBody UpdateGiftCommentRequest commentData) {
        Gift gift = giftDomainRegistry.get(giftId);

        GiftComment commentToUpdate = null;
        for (GiftComment comment : gift.getComments()) {
            if (commentData.getCommentId().getId().longValue() == comment.getId()) {
                commentToUpdate = comment;
            }
        }

        if (null == commentToUpdate) {
            throw new ResourceDoesNotExistException("The comment with the id '" + commentData.getCommentId().getId()
                    + "' is not in the comment list of the gift with the id '" + giftId.getId() + "'.");
        }

        if (!userId.getPhone().equals(commentToUpdate.getAuthor().getPhone())) {
            throw new UnauthorizedException("The user with the phone '" + userId.getPhone()
                    + "' is not the author of this comment and, therefore, he cannot udpate it.");
        }

        commentToUpdate.setComment(commentData.getComment());
        giftDomainRegistry.save(gift);
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
