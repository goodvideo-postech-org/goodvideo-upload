package com.goodvideo.upload.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class ValidarFormatoTamanhoImplTest {

  @InjectMocks
  private ValidarFormatoTamanhoImpl provider;

  @Test
  void validateEmptyFile() {

    Path path = Paths.get("/src/test/resources/sample_empty.txt");

    byte[] content = null;
    try {
      content = Files.readAllBytes(path);
    } catch (final IOException e) {
    }

    MultipartFile result = new MockMultipartFile("sample", "sample", "txt", content);

    try {
      provider.validate(result);
    } catch (final ValidarArquivoExpcetion e) {
      assertEquals("Arquivo vazio.", e.getMessage());
    }

  }

  @Test
  void validateTipoArquivo() throws IOException {

    File file = new File("src/test/resources/sample.txt");
    FileInputStream input = new FileInputStream(file);
    MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
    
    try {
      provider.validate(multipartFile);
    } catch (final ValidarArquivoExpcetion e) {
      assertEquals("Somente arquivos MP4 são suportados.", e.getMessage());
    }
  }
  
  @Test
  void validateTamanhoArquivo() throws IOException {

    ReflectionTestUtils.setField(provider, "MAX_FILE_SIZE", (long) 0.01);
    
    File file = new File("src/test/resources/sample.txt");
    FileInputStream input = new FileInputStream(file);
    MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "video/mp4", IOUtils.toByteArray(input));
    
    try {
      provider.validate(multipartFile);
    } catch (final ValidarArquivoExpcetion e) {
      assertEquals("Tamanho do arquivo excede o permitido: 0 Mb.", e.getMessage());
    }
  }

}
