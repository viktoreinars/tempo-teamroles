package com.viktortempo.demo.services;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {

    // TODO: Make configurable
    private final String userEndpointUrl = "http://tempo-test.herokuapp.com/7d1d085e-dbee-4483-aa29-ca033ccae1e4/1/user/%s";

    public UserService() {}

    /**
     * Get a single User from the Teams API
     * @param id Id of the user
     * @return User object
     */
    public User GetUser(Long id){

        // Call Teams endpoint and return the User
        RestTemplate restTemplate = new RestTemplate();

        // Initialize reply object
        ResponseEntity<User> entity = null;
        try
        {
            entity = restTemplate.getForEntity(String.format(userEndpointUrl, id), User.class);
        }
        catch(HttpClientErrorException clientEx)
        {
            // 404 in the teams endpoint means user not found ...
            if (clientEx.getStatusCode() == HttpStatus.NOT_FOUND) return null;
        }

        // Return the User
        return entity.getBody();
    }
}
