package pl.hairbybieszczii.hair_bieszczii.service;

import org.springframework.stereotype.Service;
import pl.hairbybieszczii.hair_bieszczii.exception.ImageNotFoundException;
import pl.hairbybieszczii.hair_bieszczii.interfaces.ImageManager;
import pl.hairbybieszczii.hair_bieszczii.model.ImageModel;
import pl.hairbybieszczii.hair_bieszczii.model.ImageProfileModel;
import pl.hairbybieszczii.hair_bieszczii.repo.ImageProfilRepository;
import pl.hairbybieszczii.hair_bieszczii.repo.ImageRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ImageService implements ImageManager
{

    private final ImageRepository imageRepository;
    private final ImageProfilRepository imageProfilRepository;


    public ImageService(ImageRepository imageRepository, ImageProfilRepository imageProfilRepository)
    {
        this.imageRepository = imageRepository;
        this.imageProfilRepository = imageProfilRepository;
    }

    @Override
    public ImageModel getImage(int id)
    {
        ImageModel image = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Picture not found"));

        return image;
    }

    @Override
    public List<ImageModel> getImages()
    {
        List<ImageModel> repo = imageRepository.findAll();
        repo.remove(0);
        Collections.reverse(repo);

        return repo;
    }

    @Override
    public List<ImageProfileModel> getProfilImages()
    {
        return imageProfilRepository.findAll();
    }
}