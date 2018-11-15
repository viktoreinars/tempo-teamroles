package com.viktortempo.demo.roles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.relation.RoleNotFoundException;

/**
 * Renders a roleNotFoundException as text
 */
@ControllerAdvice
class RoleNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String roleNotFoundAdvice(RoleNotFoundException ex) {
        return ex.getMessage();
    }
}
