/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.plan;

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
import com.tbd.birthdayplanner.gift.GiftDomainRegistry;
import com.tbd.birthdayplanner.gift.dto.GiftBasicData;
import com.tbd.birthdayplanner.plan.dto.CreatePlanRequest;
import com.tbd.birthdayplanner.plan.dto.GetPlanGiftsResponse;
import com.tbd.birthdayplanner.plan.dto.GetPlanParticipantsResponse;
import com.tbd.birthdayplanner.plan.dto.PlanBasicViewData;
import com.tbd.birthdayplanner.plan.dto.PlanIdData;
import com.tbd.birthdayplanner.plan.dto.RemoveGiftRequest;
import com.tbd.birthdayplanner.user.User;
import com.tbd.birthdayplanner.user.UserDomainRegistry;
import com.tbd.birthdayplanner.user.dto.UserIdData;

/**
 * Service that allows consumers to manage plans.
 *
 * @author lb185112
 */
@RestController
@RequestMapping("/plans")
public class PlanController {

    /**
     * Retrieves the participant from the given plan with the given {@link UserIdData}.
     *
     * @param plan the plan to look into
     * @param participantId the participant to look for
     * @return the {@link Participation} entity
     */
    private static Participation getParticipantFromEntity(Plan plan, UserIdData participantId) {
        for (Participation participant : plan.getParticipants()) {
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
     * Registry used to interact with {@link Plan} objects in the underlying data store.
     */
    @Resource
    private PlanDomainRegistry planDomainRegistry;

    /**
     * Registry used to interact with {@link Gift} objects in the underlying data store.
     */
    @Resource
    private GiftDomainRegistry giftDomainRegistry;

    /**
     * Initializes an instance of <code>PlanController</code> with the provided data.
     *
     * @param mapper - {@link #mapper}
     * @param userDomainRegistry - {@link #userDomainRegistry}
     * @param planDomainRegistry - {@link #planDomainRegistry}
     * @param giftDomainRegistry - {@link #giftDomainRegistry}
     */
    public PlanController(Mapper mapper, UserDomainRegistry userDomainRegistry, PlanDomainRegistry planDomainRegistry,
            GiftDomainRegistry giftDomainRegistry) {
        this.mapper = mapper;
        this.userDomainRegistry = userDomainRegistry;
        this.planDomainRegistry = planDomainRegistry;
        this.giftDomainRegistry = giftDomainRegistry;
    }

    /**
     * Add a gift.
     *
     * @param planId the plan to update
     * @param gift the gift to add
     */
    @PostMapping(value = "/{id}/gifts")
    public void addGift(PlanIdData planId, @RequestBody GiftBasicData gift) {
        Plan plan = planDomainRegistry.get(planId);
        User author = userDomainRegistry.get(gift.getAuthor());
        for (Gift existingGift : plan.getGifts()) {
            if (gift.getName().equals(existingGift.getName())) {
                throw new BusinessException("The gift with the name '" + gift.getName()
                        + "' is already part of the gift list for the plan with id '" + planId.getId() + "'.");
            }
        }
        plan.getGifts().add(new Gift(gift.getName(), gift.getDetail(), author));
        planDomainRegistry.save(plan);
    }

    /**
     * Add a participant.
     *
     * @param planId the plan to update
     * @param participantId the participant to add
     */
    @PostMapping(value = "/{id}/participants/{phone}")
    public void addParticipant(PlanIdData planId, UserIdData participantId) {
        Plan plan = planDomainRegistry.get(planId);
        if (null != getParticipantFromEntity(plan, participantId)) {
            throw new BusinessException("The participant with the phone '" + participantId.getPhone()
                    + "' is already part of the participant list for the plan with id '" + planId.getId() + "'.");
        }
        User user = userDomainRegistry.get(participantId);
        plan.getParticipants().add(new Participation(user, plan, false, false));
        planDomainRegistry.save(plan);
    }

    /**
     * Completes the plan.
     *
     * @param planId the plan to complete
     */
    @PostMapping(value = "/{id}/complete")
    public void complete(PlanIdData planId) {
        Plan plan = planDomainRegistry.get(planId);
        plan.setEventCompleted(true);
        planDomainRegistry.save(plan);
    }

    /**
     * Creates a plan.
     *
     * @param request the plan to create
     */
    @PostMapping
    public void create(@RequestBody CreatePlanRequest request) {
        Plan plan = new Plan(userDomainRegistry.get(request.getBirthdayGuy()));
        User author = userDomainRegistry.get(request.getAuthor());
        plan.getParticipants().add(new Participation(author, plan, true, true));
        planDomainRegistry.save(plan);
    }

    /**
     * Deletes a plan.
     *
     * @param planId the plan to delete
     */
    @DeleteMapping(value = "/{id}")
    public void delete(PlanIdData planId) {
        planDomainRegistry.delete(planId);
    }

    /**
     * Retrieves a plan.
     *
     * @param planId the plan to get
     * @return the plan
     */
    @GetMapping(value = "/{id}")
    public PlanBasicViewData get(PlanIdData planId) {
        Plan plan = planDomainRegistry.get(planId);
        return mapper.map(plan, PlanBasicViewData.class);
    }

    /**
     * Retrieves the plan gifts.
     *
     * @param planId the plan to get gifts of
     * @return the plan gifts
     */
    @GetMapping(value = "/{id}/gifts")
    public GetPlanGiftsResponse getGifts(PlanIdData planId) {
        Plan plan = planDomainRegistry.get(planId);
        return mapper.map(plan, GetPlanGiftsResponse.class);
    }

    /**
     * Retrieves the plan participants.
     *
     * @param planId the plan to get participants of
     * @return the plan's participants
     */
    @GetMapping(value = "/{id}/participants")
    public GetPlanParticipantsResponse getParticipants(PlanIdData planId) {
        Plan plan = planDomainRegistry.get(planId);
        return mapper.map(plan, GetPlanParticipantsResponse.class);
    }

    /**
     * Remove a gift.
     *
     * @param planId the plan to update
     * @param request the request containing details to remove the gift from the plan
     */
    @DeleteMapping(value = "/{id}/gifts")
    public void removeGift(PlanIdData planId, @RequestBody RemoveGiftRequest request) {
        Plan plan = planDomainRegistry.get(planId);
        Gift giftToRemove = giftDomainRegistry.get(request);

        if (null == giftToRemove) {
            throw new ResourceDoesNotExistException("The gift with the id '" + request.getId() + "' is not a gift.");
        }

        if (!giftToRemove.getAuthor().getPhone().equals(request.getUserRemoving().getPhone())) {
            throw new UnauthorizedException("The user with the phone '" + request.getUserRemoving().getPhone()
                    + "' is not the author of this gift and, therefore, he cannot remove it.");
        }

        if (!plan.getGifts().remove(giftToRemove)) {
            throw new ResourceDoesNotExistException(
                    "The gift with the id '" + request.getId() + "' is not a gift of the plan with the id '" + planId.getId() + "'.");

        }

        planDomainRegistry.save(plan);
        giftDomainRegistry.delete(giftToRemove);
    }

    /**
     * Remove a participant.
     *
     * @param planId the plan to update
     * @param participantId the participant to remove
     */
    @DeleteMapping(value = "/{id}/participants/{phone}")
    public void removeParticipant(PlanIdData planId, UserIdData participantId) {
        Plan plan = planDomainRegistry.get(planId);
        Participation participantToRemove = getParticipantFromEntity(plan, participantId);

        if (null == participantToRemove) {
            throw new ResourceDoesNotExistException("The user with the phone '" + participantId.getPhone()
                    + "' is not a participant of the plan with the id '" + planId.getId() + "'.");
        }

        if (participantToRemove.isAuthor()) {
            throw new UnauthorizedException("The user with the phone '" + participantId.getPhone()
                    + "' cannot be removed from the plan with the id '" + planId.getId() + "' because he is the author.");
        }

        plan.getParticipants().remove(participantToRemove);
        planDomainRegistry.save(plan);
    }

    /**
     * Validates the gift list.
     *
     * @param planId the plan to validate the gift list of
     */
    @PostMapping(value = "/{id}/validate-gift-list")
    public void validateGiftList(PlanIdData planId) {
        Plan plan = planDomainRegistry.get(planId);
        plan.setGiftListValidated(true);
        planDomainRegistry.save(plan);
    }
}
