package ru.ruthenium74.voteforrestaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ruthenium74.voteforrestaurant.exception.ErrorInfo;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static ru.ruthenium74.voteforrestaurant.exception.ErrorType.*;
import static ru.ruthenium74.voteforrestaurant.util.ValidationUtil.CONSTRAINTS_MAP;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class RestExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);



    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest request, DataIntegrityViolationException e) {
        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
        if (rootMsg != null) {
            for (Map.Entry<String, String> entry : CONSTRAINTS_MAP.entrySet()) {
                if (rootMsg.contains(entry.getKey())) {
                    return logAndGetErrorInfo(request, e, false, VALIDATION_ERROR, entry.getValue());
                }
            }
        }
        return logAndGetErrorInfo(request, e, true, DATA_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(log, req, e, logException, errorType);
        return new ErrorInfo(req.getRequestURL(), errorType,
                details.length != 0 ? details : new String[]{ValidationUtil.getMessage(rootCause)});
    }
}
