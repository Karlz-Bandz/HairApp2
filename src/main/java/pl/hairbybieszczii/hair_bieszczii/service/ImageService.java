package pl.hairbybieszczii.hair_bieszczii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hairbybieszczii.hair_bieszczii.exception.ImageNotFoundException;
import pl.hairbybieszczii.hair_bieszczii.model.ImageModel;
import pl.hairbybieszczii.hair_bieszczii.repo.ImageRepository;



import java.util.Collections;
import java.util.List;


@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }



    public ImageModel getImage(int id){
        ImageModel image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Nie znaleziono zdjęcia!"));

        return image;
    }

    public List<ImageModel> getImages(){
        List<ImageModel> repo = imageRepository.findAll();
        repo.remove(0);
        Collections.reverse(repo);

        return repo;
    }


}
