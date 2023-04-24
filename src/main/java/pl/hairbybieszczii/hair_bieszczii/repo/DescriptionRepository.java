package pl.hairbybieszczii.hair_bieszczii.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClientDescription;

public interface DescriptionRepository extends JpaRepository<EntityClientDescription, Long> {


}
