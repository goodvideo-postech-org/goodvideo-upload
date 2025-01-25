package com.goodvideo.upload.usecase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Component
public class EnviarEmailImpl implements EnviarEmail {

  @Value("${upload.email}")
  private String email;

  @Override
  public void enviar(String idVideo, String emailUsuario) {
    final String subject = "Falha ao processar imagens";
    final String bodyText = "Olá!\n\nHouve uma falha ao obter as imagens do vídeo";

    AmazonSimpleEmailService sesClient = AmazonSimpleEmailServiceClientBuilder.standard().build();

    Content subjectContent = new Content().withData(subject);
    Content bodyContent = new Content().withData(String.format("%s. IdProcessamento: %s", bodyText, idVideo));
    Body body = new Body().withText(bodyContent);

    Message message = new Message().withSubject(subjectContent).withBody(body);

    SendEmailRequest request = new SendEmailRequest().withSource(email)
        .withDestination(new Destination().withToAddresses(emailUsuario)).withMessage(message);

    sesClient.sendEmail(request);
  }

}
