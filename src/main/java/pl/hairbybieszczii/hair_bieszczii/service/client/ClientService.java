package pl.hairbybieszczii.hair_bieszczii.service.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import pl.hairbybieszczii.hair_bieszczii.dto.ChangeDescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.dto.ClientDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DeleteDescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.interfaces.ClientManager;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClientDescription;
import pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel;
import pl.hairbybieszczii.hair_bieszczii.repo.ClientRepository;
import pl.hairbybieszczii.hair_bieszczii.repo.DescriptionRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <strong>ClientService</strong> class<br>
 * This service is responsible for managing the all client data
 * that is stored inside PostgreSQL.<br>
 * It allows us to:
 * <li>Adding new client</li>
 * <li>Delete existing client</li>
 * <li>Adding description to chosen client</li>
 * <li>Update any description</li>
 * <li>Delete any description etc.</li>
 *
 * @author Karol Melak
 * @since 2.0
 */
@Service
@AllArgsConstructor
public class ClientService implements ClientManager
{

    private ClientRepository clientRepository;
    private DescriptionRepository descriptionRepository;

    @Transactional
    @Override
    public void addNewClient(@RequestBody ClientDto clientDto)
    {
        EntityClient entityClient = new EntityClient(clientDto);
        clientRepository.save(entityClient);
    }

    @Override
    public boolean existsById(int id)
    {
        return clientRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteClientById(int id)
    {
        EntityClient client = clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found")
        );
        clientRepository.delete(client);
    }

    @Override
    @Transactional
    public void deleteDescriptionById(@RequestBody DeleteDescriptionDto deleteDescriptionDto)
    {
        EntityClient entityClient = clientRepository.findById(deleteDescriptionDto.getUserId())
                        .orElseThrow(
                                () -> new RuntimeException("User not found")
                        );
        EntityClientDescription entityClientDescription = descriptionRepository.findById(deleteDescriptionDto.getDescriptionId())
                        .orElseThrow(
                                () -> new RuntimeException("Description not found")
                        );
        entityClient.getDescriptions().remove(entityClientDescription);
        descriptionRepository.deleteById(deleteDescriptionDto.getDescriptionId());
        clientRepository.save(entityClient);
    }

    @Override
    @Transactional
    public void addNewDescription(@RequestBody DescriptionDto descriptionDto)
    {
        EntityClient entityClient = clientRepository.findById(descriptionDto.getId()).orElseThrow(
                () -> new RuntimeException("Klient nie istnieje!")
        );
        EntityClientDescription entityClientDescription = new EntityClientDescription();
        entityClientDescription.setDescription(descriptionDto.getDescription());
        entityClientDescription.setWorkDate(new Date());

        entityClient.addToList(entityClientDescription);
        clientRepository.save(entityClient);
    }

    @Override
    @Transactional
    public void changeDescription(@RequestBody ChangeDescriptionDto changeDescriptionDto)
    {
        EntityClient entityClient = clientRepository.findById(changeDescriptionDto.getUserId())
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
        EntityClientDescription entityClientDescription = descriptionRepository.findById(changeDescriptionDto.getDescriptionId())
                .orElseThrow(
                        () -> new RuntimeException("Description not found")
                );

        entityClient.getDescriptions().remove(entityClientDescription);
        descriptionRepository.deleteById(changeDescriptionDto.getDescriptionId());

        EntityClientDescription entityClientDescriptionNew = new EntityClientDescription();
        entityClientDescriptionNew.setDescription(changeDescriptionDto.getDescription());
        entityClientDescriptionNew.setWorkDate(new Date());

        entityClient.getDescriptions().add(entityClientDescriptionNew);

        clientRepository.save(entityClient);
    }

    @Override
    public List<SelectBoxClientModel> getSelectClient()
    {
        List<SelectBoxClientModel> selectBoxClients = clientRepository.getSelectClients();

        return selectBoxClients.stream()
                .sorted(Comparator.comparing(SelectBoxClientModel::getClientSurname))
                .collect(Collectors.toList());
    }

    @Override
    public EntityClient showChosenClientById(int id)
    {
        EntityClient client = clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found")
        );
        client.sortListByDate();

        return client;
    }
}