package com.viktortempo.demo.teamroles;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MembershipRoleRepository extends JpaRepository<MembershipRole, Long> {

    MembershipRole findByUserIdAndTeamId(Long userId, Long teamId);

    MembershipRole findByUserIdAndTeamIdAndRoleId(Long userId, Long teamId, Long roleId);

    List<MembershipRole> findByRoleId(Long roleId);

    @Transactional
    void deleteByRoleId(Long roleId);
}
