/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner;

import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import com.tbd.birthdayplanner.gift.Gift;
import com.tbd.birthdayplanner.gift.GiftComment;
import com.tbd.birthdayplanner.gift.dto.GiftBasicViewData;
import com.tbd.birthdayplanner.gift.dto.GiftCommentBasicViewData;
import com.tbd.birthdayplanner.user.User;
import com.tbd.birthdayplanner.user.dto.CreateUserRequest;
import com.tbd.birthdayplanner.user.dto.UserBasicViewData;
import com.tbd.birthdayplanner.user.dto.UserFollowRequest;
import com.tbd.birthdayplanner.user.dto.UserViewData;

/**
 * Mapping builder.
 *
 * @author lb185112
 */
public class MappingBuilder extends BeanMappingBuilder {

    /**
     * Field <code>userId.phone</code>
     */
    private static final String USER_ID_PHONE_FIELD = "userId.phone";

    /**
     * Field <code>phone</code>
     */
    private static final String PHONE_FIELD = "phone";

    /**
     * Field <code>giftId.id</code>
     */
    private static final String GIFT_ID_FIELD = "giftId.id";

    /**
     * Field <code>id</code>
     */
    private static final String ID_FIELD = "id";

    /**
     * Field <code>commentId.id</code>
     */
    private static final String COMMENT_ID_FIELD = "commentId.id";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        mapping(CreateUserRequest.class, User.class, TypeMappingOptions.mapNull(false), TypeMappingOptions.oneWay())
                .fields(USER_ID_PHONE_FIELD, PHONE_FIELD);
        mapping(UserFollowRequest.class, User.class, TypeMappingOptions.mapNull(false), TypeMappingOptions.oneWay())
                .fields(USER_ID_PHONE_FIELD, PHONE_FIELD);
        mapping(User.class, UserViewData.class, TypeMappingOptions.mapNull(false)).fields(PHONE_FIELD, USER_ID_PHONE_FIELD);
        mapping(User.class, UserBasicViewData.class, TypeMappingOptions.mapNull(false)).fields(PHONE_FIELD, USER_ID_PHONE_FIELD);
        mapping(Gift.class, GiftBasicViewData.class, TypeMappingOptions.mapNull(false)).fields(ID_FIELD, GIFT_ID_FIELD);
        mapping(GiftComment.class, GiftCommentBasicViewData.class, TypeMappingOptions.mapNull(false)).fields(ID_FIELD,
                COMMENT_ID_FIELD);
    }
}