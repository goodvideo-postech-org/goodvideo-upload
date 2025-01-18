package com.goodvideo.upload.usecase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ValidarFormatoTamanhoImpl implements ValidarFormatoTamanho {

  @Value("${upload.maxFileSizeMb:0.001}")
  private long MAX_FILE_SIZE; // 10MB em bytes
  
  private static final String ALLOWED_CONTENT_TYPE = "video/mp4"; // Tipo MIME para MP4
  
  @Override
  public void validate(MultipartFile file) {
    
    if (file.isEmpty()) {
      throw new ValidarArquivoExpcetion("Arquivo vazio.");
    }

    // Verifica o tipo MIME do arquivo
    String contentType = file.getContentType();
    if (contentType == null || !contentType.equals(ALLOWED_CONTENT_TYPE)) {
      throw new ValidarArquivoExpcetion("Somente arquivos MP4 são suportados.");
    }

    if (file.getSize() > getMaxFileSizeInMb()) {
      throw new ValidarArquivoExpcetion(String.format("Tamanho do arquivo excede o permitido: %s Mb.", MAX_FILE_SIZE));
    }

  }

  private long getMaxFileSizeInMb() {
    return MAX_FILE_SIZE * 1024 * 1024;
  }
  
  
  

}
