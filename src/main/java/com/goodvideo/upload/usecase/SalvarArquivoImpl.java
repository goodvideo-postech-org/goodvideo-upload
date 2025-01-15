package com.goodvideo.upload.usecase;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalvarArquivoImpl implements SalvarArquivo {

  @Value("${upload.default-path}")
  private String defaultPath;
  
  @Override
  public String executar(final InputStream arquivo, final String idUsuario) {
    try {
      final String idVideo = UUID.randomUUID().toString();
      FileUtils.copyInputStreamToFile(arquivo, new File(String.format("%s/%s/%s.mp4", defaultPath, idUsuario, idVideo)));
      return idVideo;
    } catch (Exception e) {
      throw new SalvarArquivoException(e.getMessage());
    }
  }

}
