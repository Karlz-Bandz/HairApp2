package pl.hairbybieszczii.hair_bieszczii.model;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "client_description")
@NoArgsConstructor
@Data
public class EntityClientDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false, length = 1000)
    private String description;
    @Temporal(TemporalType.DATE)
    private Date workDate;
}
