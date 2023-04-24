package pl.hairbybieszczii.hair_bieszczii.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hairbybieszczii.hair_bieszczii.model.PriceListModel;
import pl.hairbybieszczii.hair_bieszczii.repo.PriceListRepository;

import java.util.List;

@Service
public class PriceListService {

    private PriceListRepository priceListRepository;


    @Autowired
    public PriceListService(PriceListRepository priceListRepository){
        this.priceListRepository = priceListRepository;
    }


    public List<PriceListModel> getPriceList(){
        return priceListRepository.findAll();
    }
}
