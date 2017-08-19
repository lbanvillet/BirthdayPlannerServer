/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbd.birthdayplanner.exception.BusinessException;
import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.exception.UnauthorizedException;
import com.tbd.birthdayplanner.gift.Gift;
import com.tbd.birthdayplanner.gift.dto.GiftBasicData;
import com.tbd.birthdayplanner.gift.dto.GiftIdData;
import com.tbd.birthdayplanner.participation.Participation;
import com.tbd.birthdayplanner.planner.dto.CreatePlannerRequest;
import com.tbd.birthdayplanner.planner.dto.GetPlannerGiftsResponse;
import com.tbd.birthdayplanner.planner.dto.GetPlannerParticipantsResponse;
import com.tbd.birthdayplanner.planner.dto.PlannerBasicViewData;
import com.tbd.birthdayplanner.planner.dto.PlannerIdData;
import com.tbd.birthdayplanner.planner.dto.RemoveGiftRequest;
import com.tbd.birthdayplanner.user.User;
import com.tbd.birthdayplanner.user.UserDomainRegistry;
import com.tbd.birthdayplanner.user.dto.UserIdData;

/**
 * Service that allows consumers to manage planners.
 *
 * @author lb185112
 */
@RestController
@RequestMapping("/planners")
public class PlannerController {

    /**
     * Retrieves the gift from the given planner with the given {@link GiftIdData}.
     *
     * @param planner the planner to look into
     * @param giftId the gift to look for
     * @return the {@link Gift} entity
     */
    private static Gift getGiftFromEntity(Planner planner, GiftIdData giftId) {
        for (Gift gift : planner.getGifts()) {
            if (giftId.getName().equals(gift.getName())) {
                return gift;
            }
        }
        return null;
    }

    /**
     * Retrieves the participant from the given planner with the given {@link UserIdData}.
     *
     * @param planner the planner to look into
     * @param participantId the participant to look for
     * @return the {@link Participation} entity
     */
    private static Participation getParticipantFromEntity(Planner planner, UserIdData participantId) {
        for (Participation participant : planner.getParticipants()) {
            if (participantId.getPhone().equals(participant.getParticipant().getPhone())) {
                return participant;
            }
        }
        return null;
    }

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
     * Registry used to interact with {@link Planner} objects in the underlying data store.
     */
    @Resource
    private PlannerDomainRegistry plannerDomainRegistry;

    /**
     * Initializes an instance of <code>PlannerController</code> with the provided data.
     *
     * @param mapper - {@link #mapper}
     * @param userDomainRegistry - {@link #userDomainRegistry}
     * @param plannerDomainRegistry - {@link #plannerDomainRegistry}
     */
    public PlannerController(Mapper mapper, UserDomainRegistry userDomainRegistry, PlannerDomainRegistry plannerDomainRegistry) {
        this.mapper = mapper;
        this.userDomainRegistry = userDomainRegistry;
        this.plannerDomainRegistry = plannerDomainRegistry;
    }

    /**
     * Add a gift.
     *
     * @param plannerId the planner to update
     * @param gift the gift to add
     */
    @PostMapping(value = "/{id}/gifts")
    public void addGift(PlannerIdData plannerId, @RequestBody GiftBasicData gift) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        User author = userDomainRegistry.get(gift.getAuthor());
        if (null != getGiftFromEntity(planner, gift)) {
            throw new BusinessException("The gift with the name '" + gift.getName()
                    + "' is already part of the gift list for the planner with id '" + plannerId.getId() + "'.");
        }
        planner.getGifts().add(new Gift(gift.getName(), gift.getDetail(), author));
        plannerDomainRegistry.save(planner);
    }

    /**
     * Add a participant.
     *
     * @param plannerId the planner to update
     * @param participantId the participant to add
     */
    @PostMapping(value = "/{id}/participants/{phone}")
    public void addParticipant(PlannerIdData plannerId, UserIdData participantId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        if (null != getParticipantFromEntity(planner, participantId)) {
            throw new BusinessException("The participant with the phone '" + participantId.getPhone()
                    + "' is already part of the participant list for the planner with id '" + plannerId.getId() + "'.");
        }
        User user = userDomainRegistry.get(participantId);
        planner.getParticipants().add(new Participation(user, planner, false, false));
        plannerDomainRegistry.save(planner);
    }

    /**
     * Completes the planner.
     *
     * @param plannerId the planner to complete
     */
    @PostMapping(value = "/{id}/complete")
    public void complete(PlannerIdData plannerId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        planner.setEventCompleted(true);
        plannerDomainRegistry.save(planner);
    }

    /**
     * Creates a planner.
     *
     * @param request the planner to create
     */
    @PostMapping
    public void create(@RequestBody CreatePlannerRequest request) {
        Planner planner = new Planner(userDomainRegistry.get(request.getBirthdayGuy()));
        User author = userDomainRegistry.get(request.getAuthor());
        planner.getParticipants().add(new Participation(author, planner, true, true));
        plannerDomainRegistry.save(planner);
    }

    /**
     * Deletes a planner.
     *
     * @param plannerId the planner to delete
     */
    @DeleteMapping(value = "/{id}")
    public void delete(PlannerIdData plannerId) {
        plannerDomainRegistry.delete(plannerId);
    }

    /**
     * Retrieves a planner.
     *
     * @param plannerId the planner to get participants of
     * @return the planner's participants
     */
    @GetMapping(value = "/{id}")
    public PlannerBasicViewData get(PlannerIdData plannerId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        return mapper.map(planner, PlannerBasicViewData.class);
    }

    /**
     * Retrieves the planner gifts.
     *
     * @param plannerId the planner to get
     * @return the planner gifts
     */
    @GetMapping(value = "/{id}/gifts")
    public GetPlannerGiftsResponse getGifts(PlannerIdData plannerId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        return mapper.map(planner, GetPlannerGiftsResponse.class);
    }

    /**
     * Retrieves the planner participants.
     *
     * @param plannerId the planner to get
     * @return the planner participants
     */
    @GetMapping(value = "/{id}/participants")
    public GetPlannerParticipantsResponse getParticipants(PlannerIdData plannerId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        return mapper.map(planner, GetPlannerParticipantsResponse.class);
    }

    /**
     * Remove a gift.
     *
     * @param plannerId the planner to update
     * @param request the request containing details to remove the gift from the planner
     */
    @DeleteMapping(value = "/{id}/gifts")
    public void removeGift(PlannerIdData plannerId, @RequestBody RemoveGiftRequest request) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        Gift giftToRemove = getGiftFromEntity(planner, request);

        if (null == giftToRemove) {
            throw new ResourceDoesNotExistException("The gift with the name '" + request.getName()
                    + "' is not a gift of the planner with the id '" + plannerId.getId() + "'.");
        }

        if (!giftToRemove.getAuthor().getPhone().equals(request.getUserRemoving().getPhone())) {
            throw new UnauthorizedException("The user with the phone '" + request.getUserRemoving().getPhone()
                    + "' is not the author of this gift and, therefore, he cannot remove it.");
        }

        planner.getGifts().remove(giftToRemove);
        plannerDomainRegistry.save(planner);
    }

    /**
     * Remove a participant.
     *
     * @param plannerId the planner to update
     * @param participantId the participant to remove
     */
    @DeleteMapping(value = "/{id}/participants/{phone}")
    public void removeParticipant(PlannerIdData plannerId, UserIdData participantId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        Participation participantToRemove = getParticipantFromEntity(planner, participantId);

        if (null == participantToRemove) {
            throw new ResourceDoesNotExistException("The user with the phone '" + participantId.getPhone()
                    + "' is not a participant of the planner with the id '" + plannerId.getId() + "'.");
        }

        if (participantToRemove.isAuthor()) {
            throw new UnauthorizedException("The user with the phone '" + participantId.getPhone()
                    + "' cannot be removed from the planner with the id '" + plannerId.getId() + "' because he is the author.");
        }

        planner.getParticipants().remove(participantToRemove);
        plannerDomainRegistry.save(planner);
    }

    /**
     * Validates the gift list.
     *
     * @param plannerId the planner to validate the gift list of
     */
    @PostMapping(value = "/{id}/validate-gift-list")
    public void validateGiftList(PlannerIdData plannerId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        planner.setGiftListValidated(true);
        plannerDomainRegistry.save(planner);
    }
}
