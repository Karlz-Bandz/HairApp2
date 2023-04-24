package pl.hairbybieszczii.hair_bieszczii.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecaptchaModel {
    private boolean success;
    private String challenge_ts;
    private String hostname;
}
