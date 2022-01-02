package io.springbootstarter.exception;

import io.springbootstarter.response.Error;
import io.springbootstarter.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Void> handleConstraintException(ConstraintViolationException constraintViolationException) {
        final List<Error> errorList = constraintViolationException.getConstraintViolations()
                .stream()
                .map(ce -> new Error(ApplException.Code.VALIDATION_ERROR.getCode(),
                        ce.getPropertyPath() + " " + ce.getMessage())).collect(Collectors.toList());
        Response<Void>  response = new Response<>();
        response.setSuccess(false);
        response.setErrors(errorList);
        return response;
    }

    @ExceptionHandler(ApplException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<Void> handleCAMException(ApplException applException) {
        Response<Void>  response = new Response<>();
        response.setSuccess(false);
        response.setErrors(Collections.singletonList(new Error(applException.getCode(), applException.getMessage())));
        return response;
    }
}
