package pl.hairbybieszczii.hair_bieszczii.service.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.hairbybieszczii.hair_bieszczii.model.MailModel;
import pl.hairbybieszczii.hair_bieszczii.model.RecaptchaModel;

@Service
public class MailSenderService {


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RestTemplate restTemplate;



    public void sendMail(MailModel mailModel){

      String url = "https://www.google.com/recaptcha/api/siteverify";
      String params = "?secret=6Lf8smkiAAAAAB1kjYg_R33lUGKgTO38Y5t4c51m&response="+mailModel.getRecaptchaResponse();

        RecaptchaModel recaptchaModel = restTemplate.exchange(url+params, HttpMethod.POST, null, RecaptchaModel.class).getBody();

    if(recaptchaModel.isSuccess()){

    SimpleMailMessage message = new SimpleMailMessage();
    SimpleMailMessage message1 = new SimpleMailMessage();

    message1.setFrom("hairbybieszczii@gmail.com");
    message1.setTo(mailModel.getSenderMail());
    message1.setText("Dziękuje za kontakt mail został wysłany ;)");
    message1.setSubject("Hair by Bieszczii - " + mailModel.getSubjectMail());

    mailSender.send(message1);

    message.setFrom("hairbybieszczii@gmail.com");
    message.setTo("hairbybieszczii@gmail.com");
    message.setText(mailModel.getBodyMail() + "\n\n\n\nWysłane z " + mailModel.getSenderMail());
    message.setSubject("App mail - " + mailModel.getSubjectMail());

    mailSender.send(message);



    System.out.println("Done!");
    }else{
        System.out.println("Invalid recaptcha!");
    }



    }
}
