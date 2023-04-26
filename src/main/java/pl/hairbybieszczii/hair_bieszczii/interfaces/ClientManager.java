package pl.hairbybieszczii.hair_bieszczii.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import pl.hairbybieszczii.hair_bieszczii.dto.ChangeDescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.dto.ClientDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DeleteDescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel;
import java.util.List;


public interface ClientManager
{
    void addNewClient(@RequestBody ClientDto clientDto);

    boolean existsById(int id);

    void deleteClientById(int id);

    void deleteDescriptionById(@RequestBody DeleteDescriptionDto deleteDescriptionDto);

    void addNewDescription(@RequestBody DescriptionDto descriptionDto);

    void changeDescription(@RequestBody ChangeDescriptionDto changeDescriptionDto);

    List<SelectBoxClientModel> getSelectClient();

    EntityClient showChosenClientById(int id);
}
