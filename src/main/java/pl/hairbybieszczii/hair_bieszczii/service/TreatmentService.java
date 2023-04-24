package pl.hairbybieszczii.hair_bieszczii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hairbybieszczii.hair_bieszczii.interfaces.TreatmentManager;
import pl.hairbybieszczii.hair_bieszczii.model.TreatmentPriceList;
import pl.hairbybieszczii.hair_bieszczii.repo.TreatmentRepository;

import java.util.List;

@Service
public class TreatmentService implements TreatmentManager
{

    private TreatmentRepository treatmentRepository;

    @Autowired
    TreatmentService(TreatmentRepository treatmentRepository)
    {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<TreatmentPriceList> getAll()
    {
        return treatmentRepository.findAll();
    }
}
