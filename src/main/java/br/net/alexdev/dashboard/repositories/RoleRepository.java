package br.net.alexdev.dashboard.repositories;

import br.net.alexdev.dashboard.entities.Role;
import br.net.alexdev.dashboard.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
