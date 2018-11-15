package com.viktortempo.demo.teamroles;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class MembershipRole {

    private @Id @GeneratedValue Long Id;
    private Long roleId;
    private Long userId;
    private Long teamId;

    public MembershipRole() {}

    public MembershipRole(Long roleId, Long userId, Long teamId){
        this.roleId = roleId;
        this.userId = userId;
        this.teamId = teamId;
    }
}
