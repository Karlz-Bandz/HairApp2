package pl.hairbybieszczii.hair_bieszczii.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.hairbybieszczii.hair_bieszczii.dto.ClientDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@Data
public class EntityClient {


    public EntityClient(ClientDto clientDto) {
        this.clientName = clientDto.getClientName();
        this.clientSurname = clientDto.getClientSurname();
        this.phoneNumber = clientDto.getPhoneNumber();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;
    @Column(nullable = false)
    private String clientName;
    @Column(nullable = false)
    private String clientSurname;
    @Column(nullable = false)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "client_description_join", joinColumns = @JoinColumn(name = "client_id",
            referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "description_id",
            referencedColumnName = "id"))
    private List<EntityClientDescription> descriptions = new ArrayList<>();

    public void addToList(EntityClientDescription entityClientDescription) {
        descriptions.add(entityClientDescription);
    }

    public void deleteDescriptionById(int id) {
        descriptions.remove(Integer.valueOf(id));
    }

    public void sortListByDate() {
        this.descriptions = descriptions.stream()
                .sorted(Comparator.comparing(EntityClientDescription::getWorkDate).reversed())
                .collect(Collectors.toList());
    }


}
