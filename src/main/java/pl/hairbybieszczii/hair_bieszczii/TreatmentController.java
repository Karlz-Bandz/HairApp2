package pl.hairbybieszczii.hair_bieszczii;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hairbybieszczii.hair_bieszczii.model.TreatmentPriceList;
import pl.hairbybieszczii.hair_bieszczii.service.TreatmentService;

import java.util.List;

@RestController
@RequestMapping("/treatments")
@AllArgsConstructor
public class TreatmentController {

    private TreatmentService treatmentService;

    @GetMapping("/list")
    public ResponseEntity<List<TreatmentPriceList>> getAllTreatments(){
        List<TreatmentPriceList> treatments = treatmentService.getAll();
        return new ResponseEntity<>(treatments, HttpStatus.OK);
    }
}
