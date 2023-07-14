package pl.hairbybieszczii.hair_bieszczii.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hairbybieszczii.hair_bieszczii.model.ImageProfileModel;

public interface ImageProfilRepository extends JpaRepository<ImageProfileModel, Integer>
{
}
