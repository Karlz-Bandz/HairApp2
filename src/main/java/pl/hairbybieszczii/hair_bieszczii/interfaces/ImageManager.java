package pl.hairbybieszczii.hair_bieszczii.interfaces;

import pl.hairbybieszczii.hair_bieszczii.model.ImageModel;
import pl.hairbybieszczii.hair_bieszczii.model.ImageProfileModel;

import java.util.List;

public interface ImageManager
{
     ImageModel getImage(int id);

     List<ImageModel> getImages();

     List<ImageProfileModel> getProfilImages();
}
