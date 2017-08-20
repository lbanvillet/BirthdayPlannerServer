/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner.gift;

import com.tbd.birthdayplanner.exception.ResourceDoesNotExistException;
import com.tbd.birthdayplanner.gift.dto.GiftIdData;

/**
 * Domain registry for manipulating {@link Gift} objects in the underlying data store.
 *
 * @author lb185112
 */
public class GiftDomainRegistry {

    /**
     * Repository used to interact with the data store.
     */
    private GiftRepository giftRepository;

    /**
     * Initializes an instance of <code>GiftDomainRegistry</code> with the provided data.
     *
     * @param giftRepository a handle to the {@link GiftRepository}
     */
    public GiftDomainRegistry(GiftRepository giftRepository) {
        super();
        this.giftRepository = giftRepository;
    }

    /**
     * Deletes a gift from the data store.
     *
     * @param gift the identifier of the gift to delete
     */
    public void delete(Gift gift) {
        giftRepository.delete(gift);
    }

    /**
     * Retrieves the gift from the data store.
     *
     * @param giftId the gift to retrieve
     * @return the found {@link Gift}, or <code>null</code> if no matching result is found
     */
    public Gift find(GiftIdData giftId) {
        return giftRepository.findOne(giftId.getId());
    }

    /**
     * Retrieves the gift from the data store. Throws a {@link ResourceDoesNotExistException} if not found.
     *
     * @param giftId the gift to retrieve
     * @return the {@link Gift} with given identifier, if it exists
     */
    public Gift get(GiftIdData giftId) {
        Gift gift = find(giftId);
        if (null == gift) {
            throw new ResourceDoesNotExistException(GiftIdData.class, giftId.getId().toString());
        }
        return gift;
    }

    /**
     * Saves the given gift.
     *
     * @param giftToSave the gift to save
     * @return {@link Gift}
     */
    public Gift save(Gift giftToSave) {
        return giftRepository.save(giftToSave);
    }
}
