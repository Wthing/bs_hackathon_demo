package com.kstu.hackathon.repo;

import com.kstu.hackathon.constants.Roles;
import com.kstu.hackathon.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(Roles roles);
}
