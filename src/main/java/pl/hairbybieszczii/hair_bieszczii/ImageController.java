package pl.hairbybieszczii.hair_bieszczii;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hairbybieszczii.hair_bieszczii.model.ImageModel;
import pl.hairbybieszczii.hair_bieszczii.service.ImageService;

import java.util.List;


@RestController
@RequestMapping("/image")
@AllArgsConstructor
public class ImageController {

    ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageModel> getImageUrl(@PathVariable("id") int id){
        ImageModel imageModel = imageService.getImage(id);
        return new ResponseEntity<>(imageModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ImageModel>> getAllImages(){
        List<ImageModel> images = imageService.getImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
