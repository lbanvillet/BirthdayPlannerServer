/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.planner;

import java.util.ArrayList;

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
import com.tbd.birthdayplanner.participation.Participation;
import com.tbd.birthdayplanner.planner.dto.CreatePlannerRequest;
import com.tbd.birthdayplanner.planner.dto.GetPlannerParticipantsResponse;
import com.tbd.birthdayplanner.planner.dto.PlannerBasicViewData;
import com.tbd.birthdayplanner.planner.dto.PlannerIdData;
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
     * Remove a participant.
     *
     * @param plannerId the planner to update
     * @param userId the user to add
     */
    @PostMapping(value = "/{id}/participants/{phone}")
    public void addParticipant(PlannerIdData plannerId, UserIdData userId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        User user = userDomainRegistry.get(userId);
        planner.getParticipants().add(new Participation(user, planner, false, false));
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
        planner.setParticipants(new ArrayList<>());
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
     * Retrieves the planner participants.
     *
     * @param plannerId the user to get
     * @return the user
     */
    @GetMapping(value = "/{id}/participants")
    public GetPlannerParticipantsResponse getParticipants(PlannerIdData plannerId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        return mapper.map(planner, GetPlannerParticipantsResponse.class);
    }

    /**
     * Add a participant.
     *
     * @param plannerId the planner to update
     * @param userId the user to remove
     */
    @DeleteMapping(value = "/{id}/participants/{phone}")
    public void removeParticipant(PlannerIdData plannerId, UserIdData userId) {
        Planner planner = plannerDomainRegistry.get(plannerId);
        Participation participantToRemove = null;
        for (Participation participant : planner.getParticipants()) {
            if (userId.getPhone().equals(participant.getParticipant().getPhone())) {
                participantToRemove = participant;
            }
        }

        if (null == participantToRemove) {
            throw new ResourceDoesNotExistException("The user with the phone '" + userId.getPhone()
                    + "' is not a participant of the planner with the id '" + plannerId.getId() + "'.");
        }

        if (participantToRemove.isAuthor()) {
            throw new BusinessException("The user with the phone '" + userId.getPhone()
                    + "' cannot be removed from the planner with the id '" + plannerId.getId() + "' because he is the author.");
        }

        planner.getParticipants().remove(participantToRemove);
        plannerDomainRegistry.save(planner);
    }
}
