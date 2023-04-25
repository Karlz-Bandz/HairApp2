package pl.hairbybieszczii.hair_bieszczii;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hairbybieszczii.hair_bieszczii.dto.ClientDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DeleteDescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.dto.DescriptionDto;
import pl.hairbybieszczii.hair_bieszczii.model.DeleteClientModel;
import pl.hairbybieszczii.hair_bieszczii.model.EntityClient;
import pl.hairbybieszczii.hair_bieszczii.model.SelectBoxClientModel;
import pl.hairbybieszczii.hair_bieszczii.service.client.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @PostMapping("add")
    public ResponseEntity<Void> addNewClient(@RequestBody ClientDto clientDto) {
//        if (clientService.existByName(clientDto.getClientName()) &&
//                clientService.existBySurname(clientDto.getClientSurname()))
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        clientService.addNewClient(clientDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("description")
    public ResponseEntity<Void> addDescription(@RequestBody DescriptionDto descriptionDto){
        if(!clientService.existsById(descriptionDto.getId()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        clientService.addNewDescription(descriptionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("delete")
    public ResponseEntity<Void> deleteClient(@RequestBody DeleteClientModel deleteClientModel){
        clientService.deleteClientById(deleteClientModel.getId());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("delete/description")
    public ResponseEntity<Void> deleteDescription(@RequestBody DeleteDescriptionDto deleteDescriptionDto){
        clientService.deleteDescriptionById(deleteDescriptionDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("select")
    public ResponseEntity<List<SelectBoxClientModel>> getSelect(){
        return new ResponseEntity<>(clientService.getSelectClient(), HttpStatus.OK);
    }

    @GetMapping("show/{id}")
    public ResponseEntity<EntityClient> getChosenClient(@PathVariable("id") int id){
        EntityClient entityClient = clientService.showChosenClientById(id);

        return new ResponseEntity<>(entityClient, HttpStatus.OK);
    }



}
