package pl.hairbybieszczii.hair_bieszczii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.hairbybieszczii.hair_bieszczii.dto.ChangeDescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.dto.ClientDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DeleteDescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClientDescription;
import pl.hairbybieszczii.hair_bieszczii.service.client.ClientService;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientService_Test
{
    @Autowired
    private ClientService clientServiceTest;

    @BeforeAll
    public void setup()
    {
        ClientDto entityClient1 = new ClientDto();
        ClientDto entityClient2 = new ClientDto();
        DescriptionDto descriptionDtoTest1 = new DescriptionDto();

        descriptionDtoTest1.setId(1);
        descriptionDtoTest1.setDescription("Test1");

        entityClient1.setClientName("Karlz");
        entityClient1.setClientSurname("Bandz");
        entityClient1.setPhoneNumber("555-222-333");

        entityClient2.setClientName("Karlz2");
        entityClient2.setClientSurname("Bandz2");
        entityClient2.setPhoneNumber("555-222-333");

        clientServiceTest.addNewClient(entityClient1);
        clientServiceTest.addNewClient(entityClient2);
        clientServiceTest.addNewDescription(descriptionDtoTest1);
    }

    @Test
    public void changeDescription_Test()
    {
        EntityClient entityClient = clientServiceTest.showChosenClientById(1);

        List<EntityClientDescription> descriptionListTest = entityClient.getDescriptions();

        EntityClientDescription descriptionTest = descriptionListTest.get(0);

        long descriptionId = descriptionTest.getId();

        int sizeBefore = descriptionListTest.size();

        ChangeDescriptionDto changeDescriptionDtoTest = new ChangeDescriptionDto();

        changeDescriptionDtoTest.setUserId(1);
        changeDescriptionDtoTest.setDescriptionId(descriptionId);
        changeDescriptionDtoTest.setDescription("Test666");

        clientServiceTest.changeDescription(changeDescriptionDtoTest);

        EntityClient entityClient2 = clientServiceTest.showChosenClientById(1);

        List<EntityClientDescription> descriptionListTest2 = entityClient2.getDescriptions();

        int sizeAfter = descriptionListTest2.size();

        Assertions.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void deleteDescription_Test()
    {
        EntityClient entityClient = clientServiceTest.showChosenClientById(1);

        List<EntityClientDescription> descriptionListTest = entityClient.getDescriptions();

        int beforeStateTest = descriptionListTest.size();

        DeleteDescriptionDto deleteDescriptionDto = new DeleteDescriptionDto();
        deleteDescriptionDto.setUserId(1);
        deleteDescriptionDto.setDescriptionId(descriptionListTest.get(0).getId());

        clientServiceTest.deleteDescriptionById(deleteDescriptionDto);

        EntityClient entityClient2 = clientServiceTest.showChosenClientById(1);

        int afterStateTest = entityClient2.getDescriptions().size();

        Assertions.assertEquals(beforeStateTest, afterStateTest+1);
    }

    @Test
    public void addNewDescription_Test()
    {
        DescriptionDto descriptionDtoTest1 = new DescriptionDto();
        DescriptionDto descriptionDtoTest2 = new DescriptionDto();

        descriptionDtoTest1.setId(1);
        descriptionDtoTest1.setDescription("Test1");
        descriptionDtoTest2.setId(1);
        descriptionDtoTest2.setDescription("Test2");

        clientServiceTest.addNewDescription(descriptionDtoTest1);
        clientServiceTest.addNewDescription(descriptionDtoTest2);

        EntityClient entityClient = clientServiceTest.showChosenClientById(1);

        List<EntityClientDescription> descriptionListTest = entityClient.getDescriptions();

        Assertions.assertEquals(3, descriptionListTest.size());
    }

    @Test
    public void addNewClient_Test()
    {
        ClientDto clientDtoTest = new ClientDto();
        clientDtoTest.setClientName("Test");
        clientDtoTest.setClientSurname("TestSur");
        clientDtoTest.setPhoneNumber("333-222-111");

        clientServiceTest.addNewClient(clientDtoTest);

        Assertions.assertEquals(3, clientServiceTest.getSelectClient().size());

        clientServiceTest.deleteClientById(3);
    }

    @Test
    public void deleteClientById()
    {
        ClientDto clientDtoTest = new ClientDto();
        clientDtoTest.setClientName("Test");
        clientDtoTest.setClientSurname("TestSur");
        clientDtoTest.setPhoneNumber("333-222-111");

        clientServiceTest.deleteClientById(2);

        Assertions.assertEquals(1, clientServiceTest.getSelectClient().size());

        clientServiceTest.addNewClient(clientDtoTest);
    }
}
