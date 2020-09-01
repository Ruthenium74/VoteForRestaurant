package ru.ruthenium74.voteforrestaurant;

import ru.ruthenium74.voteforrestaurant.model.AbstractBaseEntity;

public class AuthorizedUser {
    private static final Integer AUTHORIZED_USER_ID = AbstractBaseEntity.START_SEQ;

    public static Integer getAuthorizedUserId() {
        return AUTHORIZED_USER_ID;
    }
}
