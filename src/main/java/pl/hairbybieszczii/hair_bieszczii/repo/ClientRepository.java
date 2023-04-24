package pl.hairbybieszczii.hair_bieszczii.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<EntityClient, Integer> {

    boolean existsByClientName(String clientName);

    boolean existsByClientSurname(String clientSurname);

    Optional<EntityClient> findById(int id);

    @Query("SELECT u FROM EntityClient u")
    List<EntityClient> getClients();

    @Query("SELECT new pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel(u.id, u.clientName, u.clientSurname) FROM EntityClient u")
    List<SelectBoxClientModel> getSelectClients();
}
