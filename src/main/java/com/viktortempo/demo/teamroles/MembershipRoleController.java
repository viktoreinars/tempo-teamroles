package com.viktortempo.demo.teamroles;

import com.viktortempo.demo.services.User;
import com.viktortempo.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class MembershipRoleController {

    private final MembershipRoleRepository membershipRoleRepository;
    @Autowired
    private UserService userService;

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

        // Find the MembershipRole in question
        MembershipRole membershipRole = membershipRoleRepository.findByUserIdAndTeamId(userId, teamId);

        // If we do not find MembershipRole it may be that it does not exists yet and we then return the default role
        if (membershipRole == null)
        {
            User user = userService.GetUser(userId);
            if (user != null && user.IsMemberOrLeadInTeam(teamId)) {

                // User exists and is a member/lead in the team - return 'default' role
                membershipRole = new MembershipRole((long)1, userId, teamId); // TODO: Default roleId should be in config/database
            }
        }

        // Throw exception if we could not find the MembershipRole with the given parameters
        if (membershipRole == null) throw new MembershipRoleNotFoundException(userId, teamId);

        // Return the membershipRole
        return membershipRole;
    }

    @GetMapping(path = "/membershipRoles", params = { "roleId" })
    List<MembershipRole> getMembershipRolesByRoleId(@RequestParam(value = "roleId") Long roleId) {

        // Find the MembershipRoles in question
        return membershipRoleRepository.findByRoleId(roleId);
    }

    @PostMapping("/membershipRoles")
    MembershipRole newMembershipRole(@RequestBody MembershipRole membershipRole) {

        // Check if MembershipRole already exists
        MembershipRole existingMembershipRole = membershipRoleRepository.findByUserIdAndTeamId(membershipRole.getUserId(), membershipRole.getTeamId());
        if (existingMembershipRole != null) throw new RuntimeException("MembershipRole record with userId " + membershipRole.getUserId() + " and teamId " + membershipRole.getTeamId() + " already exists!");

        // Get User from Teams endpoint
        User user = userService.GetUser(membershipRole.getUserId());

        // Check if user exists and is in given team
        if (user == null) throw new RuntimeException("Could not find user with id " + membershipRole.getUserId() + " in Teams endpoint");
        if (!user.IsMemberOrLeadInTeam(membershipRole.getTeamId()))
            throw new RuntimeException("User " + membershipRole.getUserId() + " is not a lead or a member in team " + membershipRole.getTeamId());

        // Save MembershipRole
        return membershipRoleRepository.save(membershipRole);
    }

    @DeleteMapping("/membershipRoles/{id}")
    void deleteMembershipRole(@PathVariable Long id) {
        membershipRoleRepository.deleteById(id);
    }
}
