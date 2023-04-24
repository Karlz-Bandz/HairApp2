package pl.hairbybieszczii.hair_bieszczii.interfaces;

import pl.hairbybieszczii.hair_bieszczii.model.MailModel;

public interface Mailer
{
    public void sendMail(MailModel mailModel);
}
