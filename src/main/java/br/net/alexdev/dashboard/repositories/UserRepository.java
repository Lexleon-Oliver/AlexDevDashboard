package br.net.alexdev.dashboard.repositories;

import br.net.alexdev.dashboard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByName(String name);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Query("SELECT u.name FROM User u")
    List<String> findAllNames();
}
