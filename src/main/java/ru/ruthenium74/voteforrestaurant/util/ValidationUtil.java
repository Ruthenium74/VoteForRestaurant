package ru.ruthenium74.voteforrestaurant.util;

import org.slf4j.Logger;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.exception.IllegalRequestDataException;
import ru.ruthenium74.voteforrestaurant.model.AbstractBaseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static final String ONE_VOTE_PER_DAY_CONSTRAINT = "ONE_VOTE_PER_DAY_IDX";
    public static final String RESTAURANT_UNIQUE_NAME_CONSTRAINT = "RESTAURANT_UNIQUE_NAME_IDX";
    public static final String USER_UNIQUE_EMAIL_CONSTRAINT = "USER_UNIQUE_EMAIL_IDX";
    public static final String USER_ROLE_CONSTRAINT = "USER_ROLE_IDX";

    public static final Map<String, String> CONSTRAINTS_MAP = Map
            .of(ONE_VOTE_PER_DAY_CONSTRAINT, "You can't vote twice a day.",
                    RESTAURANT_UNIQUE_NAME_CONSTRAINT, "Restaurant with this name already exists.",
                    USER_UNIQUE_EMAIL_CONSTRAINT, "User with this email already exists.",
                    USER_ROLE_CONSTRAINT, "User already has this role.");

    public static void checkNew(AbstractBaseEntity bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    //  http://stackoverflow.com/a/28565320/548473
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable logAndGetRootCause(Logger log, HttpServletRequest req, Exception e, boolean logException, ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return rootCause;
    }
}
