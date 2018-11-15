package com.viktortempo.demo.teamroles;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class MembershipRoleController {

    private final MembershipRoleRepository membershipRoleRepository;

    MembershipRoleController(MembershipRoleRepository membershipRoleRepository){

        this.membershipRoleRepository = membershipRoleRepository;
    }

    @GetMapping("/membershipRoles")
    List<MembershipRole> all() {
        return membershipRoleRepository.findAll();
    }

    @GetMapping("/membershipRoles/{id}")
    MembershipRole one(@PathVariable Long id) {
        return membershipRoleRepository.findById(id)
                .orElseThrow(() -> new MembershipRoleNotFoundException(id));
    }

    @GetMapping("/membershipRoles/{userId}/{teamId}")
    MembershipRole one(@PathVariable Long userId, @PathVariable Long teamId) {
        MembershipRole membershipRole = membershipRoleRepository.findByUserIdAndTeamId(userId, teamId);

        // Throw exception if we could not find the MembershipRole with the given parameters
        if (membershipRole == null) throw new MembershipRoleNotFoundException(userId, teamId);

        // Return the membershipRole
        return membershipRole;
    }

    @PostMapping("/membershipRoles")
    MembershipRole newMembershipRole(@RequestBody MembershipRole membershipRole) {
        return membershipRoleRepository.save(membershipRole);
    }

    @DeleteMapping("/membershipRoles/{id}")
    void deleteMembershipRole(@PathVariable Long id) {
        membershipRoleRepository.deleteById(id);
    }
}
