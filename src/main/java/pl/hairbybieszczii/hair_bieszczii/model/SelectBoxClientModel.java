package pl.hairbybieszczii.hair_bieszczii.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectBoxClientModel
{
    private int id;
    private String clientName;
    private String clientSurname;
}
