package pl.hairbybieszczii.hair_bieszczii.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hairbybieszczii.hair_bieszczii.model.TreatmentPriceList;

public interface TreatmentRepository extends JpaRepository<TreatmentPriceList, Integer> {
}
