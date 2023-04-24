package pl.hairbybieszczii.hair_bieszczii.security.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;

    @Column(nullable = false)
    private String name;
}
