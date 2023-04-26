package pl.hairbybieszczii.hair_bieszczii.dto;

import lombok.Data;

@Data
public class ChangeDescriptionDto
{
    private int userId;
    private long descriptionId;
    private String description;
}
