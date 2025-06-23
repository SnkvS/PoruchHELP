package com.senkiv.poruchhelp.repository;

import com.senkiv.poruchhelp.model.Role;
import com.senkiv.poruchhelp.model.Role.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);

    boolean existsByRoleName(RoleName roleName);
}
