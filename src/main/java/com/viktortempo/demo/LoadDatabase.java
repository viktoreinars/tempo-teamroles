package com.viktortempo.demo;

import com.viktortempo.demo.roles.Role;
import com.viktortempo.demo.roles.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository){

        return args -> {
            log.info("Preloading " + roleRepository.save(new Role("Developer")));
            log.info("Preloading " + roleRepository.save(new Role("Product Owner")));
            log.info("Preloading " + roleRepository.save(new Role("Tester")));
        };
    }
}
