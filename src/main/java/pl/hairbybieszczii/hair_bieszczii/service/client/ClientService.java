package pl.hairbybieszczii.hair_bieszczii.service.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.hairbybieszczii.hair_bieszczii.dto.ClientDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClientDescription;
import pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel;
import pl.hairbybieszczii.hair_bieszczii.repo.ClientRepository;
import pl.hairbybieszczii.hair_bieszczii.repo.DescriptionRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;
    private DescriptionRepository descriptionRepository;

    public void addNewClient(@RequestBody ClientDto clientDto) {
        EntityClient entityClient = new EntityClient(clientDto);
        clientRepository.save(entityClient);
    }


    public boolean existsById(int id) {
        return clientRepository.existsById(id);
    }

    public void deleteClientById(int id) {
        clientRepository.deleteById(id);
    }

    public void deleteDescriptionById(long id) {
        descriptionRepository.deleteById(id);
    }

    public void addNewDescription(@RequestBody DescriptionDto descriptionDto) {
        EntityClient entityClient = clientRepository.findById(descriptionDto.getId()).orElseThrow(
                () -> new RuntimeException("Klient nie istnieje!")
        );
        EntityClientDescription entityClientDescription = new EntityClientDescription();
        entityClientDescription.setDescription(descriptionDto.getDescription());
        entityClientDescription.setWorkDate(new Date());

        entityClient.addToList(entityClientDescription);
        clientRepository.save(entityClient);
    }


    public List<SelectBoxClientModel> getSelectClient() {
        List<SelectBoxClientModel> selectBoxClients = clientRepository.getSelectClients();

        return selectBoxClients.stream()
                .sorted(Comparator.comparing(SelectBoxClientModel::getClientSurname))
                .collect(Collectors.toList());
    }

    public EntityClient showChosenClientById(int id) {
        EntityClient client = clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Klient nie istnieje!")
        );

        client.sortListByDate();

        return client;

    }


}
