package com.goodvideo.upload.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.InputStream;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SalvarArquivoImplTest {

   @InjectMocks
   private SalvarArquivoImpl provider;
   
   @Captor
   private ArgumentCaptor<String> path;
   
   @Test
   public void deveSalvarArquivo() {
     final InputStream resourceAsStream = this.getClass().getResourceAsStream("/sample.txt");
     final String idVideo = provider.executar(resourceAsStream, UUID.randomUUID().toString());
     assertNotNull(idVideo);
   }
   
   @Test
   public void deveRetornarErroAoSalvar() {
     final InputStream mockInputStream = Mockito.mock(InputStream.class);
     try {
         Mockito.doThrow(new RuntimeException("Mock exception")).when(mockInputStream).read(Mockito.any(byte[].class));
     } catch (Exception e) {}

     SalvarArquivoException exception = assertThrows(SalvarArquivoException.class, () -> {
         provider.executar(mockInputStream, UUID.randomUUID().toString());
     });

     assertEquals("Mock exception", exception.getMessage(), "The exception message should match");
   }
  
}
