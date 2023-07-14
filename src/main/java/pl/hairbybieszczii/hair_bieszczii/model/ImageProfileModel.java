package pl.hairbybieszczii.hair_bieszczii.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ImageProfileUrl")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageProfileModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private String Url;
}
