package com.viktortempo.demo.teamroles;

public class MembershipRoleNotFoundException extends RuntimeException {

    MembershipRoleNotFoundException(Long id) {
        super("Could not find MembershipRole with id " + id);
    }

    MembershipRoleNotFoundException(Long userId, Long teamId) {
        super("Could not find MembershipRole with userId " + userId + " and teamId " + teamId);
    }
}
