package pl.hairbybieszczii.hair_bieszczii;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hairbybieszczii.hair_bieszczii.model.PriceListModel;
import pl.hairbybieszczii.hair_bieszczii.service.PriceListService;

import java.util.List;

@RestController
@RequestMapping("/price")
@AllArgsConstructor
public class PriceListController
{

    private PriceListService priceListService;

    @GetMapping("/list")
    public ResponseEntity<List<PriceListModel>> getPriceListService()
    {
        List<PriceListModel> prices = priceListService.getPriceList();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

}
