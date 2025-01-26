package com.goodvideo.upload.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EnviarEmailImplTest {

  private static final String ID_VIDEO = "12345";
  private static final String EMAIL_USUARIO = "usuario@example.com";

  @InjectMocks
  private EnviarEmailImpl emailService; // Replace with your class that contains the "enviar" method

  @Test
  public void testEnviarSuccess() {
    emailService.enviar(ID_VIDEO, EMAIL_USUARIO);
  }
  
}
