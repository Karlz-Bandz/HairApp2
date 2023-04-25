package pl.hairbybieszczii.hair_bieszczii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hairbybieszczii.hair_bieszczii.model.MailModel;
import pl.hairbybieszczii.hair_bieszczii.service.mail.MailSenderService;

@RestController
@RequestMapping("/mail")
public class MailController
{
    @Autowired
    private MailSenderService mailService;

    @PostMapping("/test")
    public ResponseEntity<Void> sendMail(@RequestBody MailModel mail)
    {
        this.mailService.sendMail(mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
