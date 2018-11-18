package com.viktortempo.demo.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public Long id;
    public String username;
    public String name;

    public List<Long> lead_teams;
    public List<Long> member_teams;

    public User() {}

    /**
     * Returns true if the User is a lead or a member in the Team with the given teamId
     * @param teamId Id of the Team to check if User is a member/lead in
     * @return Whether the User is a member/lead in the given Team
     */
    public boolean IsMemberOrLeadInTeam(Long teamId){

        // Return whether User is a lead or a member in given Team
        return (this.lead_teams.contains(teamId)) || (this.member_teams.contains(teamId));
    }
}
