package pl.hairbybieszczii.hair_bieszczii.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ImageUrl")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageModel implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private String Url;
}
