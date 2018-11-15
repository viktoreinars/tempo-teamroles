package com.viktortempo.demo.teamroles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRoleRepository extends JpaRepository<MembershipRole, Long> {

    MembershipRole findByUserIdAndTeamId(Long userId, Long teamId);
}
