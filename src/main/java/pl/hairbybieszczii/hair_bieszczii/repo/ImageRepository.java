package pl.hairbybieszczii.hair_bieszczii.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hairbybieszczii.hair_bieszczii.model.ImageModel;



public interface ImageRepository extends JpaRepository<ImageModel, Integer> {

}
