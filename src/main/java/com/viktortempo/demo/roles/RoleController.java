package com.viktortempo.demo.roles;

import com.viktortempo.demo.teamroles.MembershipRoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class RoleController {

    private final RoleRepository roleRepository;
    private final MembershipRoleRepository membershipRoleRepository;

    RoleController(RoleRepository roleRepository, MembershipRoleRepository membershipRoleRepository){
        this.roleRepository = roleRepository;
        this.membershipRoleRepository = membershipRoleRepository;
    }

    @GetMapping("/roles")
    List<Role> all() {
        return roleRepository.findAll();
    }

    @GetMapping("/roles/{roleId}")
    Role one(@PathVariable Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Could not find Role with id " + roleId));
    }

    @PostMapping("/roles")
    Role newRole(@RequestBody Role role){
        return roleRepository.save(role);
    }

    @DeleteMapping("/roles/{roleId}")
    void deleteRole(@PathVariable Long roleId){

        // If default Role - throw error
        if (roleId == 1) throw new RuntimeException("You cannot delete the default Role!");

        // Delete all MembershipRole records that are linked to this Role
        membershipRoleRepository.deleteByRoleId(roleId);

        // Delete the Role
        roleRepository.deleteById(roleId);
    }
}
