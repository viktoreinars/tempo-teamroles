package com.viktortempo.demo.roles;

public class RoleNotFoundException extends RuntimeException {

    RoleNotFoundException(Long id) {
        super("Could not find Role with id " + id);
    }
}
