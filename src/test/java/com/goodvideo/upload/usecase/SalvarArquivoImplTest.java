package com.goodvideo.upload.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;

@ExtendWith(MockitoExtension.class)
public class SalvarArquivoImplTest {

  @InjectMocks
  private SalvarArquivoImpl provider;

  @Captor
  private ArgumentCaptor<String> path;

  @Mock
  private AmazonS3 amazons3;

  @Mock
  private MultipartFile arquivo;

  @Test
  public void deveSalvarArquivo() throws IOException {
    MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
        this.getClass().getResourceAsStream("/sample.txt"));
    final String idVideo = provider.executar(firstFile, UUID.randomUUID().toString());
    assertNotNull(idVideo);
  }
//
//  @Test
//   void testExecutar_Exception() throws IOException {
//       // Simulando exceção no método convertMultipartFileToFile
//      MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
//        this.getClass().getResourceAsStream("/sample.txt"));
//
//       Exception exception = assertThrows(SalvarArquivoException.class, () -> {
//         when(provider.executar(null, UUID.randomUUID().toString())).thenThrow(new IOException("Falha ao converter arquivo"));
//       });
//       
//       assertEquals("Falha ao converter arquivo", exception.getMessage());
//   }

}
