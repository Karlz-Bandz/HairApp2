package pl.hairbybieszczii.hair_bieszczii.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MailModel {
    private String senderMail;
    private String bodyMail;
    private String subjectMail;
    private String recaptchaResponse;
}
