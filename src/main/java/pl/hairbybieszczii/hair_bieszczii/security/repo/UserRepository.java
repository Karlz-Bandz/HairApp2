package pl.hairbybieszczii.hair_bieszczii.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hairbybieszczii.hair_bieszczii.security.models.EntityUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EntityUser, Integer> {

    Optional<EntityUser> findByUserName(String username);
    Boolean existsByUserName(String username);
}
