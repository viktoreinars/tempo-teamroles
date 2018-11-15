package com.viktortempo.demo.roles;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class RoleController {

    private final RoleRepository roleRepository;

    RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    List<Role> all() {
        return roleRepository.findAll();
    }

    @GetMapping("/roles/{id}")
    Role one(@PathVariable Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    @PostMapping("/roles")
    Role newRole(@RequestBody Role role){
        return roleRepository.save(role);
    }

    @DeleteMapping("/roles/{id}")
    void deleteRole(@PathVariable Long id){
        roleRepository.deleteById(id);
    }
}
