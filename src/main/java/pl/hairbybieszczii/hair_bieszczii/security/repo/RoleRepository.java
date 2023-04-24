package pl.hairbybieszczii.hair_bieszczii.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hairbybieszczii.hair_bieszczii.security.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
