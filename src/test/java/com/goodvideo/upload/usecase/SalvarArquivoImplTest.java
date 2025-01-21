package com.goodvideo.upload.usecase;

import static org.junit.Assert.assertNotNull;
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

  @Mock
  private MultipartFile mockMultipartFile;


  @Test
  public void deveSalvarArquivo() throws IOException {
    MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain",
        this.getClass().getResourceAsStream("/sample.txt"));
    final String idVideo = provider.executar(firstFile, UUID.randomUUID().toString());
    assertNotNull(idVideo);
  }

  @Test
  void testConvertMultipartFileToFile_Success() throws IOException {
    // Mocking the MultipartFile behavior
    when(mockMultipartFile.getOriginalFilename()).thenReturn("file");
    when(mockMultipartFile.getInputStream())
        .thenReturn(this.getClass().getResourceAsStream("/sample.txt"));

    String idVideo = provider.executar(mockMultipartFile, "file.txt");

    assertNotNull(idVideo);

  }

  @Test
  void testConvertMultipartFileToFile_ThrowsIOException() throws IOException {
    // Mocking the MultipartFile behavior
    when(mockMultipartFile.getOriginalFilename()).thenReturn("teste");
    when(mockMultipartFile.getInputStream()).thenThrow(new IOException("Mocked IOException"));

    // Call the method under test and assert it throws an exception
    assertThrows(RuntimeException.class, () -> {
      provider.executar(mockMultipartFile, "");
    });
  }

}
